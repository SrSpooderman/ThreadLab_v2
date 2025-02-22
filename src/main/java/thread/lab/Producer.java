package thread.lab;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
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
            status = "Esperando para producir";

            int cycles = model.getController().getLabParameter().getProducerCycles();
            boolean isCyclesActive = model.getController().getLabParameter().isCyclesActive();

            status = "Produciendo";


            while (!isCyclesActive || cycles > 0) {
                //Cerrado de hilo y pausa de hilo
                if (this.model.getController().getLabParameter().isStopRequest()) {
                    return;
                }

                while (!model.getController().getLabParameter().isRunning()) {
                    Thread.sleep(10);
                    if (this.model.getController().getLabParameter().isStopRequest()) {
                        return;
                    }
                }

                product.increaseQuantity();
                production++;

                if (isCyclesActive) {
                    cycles--;
                }

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
