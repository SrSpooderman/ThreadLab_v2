package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabParameter;
import thread.lab.dto.DTOLabResult;

@Getter
@Setter
public class TJTLController {
    private TJTLViewer viewer;
    private TJTLModel model;

    private DTOLabParameter labParameter;
    private DTOLabResult labResult;

    public TJTLController(){
        this.labParameter = new DTOLabParameter();
        System.out.println("parametros de laboratorio creados");
        this.labResult = new DTOLabResult();
        System.out.println("resultados de laboratorio creados");

        this.viewer = new TJTLViewer(this);
        System.out.println("viewer creado");
        this.model = new TJTLModel(this);
        System.out.println("modelo creado");
    }

    public void startModel(){
        this.model.start();
    }

    public void startViewer(){
        System.out.println("iniciando hilo");
        Thread viewerThread = new Thread(viewer);
        viewerThread.start();
        System.out.println("hilo iniciado");
    }

    public void resetDTO(){
        this.labParameter.resetData();
        this.labResult.resetData();
    }

    public void updateLabParameterDTO(){
        this.labParameter.setSynchronized(this.viewer.getLabParameterPanel().getIsSynchronized().isSelected());
        this.labParameter.setPreventingNegativeStock(this.viewer.getLabParameterPanel().getIsPreventingNegativeStock().isSelected());

        this.labParameter.setTotalResources((Integer) this.viewer.getLabParameterPanel().getTotalResources().getValue());
        this.labParameter.setMaxGeneralResources((Integer) this.viewer.getLabParameterPanel().getMaxGeneralResources().getValue());
        this.labParameter.setMinGeneralResources((Integer) this.viewer.getLabParameterPanel().getMinGeneralResources().getValue());

        this.labParameter.setNumberProducers((Integer)this.viewer.getLabParameterPanel().getNumberProducers ().getValue());
        this.labParameter.setProducerItemQuantity((Integer)this.viewer.getLabParameterPanel().getProducerItemQuantity ().getValue());
        this.labParameter.setNumberConsumers((Integer)this.viewer.getLabParameterPanel().getNumberConsumers ().getValue());
        this.labParameter.setConsumerItemQuantity((Integer)this.viewer.getLabParameterPanel().getConsumerItemQuantity ().getValue());

        this.labParameter.setStartDelayMin ((Integer)this.viewer.getLabParameterPanel().getStartDelayMin().getValue());
        this.labParameter.setStartDelayMax ((Integer)this.viewer.getLabParameterPanel().getStartDelayMax().getValue());

        this.labParameter.setEnableProducerMaxTime(this.viewer.getLabParameterPanel().getIsEnableProducerMaxTime().isSelected());
        this.labParameter.setProducerDelayMin((Integer)this.viewer.getLabParameterPanel().getProducerDelayMin().getValue());
        this.labParameter.setProducerDelayMax((Integer)this.viewer.getLabParameterPanel().getProducerDelayMax().getValue());

        this.labParameter.setEnableConsumerMaxTime(this.viewer.getLabParameterPanel().getIsEnableConsumerMaxTime().isSelected());
        this.labParameter.setConsumerDelayMin((Integer)this.viewer.getLabParameterPanel().getConsumerDelayMin().getValue());
        this.labParameter.setConsumerDelayMax((Integer)this.viewer.getLabParameterPanel().getConsumerDelayMax().getValue());

        System.out.println(this.labParameter.toString());
    }
}
