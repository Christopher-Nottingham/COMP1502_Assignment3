package exceptions;

/**
 * This custom exception class throws error messages with if 2 given events occur (1) User Input
 * Price is negative (2) Minimum number of players is greatest than max players for Boardgame
 * 
 * @author christophernottingham
 *
 */
public class InputValidationException extends Exception {
  /**
   * Constructor for printing message
   * 
   * @param errorMsg The message specific error message
   */
  public InputValidationException(String errorMsg) {
    super(errorMsg + " Try again.\n");
  }


}
