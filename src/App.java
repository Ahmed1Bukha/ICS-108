import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;  
import java.util.Scanner; 

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.*;

public class App extends Application {
  Scene scene1, scene2;
 @Override // Override the start method in the Application class
 public void start(Stage primaryStage) {
  primaryStage.setTitle("My First JavaFX GUI");

  //Scene 1 Ahmed's Work
  
  Button button1= new Button("Next");
  BorderPane pane1 = new BorderPane();
  
  button1.setOnAction(e -> primaryStage.setScene(scene2));  
  pane1.setPadding(new Insets(4,4,4,4));
  pane1.setBottom(button1);
  BorderPane.setAlignment(button1, Pos.TOP_RIGHT);
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
 
  FileWriter finalCoursesWriter = new FileWriter("coursesDoable.txt");
  PrintWriter printWriter = new PrintWriter(finalCoursesWriter);


    while(finishedCourseScanner.hasNextLine()){


       String courseName =finishedCourseScanner.nextLine().split(",")[0];
       Scanner kb_2 = new Scanner(new File(coursesPath));   
       Scanner degreePlanScanner = new Scanner(new File(dgreePlanPath)); 
       String courseEnable = "";
      while(degreePlanScanner.hasNextLine()){
        String [] tempoCourse = degreePlanScanner.nextLine().split(",");
        
        if(tempoCourse[2].equals(courseName)){
          courseEnable = tempoCourse[0];
          System.out.println(courseEnable);
          break;
        }

      }

       while(kb_2.hasNextLine()){
        String currentCourse = kb_2.nextLine();


        

        if(currentCourse.split(",")[0].split("-")[0].equals(courseEnable)){
            
          String []tempo = currentCourse.split(",");
            printWriter.println(currentCourse);
            Courses.add(new Course(tempo[0],tempo[1],tempo[2],tempo[4],tempo[5],tempo[6]));
          


        }
       }

    }
   
 

    
    finalCoursesWriter.close();
    System.out.println("Successfully wrote to the file.");


    System.out.println(Courses);

    return Courses;
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
    return Courses;
  }



}


}
