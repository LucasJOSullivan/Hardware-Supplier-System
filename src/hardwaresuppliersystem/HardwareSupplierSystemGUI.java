package hardwaresuppliersystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class HardwareSupplierSystemGUI extends JFrame implements ActionListener {

    JMenu fileMenu, stockMenu, accountMenu/*, authentication*/;
    JMenuItem item = null;

    //Stock s0 = new Stock(1, "Hammer", "H1", "Handtool", 15);
    public ArrayList<Stock> allStock = new ArrayList<Stock>();
    /*private static File file = new File("Mini-Project/hardwaresuppliersystem/stock.data");*/
    private static File file = new File("./stock.data");
    /*
    File inUserFile = new File ("./Username.txt");
    File outUserFile = new File ("./Username.txt");
    File inPassFile = new File ("./Password.txt");
    File outPassFile = new File ("./Password.txt");
    */
    File userFile = new File ("./Username.txt");
    File passFile = new File ("./Password.txt");

    public HardwareSupplierSystemGUI() {
        super("Hardware Supplier System");


        Container pane = getContentPane();
        pane.setBackground(new Color(180, 240, 180));

        createFileMenu();
        createItemMenu();
        createAccountMenu();
        /*
        createAuthentication();
        */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(stockMenu);
        menuBar.add(accountMenu);

        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        HardwareSupplierSystemGUI frame = new HardwareSupplierSystemGUI();
    }


    private void createFileMenu() {

        fileMenu = new JMenu("File");

        String itemNames[] = {"New", "Open", "Save", "Toggle Autosave", "Quit"};

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

    private void createAccountMenu() {
        accountMenu = new JMenu("Account");

        String itemNames[] = {"Login", "Logout", "Change Username", "Change Password"};

        for (int i = 0; i < itemNames.length; i++) {

            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);
            accountMenu.add(item);
        }
    }

    /*
    private void createAuthentication() {
        if (logged_in == true) {
            authentication.add("Logged In");
            authentication.remove("Logged Out");
        }
        else {
            authentication.remove("Logged In");
            authentication.add("Logged Out");
        }
    }
    */
    String  username = "",
            password = "",
            username_guess = "",
            password_guess = "",
            username_external,
            password_external;
    boolean logged_in = false,
            autosave = true;
    public void actionPerformed(ActionEvent e) {
        int choice;

        if (e.getActionCommand().equals("New")) {
            if (logged_in == true) {
                createFile();
            }
            else {
                JOptionPane.showMessageDialog(null,"You are logged out; you must be logged in to create a new file.","Login Required",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Open")) {
                openFile();
        } else if (e.getActionCommand().equals("Save")) {
            if (logged_in == true) {
                saveFile();
            }
            else {
                JOptionPane.showMessageDialog(null,"You are logged out; you must be logged in to save a file.","Login Required",JOptionPane.ERROR_MESSAGE);
            }
        }   else if (e.getActionCommand().equals("Toggle Autosave")) {
            int toggle_choice = -1;
            if (autosave == true) {
                toggle_choice = JOptionPane.showConfirmDialog(null,"The autosave function is currently enabled; do you wish to disable it?", "Toggle Autosave (Currently Enabled)?",JOptionPane.YES_NO_OPTION);
                if (toggle_choice == JOptionPane.YES_OPTION){
                    autosave = false;
                    JOptionPane.showMessageDialog(null,"The autosave function has been disabled.","Autosave Disabled", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                toggle_choice = JOptionPane.showConfirmDialog(null,"The autosave function is currently disabled; do you wish to enable it?", "Toggle Autosave (Currently Disabled)?",JOptionPane.YES_NO_OPTION);
                if (toggle_choice == JOptionPane.YES_OPTION) {
                    autosave = true;
                    JOptionPane.showMessageDialog(null,"The autosave function has been enabled.","Autosave Enabled", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else if (e.getActionCommand().equals("Quit")) {
            int quit_choice = -1;
            int save_choice = -1;
            while (quit_choice != JOptionPane.NO_OPTION) {
                if (logged_in == true) {
                    quit_choice = JOptionPane.showConfirmDialog(null, "Do you wish to exit the application?",
                            "Exit Application?", JOptionPane.YES_NO_OPTION);
                    if (quit_choice == JOptionPane.YES_OPTION) {
                        save_choice = -1;
                        while ((save_choice != JOptionPane.CANCEL_OPTION)) {
                            save_choice = JOptionPane.showConfirmDialog(null, "Do you wish to save the stock data before exiting the application?",
                                    "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);
                            if (save_choice == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Saving stock data before terminating",
                                        "Saving File", JOptionPane.INFORMATION_MESSAGE);
                                saveFile();
                                System.exit(0);
                            } else if (save_choice == JOptionPane.NO_OPTION) {
                                System.exit(0);
                            }
                        }
                    }
                } else {
                    quit_choice = JOptionPane.showConfirmDialog(null, "Do you wish to exit the application? Note that as you are logged out, you may not save data on exit.",
                            "Exit Application (Logged Out)?", JOptionPane.YES_NO_OPTION);
                    if (quit_choice == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(null,"Exiting application without saving.","Exiting Application",JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }
            }
        } else if (e.getActionCommand().equals("Add Item")) {
            if (logged_in == true) {

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
            char chTest = referralCode.charAt(0);

            if ((referralCode.length() < 2) && (!Character.isLetter(chTest))) {
                JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters, and the first character in the referral code must be a letter.",
                        "Invalid Entry - Multiple Errors", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            } else if (referralCode.length() < 2) {
                JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters.",
                        "Invalid Length of Entry", JOptionPane.ERROR_MESSAGE);
                referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
            } else if (!Character.isLetter(chTest)) {
                JOptionPane.showMessageDialog(null, "The first character in the referral code must be a letter.",
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
            if (autosave == true)
                autoSaveAddition();
            }
            else {
                JOptionPane.showMessageDialog(null, "You are logged out; you must be logged in to add a stock item.", "Login Required", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getActionCommand().equals("Amend Item")) {
            if (logged_in == true) {
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
                                            JOptionPane.showMessageDialog(null, "Title assigned to new item.",
                                                    "Title Assigned", JOptionPane.INFORMATION_MESSAGE);
                                            s.setTitle(title);
                                            if (autosave == true)
                                                autoSaveAmendment();
                                        }
                                    } else if (amendChoiceI == 2) {
                                        //Referral code
                                        String referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                        char chTest = referralCode.charAt(0);

                                        if ((referralCode.length() < 2) && (!Character.isLetter(chTest))) {
                                            JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters, and the first character in the referral code must be a letter.",
                                                    "Invalid Entry - Multiple Errors", JOptionPane.ERROR_MESSAGE);
                                            referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                        } else if (referralCode.length() < 2) {
                                            JOptionPane.showMessageDialog(null, "The referral code must contain at least two characters.",
                                                    "Invalid Length of Entry", JOptionPane.ERROR_MESSAGE);
                                            referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                        } else if (!Character.isLetter(chTest)) {
                                            JOptionPane.showMessageDialog(null, "The first character in the referral code must be a letter.",
                                                    "Invalid Opening Character", JOptionPane.ERROR_MESSAGE);
                                            referralCode = JOptionPane.showInputDialog("Enter the referral code of the new item.");
                                        } else if (referralCode.length() > 1 && (Character.isLetter(chTest))) {
                                            JOptionPane.showMessageDialog(null, "Referral code assigned to new item.",
                                                    "Referral Code Assigned", JOptionPane.INFORMATION_MESSAGE);
                                            s.setReferralCode(referralCode);
                                            if (autosave == true)
                                                autoSaveAmendment();
                                        }
                                    } else if (amendChoiceI == 3) {

                                        String type = JOptionPane.showInputDialog("Enter the type of the new item.");
                                        if (type.length() == 0) {
                                            JOptionPane.showMessageDialog(null, "The type code must contain at least two characters.", "Empty Entry", JOptionPane.ERROR_MESSAGE);
                                            type = JOptionPane.showInputDialog("Enter the type of the new item.");
                                        } else if (type.length() > 0) {
                                            JOptionPane.showMessageDialog(null, "Type assigned to new item.",
                                                    "Adding Stock", JOptionPane.INFORMATION_MESSAGE);
                                            s.setType(type);
                                            if (autosave == true)
                                                autoSaveAmendment();
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
                                                if (autosave == true)
                                                    autoSaveAmendment();
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

                } catch (Exception failID) {
                    JOptionPane.showMessageDialog(null, "The ID can be only an integer (e.g. '1').", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                    searchID = JOptionPane.showInputDialog("Enter the ID of the stock item to be amended.");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "You are logged out; you must be logged in to amend a stock item.", "Login Required", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Remove Item")) {
            if (logged_in == true){
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
                            if (autosave == true)
                                autoSaveRemoval();
                        } else {
                            JOptionPane.showMessageDialog(null, "Item removal canceled.",
                                    "Product Not Removed", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                }
            } catch (Exception failID) {
                JOptionPane.showMessageDialog(null, "The ID can be only an integer (e.g. '1').", "Invalid Value", JOptionPane.ERROR_MESSAGE);
                searchID = JOptionPane.showInputDialog("Enter the ID of the stock to be removed.");
            }
        }
            else {
                JOptionPane.showMessageDialog(null,"You are logged out; you must be logged in to remove a stock item.","Login Required",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("View Items")) {
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

        } else if (e.getActionCommand().equals("Login")) {
            readUserFile();
            readPassFile();
            if (logged_in == false) {
                username_guess = "i" + username;
                password_guess = "i" + password;
                while ((!username_guess.equals(username) || (!password_guess.equals(password)))) {
                    username_guess = JOptionPane.showInputDialog("Enter the correct username.");
                    while (!username_guess.equals(username)) {
                        JOptionPane.showMessageDialog(null, "Incorrect username entered.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
                        username_guess = JOptionPane.showInputDialog("Enter the correct username.");
                    }
                    password_guess = JOptionPane.showInputDialog("Enter the correct password.");
                    while (!password_guess.equals(password)){
                        JOptionPane.showMessageDialog(null, "Incorrect password entered.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                        password_guess = JOptionPane.showInputDialog("Enter the correct password.");
                    }
                }
                logged_in = true;
                JOptionPane.showMessageDialog(null,"You have successfully logged in.","Login Success", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "You are already logged in.", "Logged In", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Logout")) {
            if (logged_in == false){
                JOptionPane.showMessageDialog(null, "You are already logged out.", "Logged Out", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                int confirm_logout = JOptionPane.showConfirmDialog(null,"Are you sure you wish to logout?","Confirm Logout?",JOptionPane.YES_NO_OPTION);
                if (confirm_logout == JOptionPane.YES_OPTION) {
                    logged_in = false;
                    JOptionPane.showMessageDialog(null, "You have successfully logged out.", "Logout Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "The logout procedure has been canceled.", "Logout Canceled",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Change Username")) {
            readPassFile();
            password_guess = ("i" + password);
            while (!password_guess.equals(password)) {
                password_guess = JOptionPane.showInputDialog("Enter the current password.");
                if (!password_guess.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Incorrect password entered.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                }
            }
            username = JOptionPane.showInputDialog("Enter a new username of your choice.");
            if (username != null) {
                writeUserFile();
                JOptionPane.showMessageDialog(null, "The username has successfully been changed.", "Username Changed", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "As the username changing process was canceled, the username has not been changed.", "Username Change Canceled", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getActionCommand().equals("Change Password")) {
            readPassFile();
            password_guess = ("i" + password);
            while (!password_guess.equals(password)) {
                password_guess = JOptionPane.showInputDialog("Enter the current password.");
                if (!password_guess.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Incorrect password entered.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                }
            }
            password = JOptionPane.showInputDialog("Enter a new password of your choice.");
            if (password != null) {
                writePassFile();
                JOptionPane.showMessageDialog(null, "The password has successfully been changed.", "Password Changed", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "As the password changing process was canceled, the password has not been changed.", "Password Change Canceled", JOptionPane.INFORMATION_MESSAGE);

        }

    }

        public void createUserFile () {
            try {
                username_external = "Admin";
                FileOutputStream userStream = new FileOutputStream(userFile);
                byte[] userString = username_external.getBytes();
                userStream.write(userString);
                userStream.close();
                username = username_external;
            }
            catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Failed to create username file.","Username Creation Failure",JOptionPane.ERROR_MESSAGE);
            }
        }

        public void createPassFile () {
            try {
                password_external = "Passw";
                FileOutputStream passStream = new FileOutputStream(passFile);
                byte[] passString = password_external.getBytes();
                passStream.write(passString);
                passStream.close();
                password = password_external;
            }
            catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Failed to create password file.","Password Creation Failure",JOptionPane.ERROR_MESSAGE);
            }
        }
        public void readUserFile () {
            if (!userFile.exists()) {
                createUserFile();
            }
            else {
                try {
                    username_external = "";
                    //String userString = "";
                    FileReader userReader = new FileReader(userFile);
                    int characters;
                    //String[] userRead = new String[characters];
                    while ((characters = userReader.read()) != -1) {
                        username_external += (char)characters;
                    }
                    //username_external = String.fromCharCode(userString);
                    userReader.close();
                    username = username_external;
                }
                catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,"Failed to read username from file.","Username Read Failure",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        public void writeUserFile () {
            try {
                username_external = username;
                FileOutputStream userStream = new FileOutputStream(userFile);
                byte[] userString = username_external.getBytes();
                userStream.write(userString);
                userStream.close();
            }
            catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Failed to write username to file.","Username Write Failure",JOptionPane.ERROR_MESSAGE);
            }
        }

        public void readPassFile () {
            if (!passFile.exists()) {
                createPassFile();
            }
            else {
                try {
                    password_external = "";
                    FileReader passReader = new FileReader(passFile);
                    int characters;
                    while ((characters = passReader.read()) != -1) {
                        password_external += (char)characters;
                    }
                    passReader.close();
                    password = password_external;
                }
                catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,"Failed to read password from file.","Password Read Failure",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        public void writePassFile () {
            try {
                password_external = password;
                FileOutputStream passStream = new FileOutputStream(passFile);
                byte[] passString = password_external.getBytes();
                passStream.write(passString);
                passStream.close();
            }
            catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,"Failed to write password to file.","Password Write Failure",JOptionPane.ERROR_MESSAGE);
            }
        }
        public void createFile () {

            if (!file.exists())
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    JOptionPane.showMessageDialog(null, "New file created; new stock data will be saved to this file.",
                            "New File", JOptionPane.INFORMATION_MESSAGE);
                    fileOutputStream.close();
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "File could not be found.",
                            "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "File could not be opened.",
                            "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
                }

            else
                JOptionPane.showMessageDialog(null, "File to store stock information already exists.",
                        "New File", JOptionPane.WARNING_MESSAGE);
        }
        public void openFile () {

            ObjectInputStream objectInputStream = null;

            if (!file.exists())
                createFile();
            else {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);

                    if (file.length() != 0) {
                        objectInputStream = new ObjectInputStream(fileInputStream);
                        JOptionPane.showMessageDialog(null, "Opened stock information file.",
                                "Opened File", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "Cannot read file as it is empty.",
                                "Empty File", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "File could not be opened.",
                            "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
                }

                if (objectInputStream == null)
                    return;

                try {
                    allStock = (ArrayList<Stock>) objectInputStream.readObject();

                    System.out.println(allStock);

                    String stockData = "";

                    if (allStock != null)
                        for (Stock item : allStock)
                            stockData += item + "\n";

                    objectInputStream.close();

                    JOptionPane.showMessageDialog(null, "Details of stock read from file are:\n\n" + stockData,
                            "Opened File", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioe) {
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
        public void saveFile () {
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

    public void autoSaveAddition () {
        if (!file.exists())
            createFile();
        else {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allStock);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null,"Automatically saved addition of item to file.","Autosaved Addition",JOptionPane.INFORMATION_MESSAGE);
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

    public void autoSaveAmendment () {
        if (!file.exists())
            createFile();
        else {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allStock);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Automatically saved amendment of item to file.", "Autosaved Amendment", JOptionPane.INFORMATION_MESSAGE);
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
    public void autoSaveRemoval () {
        if (!file.exists())
            createFile();
        else {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allStock);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Automatically saved removal of item from file.", "Autosaved Removal", JOptionPane.INFORMATION_MESSAGE);
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

}

