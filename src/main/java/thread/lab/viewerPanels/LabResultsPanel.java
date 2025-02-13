package thread.lab.viewerPanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class LabResultsPanel extends JPanel {
    private JTextField productQuantity;

    private JTextField producerQuantity;
    private JTextField consumerQuantity;

    private JTextField finalizedProducerQuantity;
    private JTextField finalizedConsumerQuantity;

    private JTextField totalTimer;

    public LabResultsPanel(){
        this.productQuantity = new JTextField();
        this.productQuantity.setEditable(false);
        this.producerQuantity = new JTextField();
        this.producerQuantity.setEditable(false);
        this.consumerQuantity = new JTextField();
        this.consumerQuantity.setEditable(false);
        this.finalizedProducerQuantity = new JTextField();
        this.finalizedProducerQuantity.setEditable(false);
        this.finalizedConsumerQuantity = new JTextField();
        this.finalizedConsumerQuantity.setEditable(false);
        this.totalTimer = new JTextField();
        this.totalTimer.setEditable(false);

        resetLabResultsPanel();
        addComponentsToPanel();
    }

    public void resetLabResultsPanel(){
        this.productQuantity.setText("Esperando datos...");
        this.producerQuantity.setText("Esperando datos...");
        this.consumerQuantity.setText("Esperando datos...");
        this.finalizedProducerQuantity.setText("Esperando datos...");
        this.finalizedConsumerQuantity.setText("Esperando datos...");
        this.totalTimer.setText("Esperando inicio...");
    }

    private void addComponentsToPanel() {
        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(textLabel("Product Quantity:"), constraints);

        constraints.gridx = 1;
        configureTextField(productQuantity);
        add(productQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(textLabel("Producer Quantity:"), constraints);

        constraints.gridx = 1;
        configureTextField(producerQuantity);
        add(producerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(textLabel("Consumer Quantity:"), constraints);

        constraints.gridx = 1;
        configureTextField(consumerQuantity);
        add(consumerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(textLabel("Finalized Producer Quantity:"), constraints);

        constraints.gridx = 1;
        configureTextField(finalizedProducerQuantity);
        add(finalizedProducerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(textLabel("Finalized Consumer Quantity:"), constraints);

        constraints.gridx = 1;
        configureTextField(finalizedConsumerQuantity);
        add(finalizedConsumerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(textLabel("Total Timer:"), constraints);

        constraints.gridx = 1;
        configureTextField(totalTimer);
        add(totalTimer, constraints);
    }

    private JLabel textLabel(String text){
        Color colorForeground = new Color(0x9f998f);
        Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setForeground(colorForeground);
        return label;
    }
    private void configureTextField(JTextField textField){
        Font font = new Font("Times New Roman", Font.BOLD, 16);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(new Color(0x31241b));
        textField.setForeground(new Color(0x9f998f));
        textField.setFont(font);
    }
}