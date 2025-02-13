package thread.lab.viewerPanels;

import lombok.Getter;
import lombok.Setter;
import thread.lab.Consumer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ConsumersPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final Map<String, Integer> consumerRows;

    public ConsumersPanel(){
        setLayout(new BorderLayout());
        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));

        String[] columnNames = {"Consumer ID", "Resource", "Consume delay", "Status", "Consumption", "Processing Time", "Start Time", "End Time"};
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
            model.setValueAt(consumer.getResourceBound(),row,1);
            model.setValueAt(consumer.getConsumeDelay(), row, 2);
            model.setValueAt(consumer.getStatus(), row, 3);
            model.setValueAt(consumer.getConsumption(), row, 4);
            model.setValueAt(consumer.getProcessingTime(), row, 5);
            model.setValueAt(consumer.getStartTime(), row, 6);
            model.setValueAt(consumer.getEndTime(), row, 7);
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
