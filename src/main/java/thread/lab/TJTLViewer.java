package thread.lab;

import lombok.Getter;
import lombok.Setter;
import thread.lab.dto.DTOLabResult;
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

    private void updateParameter(){

    }

    private void updateResults(){
        DTOLabResult labResult = this.controller.getLabResult();
        this.labResultsPanel.getProductQuantity().setText(String.valueOf(labResult.getProductQuantity()));
        this.labResultsPanel.getProducerQuantity().setText(String.valueOf(labResult.getProducerQuantity()));
        this.labResultsPanel.getConsumerQuantity().setText(String.valueOf(labResult.getConsumerQuantity()));
        this.labResultsPanel.getProcessingProducerQuantity().setText(String.valueOf(labResult.getProcessingProducerQuantity()));
        this.labResultsPanel.getProcessingConsumerQuantity().setText(String.valueOf(labResult.getProcessingConsumerQuantity()));
        this.labResultsPanel.getTotalTimer().setText(String.valueOf(labResult.getTotalTimer()));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        String actionCommand = actionEvent.getActionCommand();

        switch (actionCommand){
            case "START":
                this.controlPanel.getStart().setText("PAUSE");
                this.controlPanel.getStart().setSelected(true);
                this.controller.startModel();
                break;
            case "PAUSE":
                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "STOP":
                this.controller.resetDTO();
                this.controlPanel.getStart().setSelected(false);
                this.controlPanel.getStart().setText("START");
                break;
            case "Default Configuration":
                this.controller.resetDTO();
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
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
