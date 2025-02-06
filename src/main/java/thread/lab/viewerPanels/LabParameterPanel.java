package thread.lab.viewerPanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class LabParameterPanel extends JPanel {
    private JCheckBox isSynchronized;
    private JCheckBox isPreventingNegativeStock;

    private JSpinner totalResources;
    private JSpinner maxGeneralResources;
    private JSpinner minGeneralResources;

    private JSpinner numberProducers;
    private JSpinner producerItemQuantity;
    private JSpinner numberConsumers;
    private JSpinner consumerItemQuantity;

    private JSpinner startDelayMin;
    private JSpinner startDelayMax;

    private JCheckBox isEnableProducerMaxTime;
    private JSpinner producerDelayMin;
    private JSpinner producerDelayMax;

    private JCheckBox isEnableConsumerMaxTime;
    private JSpinner consumerDelayMin;
    private JSpinner consumerDelayMax;

    public LabParameterPanel(){
        this.isSynchronized = new JCheckBox("Synchronized");
        this.isPreventingNegativeStock = new JCheckBox("Prevent Negative Stock");

        this.totalResources = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        this.maxGeneralResources = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        this.minGeneralResources = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));

        this.numberProducers = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        this.producerItemQuantity = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        this.numberConsumers = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        this.consumerItemQuantity = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));

        this.startDelayMin = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        this.startDelayMax = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));

        this.isEnableProducerMaxTime = new JCheckBox("Enable Producer Max Time");
        this.producerDelayMin = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        this.producerDelayMax = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));

        this.isEnableConsumerMaxTime = new JCheckBox("Enable Consumer Max Time");
        this.consumerDelayMin = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        this.consumerDelayMax = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        Color colorBackground = new Color(0xDD88CF);
        Color colorForeground = new Color(0xF8E7F6);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        //Synchronized
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Synchronized"), constraints);

        constraints.gridx = 1;
        add(isSynchronized, constraints);

        //Prevent Negative Stock
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Prevent Negative Stock"), constraints);

        constraints.gridx = 1;
        add(isPreventingNegativeStock, constraints);

        //Total Resources
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("Total Resources"), constraints);

        constraints.gridx = 1;
        add(totalResources, constraints);

        //Max General Resources
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(new JLabel("Max General Resources"), constraints);

        constraints.gridx = 1;
        add(maxGeneralResources, constraints);

        //Min General Resources
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(new JLabel("Min General Resources"), constraints);

        constraints.gridx = 1;
        add(minGeneralResources, constraints);

        //Number of Producers
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(new JLabel("Number of Producers"), constraints);

        constraints.gridx = 1;
        add(numberProducers, constraints);

        //Producer Item Quantity
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(new JLabel("Producer Item Quantity"), constraints);

        constraints.gridx = 1;
        add(producerItemQuantity, constraints);

        //Number of Consumers
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(new JLabel("Number of Consumers"), constraints);

        constraints.gridx = 1;
        add(numberConsumers, constraints);

        //Consumer Item Quantity
        constraints.gridx = 0;
        constraints.gridy = 8;
        add(new JLabel("Consumer Item Quantity"), constraints);

        constraints.gridx = 1;
        add(consumerItemQuantity, constraints);

        //Start Delay Min
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(new JLabel("Start Delay Min"), constraints);

        constraints.gridx = 1;
        add(startDelayMin, constraints);

        //Start Delay Max
        constraints.gridx = 0;
        constraints.gridy = 10;
        add(new JLabel("Start Delay Max"), constraints);

        constraints.gridx = 1;
        add(startDelayMax, constraints);

        //Enable Producer Max Time
        constraints.gridx = 0;
        constraints.gridy = 11;
        add(new JLabel("Enable Producer Max Time"), constraints);

        constraints.gridx = 1;
        add(isEnableProducerMaxTime, constraints);

        //Producer Delay Min
        constraints.gridx = 0;
        constraints.gridy = 12;
        add(new JLabel("Producer Delay Min"), constraints);

        constraints.gridx = 1;
        add(producerDelayMin, constraints);

        //Producer Delay Max
        constraints.gridx = 0;
        constraints.gridy = 13;
        add(new JLabel("Producer Delay Max"), constraints);

        constraints.gridx = 1;
        add(producerDelayMax, constraints);

        //Enable Consumer Max Time
        constraints.gridx = 0;
        constraints.gridy = 14;
        add(new JLabel("Enable Consumer Max Time"), constraints);

        constraints.gridx = 1;
        add(isEnableConsumerMaxTime, constraints);

        //Consumer Delay Min
        constraints.gridx = 0;
        constraints.gridy = 15;
        add(new JLabel("Consumer Delay Min"), constraints);

        constraints.gridx = 1;
        add(consumerDelayMin, constraints);

        //Consumer Delay Max
        constraints.gridx = 0;
        constraints.gridy = 16;
        add(new JLabel("Consumer Delay Max"), constraints);

        constraints.gridx = 1;
        add(consumerDelayMax, constraints);
    }
}
