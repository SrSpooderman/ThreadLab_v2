package thread.lab.viewerPanels;

import thread.lab.Consumer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConsumersPanel extends JPanel {
    private final Map<String, JLabel[]> consumerRows = new HashMap<>();

    public ConsumersPanel(){
        addComponentsToPanel();
    }
    private void addComponentsToPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Consumer ID"), constraints);
        constraints.gridx = 1;
        add(new JLabel("Status"), constraints);
        constraints.gridx = 2;
        add(new JLabel("Consumption"), constraints);
        constraints.gridx = 3;
        add(new JLabel("Processing Time"), constraints);
        constraints.gridx = 4;
        add(new JLabel("Start Time"), constraints);
        constraints.gridx = 5;
        add(new JLabel("End Time"), constraints);
    }

    public void addOrUpdateConsumer(Consumer consumer){
        String consumerID = consumer.getConsumerID();
        if (consumerRows.containsKey(consumerID)){
            JLabel[] labels = consumerRows.get(consumerID);
            labels[1].setText(consumer.getStatus());
            labels[2].setText(String.valueOf(consumer.getConsumption()));
            labels[3].setText(String.valueOf(consumer.getProcessingTime()));
            labels[4].setText(String.valueOf(consumer.getStartTime()));
            labels[5].setText(String.valueOf(consumer.getEndTime()));
        } else {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridy = consumerRows.size() + 1;

            JLabel[] labels = new JLabel[]{
                    new JLabel(consumerID),
                    new JLabel(consumer.getStatus()),
                    new JLabel(String.valueOf(consumer.getConsumption())),
                    new JLabel(String.valueOf(consumer.getProcessingTime())),
                    new JLabel(String.valueOf(consumer.getStartTime())),
                    new JLabel(String.valueOf(consumer.getEndTime()))
            };

            for (int i = 0; i < labels.length; i++) {
                constraints.gridx = i;
                add(labels[i], constraints);
            }

            consumerRows.put(consumerID, labels);

            revalidate();
            repaint();
        }
    }



    public void clearConsumers(){
        consumerRows.clear();
        removeAll();
        addComponentsToPanel();

        revalidate();
    }
}
