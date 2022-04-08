package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.*;
import exceptions.InputValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Animal;
import model.Boardgame;
import model.Figure;
import model.Puzzle;
import model.Toy;
import view.AppMenu;

/**
 * This program is used to run a store inventory, that can search/remove/add and save all stock
 * 
 *
 */
public class StockOrganizer {
  @FXML
  private TextField TextFeildName;// Search Tab

  @FXML
  private TextField TextFeildSN;// Search Tab

  @FXML
  private TextField TextFeildType;// Search Tab

  @FXML
  private Button btnBuy;// Search Tab

  @FXML
  private Button btnClear;// Search Tab

  @FXML
  private Button btnRemove;// Remove Tab

  @FXML
  private Button btnSearch;// Search Tab

  @FXML
  private Label lblName;// Search Tab

  @FXML
  private Label lblRemoveSN;// Remove Tab

  @FXML
  private Label lblSN;// Search Tab

  @FXML
  private Label lblType;// Search Tab

  @FXML
  private ListView<Toy> listviewToySearch;// Search Tab

  @FXML
  private ListView<String> lvRemoveToy;// Remove Tab

  @FXML
  private RadioButton rbName;// Search Tab

  @FXML
  private RadioButton rbSerialNum;// Search Tab

  @FXML
  private RadioButton rbType;// Search Tab

  @FXML
  private Tab tabAddToy;// Add Tab - Add Toy Tab Scene/View

  @FXML
  private ChoiceBox<String> chBoxAddCategory; // Add Tab - Choice Box

  @FXML
  private TextField TextFieldAddSN; // Add Tab - TextField (Serial Number)

  @FXML
  private TextField TextFieldAddName; // Add Tab - TextField (Name)

  @FXML
  private TextField TextFieldAddBrand; // Add Tab - TextField (Brand)

  @FXML
  private TextField TextFieldAddPrice; // Add Tab - TextField (Price)

  @FXML
  private TextField TextFieldAddCount; // Add Tab - TextField (Available Count)

  @FXML
  private TextField TextFieldAddAge; // Add Tab - TextField (Age Appropriate)

  @FXML
  private Button btnSave; // Add Tab - Button (Save)

  @FXML
  private TextField TextFieldAddClass; // Add Tab - TextField (Classification)

  @FXML
  private TextField TextFieldAddMaterial; // Add Tab - TextField (Material)

  @FXML
  private TextField TextFieldAddSize; // Add Tab - TextField (Size)

  @FXML
  private TextField TextFieldAddType; // Add Tab - TextField (Type)

  @FXML
  private TextField TextFieldAddMinPlayers; // Add Tab - TextField (Minimum Number of Players)

  @FXML
  private TextField TextFieldAddMaxPlayers; // Add Tab - TextField (Maximum Number of Players)

  @FXML
  private TextField TextFieldAddDesigners; // Add Tab - TextField (Designers)

  @FXML
  private TabPane tabMenu; // The tab pane for switching tabs

  @FXML
  private Tab tabRemoveToy;// Remove Tab

  @FXML
  private Tab tabSearch;// Search Tab

  @FXML
  private TextField textFeildSerialNumber;// Remove Tab

  @FXML
  private ToggleGroup tgSearch = new ToggleGroup();// Search tab toggle group for seeing what radio
                                                   // button is pressed

  private final String DB_PATH = "res/toys.txt"; // This is saved game data location
  private File database;// Declaring a file object
  private ArrayList<Toy> stock = new ArrayList<>();// Creating a stock inventory array list
  private AppMenu menu = new AppMenu();// Declaring and instantiating appMenu object
  Scanner input = new Scanner(System.in);// creating a scanner to read the users choice


  /**
   * Constuctor for initializing program
   * 
   * @throws Exception
   */
  public StockOrganizer() throws Exception {
    // Creating database
    database = new File(DB_PATH);
    // Start menu functionality
    loadStockDB();
  }

  @FXML
  /**
   * Method confirms that btnSave was pressed
   * 
   * @param event The button has been pressed
   */
  private void addToyHandler(ActionEvent event) {
    if (tabMenu.getSelectionModel().equals(tabAddToy)) {
      if (btnSave.isPressed()) {
        btnSearchHandler(event);
      }
    }
  }

  @FXML
  private void btnSaveHandler(ActionEvent event) {
    addToy();
  }


  /**
   * Method to create the tabs on the GUI
   */
  private void createTabs() {
    tabMenu.getTabs().add(tabRemoveToy);
    tabMenu.getTabs().add(tabAddToy);
    tabMenu.getTabs().add(tabSearch);

  }

  @FXML
  /**
   * Method checks see what radio button is pressed
   * 
   * @param event The specific radio button pressed
   */
  private void searchPaneHandler(ActionEvent event) {
    if (tabMenu.getSelectionModel().equals(tabSearch)) {
      if (btnSearch.isPressed()) {
        btnSearchHandler(event);
      } else if (btnClear.isPressed()) {
        btnClearHandler(event);
      } else {
        btnBuyHandler(event);
      }
    }

  }

  @FXML
  /**
   * Deceases the stock count of a selected toy in the search list view
   * 
   * @param event The buy button is pressed
   */
  private void btnBuyHandler(ActionEvent event) {
    Toy lvSelected = listviewToySearch.getSelectionModel().getSelectedItem();// get the selected
                                                                             // item
    lvSelected.deacreseQuanity();// deceased the stock count of the selected item
  }

  @FXML
  /**
   * Clears the search list view if the clear button is pressed
   * 
   * @param event The search button is pressed
   */
  void btnClearHandler(ActionEvent event) {
    if (event.getSource().equals(btnClear)) {// making sure remove button is pressed
      listviewToySearch.getItems().clear();// clearing the list view
    }
  }

  @FXML
  /**
   * Removes toy when remove button on remove toy tab is is pressed
   * 
   * @param event Remove button is pressed
   */
  void removeHandler(ActionEvent event) {
    removeToy();
  }

  /**
   * This method launches the application
   */
  public void launchApp() {
    try {
      loadStockDB();
    } catch (Exception e) {
      System.out.println("Problem with loading database");
      System.out.println(e.getMessage());
    }

    boolean flag = true;// Boolean statement used for terminating program
    int choice;// Declaring variable for later use with user menu choices

    menu.showWelcomeMsg("Welcome to TOY STORY COMPANY!");// The welcome message

    // flag is true continue application
    while (flag) {
      choice = menu.showMainMenu();// prints main menu and gets user choice
      switch (choice) {
        case 1: {// the sub menu choice
          searchDatabase();
          break;
        }
        case 2: {// handles add toy
          addToy();
          break;
        }
        case 3: {// handles remove toy
          removeToy();
          break;
        }
        case 4: {// handles save program
          saveProgram();// Runs through array list and saves all information to file
          flag = false;// end the program by changing program condition
          break;
        }

      }
    }
  }


  /**
   * This program reads the stock inventory file and creates an array for later processing
   * 
   * @throws FileNotFoundException
   */
  public void loadStockDB() throws Exception {

    String currentToy; // String variable containing current line data from

    Toy newInventory; // Toy variable that will contain the various created Toys

    if (database.exists()) {// if the file exits then continue or else create it
      Scanner stockReader = new Scanner(database);// Scanner to read each line

      while (stockReader.hasNextLine()) {// While the scanner can still see a line
        String serialNumber = stockReader.nextLine();// Creating a string to save and analyze it
        currentToy = serialNumber; // Setting the toy object equal to string
        serialNumber = serialNumber.substring(0, 10);// getting 10-digit serial num for analysis

        if (!currentToy.isEmpty() || !currentToy.isBlank()) {// while the toy is not empty nor blank
                                                             // continue
          String[] splitInfo = currentToy.split(";");// Splitting line by semi-colon and saving into
                                                     // an array
                                                     // of strings

          // Figure
          if (toyOfType(serialNumber) == 'f') {
            /**
             * Grabbing the data within the different indexes of the array
             */
            String theSerialNum = splitInfo[0];
            String theName = splitInfo[1];
            String theBrand = splitInfo[2];
            double thePrice = Double.parseDouble(splitInfo[3]);
            int theStockCount = Integer.parseInt(splitInfo[4]);
            int theAge = Integer.parseInt(splitInfo[5]);
            Character theClassification = splitInfo[6].charAt(0);

            // Using polymorphism to put all saved info to a figure object
            newInventory = new Figure(theSerialNum, theName, theBrand, thePrice, theStockCount,
                theAge, theClassification);
            // Adding information to array list
            stock.add(newInventory);
          }

          // Animal
          else if (toyOfType(serialNumber) == 'a') {
            /**
             * Grabbing the data within the different indexes of the array
             */
            String theSerialNum = splitInfo[0];
            String theName = splitInfo[1];
            String theBrand = splitInfo[2];
            double thePrice = Double.parseDouble(splitInfo[3]);
            int theStockCount = Integer.parseInt(splitInfo[4]);
            int theAge = Integer.parseInt(splitInfo[5]);
            String theMaterial = splitInfo[6];
            Character theSize = splitInfo[7].charAt(0);
            // Using polymorphism to put all saved info to a animal object
            newInventory = new Animal(theSerialNum, theName, theBrand, thePrice, theStockCount,
                theAge, theMaterial, theSize);
            // Adding information to array list
            stock.add(newInventory);

          }
          // Puzzle
          else if (toyOfType(serialNumber) == 'p') {
            /**
             * Grabbing the data within the different indexes of the array
             */
            String theSerialNum = splitInfo[0];
            String theName = splitInfo[1];
            String theBrand = splitInfo[2];
            double thePrice = Double.parseDouble(splitInfo[3]);
            int theStockCount = Integer.parseInt(splitInfo[4]);
            int theAge = Integer.parseInt(splitInfo[5]);
            char thePuzzleType = splitInfo[6].charAt(0);
            // Using polymorphism to put all saved info to a puzzle object
            newInventory = new Puzzle(theSerialNum, theName, theBrand, thePrice, theStockCount,
                theAge, thePuzzleType);
            // Adding information to array list
            stock.add(newInventory);
          }

          // Board Game
          else if (toyOfType(serialNumber) == 'b') {
            /**
             * Grabbing the data within the different indexes of the array
             */
            String theSerialNum = splitInfo[0];
            String theName = splitInfo[1];
            String theBrand = splitInfo[2];
            double thePrice = Double.parseDouble(splitInfo[3]);
            int theStockCount = Integer.parseInt(splitInfo[4]);
            int theAge = Integer.parseInt(splitInfo[5]);
            int theMinNumofPlayers = Integer.parseInt(splitInfo[6].substring(0, 1));
            int theMaxNumofPlayers = Integer.parseInt(splitInfo[6].substring(2));
            String theDesigner = splitInfo[7];
            // Using polymorphism to put all saved info to a figure object
            newInventory = new Boardgame(theSerialNum, theName, theBrand, thePrice, theStockCount,
                theAge, theMinNumofPlayers, theMaxNumofPlayers, theDesigner);
            // Adding information to array list
            stock.add(newInventory);

          }
        } else {
          try {
            database.createNewFile();
          } catch (IOException e) {
            e.getMessage();
          }
        }
      }

      stockReader.close(); // Closing database reader
      // System.out.println(stock);

    }
  }

  @FXML
  /**
   * Prints out the searches that the user wants
   * 
   * @param event What search radio button is pressed
   */
  void btnSearchHandler(ActionEvent event) {
    tgSearch = new ToggleGroup();
    rbName.setToggleGroup(tgSearch);
    rbSerialNum.setToggleGroup(tgSearch);
    rbType.setToggleGroup(tgSearch);

    if (event.getSource().equals(btnSearch)) {
      if (tgSearch.getSelectedToggle().equals(rbName)) {
        searchByNameLV();
      } else if (tgSearch.getSelectedToggle().equals(rbSerialNum)) {
        String serialNumber = lblSN.getText();
        searchSerialNumberLV();

      } else {
        String type = lblType.getText();
        System.out.println(lblType.getText());
        searchByTypeLV();
      }

    } else {
      // System.exit(0);
    }

  }


  /**
   * Method search
   */
  public void searchDatabase() {
    System.out.println();
    int choice = menu.showSubMenu();// prints sub menu and gets user choice

    switch (choice) {
      // if first choice search by serial number
      case 1: {
        String userChoice = menu.promptSerialNumber();
        searchSerialNumber(userChoice);
        input.nextLine();
        break;
      }
      // if this choice search by name
      case 2: {

        String toyName = menu.promptSearchName();
        searchByName(toyName);
        input.nextLine();
        break;
      }
      // if this choice search by type
      case 3: {
        String userSearchType = menu.promptType();
        // searchByType("" + userSearchType + "");
        input.nextLine();
        break;
      }
      case 4: {
        input.nextLine();
        break;
      }
    }

  }

  @FXML
  void initialize() {
    ObservableList<String> categoryDropdown =
        FXCollections.observableArrayList("Figures", "Animals", "Puzzles", "Board Games");
    chBoxAddCategory.setItems(categoryDropdown);
    chBoxAddCategory.getSelectionModel().select(0);
  }

  /**
   * This method uses the entered Serial number to generate and add a new Toy to the store stock
   * list
   */
  public void addToy() {
    String userInput;
    Toy newToy;
    String serialNum;

    /*
     * Create additional dropdown options
     */


    /*
     * Prompt for serial number, validate, and save value
     */
    userInput = menu.promptSerialNum();
    while (!isValidSN(userInput, true)) {
      userInput = menu.promptSerialNum();
    }
    serialNum = userInput;

    /*
     * Prompt for Toy name, brand, price, stock amount, minimum recommended age
     */
    String name = menu.promptToyName();
    String brand = menu.promptToyBrand();
    double price = menu.promptToyPrice();
    int stockCount = menu.promptToyCount();
    int minAge = menu.promptMinAge();

    /*
     * Check serial number to determine what kind of Toy is being made then calls the appropriate
     * unique Toy-specific prompts before generating and adding new Toy to stock.
     * 
     * Serial starting in 0 or 1 equals a Figure
     */
    if (serialNum.charAt(0) == '0' || serialNum.charAt(0) == '1') {
      char classification = menu.promptFigClass();
      newToy = new Figure(serialNum, name, brand, price, stockCount, minAge, classification);
      menu.promptNewToy();
      stock.add(newToy);
    }

    /*
     * Serial starting in 2 or 3 equals an Animal
     */
    else if (serialNum.charAt(0) == '2' || serialNum.charAt(0) == '2') {
      String material = menu.promptToyMaterial();
      char size = menu.promptToySize();
      newToy = new Animal(serialNum, name, brand, price, stockCount, minAge, material, size);
      menu.promptNewToy();
      stock.add(newToy);
    }

    /*
     * Serial starting in 4, 5, or 6 equals a Puzzle
     */
    else if (serialNum.charAt(0) == '4' || serialNum.charAt(0) == '5'
        || serialNum.charAt(0) == '6') {
      char puzzleType = menu.promptPuzzleType();
      newToy = new Puzzle(serialNum, name, brand, price, stockCount, minAge, puzzleType);
      menu.promptNewToy();
      stock.add(newToy);
    }

    /*
     * Serial starting in 7, 8, or 9 equals a board game
     */
    else if (serialNum.charAt(0) == '7' || serialNum.charAt(0) == '8'
        || serialNum.charAt(0) == '9') {

      int minPlayers = 0;
      int maxPlayers = 0;

      do {
        minPlayers = menu.promptMinPlayers();
        maxPlayers = menu.promptMaxPlayers();

        try {
          if (minPlayers > maxPlayers) {
            throw new InputValidationException(
                "Minimum number of players cannot be more than max number of players!");
          }
        } catch (InputValidationException e) {
          System.out.println(e.getMessage());
        }

        try {
          if (minPlayers == maxPlayers) {
            throw new InputValidationException(
                "Minimum number of players must be less than max number of players!");
          }
        } catch (InputValidationException e) {
          System.out.println(e.getMessage());
        }

      } while (minPlayers >= maxPlayers);

      String gameDesigners = menu.promptToyDesigner();

      newToy = new Boardgame(serialNum, name, brand, price, stockCount, minAge, minPlayers,
          maxPlayers, gameDesigners);
      menu.promptNewToy();
      stock.add(newToy);
    }
  }


  /**
   * Void returning method to remove a toy based on a serial number
   */
  private void removeToy() {
    String userInput = textFeildSerialNumber.getText();// Saving serial number input

    if (isValidSN(userInput, false)) {
      String serialNum = userInput;
    }
    for (Toy toy : stock) {


      if (toy.getSerialNum().equals(userInput.toString())) {
        int index = stock.indexOf(toy);
        // System.out.println(stock.get(index));
        // lvRemoveToy.getItems().add(toy);
        lvRemoveToy.getItems().add("Item removed!");
        stock.remove(index);
        // System.out.println(stock.get(index));


      } else {
        try {


          throw new InputValidationException("Serial number: " + userInput + " not found!");
        } catch (InputValidationException e) {

          lvRemoveToy.getItems().addAll(e.getMessage().toString());

        }

      }
    }
  }


  public void saveProgram() {

    try {
      PrintWriter saveWriter = new PrintWriter(database);

      menu.savingMsg();

      for (Toy product : stock) {
        saveWriter.println(product.format());
      }

      saveWriter.close();
    } catch (FileNotFoundException e) {
      e.getMessage();
    }

  }

  public void searchByNameLV() {



    ;
    // ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);

    for (Toy aToy : stock) {
      aToy.getName();// Getting the name of the toy
      if (aToy.getName().matches(".*(?i:" + TextFeildName.getText().toString() + ").*")) {// if the
                                                                                          // name
                                                                                          // contains
                                                                                          // the
                                                                                          // keyword
                                                                                          // then

        // it will add

        // it to the purchasing list

        listviewToySearch.getItems().addAll(aToy);


      }
    }

  }

  /**
   * Method prints the specified type of toy the user chose in the search tab
   */
  public void searchByTypeLV() {

    searchByType();// Prints the specified type

  }


  public void searchSerialNumberLV() {

    // listviewToySearch.getItems().clear();
    ArrayList<Toy> copyList = new ArrayList<>();// Array list to store all toys with same serial
    // number for
    // purchasing
    Toy line;// Creating a toy object
    int counter = 1; // Making a counter for selecting the object to remove
    for (int i = 0; i < stock.size(); i++) {

      line = stock.get(i);// saving the toy to the latest stock index
      if (line.getSerialNum().equals(TextFeildSN.getText().toString())) {// if the serial numbers
                                                                         // match print it

        // user identify
        listviewToySearch.getItems().addAll(line); // the item they want to purchase
        copyList.add(line);// adding the line to the purchasing array
      }

    }
  }

  /**
   * Searches inventory Array List for an item with the same parameter serial number
   * 
   * @param userChoice The serial number of the product you are searching for
   * @return A String with the full information of the product
   */
  public void searchSerialNumber(String userChoice) {
    ArrayList<Toy> copyList = new ArrayList<>();// Array list to store all toys with same serial
                                                // number for
                                                // purchasing
    Toy line;// Creating a toy object
    int counter = 1; // Making a counter for selecting the object to remove
    for (int i = 0; i < stock.size(); i++) {

      line = stock.get(i);// saving the toy to the latest stock index
      if (line.getSerialNum().equals(userChoice)) {// if the serial numbers match print it
        System.out.println(" (" + counter + ") " + line);// print out the line with number to help
                                                         // user identify
        listviewToySearch.getItems().addAll(line); // the item they want to purchase
        copyList.add(line);// adding the line to the purchasing array
      }
    }
    System.out.println();// printing line break

    System.out.println(
        "Enter an item that you remove or an index higher than last search to go to sub menu: ");// input
                                                                                                 // prompt


    int chosenItem = input.nextInt();// Saving the input as a variable
    int correctItemIndex = chosenItem - 1;// Variable for fixing the true index from printed list
    if (chosenItem <= copyList.size()) {// if less than the array list size it will sub the
                                        // available count

      Toy theItemToy = copyList.get(correctItemIndex);// saves an object from the copy array list

      int indexInStockArray = stock.indexOf(theItemToy);// finds the index of where the purchased
                                                        // toy is in the
                                                        // main stock inventory
      if (stock.contains(chosenItem)) {// if it contains the item then...

        stock.remove(indexInStockArray);// remove the old version
      }

      theItemToy.deacreseQuanity();// sub stock count

      stock.add(theItemToy);// add the new toy to the stock inventory

    } else {
      menu.showSubMenu();// if the choice is bigger go back to the sub menu
    }

    System.out.println("Please press Enter to contine...");
    Scanner enterKey = new Scanner(System.in);
    enterKey.nextLine();// continues if user hits the enter key
  }

  /**
   * A void returning method for searching stock inventory based on items that contain the parameter
   * string
   * 
   * @param name Product Name Keyword
   */
  public void searchByName(String name) {

    int counter = 1;// Making a counter for selecting the object to remove
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    for (Toy aToy : stock) {
      aToy.getName();// Getting the name of the toy
      if (aToy.getName().matches(".*(?i:" + name + ").*")) {// if the name contains the keyword then
                                                            // it will add
                                                            // it to the purchasing list
        listviewToySearch.getItems().add(aToy);

        copyList.add(aToy);// Add toy to purchasing list
      }
    }
    // Printing each element of array index to display to user
    for (int i = 0; i < copyList.size(); i++) {
      System.out.println("\t(" + counter + ")   " + copyList.get(i));
      counter++;
    }
    System.out.println();// printing line break

    System.out.println(
        "Enter an item that you remove or an index higher than last search to go to sub menu: ");// input
                                                                                                 // prompt

    int chosenItem = input.nextInt();// Saving the input as a variable
    int correctItemIndex = chosenItem - 1;// Variable for fixing the true index from printed list
    if (chosenItem <= copyList.size()) {// if less than the array list size it will sub the
                                        // available count

      Toy theItemToy = copyList.get(correctItemIndex);// saves an object from the copy array list

      int indexInStockArray = stock.indexOf(theItemToy);// finds the index of where the purchased
                                                        // toy is in the
                                                        // main stock inventory
      if (stock.contains(chosenItem)) {// if it contains the item then...

        stock.remove(indexInStockArray);// remove the old version
      }

      theItemToy.deacreseQuanity();// sub stock count

      stock.add(theItemToy);// add the new toy to the stock inventory
      System.out.println("Please press Enter to contine...");
      Scanner enterKey = new Scanner(System.in);
      enterKey.nextLine();// continues if user hits the enter key

    } else {
      menu.showSubMenu();// if the choice is bigger go back to the sub menu
    }
  }

  /**
   * Searches all toys by a String parameter that wants f, a, p , or b
   * 
   */
  public void searchByType() {
    // listviewToySearch.getItems().clear();

    char userSearchType = TextFeildType.getText().toLowerCase().toString().charAt(0); // saves first
                                                                                      // character
                                                                                      // of
                                                                                      // parameter
                                                                                      // to
    // handle search Type
    if (userSearchType == 'f') {// if the parameter starts with f it will read all figures
      readFiguresLV();
    } else if (userSearchType == 'a') {// if the parameter starts with a it will read all animals
      readAnimalsLV();
    } else if (userSearchType == 'p') { // if the parameter starts with p it will read all puzzles
      readPuzzleLV();

    } else {
      readBoradgamesLV();// if the parameter starts with f it will read all boardgames
    }

  }

  /**
   * Prints all figurings
   */
  public void readFigures() {


    int counter = 1;// Making a counter for selecting the object to remove
    int i = 1;
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    for (Toy aRandomToy : stock) {//
      if (aRandomToy instanceof Figure) {// prints any toys that are figures

        // System.out.println(" (" + i + ") " + aRandomToy);// printing figures to console
        i++;// increasing counter
        copyList.add(aRandomToy);// adding figure to purchasing count
        listviewToySearch.getItems().add(aRandomToy);
      }
    }

    // Printing each element of array index to display to user
    for (int a = 0; i < copyList.size(); i++) {
      System.out.println("\t(" + counter + ")   " + copyList.get(a));
      counter++;
    }
    System.out.println();// printing line break

    System.out.println(
        "Enter an item that you remove or an index higher than last search to go to sub menu: ");// input
                                                                                                 // prompt
    int chosenItem = input.nextInt();// Saving the input as a variable
    int correctItemIndex = chosenItem - 1;// Variable for fixing the true index from printed list
    if (chosenItem <= copyList.size()) {// if less than the array list size it will sub the
                                        // available count

      Toy theItemToy = copyList.get(correctItemIndex);// saves an object from the copy array list

      int indexInStockArray = stock.indexOf(theItemToy);// finds the index of where the purchased
                                                        // toy is in the
                                                        // main stock inventory
      if (stock.contains(chosenItem)) {// if it contains the item then...

        stock.remove(indexInStockArray);// remove the old version
      }

      theItemToy.deacreseQuanity();// sub stock count

      stock.add(theItemToy);// add the new toy to the stock inventory
      System.out.println("Please press Enter to contine...");
      Scanner enterKey = new Scanner(System.in);
      enterKey.nextLine();// continues if user hits the enter key

    } else {
      menu.showSubMenu();// if the choice is bigger go back to the sub menu
    }

  }

  /**
   * Prints all animal toys to the list view
   */
  public void readAnimalsLV() {

    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(stock);
    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Animal) {// prints all toys that are animals
        copyList.add(aRandomToy);// adds animal to purchasing array list
      }
    }
    for (Toy toObeservable : copyList) {
      listviewToySearch.getItems().add(toObeservable);
    }

  }

  /**
   * Prints all Board games to list view
   */
  public void readBoradgamesLV() {
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List

    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);

    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Boardgame) {// prints all toys that are Boardgames
        listviewToySearch.getItems().add(aRandomToy);
        toys.add(aRandomToy);
      }
    }

  }

  /**
   * Prints all Board games
   */
  public void readBoardgames() {


    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    int i = 1;
    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Boardgame) {// prints all toys that are Boardgames

        System.out.println("   (" + i + ") " + aRandomToy);// pritns each toy with identifier to
                                                           // console
        i++;// increase counter
        copyList.add(aRandomToy);// adds boardgame to purcahsing array list
        listviewToySearch.getItems().add(aRandomToy);
      }
    }
  }

  /**
   * Prints all puzzles to the list view
   */
  public void readPuzzleLV() {
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Puzzle) {// prints all toys that are Boardgames
        copyList.add(aRandomToy);// adds boardgame to purcahsing array list
        listviewToySearch.getItems().add(aRandomToy);
      }
    }
  }

  /**
   * Prints all figures to the list view
   */
  public void readFiguresLV() {
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    // int i = 1;
    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Figure) {// prints all toys that are Boardgames

        // System.out.println(" (" + i + ") " + aRandomToy);// pritns each toy with identifier to
        // console
        // i++;// increase counter
        copyList.add(aRandomToy);// adds boardgame to purcahsing array list
        listviewToySearch.getItems().add(aRandomToy);
      }
    }
  }

  /**
   * Prints all animals to the list view
   */
  public void readAnimals() {
    ArrayList<Toy> copyList = new ArrayList<>();// purchasing array List
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    // int i = 1;
    for (Toy aRandomToy : stock) {
      if (aRandomToy instanceof Animal) {// prints all toys that are Boardgames

        // System.out.println(" (" + i + ") " + aRandomToy);// pritns each toy with identifier to
        // console
        // i++;// increase counter
        copyList.add(aRandomToy);// adds boardgame to purcahsing array list
        listviewToySearch.getItems().add(aRandomToy);
      }
    }
  }


  /**
   * Prints all puzzles
   */
  public void readPuzzles() {
    ArrayList<Toy> copyList = new ArrayList<>();// purchasig array list
    ObservableList<Toy> toys = FXCollections.observableArrayList(copyList);
    int i = 1;// counter
    for (Toy aRandomToy : stock) {

      if (aRandomToy instanceof Puzzle) {// finds all toys that are puzzles
        System.out.println("   (" + i + ") " + aRandomToy);// Prints each puzzle with identifier to
                                                           // console
        i++;// counter increase
        copyList.add(aRandomToy);// adding toy to purchasing index
        toys.add(aRandomToy);
        listviewToySearch.getItems().add(aRandomToy);
      }
    }
    System.out.println();// printing line break

    System.out.println(
        "Enter an item that you remove or an index higher than last search to go to sub menu: ");// input
                                                                                                 // prompt


    int chosenItem = input.nextInt();// Saving the input as a variable
    int correctItemIndex = chosenItem - 1;// Variable for fixing the true index from printed list
    if (chosenItem <= copyList.size()) {// if less than the array list size it will sub the
                                        // available count

      Toy theItemToy = copyList.get(correctItemIndex);// saves an object from the copy array list

      int indexInStockArray = stock.indexOf(theItemToy);// finds the index of where the purchased
                                                        // toy is in the
                                                        // main stock inventory
      if (stock.contains(chosenItem)) {// if it contains the item then...

        stock.remove(indexInStockArray);// remove the old version
      }

      theItemToy.deacreseQuanity();// sub stock count

      stock.add(theItemToy);// add the new toy to the stock inventory
      System.out.println("Please press Enter to contine...");
      Scanner enterKey = new Scanner(System.in);
      enterKey.nextLine();// continues if user hits the enter key

    } else {
      menu.showSubMenu();// if the choice is bigger go back to the sub menu
    }

  }

  /**
   * Method to search the serial number to determine the type of toy, it returns a specified
   * character depending on what it is
   * 
   * @param serialNum the serial number of the wanted object
   * @return the type of toy from the serial num
   */
  public Character toyOfType(String serialNum) {
    Character toyType;// Creating variable to save the type of toy for identification

    Scanner serialNumberReader = new Scanner(serialNum);// Scans the serial number
    String theLineOfData = serialNumberReader.nextLine();// saves the serial number
    char firstDigit = theLineOfData.charAt(0);// saves the first character of the serial number

    /**
     * if else loop for returning the toy type
     */
    // Figure
    if (firstDigit == '0' || firstDigit == '1') {
      toyType = 'f';
    }
    // Animal
    else if (firstDigit == '2' || firstDigit == '3') {
      toyType = 'a';
    }
    // Puzzle
    else if (firstDigit == '4' || firstDigit == '5' || firstDigit == '6') {
      toyType = 'p';
    }
    // Board game
    else {
      toyType = 'b';
    }
    serialNumberReader.close();// closes reader

    return toyType;// Returns the type of toy in character form

  }

  /**
   * This method validates whether a serial number is valid or not.
   * 
   * @param serial This is the serial number entered
   * @return This returns True or False
   */
  public boolean isValidSN(String serial, boolean isThisNewToy) {
    boolean validity = true;
    boolean flag = isThisNewToy;

    if (flag) {
      try {
        for (Toy product : stock) {
          if (product.getSerialNum().equals(serial)) {
            throw new InputValidationException("A Toy With This Serial Number Already Exists!");
          }
        }
      } catch (InputValidationException e) {
        System.out.println(e.getMessage());
        return false;
      }

    }

    try {
      if (serial.length() != 10) {
        throw new InputValidationException("The Serial Number's lenght MUST be 10 digits!");
      }
    } catch (InputValidationException e) {
      System.out.println(e.getMessage());
      return false;
    }

    for (int index = 0; index < serial.length(); index++) {

      try {
        if (!Character.isDigit(serial.charAt(index))) {
          throw new InputValidationException("The Serial Number should only contain digits!");
        }
      } catch (InputValidationException e) {
        System.out.println(e.getMessage());
        return false;
      }
    }
    return validity;

  }

}
