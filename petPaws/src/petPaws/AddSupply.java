package petPaws;

import javax.swing.*;
import java.io.*;

public class AddSupply extends JFrame {
    public AddSupply() {
        setTitle("Add Supply");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

       
        panel.add(new JLabel("Supply ID:"));
        JTextField supplyIdField = new JTextField();
        panel.add(supplyIdField);

        panel.add(new JLabel("Supply Name:"));
        JTextField supplyNameField = new JTextField();
        panel.add(supplyNameField);

        panel.add(new JLabel("Price:"));
        JSpinner priceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10000.0, 0.1));
        panel.add(priceSpinner);

        panel.add(new JLabel("Quantity:"));
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        panel.add(quantitySpinner);

        panel.add(new JLabel("Category:"));
        String[] categories = {"<Select a category>","Pet Toys", "Harnesses", "Cages", "Grooming Products", "Collars", "Other"};
        JComboBox<String> categoryDropdown = new JComboBox<>(categories);
        panel.add(categoryDropdown);

        // Submit Button
        JButton submitButton = new JButton("Add Supply");
        submitButton.addActionListener(e -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("src/supplies.txt", true))) {
                writer.println(supplyIdField.getText() + "," +
                        supplyNameField.getText() + "," +
                        priceSpinner.getValue() + "," +
                        quantitySpinner.getValue() + "," +
                        categoryDropdown.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Supply Added Successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving supply details!");
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }
}
