package AimsProject.hust.soict.ict.aims.screen;

import AimsProject.hust.soict.ict.aims.media.DigitalVideoDisc;
import AimsProject.hust.soict.ict.aims.media.Media;
import AimsProject.hust.soict.ict.aims.store.Store;

import javax.swing.JTextField;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField directorField = new JTextField(20);
    private JTextField lengthField = new JTextField(20);

    public AddDigitalVideoDiscToStoreScreen(Store store, Runnable afterAdd) {
        super("Add DVD", store, afterAdd);
        addField("Director", directorField);
        addField("Length", lengthField);
        finishLayout();
    }

    @Override
    protected Media createMedia() {
        String title = titleField.getText().trim();
        String category = categoryField.getText().trim();
        double cost = getCost();
        String director = directorField.getText().trim();

        int length = 0;
        if (!lengthField.getText().trim().isEmpty()) {
            length = Integer.parseInt(lengthField.getText().trim());
        }

        return new DigitalVideoDisc(title, category, cost, length, director);
    }
}