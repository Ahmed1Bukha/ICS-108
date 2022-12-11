import javax.sound.sampled.SourceDataLine;

import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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

  List<List> days = new ArrayList<>();
  

  List<Course> jadwal = new ArrayList<>();

  
  VBox coursesColumn = new VBox();


  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
   
    primaryStage.setTitle("My First JavaFX GUI");
    /// Needs to be cleaner pleeeeeeeeeeeeeeease pleeeeeeeeease
    ScrollPane scrollPane1 = new ScrollPane();
    Button button1 = new Button("Next");
    BorderPane pane1 = new BorderPane();
    Label title = new Label("Add to Basket");

    VBox vbox = new VBox(coursesOffered.size() + 1);

    HBox courseOfferingTitles = new HBox(6);
    /// Coloring of the page
    vbox.setStyle("-fx-background-color: gray");
    scrollPane1.setStyle("-fx-background-color: gray");
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
    scrollPane1.fitToWidthProperty().set(true);
    ////// will fix and design it later
    pane1.setTop(title);
    pane1.setBottom(button1);
    scrollPane1.setContent(vbox);
    pane1.setCenter(scrollPane1);

    BorderPane.setAlignment(button1, Pos.TOP_RIGHT);
    BorderPane.setAlignment(title, Pos.CENTER);
    BorderPane.setAlignment(scrollPane1, Pos.CENTER);
    scene1 = new Scene(pane1, 1200, 800);





    // Scene 2 Abdulla's work

 


   
   


    BorderPane pane2 = new BorderPane();
    ScrollPane scrollPane2 = new ScrollPane();
    String cssLayout = "-fx-border-color: black;\n" +
                  
                   "-fx-border-width: 3;\n";
    HBox calendar = new HBox();
    calendar.setMaxWidth(1200);
    VBox sunday = new VBox(0);

  
    

    
    
    VBox monday = new VBox(0);
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
    scrollPane2.setContent(calendar);

  

    
 
    
    calendar.setStyle(cssLayout);
    calendar.setPadding(new Insets(0));
    Button button2 = new Button("Go to scene 1");



    button1.setOnAction(e ->{ 
      primaryStage.setScene(scene2);

    for(int i=0;i<basketCourses.size();i++){
      VBox courseCard = new VBox();
      courseCard.setPadding(new Insets(10,10,10,10));
     
      courseCard.getChildren().addAll((new Label(basketCourses.get(i).getTitle()+" "+basketCourses.get(i).getActivity())),
      new Label(basketCourses.get(i).getDays()),
      //there is a problem with this button, I'll fix it 
      components.AddRemoveBasket(basketCourses.get(i),jadwal,basketCourses,primaryStage,scene2,coursesColumn,courseCard));

      courseCard.setAlignment(Pos.CENTER);
      coursesColumn.getChildren().addAll(courseCard);
      
    } 
    });
   
                  

    BorderPane layout2 = new BorderPane();
    layout2.setTop(label2);
    ScrollPane scrollPane2 = new ScrollPane();
    layout2.setRight(scrollPane2);
    
    scrollPane2.setContent(coursesColumn);
    
    System.out.println(basketCourses.size());

    layout2.setPadding(new Insets(10,10,10,01));
    

    scene2 = new Scene(layout2, 800, 600);
    
    primaryStage.setScene(scene1);
    primaryStage.show();
    

  }

  public static void main(String[] args) {
    launch(args);
  }

}
