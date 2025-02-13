package thread.lab.viewerPanels;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class ControlPanel extends JPanel {
    private JToggleButton start;
    private JButton reset;
    private JToggleButton pause ;
    private JButton defaultConfiguration;

    public ControlPanel(){
        this.start = new JToggleButton("START");
        this.reset = new JButton("RESET");
        this.pause = new JToggleButton("PAUSE");
        this.defaultConfiguration = new JButton("DEFAULT");

        addComponentsToPanel();
    }

    private void addComponentsToPanel(){
        Color colorBackground = new Color(0x9f998f);
        Color colorForeground = new Color(0x6d5742);
        Color borderColor = new Color(0x5e9af7);
        Font buttonFont = new Font("New Roman", Font.BOLD, 14);

        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Fila 1, Columna 1
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 0.5;
        configureToggleButton(start);
        add(start, constraints);

        // Fila 1, Columna 2
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 0.5;
        configureButton(reset);
        add(reset, constraints);

        // Fila 2, Columna 1
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 0.5;
        configureToggleButton(pause);
        add(pause, constraints);

        // Fila 2, Columna 2
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 0.5;
        configureButton(defaultConfiguration);
        add(defaultConfiguration, constraints);
    }


    private void configureButton(JButton button){
        Color colorBackground = new Color(0x9f998f);
        Color colorForeground = new Color(0x6d5742);
        Color borderColor = new Color(0x5e9af7);
        Font buttonFont = new Font("Times New Roman", Font.PLAIN, 25);

        button.setBackground(colorBackground);
        button.setForeground(colorForeground);
        button.setFont(buttonFont);
        button.setBorder(BorderFactory.createLineBorder(borderColor,2));

        //HOVER
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                button.setBackground(colorForeground);
                button.setForeground(colorBackground);
            }
            @Override
            public void mouseExited(MouseEvent e){
                button.setBackground(colorBackground);
                button.setForeground(colorForeground);
            }
        });
    }
    private void configureToggleButton(JToggleButton button){
        Color colorBackground = new Color(0x9f998f);
        Color colorForeground = new Color(0x6d5742);
        Color borderColor = new Color(0x5e9af7);
        Font buttonFont = new Font("Times New Roman", Font.PLAIN, 25);

        button.setFont(buttonFont);
        button.setBackground(colorBackground);
        button.setForeground(colorForeground);
        button.setBorder(BorderFactory.createLineBorder(borderColor,2));
        button.setFocusPainted(false);

        // HOVER
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                button.setBackground(colorForeground);
                button.setForeground(colorBackground);
            }
            @Override
            public void mouseExited(MouseEvent e){
                button.setBackground(colorBackground);
                button.setForeground(colorForeground);
            }
        });

        // TOGGLE BUTTON
        button.addItemListener(e -> {
            if (button.isSelected()) {
                button.setBackground(colorForeground);
                button.setForeground(colorBackground);
            } else {
                button.setBackground(colorBackground);
                button.setForeground(colorForeground);
            }
        });
    }
}
