package model;

public class Animal extends Toy {

  private String material;
  private char size;


  /**
   * This is constructor contains all generic toy information
   * 
   * @param serialNum This is the toy's serial number
   * @param name This is the name of the toy
   * @param brand This is the brand of the toy
   * @param price This is the price of the toy
   * @param stockCount This is the available stock numbers of the toy
   * @param minAge This is the minimum recommended age for the toy
   * @param material This is the Toy's build material
   * @param size This is the Toy's size: small, medium, or large
   */
  public Animal(String serialNum, String name, String brand, double price, int stockCount,
      int minAge, String material, char size) {
    super(serialNum, name, brand, price, stockCount, minAge);
    this.material = material;
    this.size = size;
  }

  /**
   * This retrieves the material that makes up the Animal toy
   * 
   * @return material This is the toy's material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * This sets the material value of the Animal toy
   * 
   * @param material This is the toy's material
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * This retrieves the size value of the Animal toy
   * 
   * @return size This is the toy's size
   */
  public char getSize() {
    return size;
  }

  /**
   * This sets the size value of the Animal toy
   * 
   * @param size This is the toy's size
   */
  public void setSize(char size) {
    this.size = size;
  }

  @Override
  public String toString() {
    String category = getClass().getSimpleName();

    return "Category: " + category + ", Serial Number: " + getSerialNum() + ", Name: " + getName()
        + ", Brand: " + getBrand() + ", Price: " + getPrice() + ", Available Count: "
        + getStockCount() + ", Age Appropriate: " + getMinAge() + ", Material: " + material
        + ", Size: " + size;
  }

  /**
   * This method formats the Toy information into its correct save format
   */
  public String format() {
    return getSerialNum() + ";" + getName() + ";" + getBrand() + ";" + getPrice() + ";"
        + getStockCount() + ";" + getMinAge() + ";" + getMaterial() + ";" + getSize();
  }


}
