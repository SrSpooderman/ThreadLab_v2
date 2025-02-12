package thread.lab;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Producer implements Runnable {
    private TJTLModel model;
    private  Product product;
    private String producerID;
    private String resourceBound;
    private Integer produceDelay;
    private String status;
    private Integer production;
    private long processingTime;
    private long startTime;
    private long endTime;

    public Producer(TJTLModel model, String producerID, Product product) {
        this.model = model;
        this.product = product;
        this.producerID = producerID;
        this.resourceBound = product.getProductID();
        this.production = 0;
    }

    @Override
    public void run() {
        try {
            this.startTime = System.currentTimeMillis();
            status = "Esperando para iniciar";

            int itemQuantity = model.getController().getLabParameter().getProducerItemQuantity();

            status = "Produciendo";
            for (int i = 0; i < itemQuantity; i++) {
                //Cerrado de hilo y pausa de hilo
                if (this.model.getController().getLabParameter().isStopRequest()){
                    return;
                }
                while (!model.getController().getLabParameter().isRunning()) {
                    Thread.sleep(100);
                    if (this.model.getController().getLabParameter().isStopRequest()){
                        return;
                    }
                }
                product.increaseQuantity();
                production++;
                Thread.sleep(randomProducerDelay());
            }
            status = "Finalizado";
            this.model.increaseFinalizedProducerQuantity();
            endTime = System.currentTimeMillis();
            processingTime = endTime - startTime;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            status = "Interrumpido";
        }
    }

    private Integer randomProducerDelay(){
        Integer min = this.model.getController().getLabParameter().getProduceMinTime();
        Integer max = this.model.getController().getLabParameter().getProduceMaxTime();
        if (min > max){
            System.out.println("El valor minimo en mayor al maximo");
        }
        Random random = new Random();
        Integer result = random.nextInt(max - min + 1) + min;
        this.produceDelay = result;
        return result;
    }
}
