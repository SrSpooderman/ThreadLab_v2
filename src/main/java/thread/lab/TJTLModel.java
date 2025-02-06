package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabParameter;
import thread.lab.dto.DTOLabResult;

import java.security.cert.CertPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class TJTLModel {
    private TJTLController controller;
    private Product product;

    //Hilos consumidores y productores
    Thread producerThreadGenerator;
    Thread consumerThreadGenerator;
    List<Producer> producers;
    List<Consumer> consumers;

    public TJTLModel(TJTLController controller){
        this.controller = controller;

        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public void start(){
        this.product = new Product(this);
        this.producerThreadGenerator = new Thread(this::runProducers);
        this.consumerThreadGenerator = new Thread(this::runConsumers);

        producerThreadGenerator.start();
        consumerThreadGenerator.start();
    }
    public void runProducers(){
        DTOLabParameter labParameter = controller.getLabParameter();
        int numberProducers = labParameter.getNumberProducers();
        for (int i=0; i < numberProducers; i++){
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
        DTOLabParameter labParameter = controller.getLabParameter();
        int numberConsumers = labParameter.getNumberConsumers();
        for(int i=0; i < numberConsumers; i++){
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
}
