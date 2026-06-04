package AimsProject.hust.soict.ict.aims.screen;

import AimsProject.hust.soict.ict.aims.media.Media;
import AimsProject.hust.soict.ict.aims.store.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Runnable afterAdd;
    protected JTextField titleField = new JTextField(20);
    protected JTextField categoryField = new JTextField(20);
    protected JTextField costField = new JTextField(20);
    private JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

    public AddItemToStoreScreen(String screenTitle, Store store, Runnable afterAdd) {
        this.store = store;
        this.afterAdd = afterAdd;

        setTitle(screenTitle);
        setLayout(new BorderLayout(8, 8));

        addField("Title", titleField);
        addField("Category", categoryField);
        addField("Cost", costField);
    }

    protected void finishLayout() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItemToStore());

        add(formPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    protected void addField(String label, JTextField field) {
        formPanel.add(new JLabel(label));
        formPanel.add(field);
    }

    private void addItemToStore() {
        try {
            Media media = createMedia();
            store.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added " + media.getTitle() + " to store.");
            if (afterAdd != null) {
                afterAdd.run();
            }
            dispose();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Invalid input", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected double getCost() {
        return Double.parseDouble(costField.getText().trim());
    }

    protected abstract Media createMedia();
}