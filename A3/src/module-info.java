module A3 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  opens controller to javafx.fxml;

  exports application to javafx.graphics;
}
