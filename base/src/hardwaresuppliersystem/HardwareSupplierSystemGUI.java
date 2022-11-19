package hardwaresuppliersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class HardwareSupplierSystemGUI extends JFrame implements ActionListener {

    JMenu fileMenu, stockMenu;
    JMenuItem item = null;

    //Stock s0 = new Stock(1, "Hammer", "H1", "Handtool", 15);
    public ArrayList<Stock>allStock =new ArrayList<Stock>();
    /*private static File file = new File("Mini-Project/hardwaresuppliersystem/stock.data");*/
    private static File file = new File("./stock.data");

    public HardwareSupplierSystemGUI() {
        super("Hardware Supplier System");


        Container pane = getContentPane();
        pane.setBackground(new Color(180, 240, 180));

        createFileMenu();
        createItemMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(stockMenu);

        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        HardwareSupplierSystemGUI frame = new HardwareSupplierSystemGUI();
    }


    private void createFileMenu() {

        fileMenu = new JMenu("File");

        String itemNames[] = {"New", "Open", "Save", "Quit"};

        for (int i = 0; i < itemNames.length; i++) {
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);

            if (i == 3)
                fileMenu.addSeparator();

            fileMenu.add(item);
        }
    }


    private void createItemMenu() {

        stockMenu = new JMenu("Stock");

        String itemNames[] = {"Add Item", "Amend Item", "Remove Item", "View Items"};

        for (int i = 0; i < itemNames.length; i++) {

            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);
            stockMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {

        int choice;

        if (e.getActionCommand().equals("New")) {
            createFile();
        } else if (e.getActionCommand().equals("Open")) {
            openFile();
        } else if (e.getActionCommand().equals("Save")) {
            saveFile();
        } else if (e.getActionCommand().equals("Quit")) {
            int quit_choice = -1;
            int save_choice = -1;
            while(quit_choice != JOptionPane.NO_OPTION) {
                quit_choice = JOptionPane.showConfirmDialog(null, "Do you wish to quit the application?",
                        "Exiting Application", JOptionPane.YES_NO_OPTION);
                if (quit_choice == JOptionPane.YES_OPTION) {
                    save_choice = -1;
                    while(save_choice != JOptionPane.CANCEL_OPTION) {
                        save_choice = JOptionPane.showConfirmDialog(null, "Do you wish to save the stock data before quitting the application?",
                                "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (save_choice == JOptionPane.YES_OPTION){
                            JOptionPane.showMessageDialog(null, "Saving stock data before terminating",
                                    "Saving File", JOptionPane.INFORMATION_MESSAGE);
                            saveFile();
                            System.exit(0);
                        }
                        else if (save_choice == JOptionPane.NO_OPTION){
                            System.exit(0);
                        }
                    }

                }
            }



        } else if (e.getActionCommand().equals("Add Item")) {
            /*addStockItem(ArrayList < Stock > allStock);*/
            String title = "";
            String referralCode = "";
            String type = "";
            double value = 0;
            String valueS = "";

            Stock s = new Stock(title, referralCode, type, value);

            title = JOptionPane.showInputDialog("Enter the title of the new item.");
            if (title.length() == 0) {
                JOptionPane.showMessageDialog(null, "The title must contain at least one character.",
                        "Empty Entry", JOptionPane.ERROR_MESSAGE);
                title = JOptionPane.showInputDialog("Enter the title of the new item.");
            } else if (title.length() > 0) {
                JOptionPane.showMessageDialog(null, "Title assigned to new item",
                        "Title Assigned", JOptionPane.INFORMATION_MESSAGE);
                s.setTitle(title);
            }

            /*String referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");*/
            referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            char chTest = referralCode.charAt(1);

            if ((referralCode.length() < 2) && (!Character.isLetter(chTest))) {
                JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters, and the first character in the reference code must be a letter.",
                        "Invalid Entry - Multiple Errors", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            } else if (referralCode.length() < 2) {
                JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters.",
                        "Invalid Length of Entry", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            } else if (!Character.isLetter(chTest)) {
                JOptionPane.showMessageDialog(null, "The first character in the reference code must be a letter.",
                        "Invalid Opening Character", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            } else if (referralCode.length() > 1 && (Character.isLetter(chTest))) {
                JOptionPane.showMessageDialog(null, "Referral code assigned to new item",
                        "Referral Code Assigned", JOptionPane.INFORMATION_MESSAGE);
                s.setReferralCode(referralCode);
            }


            type = JOptionPane.showInputDialog("Enter the type of the new item.");
            if (type.length() == 0) {
                JOptionPane.showMessageDialog(null, "The type code must contain at least two characters.", "Empty Entry", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the type of the new item.");
            } else if (type.length() > 0) {
                JOptionPane.showMessageDialog(null, "Type assigned to new item",
                        "Adding Stock", JOptionPane.INFORMATION_MESSAGE);
                s.setType(type);
            }

            valueS = (JOptionPane.showInputDialog("Enter the value of the new item."));
            try {
                if (valueS.length() == 0) {
                    JOptionPane.showMessageDialog(null, "The value must contain at least one digit.", "Empty Entry", JOptionPane.ERROR_MESSAGE);
                    valueS = JOptionPane.showInputDialog("Enter the value of the new item.");
                } else if (valueS.length() > 0) {
                    value = Double.parseDouble(valueS);
                    s.setValue(value);
                }
            } catch (Exception failValue) {
                JOptionPane.showMessageDialog(null, "The value must be translatable to a double (e.g. '0.00,' '11.34,' '275.82' etc.).", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                valueS = JOptionPane.showInputDialog("Enter the price of the new item.");
            }


            allStock.add(s);
            JOptionPane.showMessageDialog(null, "Stock item created and added to array list!",
                    "New Item Added", JOptionPane.INFORMATION_MESSAGE);
        }

            else if (e.getActionCommand().equals("Amend Item")) {
            //amendStock(ArrayList < Stock > allStock);

            ArrayList<Stock> foundStock = new ArrayList<Stock>();
            String searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be amended.");

            try {
                int searchIDNum = Integer.parseInt(searchID);
                for (Stock s : allStock) {
                    if ((searchIDNum) == (s.getStockID())) {
                        foundStock.add(s);
                        choice = JOptionPane.showConfirmDialog(null, "Do you wish to amend this stock item?" + s.toString(), "Confirm / Deny Stock Amendment", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            String amendChoice = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");
                            try {
                                int amendChoiceI = Integer.parseInt(amendChoice);

                                if ((amendChoiceI < 1) || (amendChoiceI > 4)) {
                                    JOptionPane.showMessageDialog(null, "The selection is not listed.", "Unlisted Selection", JOptionPane.ERROR_MESSAGE);
                                } else if (amendChoiceI == 1) {
                                    //Title
                                    String title = JOptionPane.showInputDialog("Enter the title of the new item.");
                                    if (title.length() == 0) {
                                        JOptionPane.showMessageDialog(null, "The title must contain at least one character.",
                                                "Empty Entry", JOptionPane.ERROR_MESSAGE);
                                        title = JOptionPane.showInputDialog("Enter the title of the new item.");
                                    } else if (title.length() > 0) {
                                        JOptionPane.showMessageDialog(null, "Title assigned to new item",
                                                "Title Assigned", JOptionPane.INFORMATION_MESSAGE);
                                        s.setTitle(title);
                                    }
                                } else if (amendChoiceI == 2) {
                                    //Referral code
                                    String referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                    char chTest = referralCode.charAt(1);

                                    if ((referralCode.length() < 2) && (!Character.isLetter(chTest))) {
                                        JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters, and the first character in the reference code must be a letter.",
                                                "Invalid Entry - Multiple Errors", JOptionPane.ERROR_MESSAGE);
                                        referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                    } else if (referralCode.length() < 2) {
                                        JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters.",
                                                "Invalid Length of Entry", JOptionPane.ERROR_MESSAGE);
                                        referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                    } else if (!Character.isLetter(chTest)) {
                                        JOptionPane.showMessageDialog(null, "The first character in the reference code must be a letter.",
                                                "Invalid Opening Character", JOptionPane.ERROR_MESSAGE);
                                        referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                    } else if (referralCode.length() > 1 && (Character.isLetter(chTest))) {
                                        JOptionPane.showMessageDialog(null, "Referral code assigned to new item",
                                                "Referral Code Assigned", JOptionPane.INFORMATION_MESSAGE);
                                        s.setReferralCode(referralCode);
                                    }
                                } else if (amendChoiceI == 3) {

                                    String type = JOptionPane.showInputDialog("Enter the type of the new item.");
                                    if (type.length() == 0) {
                                        JOptionPane.showMessageDialog(null, "The type code must contain at least two characters.", "Empty Entry", JOptionPane.ERROR_MESSAGE);
                                        type = JOptionPane.showInputDialog("Enter the type of the new item.");
                                    } else if (type.length() > 0) {
                                        JOptionPane.showMessageDialog(null, "Type assigned to new item",
                                                "Adding Stock", JOptionPane.INFORMATION_MESSAGE);
                                        s.setType(type);
                                    }
                                } else if (amendChoiceI == 4) {
                                    //Referral code
                                    String valueS = (JOptionPane.showInputDialog("Enter the value of the new item."));
                                    try {
                                        if (valueS.length() == 0) {
                                            JOptionPane.showMessageDialog(null, "The value must contain at least one digit.", "Empty Entry", JOptionPane.ERROR_MESSAGE);
                                            valueS = JOptionPane.showInputDialog("Enter the value of the new item.");
                                        } else if (valueS.length() > 0) {
                                            double value = Double.parseDouble(valueS);
                                            s.setValue(value);
                                        }
                                    } catch (Exception failValue) {
                                        JOptionPane.showMessageDialog(null, "The value must be translatable to a double (e.g. '0.00,' '11.34,' '275.82' etc.).", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                                        valueS = JOptionPane.showInputDialog("Enter the price of the new item.");
                                    }
                                }
                            }



                               /* String newTitle = JOptionPane.showInputDialog("Enter the amended title.");
                                if (newTitle.length() == 0) {
                                    JOptionPane.showMessageDialog("The title must contain at least one character.", "Empty Value", JOptionPane.ERROR_MESSAGE);
                                    newTitle = JOptionPane.showInputDialog("Enter the amended title.");
                                } else if (newTitle.length() > 0) {
                                    JOptionPane.showMessageDialog(null, "The title has been amended.",
                                            "Title Amended", JOptionPane.INFORMATION_MESSAGE);
                                }*/ catch (Exception failAmend) {
                                JOptionPane.showMessageDialog(null, "The entry is not an integer.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                                amendChoice = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");
                            }


                    } else if (choice == JOptionPane.NO_OPTION) {
                        searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be amended.");
                    } else if ((searchIDNum) != (s.getStockID())) {
                        JOptionPane.showMessageDialog(null, "The ID you searched for has not been found.", "Failed to find ID", JOptionPane.ERROR_MESSAGE);
                        searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be amended.");
                    }
                    }
                }

            }
                catch (Exception failID){
                JOptionPane.showMessageDialog(null, "The ID can be only an integer (e.g. '1').", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be amended.");
            }

        }


        else if (e.getActionCommand().equals("Remove Item")) {
            String searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be Removed.");

            try {
                int searchIDNum = Integer.parseInt(searchID);
                for (Stock s : allStock) {
                    //if (Integer.parseInt(s.getStock().contains(searchID())))
                    if ((searchIDNum) == s.getStockID()) {
                        //foundStock.add(s);
                        // Check if user wants to remove this record
                        choice = JOptionPane.showConfirmDialog(null, "Do you wish to remove this Stock item? " + s.toString(),
                                "Confirm Stock Item to Amend", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {

                            allStock.remove(s);
                            JOptionPane.showMessageDialog(null, "Item removed from stock list.",
                                    "Product Removed", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Item removal canceled.",
                                    "Product Not Removed", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }
            }
            catch (Exception failID){
                JOptionPane.showMessageDialog(null, "The ID can be only an integer (e.g. '1').", "Invalid Value", JOptionPane.ERROR_MESSAGE);
                searchID = JOptionPane.showInputDialog("Enter the ID of the stock to be removed.");
            }
        }
                /*String text = "";

                for (Stock st : foundStock){
                    if (st != null) {
                        text += st + "\n";
                    }
                    }

                  Stock stockToAmend = null;
                for (Stock st : foundStock){
                    if (st != null && st.getSearchId() == searchID)
                        stockToAmend = st;
                    }

                /*String amendChoiceAsInt = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");

                try {
                    Integer.parseInt(amendChoiceAsInt);
                    String newTitle = JOptionPane.showInputDialog("Enter the amended title.");
                    if (newTitle.length() == 0) {
                        JOptionPane.showMessageDialog("The title must contain at least one character.", "Empty Value", JOptionPane.ERROR_MESSAGE);
                        newTitle = JOptionPane.showInputDialog("Enter the amended title.");
                    } else if (newTitle.length() > 0) {
                        JOptionPane.showMessageDialog(null, "The title has been amended.",
                                "Title Amended", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
               catch (Exception failAmend) {
               JOptionPane.showMessageDialog(null, "The entry is not an integer.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
               amendChoiceAsInt = JOptionPane.showInputDialog("Select a detail to modify by entering the matching integer: \n1. Title \n2. Referral Code \n3. Type \n4 Price");
               }


             }



 /*         else if (e.getActionCommand().equals("Remove")) {

            removeStockItem(ArrayList < stockItem > allStock);

            ArrayList<Stock> foundStock = new ArrayList<stockItem>();


            for (Stock st : foundStock) {
                if (st != null) {
                    text += st + "\n";
                }
            }

            int searchID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the item to be removed."));

            Stock stockItemToRemove = null;

            for (Stock st : foundStock) {
                if (st != null && st.getID() == searchID) {
                    stockItemToRemove = st;
                }
            }

            int removeChoice = JOptionPane.showConfirmDialog(null, "The details of the product you wish to amend are:\n\n" +
                    stockItemToRemove + "\n\nDo you wish to remove this item?", "Confirm Item Removal", JOptionPane.YES_NO_CANCEL_OPTION);

            if (removeChoice == JOptionPane.YES_OPTION) {
                allProducts.remove(productToRemove);
                JOptionPane.showMessageDialog(null, "Item removed from stock list.",
                        "Product Removed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Item removal canceled.",
                        "Product Not Removed", JOptionPane.INFORMATION_MESSAGE);
            }

            foundStock.clear();

        }
        */
        else if(e.getActionCommand().equals("View Items")) {
        //viewStock(ArrayList < Stock > allStock);
        String allStockData = "";
        Stock st;

        Iterator<Stock> iterator = allStock.iterator();

        while (iterator.hasNext()) {
            st = iterator.next();
            if (st != null)
                allStockData += st + "\n";
        }

        JOptionPane.showMessageDialog(null, allStockData,
                "List of stock", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Viewing stock data",
                "Viewing Stock", JOptionPane.INFORMATION_MESSAGE);
    }

}

    public void createFile() {

        if(!file.exists())
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                JOptionPane.showMessageDialog(null, "New file created; new stock data will be saved to this file.",
                        "New File", JOptionPane.INFORMATION_MESSAGE);
                fileOutputStream.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be found.",
                        "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened.",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

        else
            JOptionPane.showMessageDialog(null, "File to store stock information already exists.",
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
                    JOptionPane.showMessageDialog(null, "Opened stock information file.",
                            "Opened File", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "Cannot read file as it is empty.",
                            "Empty File", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened.",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

            if(objectInputStream==null)
                return;

            try{
                allStock = (ArrayList<Stock>) objectInputStream.readObject();

                System.out.println(allStock);

                String stockData="";

                if(allStock!=null)
                        for(Stock item: allStock)
                        stockData+=item + "\n";

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


