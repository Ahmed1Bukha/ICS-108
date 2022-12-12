import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Course implements Comparable<Course>{

    private String title;
    private String activity;
    private String CRN;
    private String instructor;
    private String days;
    private String time;
    private int beginHour;
    private int totalTime;
    private int endTime;

    public Course(String title, String activity, String CRN, String instructor, String days, String time) {
        this.title = title;
        this.activity = activity;
        this.CRN = CRN;
        this.instructor = instructor;
        this.days = days;
        this.time = time;
        setCells(time);

    }

    public String getTitle() {
        return this.title;
    }

    public String getActivity() {
        return this.activity;
    }

    public String getCRN() {
        return this.CRN;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public String getDays() {
        return this.days;
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        return this.title;
    }


    public boolean courseConflict(Course course){
      boolean isConflict = false;

      if(this.beginHour == course.beginHour){
        isConflict = true;
      }
        else if(this.beginHour > course.beginHour && this.endTime<course.beginHour){
            System.out.println(1);
            isConflict = true;
        }
        else if(this.beginHour  > course.endTime &&  this.beginHour< course.beginHour){
            isConflict=true;
            System.out.println(2);
        }

        else if(this.beginHour> course.beginHour && this.endTime< course.endTime){
            isConflict= true;
            System.out.println(3);
        }
        return isConflict;

    }
    public void setCells(String time){
        String []tempo = time.split("-");
        String beginTime = tempo[0];
        int beginHour = Integer.parseInt(beginTime.substring(0, 2));
        int beginMin = Integer.parseInt(beginTime.substring(2, 4));

        String endTime = tempo[1];
        int endHour = Integer.parseInt(endTime.substring(0, 2));
        int endMin = Integer.parseInt(endTime.substring(2, 4));

        System.out.println(beginMin+"begin");
        System.out.println(endMin+"end");


  

    this.beginHour = ((beginHour-7)*60)+(beginMin);
    this.endTime = ((endHour-7)*60)+(endMin);

    this.totalTime = (((endMin+((endHour-7)*60)) - (beginMin+((beginHour-7)*60))))/12;

    System.out.println(this.totalTime);
     
    }

    public int totalHours(){
        System.out.println(totalTime);
        return this.totalTime;
    }

    public int getBeginHour(){
        return this.beginHour;
    }
    public int getEndHour(){
        return this.endTime;
    }

    public static List<Course> coursesPossible(String coursesPath, String finishedPath, String dgreePlanPath) {
        List<Course> Courses = new ArrayList<Course>();

        try {

            Scanner finishedCourseScanner = new Scanner(new File(finishedPath));

            List<String> finishedCourse = new ArrayList<String>();
            List<String> courseAvailable = new ArrayList<String>();
            // It will check every finished course.
            BufferedReader br = new BufferedReader(new FileReader(new File(coursesPath)));
            String courseName = "";
            while (finishedCourseScanner.hasNextLine()) {

                courseName = finishedCourseScanner.nextLine().split(",")[0];
                finishedCourse.add(courseName);
                System.out.println((finishedCourse));
            }

            Scanner courseOfferingScanner = new Scanner(new File(coursesPath));
            Scanner degreePlanScanner = new Scanner(new File(dgreePlanPath));

            while (degreePlanScanner.hasNextLine()) {
                String[] tempo = degreePlanScanner.nextLine().split(",");
                if (finishedCourse.contains(tempo[2]) && !finishedCourse.contains(tempo[0])) {
                    courseAvailable.add(tempo[0]);
                }
            }
            System.out.println(courseAvailable);


            String availalbe;
            while ((availalbe = br.readLine()) != null) {
                String tempo =availalbe;
                if (courseAvailable.contains(tempo.split(",")[0].split("-")[0])) {
                    String[] tempoArray = tempo.split(",");
                    Courses.add(
                            new Course(tempoArray[0], tempoArray[1], tempoArray[2], tempoArray[4], tempoArray[5],
                                    tempoArray[6]));

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



    
    @Override
    public int compareTo(Course o) {
       
        if(this.getBeginHour()> o.getBeginHour()){
            return 1;
        }
        else if(this.getBeginHour()<o.getBeginHour()){
            return -1;
        }
        else{
            return 0;
        }
    }

}