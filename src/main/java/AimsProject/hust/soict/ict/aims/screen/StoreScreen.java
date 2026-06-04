package AimsProject.hust.soict.ict.aims.screen;

import AimsProject.hust.soict.ict.aims.cart.Cart;
import AimsProject.hust.soict.ict.aims.media.*;
import AimsProject.hust.soict.ict.aims.store.Store;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    private JPanel centerPanel;

    JPanel createNorth(){
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update");

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookToStoreScreen(store, () -> refreshCenter());
            }
        });

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompactDiscToStoreScreen(store, () -> refreshCenter());
            }
        });

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming AddDigitalVideoDiscToStoreScreen follows the same architecture
                new AddDigitalVideoDiscToStoreScreen(store, () -> refreshCenter());
            }
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View Store");
        JMenuItem viewCart = new JMenuItem("View Cart");

        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });

        menu.add(viewStore);
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartBtn = new JButton("View cart");
        cartBtn.setPreferredSize(new Dimension(100, 50));
        cartBtn.setMaximumSize(new Dimension(100, 50));

        cartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartScreen(cart);
            }
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartBtn);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter(){
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for(Media media : mediaInStore){
            MediaStore cell = new MediaStore(media, this.cart);
            center.add(cell);
        }
        return center;
    }


    public void refreshCenter() {
        remove(centerPanel);
        centerPanel = createCenter();
        add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);

        // Save component reference layout locally
        centerPanel = createCenter();
        cp.add(centerPanel, BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Store store = new Store();
            Cart cart = new Cart();

            // ==================== DUMMY DATA FOR TESTING ====================
            // Adding DVDs (Playable)
            DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", 19.95, 88, "Roger Allers");
            DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Sci-Fi", 24.95, 124, "George Lucas");
            DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99, 90, "John Musker");

            // Adding Books (Non-playable)
            Book book1 = new Book("Design Patterns", "Computer Science", 54.99);
            book1.addAuthor("Erich Gamma");
            book1.addAuthor("Richard Helm");

            Book book2 = new Book("Effective Java", "Computer Science", 45.00);
            book2.addAuthor("Joshua Bloch");

            Book book3 = new Book("Clean Code", "Software Engineering", 42.50);
            book3.addAuthor("Robert C. Martin");

            // Adding Compact Discs (Playable)
            CompactDisc cd1 = new CompactDisc("Thriller", "Pop", 15.00, "Michael Jackson", "Michael Jackson");
            Track track1 = new Track("Billie Jean", 294);
            Track track2 = new Track("Beat It", 258);
            cd1.addTrack(track1);
            cd1.addTrack(track2);

            CompactDisc cd2 = new CompactDisc("Abbey Road", "Rock", 20.00, "The Beatles", "The Beatles");
            Track track3 = new Track("Come Together", 259);
            cd2.addTrack(track3);

            CompactDisc cd3 = new CompactDisc("Random Access Memories", "Electronic", 16.50, "Daft Punk", "Daft Punk");
            Track track4 = new Track("Get Lucky", 369);
            cd3.addTrack(track4);

            // Adding all elements to the store using your store's addition method
            // Adjust the method name (e.g., addMedia) to match your Store class implementation
            store.addMedia(dvd1);
            store.addMedia(book1);
            store.addMedia(cd1);
            store.addMedia(dvd2);
            store.addMedia(book2);
            store.addMedia(cd2);
            store.addMedia(dvd3);
            store.addMedia(book3);
            store.addMedia(cd3);
            // =================================================================
            new StoreScreen(store, cart);
        });
    }
}