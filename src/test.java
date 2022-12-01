


import java.io.*;  
import java.util.Scanner; 

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.*;
public class test {  
    public static void main(String[] args) throws Exception  
    {  
    
        coursesPossible("CourseOffering.csv", "coursesDoable.txt");

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