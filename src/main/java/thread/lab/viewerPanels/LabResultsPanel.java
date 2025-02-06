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
        Color colorBackground = new Color(0xDD88CF);
        Color colorForeground = new Color(0xF8E7F6);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Product Quantity:"), constraints);

        constraints.gridx = 1;
        add(productQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Producer Quantity:"), constraints);

        constraints.gridx = 1;
        add(producerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("Consumer Quantity:"), constraints);

        constraints.gridx = 1;
        add(consumerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(new JLabel("Finalized Producer Quantity:"), constraints);

        constraints.gridx = 1;
        add(finalizedProducerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(new JLabel("Finalized Consumer Quantity:"), constraints);

        constraints.gridx = 1;
        add(finalizedConsumerQuantity, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(new JLabel("Total Timer:"), constraints);

        constraints.gridx = 1;
        add(totalTimer, constraints);
    }
}