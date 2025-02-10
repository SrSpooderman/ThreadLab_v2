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

        addComponentToColumn(leftPanel,0,0, 1F, 0.7F,labResultsPanel);
        addComponentToColumn(leftPanel,0,1, 1F, 0.3F,controlPanel);
        addComponentToColumn(centerPanel,0,0, 1F, 1F,labParameterPanel);

        addComponentToColumn(rightPanel,0,0, 1F, 0.2F,productPanel);
        JScrollPane scrollPaneConsumer = new JScrollPane(consumersPanel);
        scrollPaneConsumer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneConsumer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addComponentToColumn(rightPanel,0,1,1F,0.4F,scrollPaneConsumer);
        JScrollPane scrollPaneProducer = new JScrollPane(producersPanel);
        scrollPaneProducer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneProducer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addComponentToColumn(rightPanel,0,2,1F,0.4F,scrollPaneProducer);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(leftPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.weighty = 1.0;
        panel.add(centerPanel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        panel.add(rightPanel, constraints);
    }

    private void addComponentToColumn(JPanel column, int gridX, int gridY, float weightX, float weightY, Component component){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.weightx = weightX;
        constraints.weighty = weightY;

        column.add(component, constraints);
    }

    private void confButtonFunc(){
        this.controlPanel.getStart().addActionListener(this);
        this.controlPanel.getStop().addActionListener(this);
        this.controlPanel.getLoadConfiguration().addActionListener(this);
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
        this.labParameterPanel.getProducerItemQuantity().setValue(this.controller.getLabParameter().getProducerItemQuantity());
        this.labParameterPanel.getConsumerItemQuantity().setValue(this.controller.getLabParameter().getConsumerItemQuantity());

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

        this.productPanel.getProductID().setText(String.valueOf(product.getProductID()));
        this.productPanel.getQuantity().setText(String.valueOf(product.getQuantity()));
        this.productPanel.getQuantityConsumed().setText(String.valueOf(product.getQuantityConsumed()));
        this.productPanel.getQuantityProduced().setText(String.valueOf(product.getQuantityProduced()));
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

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        String actionCommand = actionEvent.getActionCommand();

        switch (actionCommand){
            case "START":
                if (!this.controller.getLabParameter().isRunning()){
                    this.controller.getLabParameter().setRunning(true);
                }else {
                    this.controller.startModel();
                }

                this.controlPanel.getStart().setText("PAUSE");
                this.controlPanel.getStart().setSelected(true);
                break;
            case "PAUSE":
                this.controller.getLabParameter().setRunning(false);

                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "STOP":
                this.controller.stopAllThreads();
                this.controller.resetDTO();
                this.controller.getModel().resetVariables();

                updateLabParameterPanel();
                this.producersPanel.clearProducers();
                this.consumersPanel.clearConsumers();

                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "Default Configuration":
                this.controller.resetDTO();
                this.controller.getModel().resetVariables();
                this.controller.stopAllThreads();
                updateLabParameterPanel();
                this.producersPanel.clearProducers();
                this.consumersPanel.clearConsumers();
                break;
            case "Load Configuration":
                this.controller.updateLabParameterDTO();
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
