import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import javafx.scene.control.ScrollBar;  
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;  
import java.util.Scanner; 

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.*;

public class App extends Application {
  Scene scene1, scene2;

  List<Course> courses= coursesPossible("CourseOffering.csv", "FinishedCourses.csv","DegreePlan.csv");
  
 @Override // Override the start method in the Application class
 public void start(Stage primaryStage) {
  primaryStage.setTitle("My First JavaFX GUI");

  //Scene 1 Ahmed's Work
 
   
  Button button1= new Button("Next");
  BorderPane pane1 = new BorderPane();
  Label title = new Label("Add to Basket");
  StackPane stack1 = new StackPane();

VBox vbox = new VBox(courses.size());

  for(int i=0; i<courses.size(); i++){
    HBox hbox = new HBox(6);
    hbox.getChildren().addAll(
      new Label(courses.get(i).getTitle()),
      new Label(courses.get(i).getActivity()),
      new Label(courses.get(i).getCRN()),
      new Label(courses.get(i).getInstructor()),
      new Label(courses.get(i).getTime()),
      new Label(courses.get(i).getDays())

    );
    vbox.getChildren().add(hbox);
  }

  title.setFont(new Font("Arial", 50));
  button1.setOnAction(e -> primaryStage.setScene(scene2));  
  pane1.setPadding(new Insets(4,4,4,4));

  pane1.setTop(title);
  pane1.setBottom(button1);
  pane1.setLeft(vbox);
  


  BorderPane.setAlignment(button1, Pos.TOP_RIGHT);
  BorderPane.setAlignment(title,Pos.CENTER);
  scene1= new Scene(pane1, 1400, 900);
                 



  //Scene 2 Abdulla's work
  Label label2= new Label("This is the second scene");
  Button button2= new Button("Go to scene 1");
  button2.setOnAction(e -> primaryStage.setScene(scene1));
  VBox layout2= new VBox(20);
  layout2.getChildren().addAll(label2, button2);
  scene2= new Scene(layout2,1200,700);
          
          
  primaryStage.setScene(scene1);
  primaryStage.show();

 }

 public static void main(String[] args) {
  launch(args);
 }

 
 public static List<Course> coursesPossible (String coursesPath , String finishedPath, String dgreePlanPath){
  List<Course> Courses =new ArrayList<Course>();  

  try {

 

  Scanner finishedCourseScanner = new Scanner(new File(finishedPath));  
 
 

  List<String> finishedCourse= new ArrayList<String>();
  List<String> courseAvailable = new ArrayList<String>();
    //It will check every finished course.

    String courseName ="";
    while(finishedCourseScanner.hasNextLine()){
    
       courseName =finishedCourseScanner.nextLine().split(",")[0];
      finishedCourse.add(courseName);
    System.out.println((finishedCourse));
    }
       
       Scanner courseOfferingScanner = new Scanner(new File(coursesPath));   
       Scanner degreePlanScanner = new Scanner(new File(dgreePlanPath)); 
      

       while(degreePlanScanner.hasNextLine()){
        String []tempo = degreePlanScanner.nextLine().split(",");
        if(finishedCourse.contains(tempo[2]) && !finishedCourse.contains(tempo[0])){
          courseAvailable.add(tempo[0]);
        }
       }
       System.out.println(courseAvailable);

       while(courseOfferingScanner.hasNextLine()){
        String tempo = courseOfferingScanner.nextLine();
        if(courseAvailable.contains(tempo.split(",")[0].split("-")[0])
        ){
          String []tempoArray = tempo.split(",");
          Courses.add(new Course(tempoArray[0],tempoArray[1],tempoArray[2],tempoArray[4],tempoArray[5],tempoArray[6]));

        }
       }
      System.out.println(Courses);
    return Courses;
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
    return Courses;
  }



}


}
