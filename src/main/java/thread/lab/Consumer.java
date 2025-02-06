package thread.lab;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consumer implements Runnable {
    private TJTLModel model;
    private String consumerID;
    private String status;
    private Integer consumption;
    private long processingTime;
    private long startTime;
    private long endTime;

    public Consumer(TJTLModel model, String consumerID) {
        this.model = model;
        this.consumerID = consumerID;
        this.consumption = 0;
    }

    @Override
    public void run() {
        this.model.getController().getLabResult().incrementConsumerQuantity();
        try {
            this.startTime = System.currentTimeMillis();
            status = "Esperando para consumir";

            int itemQuantity = model.getController().getLabParameter().getConsumerItemQuantity();
            boolean enableConsumerMaxTime = model.getController().getLabParameter().isEnableConsumerMaxTime();
            int consumerDelayMax = model.getController().getLabParameter().getConsumerDelayMax();

            status = "Consumiendo";
            for (int i = 0; i < itemQuantity; i++) {
                while (!model.getController().getLabParameter().isRunning()) {
                    if (enableConsumerMaxTime && (System.currentTimeMillis() - startTime >= consumerDelayMax)) {
                        status = "Finalizado (Tiempo excedido)";
                        endTime = System.currentTimeMillis();
                        processingTime = endTime - startTime;
                        return;
                    }
                    Thread.sleep(100);
                }

                this.model.getProduct().decreaseQuantity();
                consumption++;
            }

            status = "Finalizado";
            this.model.getController().getLabResult().decrementConsumerQuantity();
            endTime = System.currentTimeMillis();
            processingTime = endTime - startTime;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            status = "Interrumpido";
            this.model.getController().getLabResult().decrementConsumerQuantity();
        }
    }
}
