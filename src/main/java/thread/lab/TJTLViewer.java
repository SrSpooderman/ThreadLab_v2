package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.viewerPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TJTLViewer extends JFrame implements Runnable, ActionListener {
    private TJTLController controller;
    private LabResultsPanel labResultsPanel;
    private ControlPanel controlPanel;
    private LabParameterPanel labParameterPanel;
    private ProductPanel productPanel;
    private ProducersPanel producersPanel;
    private ConsumersPanel consumersPanel;

    public TJTLViewer (TJTLController controller){
        this.controller = controller;
        this.labResultsPanel = new LabResultsPanel();
        this.controlPanel = new ControlPanel();
        this.labParameterPanel = new LabParameterPanel();
        this.productPanel = new ProductPanel();
        this.consumersPanel = new ConsumersPanel();
        this.producersPanel = new ProducersPanel();

        configureJFrame();
    }

    private void configureJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Thread Lab");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        addComponentsToPane(this.getContentPane());
        confButtonFunc();
    }

    private void addComponentsToPane(Container panel){
        panel.setBackground(new Color(44,47,51));
        GridBagConstraints constraints = new GridBagConstraints();

        //Creo un panel por columna
        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new GridBagLayout());

        addComponentToColumn(leftPanel,0,0, 1F, 0.7F,labParameterPanel);
        addComponentToColumn(leftPanel,0,1, 1F, 0.3F,controlPanel);

        addComponentToColumn(centerPanel,0,0, 1F, 1F,labResultsPanel);

        addComponentToColumn(rightPanel,0,0, 1F, 0.3F,productPanel);
        addComponentToColumn(rightPanel,0,1,1F,0.3F,consumersPanel);
        addComponentToColumn(rightPanel,0,2,1F,0.3F,producersPanel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(leftPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(centerPanel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(rightPanel, constraints);
    }

    private void addComponentToColumn(JPanel column, int gridX, int gridY, float weightX, float weightY, Component component){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 5, 5, 5);

        column.add(component, constraints);
    }

    private void confButtonFunc(){
        this.controlPanel.getStart().addActionListener(this);
        this.controlPanel.getReset().addActionListener(this);
        this.controlPanel.getPause().addActionListener(this);
        this.controlPanel.getDefaultConfiguration().addActionListener(this);
    }

    private void updateLabParameterPanel(){
        // Resource Settings
        this.labParameterPanel.getProductMaxQuantity().setValue(this.controller.getLabParameter().getProductMaxQuantity());
        this.labParameterPanel.getProductMinQuantity().setValue(this.controller.getLabParameter().getProductMinQuantity());

        // Producer/Consumer Count
        this.labParameterPanel.getNumberProducers().setValue(this.controller.getLabParameter().getNumberProducers());
        this.labParameterPanel.getNumberConsumers().setValue(this.controller.getLabParameter().getNumberConsumers());

        // Start Delay Settings
        this.labParameterPanel.getStartDelayMax().setValue(this.controller.getLabParameter().getStartDelayMax());
        this.labParameterPanel.getStartDelayMin().setValue(this.controller.getLabParameter().getStartDelayMin());

        // Producer Timing
        this.labParameterPanel.getProduceMaxTime().setValue(this.controller.getLabParameter().getProduceMaxTime());
        this.labParameterPanel.getProduceMinTime().setValue(this.controller.getLabParameter().getProduceMinTime());

        // Consumer Timing
        this.labParameterPanel.getConsumeMaxTime().setValue(this.controller.getLabParameter().getConsumeMaxTime());
        this.labParameterPanel.getConsumeMinTime().setValue(this.controller.getLabParameter().getConsumeMinTime());

        // Lifecycle
        this.labParameterPanel.getProducerCycles().setValue(this.controller.getLabParameter().getProducerItemQuantity());
        this.labParameterPanel.getConsumerCycles().setValue(this.controller.getLabParameter().getConsumerItemQuantity());

        // Operation Settings
        this.labParameterPanel.getIsSynchronized().setSelected(this.controller.getLabParameter().isSynchronized());
        this.labParameterPanel.getIsPreventingNegativeStock().setSelected(this.controller.getLabParameter().isPreventingNegativeStock());
    }

    private void updateResults(){
        this.labResultsPanel.getProductQuantity().setText(String.valueOf(this.controller.getModel().getProduct().getQuantity()));

        this.labResultsPanel.getProducerQuantity().setText(String.valueOf(this.controller.getModel().getProducers().size()));
        this.labResultsPanel.getConsumerQuantity().setText(String.valueOf(this.controller.getModel().getConsumers().size()));

        this.labResultsPanel.getFinalizedConsumerQuantity().setText(String.valueOf(this.controller.getModel().getFinalizedConsumerQuantity()));
        this.labResultsPanel.getFinalizedProducerQuantity().setText(String.valueOf(this.controller.getModel().getFinalizedProducerQuantity()));
    }

    private void updateProductPanel(){
        Product product = this.controller.getModel().getProduct();

        this.productPanel.addOrUpdateProduct(product);
    }

    private void updateConsumersPanel(){
        List<Consumer> consumers = new ArrayList<>(this.controller.getModel().getConsumers());

        for (Consumer consumer : consumers){
            if (this.controller.getLabParameter().isStopRequest()){
                return;
            }

            this.consumersPanel.addOrUpdateConsumer(consumer);
        }
    }

    private void updateProducersPanel(){
        List<Producer> producers = new ArrayList<>(this.controller.getModel().getProducers());

        for (Producer producer : producers){
            if (this.controller.getLabParameter().isStopRequest()){
                return;
            }
            this.producersPanel.addOrUpdateProducer(producer);
        }
    }

    private void resetProgram(){
        this.controller.stopAllThreads();
        this.controller.getLabParameter().setStopRequest(false);
        this.controller.getLabParameter().setRunning(true);
        this.controller.getModel().resetVariables();

        updateLabParameterPanel();

        this.producersPanel.clearProducers();
        this.consumersPanel.clearConsumers();
        this.productPanel.clearProducts();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        String actionCommand = actionEvent.getActionCommand();

        switch (actionCommand){
            case "START":
                this.controller.updateLabParameterDTO();
                resetProgram();
                this.controller.startModel();
                break;
            case "PAUSE":
                if (this.controller.getLabParameter().isRunning()){
                    this.controller.getLabParameter().setRunning(false);
                } else {
                    System.out.println("Sistema ya pausado");
                }
                this.controlPanel.getPause().setText("CONTINUE");
                this.controlPanel.getStart().setSelected(true);
                break;

            case "CONTINUE":
                if (!this.controller.getLabParameter().isRunning()){
                    this.controller.getLabParameter().setRunning(true);
                }else {
                    System.out.println("Sistema ya en ejecucion");
                }
                this.controlPanel.getPause().setText("PAUSE");
                this.controlPanel.getStart().setSelected(false);
                break;
            case "RESET":
                resetProgram();

                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "DEFAULT":
                this.controller.resetDTO();
                resetProgram();

                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            default:
                System.err.println("Acción no tratada: "+actionCommand);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            updateResults();
            updateProductPanel();
            updateConsumersPanel();
            updateProducersPanel();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
