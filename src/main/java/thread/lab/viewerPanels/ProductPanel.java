package thread.lab.viewerPanels;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ProductPanel extends JPanel {
    private JTextField productID;
    private JTextField quantity;
    private JTextField quantityConsumed;
    private JTextField quantityProduced;

    public ProductPanel(){
        this.productID = new JTextField();
        this.productID.setEditable(false);
        this.quantity = new JTextField();
        this.quantity.setEditable(false);
        this.quantityConsumed = new JTextField();
        this.quantityConsumed.setEditable(false);
        this.quantityProduced = new JTextField();
        this.quantityProduced.setEditable(false);

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Product ID"), constraints);
        constraints.gridx = 1;
        add(new JLabel("Quantity"), constraints);
        constraints.gridx = 2;
        add(new JLabel("Quantity Consumed"), constraints);
        constraints.gridx = 3;
        add(new JLabel("Quantity Produced"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(productID, constraints);
        constraints.gridx = 1;
        add(quantity, constraints);
        constraints.gridx = 2;
        add(quantityConsumed, constraints);
        constraints.gridx = 3;
        add(quantityProduced, constraints);
    }
}
