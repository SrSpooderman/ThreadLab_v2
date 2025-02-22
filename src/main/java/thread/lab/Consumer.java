package thread.lab;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Random;

@Getter
@Setter
public class Consumer implements Runnable {
    private TJTLModel model;
    private Product product;
    private String consumerID;
    private String resourceBound;
    private Integer consumeDelay;
    private String status;
    private Integer consumption;
    private long processingTime;
    private long startTime;
    private long endTime;

    public Consumer(TJTLModel model, String consumerID, Product product) {
        this.model = model;
        this.product = product;
        this.consumerID = consumerID;
        this.resourceBound = product.getProductID();
        this.consumption = 0;
    }

    @Override
    public void run() {
        try {
            this.startTime = System.currentTimeMillis();
            status = "Esperando para consumir";

            int cycles = model.getController().getLabParameter().getConsumerCycles();
            boolean isCyclesActive = model.getController().getLabParameter().isCyclesActive();

            status = "Consumiendo";

            while (!isCyclesActive || cycles > 0) {
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

                product.decreaseQuantity();
                consumption++;

                if (isCyclesActive){
                    cycles--;
                }

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

    public String getStartTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        return formatter.format(this.startTime);
    }
    public String getEndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        if (endTime == 0){
            return "No ha finalizado";
        }
        return formatter.format(this.endTime);
    }
    public String getProcessingTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        if (processingTime == 0){
            return "No ha finalizado";
        }
        return formatter.format(this.processingTime);
    }
}
