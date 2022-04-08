

module A3 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.base;
  requires java.logging;
  requires org.apache.logging.log4j;



  opens controller to javafx.fxml;

  exports application to javafx.graphics;
}
