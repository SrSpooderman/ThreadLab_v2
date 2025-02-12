package thread.lab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOLabParameter {
    private Integer numberProducts;
    private Integer productMaxQuantity;
    private Integer productMinQuantity;

    private Integer numberProducers;
    private Integer numberConsumers;

    private Integer startDelayMax;
    private Integer startDelayMin;

    private Integer produceMaxTime;
    private Integer produceMinTime;

    private Integer consumeMaxTime;
    private Integer consumeMinTime;

    private Integer producerItemQuantity;
    private Integer consumerItemQuantity;

    private boolean isSynchronized;
    private boolean isPreventingNegativeStock;

    private boolean isRunning;
    private boolean stopRequest;

    public DTOLabParameter(){
        resetData();
    }

    public void resetData(){
        this.numberProducts = 1;
        this.productMaxQuantity = 1;
        this.productMinQuantity = 1;

        this.numberProducers = 1;
        this.numberConsumers = 1;

        this.startDelayMax = 1;
        this.startDelayMin = 1;

        this.produceMaxTime = 1;
        this.produceMinTime = 1;

        this.consumeMaxTime = 1;
        this.consumeMinTime = 1;

        this.producerItemQuantity = 1;
        this.consumerItemQuantity = 1;

        this.isSynchronized = false;
        this.isPreventingNegativeStock = false;

        this.isRunning = true;
        this.stopRequest = false;
    }

    @Override
    public String toString() {
        return "DTOLabParameter{" +
                "numberProducts=" + numberProducts +
                ", productMaxQuantity=" + productMaxQuantity +
                ", productMinQuantity=" + productMinQuantity +
                ", numberProducers=" + numberProducers +
                ", numberConsumers=" + numberConsumers +
                ", startDelayMax=" + startDelayMax +
                ", startDelayMin=" + startDelayMin +
                ", produceMaxTime=" + produceMaxTime +
                ", produceMinTime=" + produceMinTime +
                ", consumeMaxTime=" + consumeMaxTime +
                ", consumeMinTime=" + consumeMinTime +
                ", producerItemQuantity=" + producerItemQuantity +
                ", consumerItemQuantity=" + consumerItemQuantity +
                ", isSynchronized=" + isSynchronized +
                ", isPreventingNegativeStock=" + isPreventingNegativeStock +
                ", isRunning=" + isRunning +
                ", stopRequest=" + stopRequest +
                '}';
    }
}
