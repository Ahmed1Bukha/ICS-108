
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class App extends Application {

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    Drawer drawer = new Drawer(primaryStage);
    List<Course> basketCourses = new ArrayList<>();

    primaryStage.setTitle("ICS 108 project");

    /// bringing the first page scene and setting it to the primary stage

    Scene firstPage = FirstPage.scene(
        basketCourses, primaryStage, drawer);

    primaryStage.setScene(firstPage);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }

}
