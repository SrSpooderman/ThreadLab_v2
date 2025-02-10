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
    private boolean isSynchronized;
    private boolean isPreventingNegativeStock;

    private Integer quantityConsumed;
    private Integer quantityProduced;

    public Product(TJTLModel model, String productID){
        this.model = model;
        this.productID = productID;
        quantityRandomGenerator();
        this.isSynchronized = this.model.getController().getLabParameter().isSynchronized();
        this.isPreventingNegativeStock = this.model.getController().getLabParameter().isPreventingNegativeStock();

        this.quantityConsumed = 0;
        this.quantityProduced = 0;
    }

    private void quantityRandomGenerator(){
        Integer min = this.model.getController().getLabParameter().getProductMinQuantity();
        Integer max = this.model.getController().getLabParameter().getProductMaxQuantity();
        if (min > max){
            System.out.println("El valor minimo en mayor al maximo");
        }
        Random random = new Random();
        this.quantity = random.nextInt(max - min + 1) + min;
    }

    public void increaseQuantity() {
        if (isSynchronized) {
            synchronized (this) {
                quantity++;
                quantityProduced++;
                notify();
            }
        } else {
            quantity++;
            quantityProduced++;
        }
    }

    public void decreaseQuantity() {
        if (isSynchronized) {
            synchronized (this) {
                while (isPreventingNegativeStock && quantity <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                quantity--;
                quantityConsumed++;
            }
        } else {
            if (isPreventingNegativeStock && quantity <= 0) {
                return;
            }
            quantity--;
            quantityConsumed++;
        }
    }
}
