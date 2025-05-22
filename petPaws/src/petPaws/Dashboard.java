package petPaws;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard(String role) {
        setTitle("Dashboard - Pet Supply");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4, 10, 10)); 

        JButton addSupplyButton = new JButton("Add Supplies");
        addSupplyButton.addActionListener(e -> new AddSupply());
        panel.add(addSupplyButton);

        JButton viewSupplyButton = new JButton("View Supplies");
        viewSupplyButton.addActionListener(e -> new ViewSupplies()); 
        panel.add(viewSupplyButton);

        JButton searchSupplyButton = new JButton("Search Supplies");
        searchSupplyButton.addActionListener(e -> new SearchSupply());
        panel.add(searchSupplyButton);

        JButton addCashierButton = new JButton("Add Cashier");
        addCashierButton.addActionListener(e -> new AddCashier()); 
        panel.add(addCashierButton);

        add(panel, BorderLayout.NORTH);
        JLabel welcomeLabel = new JLabel("Welcome to the Pet Supply System!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        setVisible(true);
    }
}
