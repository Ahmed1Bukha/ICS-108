


import java.io.*;  
import java.util.Scanner; 

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.*;
public class test {  
    public static void main(String[] args) throws Exception  
    {  
    
        coursesPossible("CourseOffering.csv", "FinishedCourses.csv","DegreePlan.csv");

    }
    


    public static void coursesPossible (String coursesPath , String finishedPath, String dgreePlanPath){
    try {

      List<String> Courses =new ArrayList<String>();  

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