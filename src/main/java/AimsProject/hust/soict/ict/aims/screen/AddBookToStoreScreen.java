package AimsProject.hust.soict.ict.aims.screen;

import AimsProject.hust.soict.ict.aims.media.Book;
import AimsProject.hust.soict.ict.aims.media.Media;
import AimsProject.hust.soict.ict.aims.screen.AddItemToStoreScreen;
import AimsProject.hust.soict.ict.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store, Runnable afterAdd) {
        super("Add Book", store, afterAdd);
        finishLayout();
    }

    @Override
    protected Media createMedia() {
        return new Book(titleField.getText().trim(), categoryField.getText().trim(), getCost());
    }
}