package view;

import java.util.Scanner;
import view.*;
import model.*;
import controller.*;
import exceptions.*;


/**
 * This class contains all menu prompts
 * 
 * @author Christopher Nottingham, Joseph Robinson-Russell
 *
 */
public class AppMenu {
  //private java.util.logging.Logger LOGR = java.util.logging.Logger.getLogger(AppMenu.class.getName());
  
  private int userValue;
  private String toyName;

  Scanner input = new Scanner(System.in);

  public void showWelcomeMsg(String welcomeMsg) {
    int msgLength = welcomeMsg.length();
    for (int index = 0; index < msgLength + 4; index++) {
      System.out.print("*");
    }
    System.out.println("");
    System.out.println("* " + welcomeMsg.toUpperCase() + " *");
    for (int index = 0; index < msgLength + 4; index++) {
      System.out.print("*");
    }
    System.out.println("");

  }

  /**
   * Prints the main menu to user
   * 
   * @return the user menu choice
   */
  public int showMainMenu() {
    System.out.println("\nHow May We Help You?");
    System.out.println("");
    System.out.println("(1)\tSearch Inventory and Purchase Toy");
    System.out.println("(2)\tAdd New Toy");
    System.out.println("(3)\tRemove Toy");
    System.out.println("(4)\tSave & Exit");
    System.out.println("");
    System.out.print("Enter Option: ");

    try {
      userValue = input.nextInt();
    } catch (Exception e) {
      userValue = 0;
    }

    while (!(userValue >= 1 && userValue <= 4)) {
      System.out.println("Invalid input, please try again");
      System.out.println("");
      System.out.println("(1)\tSearch Inventory and Purchase Toy");
      System.out.println("(2)\tAdd New Toy");
      System.out.println("(3)\tRemove Toy");
      System.out.println("(4)\tSave & Exit");
      System.out.println("");
      System.out.print("Enter Option: ");
      try {
        userValue = input.nextInt();
      } catch (Exception e) {
        userValue = 0;
      }
    }

    input.nextLine();
    return userValue;
  }

  /**
   * Shows sub menu
   * 
   * @return the users menu choice item
   */
  public int showSubMenu() {
    System.out.println("\nFind Toys With:");
    System.out.println("(1)\tSerial Number (SN)");
    System.out.println("(2)\tToy Name");
    System.out.println("(3)\tType");
    System.out.println("(4)\tBack to Main Menu");
    System.out.println("");
    System.out.print("Enter Option: ");

    try {
      userValue = input.nextInt();
    } catch (Exception e) {
      userValue = 0;
    }

    while (!(userValue >= 1 && userValue <= 4)) {
      System.out.println("Invalid input, please try again");
      System.out.println("");
      System.out.println("Find Toys With:");
      System.out.println("(1)\tSerial Number (SN)");
      System.out.println("(2)\tToy Name");
      System.out.println("(3)\tType");
      System.out.println("(4)\tBack to Main Menu");
      System.out.println("");
      System.out.print("Enter Option: ");
      try {
        userValue = input.nextInt();
      } catch (Exception e) {
        userValue = 0;
      }
    }

    input.nextLine();
    return userValue;
  }

  public char displayItemRemoval(model.Toy product) {

    char userInput = '0';
    System.out.println("This Item Found: \n");
    System.out.println(product);

    while (userInput != 'y' && userInput != 'n') {
      System.out.print("\nDo you want to remove it (Y/N)? ");

      userInput = input.nextLine().trim().toLowerCase().charAt(0);

      if (userInput != 'y' && userInput != 'n') {
        System.out.println("Invalid selection. Try again");
      }
    }

    return userInput;
  }

  public String promptSerialNum() {
    String userValue;
    System.out.print("Enter Serial Number: ");
    userValue = input.nextLine();
    return userValue;
  }

  public String promptToyName() {
    String toyName;
    System.out.print("Enter Toy Name: ");
    toyName = input.nextLine();
    return toyName;
  }

  public String promptToyBrand() {
    String toyBrand = "";
    System.out.print("Enter Toy Brand: ");
    toyBrand = input.nextLine();
    return toyBrand;
  }

  public double promptToyPrice() {
    boolean flag = true;
    double toyPrice = 0;

    /*
     * Loop prompt until a valid a price is entered
     */
    while (flag) {
      System.out.print("Enter Toy Price: ");
      try {
        toyPrice = Double.parseDouble(input.next()); // Attempt to parse input as a double value
        flag = false; // Stop loop if valid Double entered

        /*
         * If price is negative, thrown exception
         */
        try {
          if (toyPrice < 0) {
            throw new InputValidationException("Serial number cannot be negative!");
          }
        } catch (InputValidationException e) {
          e.getLocalizedMessage();
        }

      } catch (NumberFormatException e) {
        System.out.println("This is not a valid price! Try again."); // Catch exception and prompt
                                                                     // new attempt
      }
    }
    return toyPrice;
  }

  public int promptToyCount() {
    int stockCount = 0;

    while (stockCount <= 0) {
      System.out.print("Enter Available Count: ");
      try {
        stockCount = Integer.parseInt(input.next());
      } catch (NumberFormatException e) {
        System.out.println("Invalid stock amount, try again.");
      }
    }
    return stockCount;
  }

  public int promptMinAge() {
    int min_Age = 0;

    while (min_Age <= 0) {
      System.out.print("Enter Minimum Age: ");
      try {
        min_Age = Integer.parseInt(input.next());
      } catch (NumberFormatException e) {
        System.out.println("Invalid stock amount, try again.");
      }
    }
    return min_Age;
  }

  public int promptMinPlayers() {
    int minPlayers = 0;

    while (minPlayers <= 0) {
      System.out.print("Enter Minimum Number of Players: ");
      try {
        minPlayers = Integer.parseInt(input.next());
      } catch (NumberFormatException e) {
        System.out.println("Invalid stock amount, try again.");
      }
    }
    return minPlayers;
  }

  public int promptMaxPlayers() {
    int maxPlayers = 0;
    while (maxPlayers <= 0) {
      System.out.print("Enter Minimum Number of Players: ");
      try {
        maxPlayers = Integer.parseInt(input.next());
      } catch (NumberFormatException e) {
        System.out.println("Invalid stock amount, try again.");
      }
    }
    return maxPlayers;
  }

  public String promptToyDesigner() {
    String designerNames = "";
    System.out.print("Enter Designer Names "
        + "(Use ',' to separate the names if there is more than one name): ");
    return designerNames;
  }

  public char promptPuzzleType() {
    int userValue = 0;
    char puzzleType = 'm';

    System.out.println("Select Puzzle Type: ");
    System.out.println("(1) Mechanical");
    System.out.println("(2) Cryptic");
    System.out.println("(3) Logic");
    System.out.println("(4) Trivia");
    System.out.println("(5) Riddle");

    try {
      userValue = input.nextInt();
    } catch (Exception e) {
      userValue = 0;
    }

    while (!(userValue >= 1 && userValue <= 5)) {
      System.out.println("Invalid selection! Try again.\n");
      System.out.println("Enter Puzzle Type: ");
      System.out.println("(1) Mechanical");
      System.out.println("(2) Cryptic");
      System.out.println("(3) Logic");
      System.out.println("(4) Trivia");
      System.out.println("(5) Riddle");
      System.out.print("Enter option: ");

      try {
        userValue = input.nextInt();
      } catch (Exception e) {
        userValue = 0;
      }
    }

    switch (userValue) {
      case 1:
        puzzleType = 'm';
        break;
      case 2:
        puzzleType = 'c';
        break;
      case 3:
        puzzleType = 'l';
        break;
      case 4:
        puzzleType = 't';
        break;
      case 5:
        puzzleType = 'r';
        break;
    }

    return puzzleType;
  }

  public char promptFigClass() {
    int userValue = 0;
    char classification = 'a';

    System.out.println("Select Figure classification: ");
    System.out.println("(1) Action");
    System.out.println("(2) Doll");
    System.out.println("(3) Historic");
    System.out.print("Enter option: ");

    try {
      userValue = input.nextInt();
    } catch (Exception e) {
      userValue = 0;
    }

    while (!(userValue >= 1 && userValue <= 3)) {
      System.out.println("Invalid selection! Try again.\n");
      System.out.println("Select Figure classification: ");
      System.out.println("(1) Action");
      System.out.println("(2) Doll");
      System.out.println("(3) Historic");
      System.out.print("Enter option: ");

      try {
        userValue = input.nextInt();
      } catch (Exception e) {
        userValue = 0;
      }
    }

    switch (userValue) {
      case 1:
        classification = 'a';
        break;
      case 2:
        classification = 'd';
        break;
      case 3:
        classification = 'h';
        break;
    }
    return classification;
  }

  public String promptToyMaterial() {
    String toyMatierial = "plastic";
    System.out.print("Enter Toy Material: ");
    toyMatierial = input.nextLine();
    return toyMatierial;
  }

  public char promptToySize() {
    int userValue = 0;
    char toySize = 'm';

    System.out.println("Select Toy Size: ");
    System.out.println("(1) Small");
    System.out.println("(2) Medium");
    System.out.println("(3) Large");
    System.out.print("Enter option: ");

    try {
      userValue = input.nextInt();
    } catch (Exception e) {
      userValue = 0;
    }

    while (!(userValue >= 1 && userValue <= 3)) {
      System.out.println("Invalid selection! Try again.\n");
      System.out.println("Select Toy Size: ");
      System.out.println("(1) Small");
      System.out.println("(2) Medium");
      System.out.println("(3) Large");
      System.out.print("Enter option: ");

      try {
        userValue = input.nextInt();
      } catch (Exception e) {
        userValue = 0;
      }
    }

    switch (userValue) {
      case 1:
        toySize = 's';
        break;
      case 2:
        toySize = 'm';
        break;
      case 3:
        toySize = 'l';
        break;
    }
    return toySize;
  }

  /**
   * Prompt for asking user they toy name that they would like to search for
   */
  public String promptSearchName() {

    System.out.println("");
    System.out.println("Enter Toy Name: ");
    if (input.hasNextLine()) {
      toyName = input.nextLine();
    } else {
      System.out.println("Please only enter words. Try again...");
    }
    return toyName;

  }

  /**
   * Prompt to ask the user what type of toy they want to search for
   */
  public String promptType() {
    System.out.println("");
    System.out.println("Enter Type you would like to search for (A/F/B/P): ");
    System.out.println(" ");

    String searchType = input.next();
    while (!input.hasNext()) {
      System.out.print("Please enter only characters!");
    }
    return searchType;
  }

  /**
   * Prompt for asking user to enter a serial number
   */
  public String promptSerialNumber() {
    System.out.println("");
    System.out.println("Enter the serial number you would like to search for: ");
    System.out.println(" ");

    String searchType = input.nextLine();
    while (!input.hasNextLine()) {
      System.out.print("Numbers only, try again....");
    }

    return searchType;

  }

  public void savingMsg() {
    System.out.println("\nSaving database...");
  }

  public void promptNewToy() {
    System.out.println("\nAdding new item...");

  }

}
