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

    private JCheckBox isCyclesActive;
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

        this.isCyclesActive = new JCheckBox();
        this.producerCycles = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        this.consumerCycles = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        this.isSynchronized = new JCheckBox();
        this.isPreventingNegativeStock = new JCheckBox();

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(3, 3, 3, 3  );

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Resource Settings"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Number of Products:"), constraints);
        constraints.gridx = 1;
        configureSpinner(numberProducts);
        add(numberProducts, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Product Max Quantity:"), constraints);
        constraints.gridx = 1;
        configureSpinner(productMaxQuantity);
        add(productMaxQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Product Min Quantity:"), constraints);
        constraints.gridx = 1;
        configureSpinner(productMinQuantity);
        add(productMinQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Producer/Consumer Count"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Number of Producers:"), constraints);
        constraints.gridx = 1;
        configureSpinner(numberProducers);
        add(numberProducers, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Number of Consumers:"), constraints);
        constraints.gridx = 1;
        configureSpinner(numberConsumers);
        add(numberConsumers, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Start Delay Settings"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Start Delay Max:"), constraints);
        constraints.gridx = 1;
        configureSpinner(startDelayMax);
        add(startDelayMax, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Start Delay Min:"), constraints);
        constraints.gridx = 1;
        configureSpinner(startDelayMin);
        add(startDelayMin, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Producer Timing"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Produce Max Time:"), constraints);
        constraints.gridx = 1;
        configureSpinner(produceMaxTime);
        add(produceMaxTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Produce Min Time:"), constraints);
        constraints.gridx = 1;
        configureSpinner(produceMinTime);
        add(produceMinTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Consumer Timing"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Consume Max Time:"), constraints);
        constraints.gridx = 1;
        configureSpinner(consumeMaxTime);
        add(consumeMaxTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Consume Min Time:"), constraints);
        constraints.gridx = 1;
        configureSpinner(consumeMinTime);
        add(consumeMinTime, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Lifecycle"), constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Active Cycles Limit"), constraints);
        constraints.gridx = 1;
        configureCheckBox(isCyclesActive);
        add(isCyclesActive, constraints);


        constraints.gridy++;
        constraints.gridx = 0;
        add(textSpinnerLabel("Producer Cycles:"), constraints);
        constraints.gridx = 1;
        configureSpinner(producerCycles);
        add(producerCycles, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Consumer Cycles:"), constraints);
        constraints.gridx = 1;
        configureSpinner(consumerCycles);
        add(consumerCycles, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(paragraphLabel("Operation Settings"), constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.WEST;
        add(textSpinnerLabel("Synchronized:"), constraints);
        constraints.gridx = 1;
        configureCheckBox(isSynchronized);
        add(isSynchronized, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(textSpinnerLabel("Prevent Negative Stock:"), constraints);
        constraints.gridx = 1;
        configureCheckBox(isPreventingNegativeStock);
        add(isPreventingNegativeStock, constraints);
    }

    private JLabel paragraphLabel(String text){
        Color colorForeground = new Color(0x9f998f);
        Font sectionFont = new Font("Times New Roman", Font.BOLD, 20);
        JLabel label = new JLabel(text);
        label.setFont(sectionFont);
        label.setForeground(colorForeground);
        return label;
    }
    private JLabel textSpinnerLabel(String text){
        Color colorForeground = new Color(0x9f998f);
        Font labelFont = new Font("Times New Roman", Font.PLAIN, 16);
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setForeground(colorForeground);
        return label;
    }
    private void configureSpinner(JSpinner spinner){
        JComponent editor = spinner.getEditor();
        Font spinnerFont = new Font("Times New Roman", Font.BOLD, 16);
        if (editor instanceof JSpinner.NumberEditor defaultEditor) {
            JTextField textField = defaultEditor.getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setBackground(new Color(0x31241b));
            textField.setForeground(new Color(0x9f998f));
            textField.setFont(spinnerFont);
        }
        spinner.setBorder(BorderFactory.createLineBorder(new Color(0x9f998f), 2));
    }
    private void configureCheckBox(JCheckBox checkBox) {
        Color backgroundColor = new Color(0x9f998f);
        Color foregroundColor = new Color(0x6d5742);
        Color borderColor = new Color(0x5e9af7);

        checkBox.setBackground(backgroundColor);
        checkBox.setForeground(foregroundColor);
        checkBox.setBorder(BorderFactory.createLineBorder(borderColor, 2));
    }
}
