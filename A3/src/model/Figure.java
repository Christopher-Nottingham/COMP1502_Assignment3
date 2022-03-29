package model;

/**
 * This class signifies a figure object
 * 
 * @author Joseph Robinson-Russell
 *
 */
public class Figure extends Toy {
  private char classification;

  /**
   * Standard Constructor
   * 
   * @param serialNum The serial number of the product
   * @param name The name of the product
   * @param brand The brand name of the product
   * @param price The price of the product
   * @param avaliableCount The stock count of the product
   * @param minAge The suggested appropriate age of the product
   * @param classification The figure classification
   */
  public Figure(String serialNum, String name, String brand, double price, int avaliableCount,
      int minAge, char classification) {
    super(serialNum, name, brand, price, avaliableCount, minAge);
    this.classification = classification;
  }

  /**
   * This method returns the classification of toy as a single character
   * 
   * @return This returns the figure classification
   */
  public char getClassification() {
    return classification;
  }

  /**
   * This method sets the classification of the toy using a single character as a parameter
   * 
   * @param classification This is the Figure classification to be set
   */
  public void setClassification(char classification) {
    this.classification = classification;
  }

  /**
   * This method lists all relevant Toy information
   * 
   * 
   */
  public String toString() {
    String category = getClass().getSimpleName();

    return "Category: " + category + ", Serial Number: " + getSerialNum() + ", Name: " + getName()
        + ", Brand: " + getBrand() + ", Price: " + getPrice() + ", Available Count: "
        + getStockCount() + ", Age Appropriate: " + getMinAge() + ", Classification: "
        + getClassification();
  }

  @Override
  /**
   * This method formats the Toy information into its correct save format
   */
  public String format() {
    return getSerialNum() + ";" + getName() + ";" + getBrand() + ";" + getPrice() + ";"
        + getStockCount() + ";" + getMinAge() + ";" + getClassification();

  }
}
