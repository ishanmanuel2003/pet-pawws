package petPaws;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class ViewSupplies extends JFrame {
    public ViewSupplies() {
        setTitle("View Supplies");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Supply ID", "Supply Name", "Price", "Quantity", "Category"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        
        System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());

        try (Scanner scanner = new Scanner(new File("src/supplies.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                model.addRow(data);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Supply data file not found!");
            e.printStackTrace(); 
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, "South");

        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                new EditSupply(model, selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.removeRow(selectedRow);
                saveTableToFile(model);
                JOptionPane.showMessageDialog(this, "Supply deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });

        setVisible(true);
    }

    private void saveTableToFile(DefaultTableModel model) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/supplies.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.println(model.getValueAt(i, 0) + "," +
                               model.getValueAt(i, 1) + "," +
                               model.getValueAt(i, 2) + "," +
                               model.getValueAt(i, 3) + "," +
                               model.getValueAt(i, 4));
            }
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving supply details!");
            e.printStackTrace(); 
        }
    }
}
