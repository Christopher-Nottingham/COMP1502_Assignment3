

module A3 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;
  requires java.logging;



  opens controller to javafx.fxml;

  exports application to javafx.graphics;
}
