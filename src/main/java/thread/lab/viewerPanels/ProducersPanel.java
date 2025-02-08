package thread.lab.viewerPanels;

import thread.lab.Producer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProducersPanel extends JPanel {
    private final Map<String, JLabel[]> producerRows = new HashMap<>();

    public ProducersPanel(){
        setLayout(new GridBagLayout());
        addComponentsToPanel();
    }
    private void addComponentsToPanel(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Producer ID"), constraints);
        constraints.gridx = 1;
        add(new JLabel("Status"), constraints);
        constraints.gridx = 2;
        add(new JLabel("Production"), constraints);
        constraints.gridx = 3;
        add(new JLabel("Processing Time"), constraints);
        constraints.gridx = 4;
        add(new JLabel("Start Time"), constraints);
        constraints.gridx = 5;
        add(new JLabel("End Time"), constraints);
    }

    public void addOrUpdateProducer(Producer producer){
        String producerID = producer.getProducerID();
        if (producerRows.containsKey(producerID)){
            JLabel[] labels = producerRows.get(producerID);
            labels[1].setText(producer.getStatus());
            labels[2].setText(String.valueOf(producer.getProduction()));
            labels[3].setText(String.valueOf(producer.getProcessingTime()));
            labels[4].setText(String.valueOf(producer.getStartTime()));
            labels[5].setText(String.valueOf(producer.getEndTime()));
        } else {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridy = producerRows.size() + 1;

            JLabel[] labels = new JLabel[]{
                    new JLabel(producerID),
                    new JLabel(producer.getStatus()),
                    new JLabel(String.valueOf(producer.getProduction())),
                    new JLabel(String.valueOf(producer.getProcessingTime())),
                    new JLabel(String.valueOf(producer.getStartTime())),
                    new JLabel(String.valueOf(producer.getEndTime()))
            };

            for (int i = 0; i < labels.length; i++) {
                constraints.gridx = i;
                add(labels[i], constraints);
            }

            producerRows.put(producerID, labels);

            revalidate();
            repaint();
        }
    }

    public void clearProducers(){
        producerRows.clear();
        removeAll();
        addComponentsToPanel();

        revalidate();
        repaint();
    }
}
