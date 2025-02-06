package thread.lab.viewerPanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ControlPanel extends JPanel {
    private JToggleButton start;
    private JButton stop;
    private JButton loadConfiguration;
    private JButton defaultConfiguration;

    public ControlPanel(){
        this.start = new JToggleButton("START");
        this.stop = new JButton("STOP");
        this.loadConfiguration = new JButton("Load Configuration");
        this.defaultConfiguration = new JButton("Default Configuration");

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
        stop.setBackground(colorBackground);
        stop.setForeground(colorForeground);
        add(stop, constraints);

        // Fila 2, Columna 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        loadConfiguration.setBackground(colorBackground);
        loadConfiguration.setForeground(colorForeground);
        add(loadConfiguration, constraints);

        // Fila 2, Columna 2
        constraints.gridx = 1;
        constraints.gridy = 1;
        defaultConfiguration.setBackground(colorBackground);
        defaultConfiguration.setForeground(colorForeground);
        add(defaultConfiguration, constraints);
    }
}
