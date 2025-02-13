package thread.lab.viewerPanels;

import lombok.Getter;
import lombok.Setter;
import thread.lab.Producer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ProducersPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final Map<String, Integer> producerRows;

    public ProducersPanel(){
        setLayout(new BorderLayout());
        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));

        String[] columnNames = {"Producer ID", "Resource", "Produce delay", "Status", "Production", "Processing Time", "Start Time", "End Time"};
        this.model = new DefaultTableModel(columnNames, 0);
        this.table = new JTable(model);
        this.table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        producerRows = new HashMap<>();
    }

    public void addOrUpdateProducer(Producer producer){
        String producerID = producer.getProducerID();
        if (producerRows.containsKey(producerID)){
            int row = producerRows.get(producerID);
            model.setValueAt(producer.getResourceBound(), row, 1);
            model.setValueAt(producer.getProduceDelay(), row, 2);
            model.setValueAt(producer.getStatus(), row, 3);
            model.setValueAt(producer.getProduction(), row, 4);
            model.setValueAt(producer.getProcessingTime(), row, 5);
            model.setValueAt(producer.getStartTime(), row, 6);
            model.setValueAt(producer.getEndTime(), row, 7);
        } else {
            Object[] rowData = {
                    producerID,
                    producer.getStatus(),
                    producer.getProduction(),
                    producer.getProcessingTime(),
                    producer.getStartTime(),
                    producer.getEndTime()
            };
            model.addRow(rowData);
            producerRows.put(producerID, model.getRowCount() - 1);
        }
    }

    public void clearProducers(){
        this.model.setRowCount(0);
        this.producerRows.clear();
    }
}
