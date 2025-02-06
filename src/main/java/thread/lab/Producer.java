package thread.lab;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producer implements Runnable {
    private TJTLModel model;
    private String producerID;
    private String status;
    private Integer production;
    private long processingTime;
    private long startTime;
    private long endTime;

    public Producer(TJTLModel model, String producerID) {
        this.model = model;
        this.producerID = producerID;
        this.production = 0;
    }

    @Override
    public void run() {
        this.model.getController().getLabResult().incrementProducerQuantity();
        try {
            this.startTime = System.currentTimeMillis();
            status = "Esperando para iniciar";

            int itemQuantity = model.getController().getLabParameter().getProducerItemQuantity();
            boolean enableProducerMaxTime = model.getController().getLabParameter().isEnableProducerMaxTime();
            int producerDelayMax = model.getController().getLabParameter().getProducerDelayMax();

            status = "Produciendo";
            for (int i = 0; i < itemQuantity; i++) {
                while (!model.getController().getLabParameter().isRunning()) {
                    if (enableProducerMaxTime && (System.currentTimeMillis() - startTime >= producerDelayMax)) {
                        status = "Finalizado (Tiempo excedido)";
                        endTime = System.currentTimeMillis();
                        processingTime = endTime - startTime;
                        return;
                    }
                    Thread.sleep(100);
                }

                model.getProduct().increaseQuantity();
                production++;
            }

            status = "Finalizado";
            this.model.getController().getLabResult().decrementProducerQuantity();
            endTime = System.currentTimeMillis();
            processingTime = endTime - startTime;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            status = "Interrumpido";
            this.model.getController().getLabResult().decrementProducerQuantity();
        }
    }
}
