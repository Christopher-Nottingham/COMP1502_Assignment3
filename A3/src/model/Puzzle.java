package model;

/**
 * This class represents all Puzzle toys and inherits public Toy fields and methods while adding a
 * type field
 * 
 * @author Joseph Robinson-Russell
 *
 */
public class Puzzle extends Toy {

  private char puzzleType; // Unique Toy Attribute - Puzzle Type

  /**
   * Standard Constructor
   * 
   * @param serialNum This is the toy's serial number
   * @param name This is the name of the toy
   * @param brand This is the brand of the toy
   * @param price This is the price of the toy
   * @param stockCount This is the available stock numbers of the toy
   * @param minAge This is the minimum recommended age for the toy
   * @param puzzleType This is the type of puzzle
   */
  public Puzzle(String serialNum, String name, String brand, double price, int stockCount,
      int minAge, char puzzleType) {
    super(serialNum, name, brand, price, stockCount, minAge);
    this.puzzleType = puzzleType;
  }

  /**
   * This method returns the puzzle type
   * 
   * @return puzzleType This returns the type of puzzle
   */
  public char getPuzzleType() {
    return puzzleType;
  }

  /**
   * This method sets the puzzle type
   * 
   * @param puzzleType This sets the type of puzzle
   */
  public void setPuzzleType(char puzzleType) {
    this.puzzleType = puzzleType;
  }

  public String toString() {
    String category = getClass().getSimpleName();

    return "Category: " + category + ", Serial Number: " + getSerialNum() + ", Name: " + getName()
        + ", Brand: " + getBrand() + ", Price: " + getPrice() + ", Available Count: "
        + getStockCount() + ", Age Appropriate: " + getMinAge() + ", Type: " + getPuzzleType();
  }

  @Override
  public String format() {
    return getSerialNum() + ";" + getName() + ";" + getBrand() + ";" + getPrice() + ";"
        + getStockCount() + ";" + getMinAge() + ";" + getPuzzleType();
  }

}
