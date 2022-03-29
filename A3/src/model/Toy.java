package model;

/**
 * This class represents all generic Toy information and methods
 * 
 * @author Joseph Russell
 *
 */
public abstract class Toy {

  private String serialNum;
  private String name;
  private String brand;
  private double price;
  private int stockCount;
  private int minAge;

  /**
   * This is constructor contains all generic toy information
   * 
   * @param serialNum This is the toy's serial number
   * @param name This is the name of the toy
   * @param brand This is the brand of the toy
   * @param price This is the price of the toy
   * @param stockCount This is the available stock numbers of the toy
   * @param minAge This is the minimum recommended age for the toy
   */
  public Toy(String serialNum, String name, String brand, double price, int stockCount,
      int minAge) {
    this.serialNum = serialNum;
    this.name = name;
    this.brand = brand;
    this.price = price;
    this.stockCount = stockCount;
    this.minAge = minAge;
  }

  /**
   * This retrieves the Toy's serial number
   * 
   * @return the serialNum
   */
  public String getSerialNum() {
    return serialNum;
  }

  /**
   * This sets the Toy's serial number to a specific value
   * 
   * @param serialNum The serial number to be set
   */
  public void setSerialNum(String serialNum) {
    this.serialNum = serialNum;
  }

  /**
   * This retrieves the Toy's name
   * 
   * @return name This is the toy's name
   */
  public String getName() {
    return name;
  }

  /**
   * This sets the Toy's name to a specific value
   * 
   * @param name This is the toy's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This retrieves the Toy's brand
   * 
   * @return brand This is the toy's brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * This sets the Toy's brand to a specific value
   * 
   * @param brand This is the toy's brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * This retrieves the Toy's price
   * 
   * @return price This is the toy's price
   */
  public double getPrice() {
    return price;
  }

  /**
   * This sets the Toy's price to a specific value
   * 
   * @param price This is the toy's price
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * This retrieves the current available stock count of the specific toy
   * 
   * @return stockCount This is the amount of this specific Toy available to purchase
   */
  public int getStockCount() {
    return stockCount;
  }

  /**
   * This sets the amount of this specific Toy that is onhand or available.
   * 
   * @param stockCount This is the amount to be set
   */
  public void setStockCount(int stockCount) {
    this.stockCount = stockCount;
  }

  /**
   * This returns the minimum age appropriate for toy usage as recommended by the manufacturer
   * 
   * @return minAge This is the minimum recommended age
   */
  public int getMinAge() {
    return minAge;
  }

  /**
   * This sets the minimum recommended age for the Toy
   * 
   * @param minAge This is the minimum recommended age
   */
  public void setMinAge(int minAge) {
    this.minAge = minAge;
  }

  /**
   * This decreases stock levels by one when called
   */
  public void deacreseQuanity() {
    stockCount--;
  }

  /**
   * This abstract method is uniquely implemented within each child/subclass formatting the Toy
   * information into its correct save format
   */
  public abstract String format();
}
