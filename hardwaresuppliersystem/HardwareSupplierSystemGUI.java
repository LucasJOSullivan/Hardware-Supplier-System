package hardwaresuppliersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class HardwareSupplierSystemGUI extends JFrame implements ActionListener {

    JMenu fileMenu, stockMenu;
    JMenuItem item=null;
    
    stockItem s1 = new stockItem
    
    private final File file = new File("Mini-Project/hardwaresuppliersystem/stock.data");

    public HardwareSupplierSystemGUI( ) {
        super("Hardware Supplier System");

        Container pane = getContentPane();
        pane.setBackground(new Color(180,240,180));

        createFileMenu();
        createStockMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(stockMenu);

        ImageIcon icon = new ImageIcon("labsheet15/exercise1/bike.png");
        setIconImage(icon.getImage());

        setSize(400,400 );
        setVisible(true);
    }

    public static void main( String[] args ) {
        HardwareSupplierSystemGUI frame = new HardwareSupplierSystemGUI();
        ArrayList<stockItem> allStock = new ArrayList<stockItem>(Arrays.asList(s0));
    }


    private void createFileMenu(){

        fileMenu = new JMenu("File");

        String itemNames[] = {"New","Open","Save","Quit"};

        for(int i=0;i<itemNames.length;i++){
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);

            if(i==3)
                fileMenu.addSeparator();

            fileMenu.add(item);
        }
    }


    private void createItemMenu(){

        stockMenu = new JMenu("Stock");

        String itemNames[] = {"Add Item","Amend Item","Remove Item","View Items"};

        for(int i=0;i<itemNames.length;i++) {

            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);
            stockMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {

        int choice;

        if(e.getActionCommand().equals("New")) {
            createFile();
        }
        else if(e.getActionCommand().equals("Open")) {
            openFile();
        }
        else if(e.getActionCommand().equals("Save")) {
            saveFile();

        }
        else if(e.getActionCommand().equals("Quit")) {
            choice = JOptionPane.showConfirmDialog(null, "Do you wish to quit the application?",
                    "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);

            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Saving stock data before terminating",
                        "Saving File",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

        }
        else if(e.getActionCommand().equals("Add")) {
           addStockItem(ArrayList<stockItem> allStock);

           stockItem s = new stockItem(stockID, title, referralCode, type, price);

            String title = JOptionPane.showInputDialog("Enter the title of the new item.");
            
             if (title.length() == 0) {
            	 JOptionPane.showMessageDialog(null,"The title must contain at least one character.",
            	 "Empty Value",JOptionPane.ERROR_MESSAGE);
            	 title = JOptionPane.showInputDialog("Enter the title of the new item.");
            }
        else if (title.length() > 0) {
            	 JOptionPane.showMessageDialog(null,"Title assigned to new item",
                 "Title Assigned",JOptionPane.INFORMATION_MESSAGE);
            }

            String referralCode = JOptionPane.showInputDialog("Enter the reference code of the new item.");

             if (referralCode.length() < 2 && !referralCode.isLetter(referralCode.charAt(1))) {
            	JOptionPane.showMessageDialog(null,"The referral code must contain at least two characters, and the first character in the reference code must be a letter.",
            	"Invalid Value - Multiple Errors",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            }            
        else if (referralCode.length() < 2) {
            	JOptionPane.showMessageDialog(null,"The refferal code must contain at least two characters.",
            	"Invalid Length of Value",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            }
        else if (!referralCode.isLetter(referralCode.charAt(1))) {
            	JOptionPane.showMessageDialog(null,"The first character in the reference code must be a letter.",
            	"Invalid Opening Character",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            }
        else if (referralCode.length() > 1 && referralCode.isLetter(referenceCode.charAt(1))) {
            	JOptionPane.showMessageDialog(null,"Referral code assigned to new item",
                "Referral Code Assigned",JOptionPane.INFORMATION_MESSAGE);
                stockID++;
            }
          

            
            String type = JOptionPane.showInputDialog("Enter the type of the new item.");
             if (type.length() == 0) {
            	JOptionPane.showMessageDialog(null,"The referral code must contain at least two characters.","Empty Value",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the type of the new item.");
            }
        else if (type.length() > 0) {
            	JOptionPane.showMessageDialog(null,"Type assigned to new item",
                "Adding Stock",JOptionPane.INFORMATION_MESSAGE);
            }
        
            Double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the new item."));
             if (price.length() == 0) {
            	JOptionPane.showMessageDialog(null,"The value must contain at least one character.", "Empty Value",JOptionPane.ERROR_MESSAGE);
                value = JOptionPane.showInputDialog("Enter the price of the new item.");
            }
            
        else if (!Double.parseDouble(price)) {
            	JOptionPane.showMessageDialog(null,"The 'price' value must be translatable to a double (e.g. '0.00').","Invalid Value",JOptionPane.ERROR_MESSAGE);
                value = JOptionPane.showInputDialog("Enter the price of the new item.");
            }
        else if (price.length() > 0 && Double.ParseDouble(price)) {
            	JOptionPane.showMessageDialog(null,"Price assigned to new item",
                "Adding Stock",JOptionPane.INFORMATION_MESSAGE);
            }
            stockID++;
            
        allStock.add(s);
        JOptionPane.showMessageDialog(null,"Stock item created and added to array list!",
                "New Item Added",JOptionPane.INFORMATION_MESSAGE);
            }
        

        else if(e.getActionCommand().equals("Amend")){
        	         amendStockItem(ArrayList<stockItem> allStock);
        	         
        	          ArrayList<stockItem> foundStock = new ArrayList<stockItem>();
        	          int searchID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the stock item to be amended."));
        	          
        	          if (!searchID.isDigit()) {
        	          	JOptionPane.showMessageDialog (null,"The ID can be only an integer (e.g. '1').","Invalid Value",JOptionPane.ERROR_MESSAGE);
        	          	searchID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the stock to be amended."));
        	          }
        	          
        	          else {
        	          	        for(stockItem s: allStock)
            if(s.getStock().contains(searchID()))
                foundStock.add(s);

        String text="";

        for (StockItem st : foundStock)
            if (st != null) {
                text += st + "\n";
            }

        stockItem stockItemToAmend=null;

        for (stockItem st : foundStock)
            if (st != null && st.getId()==searchID)
                stockItemToAmend = st;

        Integer.parseInt(amendChoiceAsInt) = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");
        
        if (!Integer.parseInt(amendChoiceAsInt)){
        	JOptionPane.showMessageDialog (null,"The entered value is not an integer.","Invalid Value",JOptionPane.ERROR_MESSAGE);
        }
        
        else if (Integer.parseInt(amendChoiceAsInt) && ((amendChoiceAsInt < 1) || (amendChoiceAsInt > 4))){
        	JOptionPane.showMessageDialog (null,"The entered integer does not match that of any detail.","Invalid Integer",JOptionPane.ERROR_MESSAGE);
        Integer.parseInt(amendChoiceAsInt) = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");
        }

        
        else if (Integer.parseInt(amendChoiceAsInt) && amendChoiceAsInt == 1){
                
                String newTitle = JOptionPane.showInputDialog("Enter the amended title.");
                 if (newTitle.length() == 0) {
            	     JOptionPane.showMessageDialog("The title must contain at least one character.",
            	     "Empty Value",JOptionPane.ERROR_MESSAGE);
            	     newTitle = JOptionPane.showInputDialog("Enter the amended title.");
            }
            else if (newTitle.length() > 0) {
            	     JOptionPane.showMessageDialog(null,"The title has been amended.",
                     "Title Amended",JOptionPane.INFORMATION_MESSAGE);
            }

                stockItemToAmend.setTitle(newTitle);
        }
        else if (Integer.parseInt(amendChoiceAsInt) && amendChoiceAsInt == 2){
               
                String newReferralCode = JOptionPane.showInputDialog("Enter the amended referral code.");
                
             if (newReferralCode.length() < 2 && !newReferralCode.isLetter(referralCode.charAt(1))) {
            	JOptionPane.showMessageDialog("The referral code must contain at least two characters, and the first character in the referral code must be a letter.",
            	"Invalid Value - Multiple Errors",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the amended referral code.");
            }            
        else if (referralCode.length() < 2) {
            	JOptionPane.showMessageDialog("The referral code must contain at least two characters.",
            	"Invalid Length of Value",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the amended referral code.");
            }
        else if (!referralCode.isLetter(referralCode.charAt(1))) {
            	JOptionPane.showMessageDialog("The first character in the referral code must be a letter.",
            	"Invalid Opening Character",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the amended referral code.");
            }
        else if (referralCode.length() > 1 && referralCode.isLetter(referenceCode.charAt(1))) {
            	JOptionPane.showMessageDialog(null,"The referral code has been amended.",
                "Referral Code Amended",JOptionPane.INFORMATION_MESSAGE);
            }

                stockItemToAmend.setReferralCode(newReferralCode);
        }
        else if (Integer.parseInt(amendChoiceAsInt) && amendChoiceAsInt == 3){
                
                String newType = JOptionPane.showInputDialog("Please enter the new type.");
             if (type.length() == 0) {
            	JOptionPane.showMessageDialog("The referral code must contain at least two characters.","Empty Value",JOptionPane.ERROR_MESSAGE);
            	referralCode = JOptionPane.showInputDialog("Enter the type of the new item.");
            }
        else if (type.length() > 0) {
            	JOptionPane.showMessageDialog(null,"The item's type has been amended.",
                "Type Amended",JOptionPane.INFORMATION_MESSAGE);
            }

                stockItemToAmend.setType(newType);

        	          }
            	
        else if (Integer.parseInt(amendChoiceAsInt) && amendChoiceAsInt == 4){
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the amended price."));

                 if (newPrice.length() == 0) {
            	JOptionPane.showMessageDialog("The value must contain at least one character.", "Empty Value",JOptionPane.ERROR_MESSAGE);
                newPrice = JOptionPane.showInputDialog("Enter the amended price.");
            }
            
            else if (!Double.parseDouble(newPrice)) {
            	JOptionPane.showMessageDialog("The 'price' value must be translatable to a double (e.g. '0.00').","Invalid Value",JOptionPane.ERROR_MESSAGE);
                newPrice = JOptionPane.showInputDialog("Enter the amended price.");
            }

            else if (newPrice.length() > 0 && Double.parseDouble(newPrice)) {
            	JOptionPane.showMessageDialog(null,"The item's price has been amended.",
                "Price Amended",JOptionPane.INFORMATION_MESSAGE);
            } 
                 
            stockItemToAmend.setPrice(newPrice);

        	          }

        	}
        }

        else if(e.getActionCommand().equals("Remove")) {
       
removeStockItem(ArrayList<stockItem> allStock); 

        ArrayList<stockItem> foundStock = new ArrayList<stockItem>();


        for (stockItem st : foundStock)
            if (st != null) {
                text += st + "\n";
            }

        int searchID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the item to be removed."));

        stockItem stockItemToRemove=null;

        for (stockItem st : foundStock)
            if (st != null && st.getID()==searchID)
                stockItemToRemove = st;

        int removeChoice = JOptionPane.showConfirmDialog(null,"The details of the product you wish to amend are:\n\n" +
                stockItemToRemove + "\n\nDo you wish to remove this item?","Confirm Item Removal",JOptionPane.YES_NO_CANCEL_OPTION);

        if(removeChoice==JOptionPane.YES_OPTION) {
            allProducts.remove(productToRemove);
            JOptionPane.showMessageDialog(null, "Item removed from stock list.",
                    "Product Removed", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Item removal canceled.",
                    "Product Not Removed", JOptionPane.INFORMATION_MESSAGE);

        foundStock.clear();
      
       

        }
        
        else if (e.getActionCommand().equals("View")) {
        viewStock(ArrayList<stockItem> allStock);
        String allStockData="";
        stockItem st;

        Iterator<stockItem> iterator = allStock.iterator();

        while(iterator.hasNext()) {
            st = iterator.next();
            if (st != null)
                allStockData += st + "\n";
        }

        JOptionPane.showMessageDialog(null,allProductData,
                "List of stock",JOptionPane.INFORMATION_MESSAGE);
        
            JOptionPane.showMessageDialog(null,"Viewing stock data",
                    "Viewing Stock",JOptionPane.INFORMATION_MESSAGE);
        	          }
    }

    public static void createFile() {

        if(!file.exists())
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                JOptionPane.showMessageDialog(null, "New file created; new stock data will be saved to this file.",
                        "New File", JOptionPane.INFORMATION_MESSAGE);
                fileOutputStream.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be found!",
                        "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened!",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

        else
            JOptionPane.showMessageDialog(null, "File to store bicycle details already exists!",
                    "New File", JOptionPane.WARNING_MESSAGE);
    }

    public void openFile() {

        ObjectInputStream objectInputStream=null;

        if(!file.exists())
            createFile();
        else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);

                if(file.length()!= 0){
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    JOptionPane.showMessageDialog(null, "Opened the file that stores bicycle details",
                            "Opened File", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "Can't read file as it is empty!",
                            "Empty File", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened!",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

            if(objectInputStream==null)
                return;

            try{
                ArrayList<Stock> allStock = null;

                allStock = (ArrayList<StockItem>) objectInputStream.readObject();

                System.out.println(allStock);

                String stockData="";

                if(allStock!=null)
                    for(Bicycle bike: allStock)
                        bikeData+=bike + "\n";

                objectInputStream.close();

                JOptionPane.showMessageDialog(null, "Details of stock read from file are:\n\n" + stockData,
                        "Opened File", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be read.",
                        "Class Read Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not find appropriate class.",
                        "Class Search Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void saveFile() {
        if (!file.exists())
            createFile();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            ArrayList<Stock> allStock = new ArrayList<>();
            allStock.add(item1);
            allStock.add(item2);
            objectOutputStream.writeObject(allStock);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Saved all stock data.",
                    "Saved File", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File was not found.",
                    "File Search Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File was not written.",
                    "File Write Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}