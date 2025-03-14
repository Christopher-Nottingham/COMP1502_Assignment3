package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {

    try {

      TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("/view/Untitled.fxml"));

      Scene scene = new Scene(root, 1280, 720);
      scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
