package petPaws;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Username Field
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);

       
        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        
        panel.add(new JLabel("Role:"));
        JRadioButton adminRadio = new JRadioButton("Admin");
        JRadioButton cashierRadio = new JRadioButton("Cashier");
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadio);
        roleGroup.add(cashierRadio);
        panel.add(adminRadio);
        panel.add(cashierRadio);

        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = adminRadio.isSelected() ? "Admin" : cashierRadio.isSelected() ? "Cashier" : null;

            if (role == null) {
                JOptionPane.showMessageDialog(this, "Please select a role!");
            } else if (authenticate(username, password, role)) {
                new Dashboard(role);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        });
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private boolean authenticate(String username, String password, String role) {
        
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File("src/users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] userDetails = scanner.nextLine().split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password) && userDetails[2].equals(role)) {
                    return true;
                }
            }
        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "User data file not found! Ensure 'users.txt' exists in the 'src' folder.");
        }
        return false;
    }

    public static void addUser(String username, String password, String role) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/users.txt", true))) {
            writer.println(username + "," + password + "," + role);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user details!");
        }
    }
}
