package thread.lab.viewerPanels;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class LabParameterPanel extends JPanel {
    private JSpinner numberProducts;
    private JSpinner productMaxQuantity;
    private JSpinner productMinQuantity;

    private JSpinner numberProducers;
    private JSpinner numberConsumers;

    private JSpinner startDelayMax;
    private JSpinner startDelayMin;

    private JSpinner produceMaxTime;
    private JSpinner produceMinTime;

    private JSpinner consumeMaxTime;
    private JSpinner consumeMinTime;

    private JSpinner producerCycles;
    private JSpinner consumerCycles;

    private JCheckBox isSynchronized;
    private JCheckBox isPreventingNegativeStock;

    public LabParameterPanel(){
        this.numberProducts = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.productMaxQuantity = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.productMinQuantity = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.numberProducers = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.numberConsumers = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.startDelayMax = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.startDelayMin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.produceMaxTime = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.produceMinTime = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.consumeMaxTime = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.consumeMinTime = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.producerCycles = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.consumerCycles = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.isSynchronized = new JCheckBox();
        this.isPreventingNegativeStock = new JCheckBox();

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        //Fuentes
        Font sectionFont = new Font("Arial", Font.BOLD, 14);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel resourceLabel = new JLabel("Resource Settings");
        resourceLabel.setFont(sectionFont);
        add(resourceLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Number of Products:"), constraints);
        constraints.gridx = 1;
        add(numberProducts, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Product Max Quantity:"), constraints);
        constraints.gridx = 1;
        add(productMaxQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Product Min Quantity:"), constraints);
        constraints.gridx = 1;
        add(productMinQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel producerConsumerLabel = new JLabel("Producer/Consumer Count");
        producerConsumerLabel.setFont(sectionFont);
        add(producerConsumerLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Number of Producers:"), constraints);
        constraints.gridx = 1;
        add(numberProducers, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Number of Consumers:"), constraints);
        constraints.gridx = 1;
        add(numberConsumers, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel startDelayLabel = new JLabel("Start Delay Settings");
        startDelayLabel.setFont(sectionFont);
        add(startDelayLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Start Delay Max:"), constraints);
        constraints.gridx = 1;
        add(startDelayMax, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Start Delay Min:"), constraints);
        constraints.gridx = 1;
        add(startDelayMin, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel producerTimingLabel = new JLabel("Producer Timing");
        producerTimingLabel.setFont(sectionFont);
        add(producerTimingLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Produce Max Time:"), constraints);
        constraints.gridx = 1;
        add(produceMaxTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Produce Min Time:"), constraints);
        constraints.gridx = 1;
        add(produceMinTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel consumerTimingLabel = new JLabel("Consumer Timing");
        consumerTimingLabel.setFont(sectionFont);
        add(consumerTimingLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Consume Max Time:"), constraints);
        constraints.gridx = 1;
        add(consumeMaxTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Consume Min Time:"), constraints);
        constraints.gridx = 1;
        add(consumeMinTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel lifecycleLabel = new JLabel("Lifecycle");
        lifecycleLabel.setFont(sectionFont);
        add(lifecycleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Producer Cycles:"), constraints);
        constraints.gridx = 1;
        add(producerCycles, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Consumer Cycles:"), constraints);
        constraints.gridx = 1;
        add(consumerCycles, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JLabel operationLabel = new JLabel("Operation Settings");
        operationLabel.setFont(sectionFont);
        add(operationLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("Synchronized:"), constraints);
        constraints.gridx = 1;
        add(isSynchronized, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Prevent Negative Stock:"), constraints);
        constraints.gridx = 1;
        add(isPreventingNegativeStock, constraints);
    }
}
