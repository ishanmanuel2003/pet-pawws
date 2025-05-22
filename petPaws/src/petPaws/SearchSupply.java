package petPaws;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.*;
import java.util.*;

public class SearchSupply extends JFrame {
    public SearchSupply() {
        setTitle("Search Supply");
        setSize(350, 200); // Adjusted the frame size to a smaller, more compact layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Label
        JLabel categoryLabel = new JLabel("Category:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(categoryLabel, gbc);

        
        String[] categories = {"<Select a category>", "Pet Toys", "Harnesses", "Cages", "Grooming Products", "Collars", "Other"};
        JComboBox<String> categoryDropdown = new JComboBox<>(categories);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        panel.add(categoryDropdown, gbc);

        
        JButton searchButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0;
        panel.add(searchButton, gbc);

        searchButton.addActionListener(e -> {
            String selectedCategory = categoryDropdown.getSelectedItem().toString();
            List<String[]> results = searchSupplies(selectedCategory);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No supplies found for the selected category.");
            } else {
                showResultsInTable(results);
            }
        });

        add(panel);
        setVisible(true);
    }

    private List<String[]> searchSupplies(String category) {
        List<String[]> results = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/supplies.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data[4].equalsIgnoreCase(category)) {
                    results.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Supplies data file not found! Ensure 'supplies.txt' exists in the 'src' folder.");
        }
        return results;
    }

    private void showResultsInTable(List<String[]> results) {
        String[] columnNames = {"Supply ID", "Supply Name", "Price", "Quantity", "Category"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (String[] row : results) {
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame tableFrame = new JFrame("Search Results");
        tableFrame.setSize(500, 300); 
        tableFrame.add(scrollPane);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        tableFrame.setVisible(true);
    }
}
