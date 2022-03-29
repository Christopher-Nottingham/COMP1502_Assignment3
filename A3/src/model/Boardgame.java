package model;

/**
 * This class represents all Puzzle toys and inherits public Toy fields and methods while adding a
 * type field
 * 
 * @author Joseph Russell, Christopher Nottingham
 *
 */
public class Boardgame extends Toy {

  /*
   * Unique instance fields for Boardgame objects
   */
  private int minNumPlayers;
  private int maxNumPlayers;
  private String designer;

  /**
   * Standard constructor
   * 
   * @param serialNum This is the Toy's serial number
   * @param name This is the Toy's name
   * @param brand This is the Toy's brand
   * @param price This is the Toy's price
   * @param stockCount This is the Toy's current stock amount
   * @param minAge This is the Toy's minimum recommended age
   * @param minNumPlayers This is the minimum amount of players required to play
   * @param maxNumPlayers This is the maximum amount of players designed to play
   * @param designer This lists the game designers
   */
  public Boardgame(String serialNum, String name, String brand, double price, int stockCount,
      int minAge, int minNumPlayers, int maxNumPlayers, String designer) {
    super(serialNum, name, brand, price, stockCount, minAge);
    this.minNumPlayers = minNumPlayers;
    this.maxNumPlayers = maxNumPlayers;
    this.designer = designer;
  }

  /**
   * This method returns the minimum amount of players required to play
   * 
   * @return This returns the amount of players
   */
  public int getMinNumPlayers() {
    return minNumPlayers;
  }

  /**
   * This method sets the minimum amount of players required to play
   * 
   * @param number This is the amount of players
   */
  public void setMinNumPlayers(int number) {
    minNumPlayers = number;
  }

  /**
   * This method returns the maximum amount of players required to play
   * 
   * @return This returns the amount of players
   */
  public int getMaxNumPlayers() {
    return maxNumPlayers;
  }

  /**
   * This method sets the maximum amount of players required to play
   * 
   * @param number This is the amount of players
   */
  public void setMaxNumPlayers(int number) {
    maxNumPlayers = number;
  }

  /**
   * This returns the designer(s) of the board game
   * 
   * @return This returns all board game designers
   */
  public String getDesigner() {
    return designer;
  }


  /**
   * This sets the designer(s) of the board game
   * 
   * @param designer This is designer(s) of the boardgame
   */
  public void setDesigner(String designer) {
    this.designer = designer;
  }

  public String toString() {
    String category = getClass().getSimpleName();

    return "Category: " + category + ", Serial Number: " + getSerialNum() + ", Name: " + getName()
        + ", Brand: " + getBrand() + ", Price: " + getPrice() + ", Available Count: "
        + getStockCount() + ", Age Appropriate: " + getMinAge() + ", Number of Players: "
        + getMinNumPlayers() + "-" + getMaxNumPlayers() + ", Designer(s): " + getDesigner();
  }

  @Override
  public String format() {
    return getSerialNum() + ";" + getName() + ";" + getBrand() + ";" + getPrice() + ";"
        + getStockCount() + ";" + +getMinAge() + ";" + getMinNumPlayers() + "-" + getMaxNumPlayers()
        + ";" + getDesigner();
  }
}


