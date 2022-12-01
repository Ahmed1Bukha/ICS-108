import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;  
import java.util.Scanner; 

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.*;

public class App extends Application {
 @Override // Override the start method in the Application class
 public void start(Stage primaryStage) {
 // Create a button and place it in the scene
 Button btOK = new Button("OK");
 Scene scene = new Scene(btOK, 500, 250);
 primaryStage.setTitle("MyJavaFX"); // Set the stage title
 primaryStage.setScene(scene); // Place the scene in the stage
 primaryStage.show(); // Display the stage
 }

 public static void main(String[] args) {
  launch(args);
 }


 public static void coursesPossible (String coursesPath , String finishedPath){
    try {
        Scanner finishedScanner = new Scanner(new File(finishedPath));  


        FileWriter coursesToShow = new FileWriter(coursesPath);
        PrintWriter printWriter = new PrintWriter(coursesToShow);


        while(finishedScanner.hasNextLine()){


           String courseName =finishedScanner.nextLine().split(",")[0];
           Scanner kb_2 = new Scanner(new File("CourseOffering.csv"));   
           while(kb_2.hasNextLine()){
            String currentCourse = kb_2.nextLine();


            System.out.println(courseName+" lmfa");
            System.out.println(currentCourse.split(",")[0].split("-")[0]);

            if(courseName.equals(currentCourse.split(",")[0].split("-")[0])){
                
                printWriter.println(currentCourse);
            }
           }

        }
       
     

        
        coursesToShow.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  


    }
 



}
