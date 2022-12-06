import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course {

    private String title;
    private String activity;
    private String CRN;
    private String instructor;
    private String days;
    private String time;

    public Course(String title, String activity, String CNR, String instructor, String days, String time) {
        this.title = title;
        this.activity = activity;
        this.CRN = CRN;
        this.instructor = instructor;
        this.days = days;
        this.time = time;

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

    public static List<Course> coursesPossible(String coursesPath, String finishedPath, String dgreePlanPath) {
        List<Course> Courses = new ArrayList<Course>();

        try {

            Scanner finishedCourseScanner = new Scanner(new File(finishedPath));

            List<String> finishedCourse = new ArrayList<String>();
            List<String> courseAvailable = new ArrayList<String>();
            // It will check every finished course.

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

            while (courseOfferingScanner.hasNextLine()) {
                String tempo = courseOfferingScanner.nextLine();
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
}