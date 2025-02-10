package thread.lab.viewerPanels;

import thread.lab.Consumer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConsumersPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final Map<String, Integer> consumerRows;

    public ConsumersPanel(){
        setLayout(new BorderLayout());

        String[] columnNames = {"Consumer ID", "Status", "Consumption", "Processing Time", "Start Time", "End Time"};
        this.model = new DefaultTableModel(columnNames, 0);
        this.table = new JTable(model);
        this.table.setDefaultEditor(Object.class, null);
        //Scroll
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        //Hashmaps
        consumerRows = new HashMap<>();
    }

    public void addOrUpdateConsumer(Consumer consumer){
        String consumerID = consumer.getConsumerID();
        if (consumerRows.containsKey(consumerID)){
            int row = consumerRows.get(consumerID);
            model.setValueAt(consumer.getStatus(), row, 1);
            model.setValueAt(consumer.getConsumption(), row, 2);
            model.setValueAt(consumer.getProcessingTime(), row, 3);
            model.setValueAt(consumer.getStartTime(), row, 4);
            model.setValueAt(consumer.getEndTime(), row, 5);
        } else {
            Object[] rowData = {
                    consumerID,
                    consumer.getStatus(),
                    consumer.getConsumption(),
                    consumer.getProcessingTime(),
                    consumer.getStartTime(),
                    consumer.getEndTime()
            };
            model.addRow(rowData);
            consumerRows.put(consumerID, model.getRowCount() - 1);
        }
    }



    public void clearConsumers(){
        this.model.setRowCount(0);
        this.consumerRows.clear();
    }
}
