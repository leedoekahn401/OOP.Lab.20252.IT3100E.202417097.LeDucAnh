package AimsProject.hust.soict.ict.aims.screen;

import AimsProject.hust.soict.ict.aims.media.CompactDisc;
import AimsProject.hust.soict.ict.aims.media.Media;
import AimsProject.hust.soict.ict.aims.store.Store;

import javax.swing.JTextField;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField artistField = new JTextField(20);
    private JTextField directorField = new JTextField(20); // Added director field

    public AddCompactDiscToStoreScreen(Store store, Runnable afterAdd) {
        super("Add CD", store, afterAdd);
        addField("Artist", artistField);
        addField("Director", directorField);
        finishLayout();
    }

    @Override
    protected Media createMedia() {
        return new CompactDisc(
                titleField.getText().trim(),
                categoryField.getText().trim(),
                getCost(),
                directorField.getText().trim(),
                artistField.getText().trim()
        );
    }
}