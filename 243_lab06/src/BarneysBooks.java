/**
 * @author: Jenny Zhen
 * @name: Book.java
 * @date: 04.23.12
 */

/**
 * Store.java
 * 
 * $Id: BarneysBooks.java,v 1.4 2012-04-30 01:36:19 jxz6853 Exp $
 * $Log: BarneysBooks.java,v $
 * Revision 1.4  2012-04-30 01:36:19  jxz6853
 * Fixed text areas to be of uniform height by putting them all into BorderLayouts rather than GridLayouts.
 *
 * Completed.
 *
 * Revision 1.3  2012-04-30 00:53:01  jxz6853
 * Completed.
 *
 * Revision 1.2  2012-04-30 00:46:15  jxz6853
 * Version numbers messed up after fixing repository.
 *
 * Revision 1.1  2012-04-30 00:39:19  jxz6853
 * Need commenting.
 *
 * Revision 1.3  2012-04-29 06:33:28  jxz6853
 * Fix selection after buy/rent.
 *
 * Revision 1.2  2012-04-28 18:23:45  jxz6853
 * Fixed Store.java to do GUI requirements.
 *
 * Revision 1.1  2012-04-26 17:16:58  jxz6853
 * Template.
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;   // JButton, JFrame
import java.awt.*;  // BorderLayout, GridLayout, Container
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BarneysBooks extends JFrame {
	private static final long serialVersionUID = 1L;
	private Store store;
	private Book bookFound;
	private JTextField searchBox;
	private ButtonGroup radios = new ButtonGroup();
	private JRadioButton title = new JRadioButton("Title");
    private JRadioButton author = new JRadioButton("Author");
    private JRadioButton media = new JRadioButton("Media Format");
    private JTextField titleF = new JTextField();
    private JTextField authorF = new JTextField();
    private JTextField mediaF = new JTextField();
    private JTextField priceF = new JTextField();
    private JButton buy = new JButton("Buy");
    private JButton rent = new JButton("Rent");
    private JTextField message = new JTextField(null);
    private JList bookList = new JList();
    private JScrollPane scrollPane = new JScrollPane(bookList);
	private ArrayList<Book> foundBookData;
	private String[] newBooks;
	private boolean removingBook = false;
    
	/**
	 * The constructor creates the GUI view and associated controller objects.
	 * @param aStore - the store model
	 * @throws IOException - if logo not found
	 */
	public BarneysBooks(Store aStore) throws IOException {
		store = aStore;
		
		//create and set up the window
        JFrame frame = new JFrame("Barney's Books");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //horizontal gap 5, vertical gap 10
        setLayout(new BorderLayout(5, 15));
        
        /***********************************************/
        //split north of main border layout
        JPanel panel1 = new JPanel();
        add(panel1, BorderLayout.NORTH);
        panel1.setLayout(new BorderLayout());
        
        //read in the logo image
        BufferedImage readLogo = ImageIO.read(new File("barneysbooks.png"));
        JLabel logo = new JLabel(new ImageIcon(readLogo));
        
        //adding the logo
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(0,0,25,0));
        northPanel.setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        northPanel.add(logo, BorderLayout.NORTH);
        panel1.add(northPanel, BorderLayout.NORTH);
        
        //create radio buttons
        radios.add(title);   radios.add(author);   radios.add(media);
        title.setFocusPainted(false);
        author.setFocusPainted(false);
        media.setFocusPainted(false);
        
        //adding the radio buttons 
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        centerPanel.setLayout(new GridLayout(1,3));
        centerPanel.add(title);
        centerPanel.add(author);
        centerPanel.add(media);
        panel1.add(centerPanel, BorderLayout.CENTER);
        
        //create search box and button
        searchBox = new JTextField();
        searchBox.setEditable(true);
        searchBox.setBackground(Color.white);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSearchData();
				titleF.setText(null);
				authorF.setText(null);
				mediaF.setText(null);
				priceF.setText(null);
				if(newBooks.length == 0) {
					message.setText("There are no books of that search criteria.");
					return;
				} else {
					message.setText(null);
				}
			}
		});
        
        
        
        //adding the search box and button
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout(0,5));
        southPanel.add(searchBox, BorderLayout.NORTH);
        southPanel.add(searchButton, BorderLayout.SOUTH);
        panel1.add(southPanel, BorderLayout.SOUTH);
        add(panel1, BorderLayout.NORTH);
        /***********************************************/
        //split center of main border layout
        JPanel center = new JPanel();
        center.setLayout(new GridLayout());
        
        //creating the selection box
        bookList.setVisibleRowCount(4);
        bookList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(removingBook)
					return;
				int index = bookList.getSelectedIndex();
				bookFound = foundBookData.get(index);
				titleF.setText(bookFound.getTitle());
				authorF.setText(bookFound.getAuthor());
				mediaF.setText(bookFound.getMedia());
				if(bookFound != null) {
					if(bookFound.isForSale()) {
						priceF.setText("US$ " + bookFound.getCost());
						rent.setEnabled(false);
						buy.setEnabled(true);
					} else if(!bookFound.isForSale()) {
						priceF.setText("US$ " + store.rentalPrice);
						buy.setEnabled(false);
						rent.setEnabled(true);
					} message.setText("");
				} else {
					return;
				}
			}
		});
        
        //adding the selection box
        center.add(scrollPane);
        add(center, BorderLayout.CENTER);
        /***********************************************/
        //split south of main border layout
        JPanel panel2 = new JPanel();
        add(panel2, BorderLayout.SOUTH);
        panel2.setLayout(new BorderLayout());
        
        //creating the text area labels
        JLabel title = new JLabel("Title:");
        JLabel author = new JLabel("Author:");
        JLabel media = new JLabel("Media Format:    ");
        JLabel price = new JLabel("Price:");
        
        //adding the text area labels
        JPanel westPanel2 = new JPanel();
        westPanel2.setLayout(new GridLayout(4,1,0,15));
        westPanel2.add(title);   westPanel2.add(author);
        westPanel2.add(media);   westPanel2.add(price);
        panel2.add(westPanel2, BorderLayout.WEST);
        
        //creating the text areas
        titleF.setEditable(false);
        titleF.setBackground(Color.white);
        authorF.setEditable(false);
        authorF.setBackground(Color.white);
        mediaF.setEditable(false);
        mediaF.setBackground(Color.white);
        priceF.setEditable(false);
        priceF.setBackground(Color.white);
        titleF.setPreferredSize(new Dimension(150,20));
        authorF.setPreferredSize(new Dimension(150,20));
        mediaF.setPreferredSize(new Dimension(150,20));
        priceF.setPreferredSize(new Dimension(150,20));
        
        //adding the text areas
        JPanel centerPanel2 = new JPanel();
        centerPanel2.setLayout(new GridLayout(4,1,0,15));
        centerPanel2.add(titleF);   centerPanel2.add(authorF);
        centerPanel2.add(mediaF);   centerPanel2.add(priceF);
        panel2.add(centerPanel2, BorderLayout.CENTER);
        
        //south of main border layout
        JPanel southPanel2 = new JPanel();
        southPanel2.setLayout(new GridLayout(2,1,0,5));
        
        //adding the buy/rent buttons
        JPanel buyPanel = new JPanel();
        buyPanel.setBorder(BorderFactory.createEmptyBorder(10,0,-5,0));
        buyPanel.setLayout(new FlowLayout());
        buyPanel.add(buy);
        buy.setEnabled(false);
        buyPanel.add(rent);
        rent.setEnabled(false);
        buy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(bookFound != null) {
	        		removingBook = true;
	        		message.setText("You have bought " + bookFound.getTitle() + 
	        				" for US$ " + bookFound.getCost());
	        		System.out.println(bookFound.getTitle() + " has been bought.");
	        		bookList.removeAll();
	        		store.removeBook(bookFound);
	        		foundBookData.remove(bookFound);
	        		bookFound = null;
	        		scrollPane.revalidate();
	        		updateSearchData();
        		} else {
        			message.setText("Sorry, the book has been bought already.");
        			return; 
        		}
        	}
        });
        rent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(bookFound != null) {
	        		removingBook = true;
	        		message.setText("You have rented " + bookFound.getTitle() + 
	        				" for US$ " + store.rentalPrice);
	        		System.out.println(bookFound.getTitle() + " has been rented.");
	        		bookList.removeAll();
	        		store.removeBook(bookFound);
	        		foundBookData.remove(bookFound);
	        		bookFound = null;
	        		scrollPane.revalidate();
	        		updateSearchData();
        		} else {
        			message.setText("Sorry, the book has been rented already.");
        			return; 
        		}
        	}
        });
        
        southPanel2.add(buyPanel);
        
        //adding the text area message (buy/rent status)
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout(1,1));
        //messagePanel.setMaximumSize(new Dimension(100,10));
        messagePanel.add(message, BorderLayout.SOUTH);
        message.setText(null);
        message.setEditable(false);
        message.setBackground(Color.white);
        southPanel2.add(messagePanel);
        
        //adding to main border layout
        panel2.add(southPanel2, BorderLayout.SOUTH);
        
        /***********************************************/

        //set the frame appearance
        pack();
        setTitle("Barney's Books");
        setSize(500, 550);
        setVisible(true);
	}
	
	/**
	 * Getting the search option.
	 * 't' for title search, 'a' for author, 'm' for media format.
	 * @return a char representing the type of search.
	 */
	protected char getSearchOption() {
		if(title.isSelected())
        	return 't';
        else if(author.isSelected())
        	return 'a';
        else
        	return 'm';
	}

	/**
	 * Build the list of books in the inventory.
	 * @param searchText - the title of books to search for
	 * @param searchOption - the format of the books
	 * @param store - the store object
	 * @return a list of the book titles
	 */
	public String[] buildBookList(String searchText, char searchOption, 
			Store store){
		
		int index = 0;
        String[] foundBooks = new String[store.listMatching
                        (searchText, searchOption).size()];
        foundBookData = store.listMatching(searchText, searchOption);
        for(Book book : foundBookData) {
        	foundBooks[index] = book.getTitle();
        	index += 1;
        } return foundBooks;
	}

	/**
	 * Main method gets the book inventory from the command line file name.
	 * @param args - must contain the name of the file that holds the inventory.
	 * @throws IOException - if logo not found
	 */
	public static void main(String[] args) throws IOException {
		if ( args.length != 1 ) {
            System.out.println( "Usage: java Store filename" );

        } else {
            Store store = new Store();
            store.fillInventory(args[0]);
            new BarneysBooks(store);
        }
	}
	
	/**
	 * Updates the books displayed after a book has been bought or rented.
	 */
	public void updateSearchData(){
		String searchText = searchBox.getText();
		char searchOption = getSearchOption();
		newBooks = buildBookList(searchText, searchOption, store);
		bookList.setListData(newBooks);
		removingBook = false;
	}
}