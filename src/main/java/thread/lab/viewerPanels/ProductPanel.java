package thread.lab.viewerPanels;

import lombok.Getter;
import lombok.Setter;
import thread.lab.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ProductPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final Map<String, Integer> productRows;

    public ProductPanel(){
        setLayout(new BorderLayout());
        setBackground(new Color(0x31241b));
        setBorder(BorderFactory.createLineBorder(new Color(0x5e9af7), 3));

         String[] columnNames = {"Product ID", "Quantity", "Max Quantity", "Min Quantity", "Quantity Consumed", "Quantity Produced", "Underflow", "Overflow", "State"};
         this.model = new DefaultTableModel(columnNames, 0);
         this.table = new JTable(model);
         this.table.setDefaultEditor(Object.class, null);

         JScrollPane scrollPane = new JScrollPane(table);
         add(scrollPane, BorderLayout.CENTER);

         productRows = new HashMap<>();
    }

    public void addOrUpdateProduct(Product product){
        String productID = product.getProductID();
        if (productRows.containsKey(productID)){
            int row = productRows.get(productID);
            model.setValueAt(product.getQuantity(), row,1);
            model.setValueAt(product.getMaxQuantity(), row,2);
            model.setValueAt(product.getMinQuantity(), row,3);
            model.setValueAt(product.getQuantityConsumed(), row,4);
            model.setValueAt(product.getQuantityProduced(), row,5);
            model.setValueAt(product.getUnderflow(), row,6);
            model.setValueAt(product.getOverflow(), row,7);
            model.setValueAt(product.getState(), row,8);
        } else {
            Object[] rowData ={
                    productID,
                    product.getQuantity(),
                    product.getMaxQuantity(),
                    product.getMinQuantity(),
                    product.getQuantityConsumed(),
                    product.getQuantityProduced(),
                    product.getUnderflow(),
                    product.getOverflow(),
                    product.getState()
            };
            model.addRow(rowData);
            productRows.put(productID, model.getRowCount() - 1);
        }
    }

    public void clearProducts(){
        this.model.setRowCount(0);
        this.productRows.clear();
    }
}
