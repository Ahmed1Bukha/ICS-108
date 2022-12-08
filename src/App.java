import javax.sound.sampled.SourceDataLine;

import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.*;

public class App extends Application {
  Scene scene1, scene2;

  List<Course> coursesOffered = Course.coursesPossible("CourseOffering.csv", "FinishedCourses.csv", "DegreePlan.csv");
  List<Course> basketCourses = new ArrayList<>();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    primaryStage.setTitle("My First JavaFX GUI");
    /// Needs to be cleaner pleeeeeeeeeeeeeeease pleeeeeeeeease
    ScrollPane scrollPane = new ScrollPane();
    Button button1 = new Button("Next");
    BorderPane pane1 = new BorderPane();
    Label title = new Label("Add to Basket");

    VBox vbox = new VBox(coursesOffered.size() + 1);

    HBox courseOfferingTitles = new HBox(6);
    /// Coloring of the page
    vbox.setStyle("-fx-background-color: gray");
    scrollPane.setStyle("-fx-background-color: gray");
    courseOfferingTitles.setStyle("-fx-background-color: black");
    /// titles of courses row
    courseOfferingTitles.getChildren().addAll(
        components.itemCard("        Title     "),
        components.itemCard("    Activity  "),
        components.itemCard(" CRN "),
        components.itemCard("     Instructor   "),
        components.itemCard("           Time     "),
        components.itemCard("    Days "));
    vbox.getChildren().add(courseOfferingTitles);

    /// Courses List view
    for (int i = 0; i < coursesOffered.size(); i++) {
      HBox courseRow = new HBox(8);

      courseRow.getChildren().addAll(
          components.itemCard("  " + coursesOffered.get(i).getTitle()),
          components.itemCard("   " + coursesOffered.get(i).getActivity() + "   "),
          components.itemCard("   " + coursesOffered.get(i).getCRN()),
          components.itemCard(coursesOffered.get(i).getInstructor()),
          components.itemCard(coursesOffered.get(i).getTime()),
          components.itemCard("      " + coursesOffered.get(i).getDays()),
          components.itemCard("                                    "),
          components.AddRemoveButton(coursesOffered.get(i), basketCourses)

      );
      vbox.getChildren().add(courseRow);
    }

    title.setFont(new Font("Arial", 50));
    button1.setOnAction(e -> primaryStage.setScene(scene2));
    pane1.setPadding(new Insets(4, 4, 4, 4));
    scrollPane.fitToWidthProperty().set(true);
    ////// will fix and design it later
    pane1.setTop(title);
    pane1.setBottom(button1);
    scrollPane.setContent(vbox);
    pane1.setCenter(scrollPane);

    BorderPane.setAlignment(button1, Pos.TOP_RIGHT);
    BorderPane.setAlignment(title, Pos.CENTER);
    BorderPane.setAlignment(scrollPane, Pos.CENTER);
    scene1 = new Scene(pane1, 1200, 800);





    // Scene 2 Abdulla's work
    BorderPane pane2 = new BorderPane();
    String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n";
    HBox calendar = new HBox();
    calendar.setMaxWidth(1000);
    VBox sunday = new VBox();
    sunday.setStyle(cssLayout);
    sunday.setMinWidth(200);

    VBox monday = new VBox();
    monday.setStyle(cssLayout);
    monday.setMinWidth(200);
   
    VBox tuesday = new VBox();
    tuesday.setStyle(cssLayout);
    tuesday.setMinWidth(200);

    VBox wednsday = new VBox();
    wednsday.setStyle(cssLayout);
    wednsday.setMinWidth(200);

    VBox thursday = new VBox();
    thursday.setStyle(cssLayout);
    thursday.setMinWidth(200);

    calendar.getChildren().addAll(sunday,monday,tuesday,wednsday,thursday);
    
 
    
    calendar.setStyle(cssLayout);
    Button button2 = new Button("Go to scene 1");
    button2.setOnAction(e -> primaryStage.setScene(scene1));
    
    
    pane2.setBottom(button2);
    pane2.setLeft(calendar);
    scene2 = new Scene(pane2, 1200, 800);

    primaryStage.setScene(scene1);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }

}
