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
        this.quantity = this.model.getController().getLabParameter().getTotalResources();
        this.isSynchronized = this.model.getController().getLabParameter().isSynchronized();
        this.isPreventingNegativeStock = this.model.getController().getLabParameter().isPreventingNegativeStock();
    }

    public void increaseQuantity() {
        if (isSynchronized) {
            synchronized (this) {
                quantity++;
                this.model.getController().getLabResult().setProductQuantity(quantity);
                notify();
            }
        } else {
            quantity++;
            this.model.getController().getLabResult().setProductQuantity(quantity);
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
                this.model.getController().getLabResult().setProductQuantity(quantity);
            }
        } else {
            if (isPreventingNegativeStock && quantity <= 0) {
                return;
            }
            quantity--;
            this.model.getController().getLabResult().setProductQuantity(quantity);
        }
    }
}
