package thread.lab;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Product {
    private TJTLModel model;
    private String productID;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer minQuantity;
    private Integer quantityConsumed;
    private Integer quantityProduced;
    private Integer underflow;
    private Integer overflow;
    private String state;

    private boolean isSynchronized;
    private boolean isPreventingNegativeStock;

    public Product(TJTLModel model, String productID) {
        this.model = model;
        this.productID = productID;
        quantityRandomGenerator();
        this.maxQuantity = this.model.getController().getLabParameter().getProductMaxQuantity();
        this.minQuantity = this.model.getController().getLabParameter().getProductMinQuantity();
        this.quantityConsumed = 0;
        this.quantityProduced = 0;
        this.underflow = 0;
        this.overflow = 0;
        this.state = "Disponible";

        this.isSynchronized = this.model.getController().getLabParameter().isSynchronized();
        this.isPreventingNegativeStock = this.model.getController().getLabParameter().isPreventingNegativeStock();
    }

    private void quantityRandomGenerator() {
        Integer min = this.model.getController().getLabParameter().getProductMinQuantity();
        Integer max = this.model.getController().getLabParameter().getProductMaxQuantity();
        if (min > max) {
            System.out.println("El valor mínimo es mayor que el máximo.");
        }
        Random random = new Random();
        this.quantity = random.nextInt(max - min + 1) + min;
    }

    public void increaseQuantity() {
        if (isSynchronized) {
            synchronized (this) {
                state = "En proceso";
                quantity++;
                checkOverflow();
                quantityProduced++;
                updateState();
                notify();
            }
        } else {
            state = "En proceso";
            quantity++;
            checkOverflow();
            quantityProduced++;
            updateState();
        }
    }

    /*public void decreaseQuantity() {
        Integer minProduct = this.model.getController().getLabParameter().getProductMinQuantity();
        if (isSynchronized) {
            synchronized (this) {
                state = "En proceso";
                while (isPreventingNegativeStock && quantity <= minProduct) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                quantity--;
                checkUnderflow();
                quantityConsumed++;
                updateState();
            }
        } if (!isSynchronized){
            state = "En proceso";
            if (isPreventingNegativeStock && quantity <= minProduct) {
                return;
            }

            int temp = quantity;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            quantity = temp-1;

            checkUnderflow();
            quantityConsumed++;
            updateState();
        }
    }*/

    public void decreaseQuantity() {
        if (isSynchronized) {
            if (isPreventingNegativeStock) {
                decreaseQuantitySynchronizedWithProtection();
            } else {
                decreaseQuantitySynchronized();
            }
        } else {
            if (isPreventingNegativeStock) {
                decreaseQuantityUnsynchronizedWithProtection();
            } else {
                decreaseQuantityUnsynchronized();
            }
        }
    }

    private synchronized void decreaseQuantitySynchronizedWithProtection() {
        Integer minProduct = this.model.getController().getLabParameter().getProductMinQuantity();
        state = "En proceso";

        while (quantity <= minProduct) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        quantity--;
        quantityConsumed++;
        checkUnderflow();
        updateState();
    }
    private synchronized void decreaseQuantitySynchronized() {
        state = "En proceso";
        quantity--;
        quantityConsumed++;
        checkUnderflow();
        updateState();
    }
    private void decreaseQuantityUnsynchronizedWithProtection() {
        Integer minProduct = this.model.getController().getLabParameter().getProductMinQuantity();
        state = "En proceso";

        System.out.println("NO SYNC SI 0 :"+quantity);

        while (quantity <= minProduct) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        checkUnderflow();
        quantity--;
        checkUnderflow();
        quantityConsumed++;
        updateState();
    }
    private void decreaseQuantityUnsynchronized() {
        state = "En proceso";
        quantity--;
        quantityConsumed++;
        checkUnderflow();
        updateState();
    }

    private void checkOverflow() {
        if (quantity > maxQuantity) {
            overflow += (quantity - maxQuantity);
            state = "Overflow";
        }
    }

    private void checkUnderflow() {
        if (quantity < minQuantity) {
            System.out.println(quantity + "------------------------------------");
            underflow += (minQuantity - quantity);
            state = "Underflow";
        }
    }

    private void updateState() {
        if (quantity > maxQuantity) {
            state = "Overflow";
        } else if (quantity < minQuantity) {
            state = "Underflow";
        } else if (quantity > 0) {
            state = "Disponible";
        } else if (!isPreventingNegativeStock && quantity == 0) {
            state = "Finalizado";
        }
    }
}