


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