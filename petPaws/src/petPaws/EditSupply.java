package petPaws;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditSupply extends JFrame {
    public EditSupply(DefaultTableModel model, int rowIndex) {
        setTitle("Edit Supply");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

       
        panel.add(new JLabel("Supply ID:"));
        JTextField supplyIdField = new JTextField(model.getValueAt(rowIndex, 0).toString());
        panel.add(supplyIdField);

        panel.add(new JLabel("Supply Name:"));
        JTextField supplyNameField = new JTextField(model.getValueAt(rowIndex, 1).toString());
        panel.add(supplyNameField);

        panel.add(new JLabel("Price:"));
        JSpinner priceSpinner = new JSpinner(new SpinnerNumberModel(Double.parseDouble(model.getValueAt(rowIndex, 2).toString()), 0.0, 10000.0, 0.1));
        panel.add(priceSpinner);

        panel.add(new JLabel("Quantity:"));
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(Integer.parseInt(model.getValueAt(rowIndex, 3).toString()), 1, 1000, 1));
        panel.add(quantitySpinner);

        panel.add(new JLabel("Category:"));
        String[] categories = {"<Select a category>","Pet Toys", "Harnesses", "Cages", "Grooming Products", "Collars", "Other"};
        JComboBox<String> categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.setSelectedItem(model.getValueAt(rowIndex, 4).toString());
        panel.add(categoryDropdown);

        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            model.setValueAt(supplyIdField.getText(), rowIndex, 0);
            model.setValueAt(supplyNameField.getText(), rowIndex, 1);
            model.setValueAt(priceSpinner.getValue(), rowIndex, 2);
            model.setValueAt(quantitySpinner.getValue(), rowIndex, 3);
            model.setValueAt(categoryDropdown.getSelectedItem(), rowIndex, 4);
            JOptionPane.showMessageDialog(this, "Supply updated successfully.");
            dispose();
        });
        panel.add(saveButton);

        add(panel);
        setVisible(true);
    }
}
