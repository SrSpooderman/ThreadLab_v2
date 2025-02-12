package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class TJTLModel {
    private TJTLController controller;

    //Hilos consumidores y productores
    private Thread producerThreadGenerator;
    private Thread consumerThreadGenerator;
    private List<Producer> producers;
    private List<Consumer> consumers;

    private List<Product> products;

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

        createProducts();
        producerThreadGenerator.start();
        consumerThreadGenerator.start();
    }

    public void resetVariables(){
        this.finalizedConsumerQuantity = 0;
        this.finalizedProducerQuantity = 0;

        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void createProducts(){
        int numberProducts = this.controller.getLabParameter().getNumberProducts();
        for (int i=0; i < numberProducts; i++){
            Product product = new Product(this, "Product-"+i);
            this.products.add(product);
        }
    }
    public void runProducers(){
        int numberProducers = this.controller.getLabParameter().getNumberProducers();
        for (int i=0; i < numberProducers; i++){
            Product product = this.products.get(new Random().nextInt(products.size()));
            //Cerrado de hilo
            if (this.getController().getLabParameter().isStopRequest()) {
                return;
            }
            Producer producer = new Producer(this, "Producer-"+i, product);
            producers.add(producer);

            Thread producerThread = new Thread(producer);
            producerThread.start();
            try{
                Thread.sleep(randomStartDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    public void runConsumers(){
        int numberConsumers = this.controller.getLabParameter().getNumberConsumers();
        for(int i=0; i < numberConsumers; i++){
            Product product = this.products.get(new Random().nextInt(products.size()));
            //Cerrado de hilo
            if (this.getController().getLabParameter().isStopRequest()) {
                return;
            }

            Consumer consumer = new Consumer(this, "Consumer-"+i, product);
            consumers.add(consumer);

            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            try{
                Thread.sleep(randomStartDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }

    private Integer randomStartDelay(){
        Integer min = this.controller.getLabParameter().getStartDelayMin();
        Integer max = this.controller.getLabParameter().getStartDelayMax();
        if (min > max){
            System.out.println("El valor minimo en mayor al maximo");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public Integer getTotalProducts(){
        Integer totalProducts = 0;
        for (Product product : this.products){
            totalProducts += product.getQuantity();
        }

        return totalProducts;
    }

    public void increaseFinalizedConsumerQuantity(){
        this.finalizedConsumerQuantity++;
    }
    public void increaseFinalizedProducerQuantity(){
        this.finalizedProducerQuantity++;
    }
}
