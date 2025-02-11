package thread.lab.viewerPanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ControlPanel extends JPanel {
    private JButton start;
    private JButton reset;
    private JToggleButton pause ;
    private JButton defaultConfiguration;

    public ControlPanel(){
        this.start = new JButton("START");
        this.reset = new JButton("RESET");
        this.pause = new JToggleButton("PAUSE");
        this.defaultConfiguration = new JButton("DEFAULT");

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        Color colorBackground = new Color(0xDD88CF);
        Color colorForeground = new Color(0xF8E7F6);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Fila 1, Columna 1
        constraints.gridx = 0;
        constraints.gridy = 0;
        start.setBackground(colorBackground);
        start.setForeground(colorForeground);
        add(start, constraints);

        // Fila 1, Columna 2
        constraints.gridx = 1;
        constraints.gridy = 0;
        reset.setBackground(colorBackground);
        reset.setForeground(colorForeground);
        add(reset, constraints);

        // Fila 2, Columna 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        pause.setBackground(colorBackground);
        pause.setForeground(colorForeground);
        add(pause, constraints);

        // Fila 2, Columna 2
        constraints.gridx = 1;
        constraints.gridy = 1;
        defaultConfiguration.setBackground(colorBackground);
        defaultConfiguration.setForeground(colorForeground);
        add(defaultConfiguration, constraints);
    }
}
