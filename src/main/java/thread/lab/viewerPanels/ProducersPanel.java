package thread.lab.viewerPanels;

import thread.lab.Producer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProducersPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final Map<String, Integer> producerRows;

    public ProducersPanel(){
        setLayout(new BorderLayout());

        String[] columnNames = {"Producer ID", "Status", "Production", "Processing Time", "Start Time", "End Time"};
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
            model.setValueAt(producer.getStatus(), row, 1);
            model.setValueAt(producer.getProduction(), row, 2);
            model.setValueAt(producer.getProcessingTime(), row, 3);
            model.setValueAt(producer.getStartTime(), row, 4);
            model.setValueAt(producer.getEndTime(), row, 5);
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
