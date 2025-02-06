package thread.lab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOLabResult {
    private Integer productQuantity;

    private Integer producerQuantity;
    private Integer consumerQuantity;

    private Integer processingProducerQuantity;
    private Integer processingConsumerQuantity;
    private Integer totalTimer;


    public DTOLabResult(){
        resetData();
    }

    public void resetData(){
        this.productQuantity = 0;

        this.consumerQuantity = 0;
        this.producerQuantity = 0;

        this.processingProducerQuantity = 0;
        this.processingConsumerQuantity = 0;

        this.totalTimer = 0;
    }
    public synchronized void incrementProducerQuantity() {
        producerQuantity++;
    }

    public synchronized void incrementConsumerQuantity() {
        consumerQuantity++;
    }

    public synchronized void decrementProducerQuantity() {
        producerQuantity--;
    }

    public synchronized void decrementConsumerQuantity() {
        consumerQuantity--;
    }

    public synchronized void incrementProcessingProducerQuantity() {
        processingProducerQuantity++;
    }

    public synchronized void incrementProcessingConsumerQuantity() {
        processingConsumerQuantity++;
    }

    public synchronized void decrementProcessingProducerQuantity() {
        processingProducerQuantity--;
    }

    public synchronized void decrementProcessingConsumerQuantity() {
        processingConsumerQuantity--;
    }

    @Override
    public String toString() {
        return "DTOLabResult{" +"\n"+
                "productQuantity=" + productQuantity + "\n"+
                ", producerQuantity=" + producerQuantity +"\n"+
                ", consumerQuantity=" + consumerQuantity +"\n"+
                ", processingProducerQuantity=" + processingProducerQuantity +"\n"+
                ", processingConsumerQuantity=" + processingConsumerQuantity +"\n"+
                ", totalTimer=" + totalTimer +"\n"+
                '}';
    }
}
