package thread.lab;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private TJTLModel model;
    private int quantity;
    private boolean isSynchronized;
    private boolean isPreventingNegativeStock;

    public Product(TJTLModel model){
        this.model = model;
        this.quantity = 0;
        this.isSynchronized = this.model.getController().getLabParameter().isSynchronized();
        this.isPreventingNegativeStock = this.model.getController().getLabParameter().isPreventingNegativeStock();
    }

    public void increaseQuantity() {
        if (isSynchronized) {
            synchronized (this) {
                quantity++;
                notify();
            }
        } else {
            quantity++;
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
            }
        } else {
            if (isPreventingNegativeStock && quantity <= 0) {
                return;
            }
            quantity--;
        }
    }
}
