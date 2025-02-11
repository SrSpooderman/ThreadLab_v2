package thread.lab;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Consumer implements Runnable {
    private TJTLModel model;
    private String consumerID;
    private String resourceBound;
    private Integer consumeDelay;
    private String status;
    private Integer consumption;
    private long processingTime;
    private long startTime;
    private long endTime;

    public Consumer(TJTLModel model, String consumerID, String resourceBound) {
        this.model = model;
        this.consumerID = consumerID;
        this.resourceBound = resourceBound;
        this.consumption = 0;
    }

    @Override
    public void run() {
        try {
            this.startTime = System.currentTimeMillis();
            status = "Esperando para consumir";

            int itemQuantity = model.getController().getLabParameter().getConsumerItemQuantity();

            status = "Consumiendo";
            for (int i = 0; i < itemQuantity; i++) {
                //Cerrado y pausado de hilo
                if (this.model.getController().getLabParameter().isStopRequest()){
                    return;
                }
                while (!model.getController().getLabParameter().isRunning()) {
                    Thread.sleep(100);
                    if (this.model.getController().getLabParameter().isStopRequest()){
                        return;
                    }
                }
                this.model.getProduct().decreaseQuantity();
                consumption++;
                Thread.sleep(randomConsumerDelay());
            }
            status = "Finalizado";
            this.model.increaseFinalizedConsumerQuantity();
            endTime = System.currentTimeMillis();
            processingTime = endTime - startTime;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            status = "Interrumpido";
        }
    }

    private Integer randomConsumerDelay(){
        Integer min = this.model.getController().getLabParameter().getConsumeMinTime();
        Integer max = this.model.getController().getLabParameter().getConsumeMaxTime();
        if (min > max){
            System.out.println("El valor minimo en mayor al maximo");
        }
        Random random = new Random();
        Integer result = random.nextInt(max - min + 1) + min;
        this.consumeDelay = result;
        return result;
    }
}
