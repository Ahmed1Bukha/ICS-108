


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
        Scanner finishedCourseScanner = new Scanner(new File(finishedPath));  


        FileWriter finalCoursesWriter = new FileWriter("coursesDoable.txt");
        PrintWriter printWriter = new PrintWriter(finalCoursesWriter);


        while(finishedCourseScanner.hasNextLine()){


           String courseName =finishedCourseScanner.nextLine().split(",")[0];
           Scanner kb_2 = new Scanner(new File(coursesPath));   
           while(kb_2.hasNextLine()){
            String currentCourse = kb_2.nextLine();


            System.out.println(courseName+" lmfa");
            System.out.println(currentCourse.split(",")[0].split("-")[0]);

            if(courseName.equals(currentCourse.split(",")[0].split("-")[0])){
                
                printWriter.println(currentCourse);
                System.out.println("Done");
            }
           }

        }
       
     

        
        finalCoursesWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  


    }
    
    


}