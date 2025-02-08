package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabParameter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TJTLModel {
    private TJTLController controller;
    private Product product;

    //Hilos consumidores y productores
    private Thread producerThreadGenerator;
    private Thread consumerThreadGenerator;
    private List<Producer> producers;
    private List<Consumer> consumers;

    private Integer finalizedConsumerQuantity;
    private Integer finalizedProducerQuantity;

    public TJTLModel(TJTLController controller){
        this.controller = controller;
        resetVariables();
    }

    public void start(){
        resetVariables();
        this.producerThreadGenerator = new Thread(this::runProducers);
        this.consumerThreadGenerator = new Thread(this::runConsumers);

        producerThreadGenerator.start();
        consumerThreadGenerator.start();
    }

    public void resetVariables(){
        this.finalizedConsumerQuantity = 0;
        this.finalizedProducerQuantity = 0;

        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();

        this.product = new Product(this, "Producto principal");
    }

    public void runProducers(){
        int numberProducers = this.controller.getLabParameter().getNumberProducers();
        for (int i=0; i < numberProducers; i++){
            //Cerrado de hilo
            if (this.getController().getLabParameter().isStopRequest()) {
                return;
            }
            Producer producer = new Producer(this, "Producer-"+i);
            producers.add(producer);

            Thread producerThread = new Thread(producer);
            producerThread.start();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    public void runConsumers(){
        int numberConsumers = this.controller.getLabParameter().getNumberConsumers();
        for(int i=0; i < numberConsumers; i++){
            //Cerrado de hilo
            if (this.getController().getLabParameter().isStopRequest()) {
                return;
            }

            Consumer consumer = new Consumer(this, "Consumer-"+i);
            consumers.add(consumer);

            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }

    public void increaseFinalizedConsumerQuantity(){
        this.finalizedConsumerQuantity++;
    }
    public void increaseFinalizedProducerQuantity(){
        this.finalizedProducerQuantity++;
    }
}
