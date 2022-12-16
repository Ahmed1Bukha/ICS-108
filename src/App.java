
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;
//Some notes:
// Calendar == jadwal on the left(Just like google calendae.)

public class App extends Application {

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    Drawer drawer = new Drawer(primaryStage);
    List<Course> basketCourses = new ArrayList<>();

    primaryStage.setTitle("My First JavaFX GUI");

    Scene firstPage = FirstPage.scene(
        basketCourses, primaryStage, drawer);

    


    primaryStage.setScene(firstPage);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }

}
