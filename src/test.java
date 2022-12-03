


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