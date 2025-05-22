package petPaws;
import javax.swing.*;
import java.io.*;

public class AddCashier extends JFrame {
    public AddCashier() {
        setTitle("Add Cashier");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton addButton = new JButton("Add Cashier");
        addButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields!");
            } else {
                try (PrintWriter writer = new PrintWriter(new FileWriter("src/users.txt", true))) {
                    writer.println(username + "," + password + ",Cashier");
                    JOptionPane.showMessageDialog(this, "Cashier added successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving cashier details!");
                }
            }
        });
        panel.add(addButton);

        add(panel);
        setVisible(true);
    }
}

