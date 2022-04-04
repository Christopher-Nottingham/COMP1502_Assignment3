package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {    try {
      
      
      TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("/view/Untitled.fxml"));
//      BorderPane root = new BorderPane();
        
//    TabPane tp = new TabPane();
//    Tab search = new Tab();
//    Tab search1 = new Tab();
//    Tab search2 = new Tab();
//    
//    tp.getTabs().add(search);
//    tp.getTabs().add(search2);
      
      Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    } catch(Exception e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    launch(args);
}
}
