package thread.lab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOLabParameter {
    private boolean isSynchronized;
    private boolean isPreventingNegativeStock;

    private Integer numberProducers;
    private Integer producerItemQuantity;


    private Integer numberConsumers;
    private Integer consumerItemQuantity;

    private boolean isEnableProducerMaxTime;
    private Integer producerDelayMax;

    private boolean isEnableConsumerMaxTime;
    private Integer consumerDelayMax;

    private boolean isRunning;

    public DTOLabParameter(){
        resetData();
    }

    public void resetData(){
        this.isSynchronized = false;
        this.isPreventingNegativeStock = false;

        this.numberProducers = 0;
        this.producerItemQuantity = 0;

        this.numberConsumers = 0;
        this.consumerItemQuantity = 0;

        this.isEnableProducerMaxTime = false;
        this.producerDelayMax = 0;

        this.isEnableConsumerMaxTime = false;
        this.consumerDelayMax = 0;

        this.isRunning = true;
    }

    @Override
    public String toString() {
        return "DTOLabParameter{" +
                "isSynchronized=" + isSynchronized +
                ", isPreventingNegativeStock=" + isPreventingNegativeStock +
                ", numberProducers=" + numberProducers +
                ", producerItemQuantity=" + producerItemQuantity +
                ", numberConsumers=" + numberConsumers +
                ", consumerItemQuantity=" + consumerItemQuantity +
                ", isEnableProducerMaxTime=" + isEnableProducerMaxTime +
                ", producerDelayMax=" + producerDelayMax +
                ", isEnableConsumerMaxTime=" + isEnableConsumerMaxTime +
                ", consumerDelayMax=" + consumerDelayMax +
                ", isRunning=" + isRunning +
                '}';
    }
}
