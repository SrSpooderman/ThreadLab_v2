package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabParameter;

@Getter
@Setter
public class TJTLController {
    private TJTLViewer viewer;
    private TJTLModel model;

    private DTOLabParameter labParameter;

    public TJTLController(){
        this.labParameter = new DTOLabParameter();
        System.out.println("parametros de laboratorio creados");

        this.viewer = new TJTLViewer(this);
        System.out.println("viewer creado");
        this.model = new TJTLModel(this);
        System.out.println("modelo creado");
    }

    public void startModel(){
        this.model.start();
        System.out.println("Modelo iniciado");
    }

    public void startViewer(){
        System.out.println("iniciando hilo");
        Thread viewerThread = new Thread(viewer);
        viewerThread.start();
        System.out.println("hilo iniciado");
    }

    public void resetDTO(){
        this.labParameter.resetData();
    }

    public void updateLabParameterDTO(){
        // Resource Settings
        this.labParameter.setProductMaxQuantity((Integer) this.viewer.getLabParameterPanel().getProductMaxQuantity().getValue());
        this.labParameter.setProductMinQuantity((Integer) this.viewer.getLabParameterPanel().getProductMinQuantity().getValue());

        // Producer/Consumer Count
        this.labParameter.setNumberProducers((Integer) this.viewer.getLabParameterPanel().getNumberProducers().getValue());
        this.labParameter.setNumberConsumers((Integer) this.viewer.getLabParameterPanel().getNumberConsumers().getValue());

        // Start Delay Settings
        this.labParameter.setStartDelayMax((Integer) this.viewer.getLabParameterPanel().getStartDelayMax().getValue());
        this.labParameter.setStartDelayMin((Integer) this.viewer.getLabParameterPanel().getStartDelayMin().getValue());

        // Producer Timing
        this.labParameter.setProduceMaxTime((Integer) this.viewer.getLabParameterPanel().getProduceMaxTime().getValue());
        this.labParameter.setProduceMinTime((Integer) this.viewer.getLabParameterPanel().getProduceMinTime().getValue());

        // Consumer Timing
        this.labParameter.setConsumeMaxTime((Integer) this.viewer.getLabParameterPanel().getConsumeMaxTime().getValue());
        this.labParameter.setConsumeMinTime((Integer) this.viewer.getLabParameterPanel().getConsumeMinTime().getValue());

        // Lifecycle
        this.labParameter.setProducerItemQuantity((Integer) this.viewer.getLabParameterPanel().getProducerCycles().getValue());
        this.labParameter.setConsumerItemQuantity((Integer) this.viewer.getLabParameterPanel().getConsumerCycles().getValue());

        // Operation Settings
        this.labParameter.setSynchronized(this.viewer.getLabParameterPanel().getIsSynchronized().isSelected());
        this.labParameter.setPreventingNegativeStock(this.viewer.getLabParameterPanel().getIsPreventingNegativeStock().isSelected());

        System.out.println(this.labParameter.toString());
    }

    public void stopAllThreads(){
        this.labParameter.setStopRequest(true);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {throw new RuntimeException(e);}
    }
}
