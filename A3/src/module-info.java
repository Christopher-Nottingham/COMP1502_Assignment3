import javafx.scene.layout.BorderPane;

module A3 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;

  
  
  opens controller to javafx.fxml;

  exports application to javafx.graphics;
}
