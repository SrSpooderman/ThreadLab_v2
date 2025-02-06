package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.viewerPanels.ControlPanel;
import thread.lab.viewerPanels.LabParameterPanel;
import thread.lab.viewerPanels.LabResultsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class TJTLViewer extends JFrame implements Runnable, ActionListener {
    private TJTLController controller;
    private LabResultsPanel labResultsPanel;
    private ControlPanel controlPanel;
    private LabParameterPanel labParameterPanel;

    public TJTLViewer (TJTLController controller){
        this.controller = controller;
        this.labResultsPanel = new LabResultsPanel();
        this.controlPanel = new ControlPanel();
        this.labParameterPanel = new LabParameterPanel();

        configureJFrame();
    }

    private void configureJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Thread Lab");
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        addComponentsToPane(this.getContentPane());
        confButtonFunc();
    }

    private void addComponentsToPane(Container panel){
        GridBagConstraints constraints = new GridBagConstraints();

        //Primera columna
        constraints.gridx = 0;
        constraints.gridy = 0; //Fila 1
        add(labResultsPanel, constraints);

        constraints.gridy = 1; // Fila 2
        add(controlPanel, constraints);

        //Segunda columna
        constraints.gridx = 1;
        constraints.gridy = 0;  // Fila 1
        add(labParameterPanel, constraints);

        //Tercera columna
        constraints.gridx = 2;
        //add(a, constraints); //Fila 1

        constraints.gridy = 1;  // Fila 2
        //add(a, constraints);

        constraints.gridy = 2;  // Fila 3
        //add(a, constraints);

    }

    private void confButtonFunc(){
        this.controlPanel.getStart().addActionListener(this);
        this.controlPanel.getStop().addActionListener(this);
        this.controlPanel.getLoadConfiguration().addActionListener(this);
        this.controlPanel.getDefaultConfiguration().addActionListener(this);
    }

    private void updateLabParameterPanel(){
        this.labParameterPanel.getIsSynchronized().setSelected(this.controller.getLabParameter().isSynchronized());
        this.labParameterPanel.getIsPreventingNegativeStock().setSelected(this.controller.getLabParameter().isPreventingNegativeStock());

        this.labParameterPanel.getNumberProducers().setValue((Integer)this.controller.getLabParameter().getNumberProducers());
        this.labParameterPanel.getProducerItemQuantity().setValue((Integer)this.controller.getLabParameter().getProducerItemQuantity());

        this.labParameterPanel.getNumberConsumers().setValue((Integer)this.controller.getLabParameter().getNumberConsumers());
        this.labParameterPanel.getConsumerItemQuantity().setValue((Integer)this.controller.getLabParameter().getConsumerItemQuantity());

        this.labParameterPanel.getIsEnableProducerMaxTime().setSelected(this.controller.getLabParameter().isEnableProducerMaxTime());
        this.labParameterPanel.getProducerDelayMax().setValue((Integer)this.controller.getLabParameter().getProducerDelayMax());

        this.labParameterPanel.getIsEnableConsumerMaxTime().setSelected(this.controller.getLabParameter().isEnableConsumerMaxTime());
        this.labParameterPanel.getConsumerDelayMax().setValue((Integer)this.controller.getLabParameter().getConsumerDelayMax());
    }

    private void updateResults(){
        this.labResultsPanel.getProductQuantity().setText(String.valueOf(this.controller.getModel().getProduct().getQuantity()));

        this.labResultsPanel.getProducerQuantity().setText(String.valueOf(this.controller.getModel().getProducers().size()));
        this.labResultsPanel.getConsumerQuantity().setText(String.valueOf(this.controller.getModel().getConsumers().size()));

        this.labResultsPanel.getFinalizedConsumerQuantity().setText(String.valueOf(this.controller.getModel().getFinalizedConsumerQuantity()));
        this.labResultsPanel.getFinalizedProducerQuantity().setText(String.valueOf(this.controller.getModel().getFinalizedProducerQuantity()));
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

                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "Default Configuration":
                this.controller.resetDTO();
                this.controller.getModel().resetVariables();
                this.controller.stopAllThreads();
                updateLabParameterPanel();
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

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
