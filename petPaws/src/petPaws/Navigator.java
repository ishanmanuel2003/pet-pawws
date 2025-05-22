package petPaws;
import javax.swing.*;
import java.awt.event.*;

public class Navigator extends JPanel {
    public Navigator(String role) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addButton("Add Supplies", "addSupplies");
        addButton("View Supplies", "viewSupplies");
        addButton("Search Supplies", "searchSupplies");

        if ("Admin".equals(role)) {
            addButton("Add Cashier", "addCashier");
        }
    }

    private void addButton(String label, String actionCommand) {
        JButton button = new JButton(label);
        button.setActionCommand(actionCommand);
        button.addActionListener(e -> handleNavigation(actionCommand));
        add(button);
    }

    private void handleNavigation(String actionCommand) {
        switch (actionCommand) {
            case "addSupplies":
                new AddSupply();
                break;
            case "viewSupplies":
                new ViewSupplies();
                break;
            case "searchSupplies":
                new SearchSupply();
                break;
            case "addCashier":
                new AddCashier();
                break;
        }
    }
}
