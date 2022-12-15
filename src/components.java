import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class components {

    public static Label itemCard(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(new Font("Roboto", 18));
        titleLabel.setMaxWidth(200);

        return titleLabel;
    }

    // This is the method for the buttons in page1.
    public static Button AddRemoveButton(Course course, List<Course> coursesInBasket) {

        Button button = new Button("Add");
        button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        button.setMinWidth(60);

        button.setOnAction(e -> {
            if (button.getText().equals("Add")) {
                coursesInBasket.add(course);
                button.setText("Remove");
                button.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }

            else if (button.getText().equals("Remove")) {
                coursesInBasket.remove(course);
                button.setText("Add");
                button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            }

            // System.out.println(course.getTitle());
            // System.out.println(coursesInBasket);

        });

        return button;

    }

    // This method will be used to check whether the user is tryint to enter a
    // course that has another sections been added to it.
    public static boolean duplicateCourses(Course course, List<List> days) {
        for (int i = 0; i < days.size(); i++) {
            List<Course> tempCourses = days.get(i);

            for (int j = 0; j < tempCourses.size(); j++) {
                if (course.getTitle().split("-")[0].equals(tempCourses.get(j).getTitle().split("-")[0])) {
                    return true;
                }
            }

        }
        return false;
    }

    // This method for creating a card that will be added to the basket with the
    // functinoality of adding to schudgle.
    public static Button AddRemoveBasket(Course course, List<Course> coursesInJadwal, List<Course> coursesInBasket,
            VBox card, VBox cardsmol, List<List> dayLists, HBox calendar, Drawer drawer) {

        Button button = new Button("Add");

        button.setOnAction(e -> {
            if (button.getText().equals("Add")) {

                boolean doesConflict = checkConflict(dayLists, course);
                boolean dublicateCourses = duplicateCourses(course, dayLists);
                // Check if it conflicts with another section.
                if (doesConflict) {
                    // Display msg.
                    drawer.alertMsg();
                }
                // Check if another section is already in.
                else if (dublicateCourses) {
                    // Display msg.
                    drawer.courseDuplicate();
                }
                // Start creating the card for the basket.
                else {
                    coursesInJadwal.add(course);
                    coursesInBasket.remove(course);

                    System.out.println("Done1");
                    // Remove old children to add new ones.
                    calendar.getChildren().clear();
                    System.out.println("Done2");
                    // pass the new course to be added to the jadwal.
                    calendar.getChildren().addAll(addCourseToJadwal(course, dayLists, drawer, calendar));
                    System.out.println("Done3");
                    // Remove the card that has been pressed from basket.
                    card.getChildren().remove(cardsmol);
                }

                // Setstate.
                drawer.reDraw();
            }

            System.out.println(coursesInJadwal.size());
        });

        return button;

    }

    // This method will create the card in jadawa, it will take in considration the
    // lenght of the course and what time it should be, so it takes in perspictive
    // the breaks between classes.
    public static VBox jadwalCard(Course course, double height, Drawer drawer, List<List> days, HBox caledar) {

        Button button = new Button("Del");
        button.setOnAction(e -> {
            // Remove from jadwal when clicked.
            for (int i = 0; i < days.size(); i++) {
                if (days.get(i).contains(course)) {
                    days.get(i).remove(course);

                }
            }
            caledar.getChildren().clear();
            // Redraw the jadwal.
            caledar.getChildren().addAll(addCourseToJadwal(null, days, drawer, caledar));

        });
        VBox vBox = new VBox(0);
        vBox.setMinHeight(height);
        vBox.getChildren().addAll(new Label(course.getTitle()),
                button);
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n";
        vBox.setStyle(cssLayout);

        return vBox;
    }

    // This method is for checking if 2 courses will be conflict, this has been done
    // using comparator in class Course.
    public static boolean checkConflict(List<List> days, Course course) {
        boolean isConflict = false;
        char[] courseDays = course.getDays().toCharArray();

        for (Character day : courseDays) {
            // Check for each day.
            switch (day) {
                case 'U':
                    List<Course> tempoCourses1 = days.get(0);
                    for (int i = 0; i < tempoCourses1.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses1.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'M':
                    List<Course> tempoCourses2 = days.get(1);
                    for (int i = 0; i < tempoCourses2.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses2.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'T':
                    List<Course> tempoCourses3 = days.get(2);
                    for (int i = 0; i < tempoCourses3.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses3.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'W':
                    List<Course> tempoCourses4 = days.get(3);
                    for (int i = 0; i < tempoCourses4.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses4.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;
                case 'R':
                    List<Course> tempoCourses5 = days.get(4);
                    for (int i = 0; i < tempoCourses5.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses5.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

            }

        }
        return isConflict;

    }

    // This is the method that will generate a column full of cards for courses.
    public static List<VBox> columnMaker(List<Course> courses, List<List> days, Drawer drawer, HBox calendar) {
        Collections.sort(courses);
        List<VBox> columnFinal = new ArrayList<>();
        int base = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            VBox vBox = new VBox();
            vBox.setPrefHeight((course.getBeginHour() - base) * 2);
            columnFinal.add(vBox);
            VBox courseBox = jadwalCard(course, (course.getEndHour() - course.getBeginHour()) * 2, drawer, days,
                    calendar);
            columnFinal.add(courseBox);
            base = course.getEndHour();

        }

        return columnFinal;

    }

    // This method will draw the calendar again with the new changes, either delete
    // or add course.
    public static List<VBox> addCourseToJadwal(Course course, List<List> days, Drawer drawer, HBox oldCalendar) {
        VBox sunday = new VBox();
        VBox monday = new VBox();
        VBox tuesday = new VBox();
        VBox wednsday = new VBox();
        VBox thursday = new VBox();
        String cssLayout = "-fx-border-color: black;\n" +

                "-fx-border-width: 3;\n";
        sunday.setMinWidth(200);
        sunday.setStyle(cssLayout);

        monday.setStyle(cssLayout);
        monday.setPrefWidth(200);

        tuesday.setStyle(cssLayout);
        tuesday.setPrefWidth(200);

        wednsday.setStyle(cssLayout);
        wednsday.setPrefWidth(200);

        thursday.setStyle(cssLayout);
        thursday.setPrefWidth(200);
        List<VBox> calendar = new ArrayList<>();
        if (course != null) {

            char[] courseDays = course.getDays().toCharArray();

            System.out.println(days);

            for (Character day : courseDays) {
                switch (day) {
                    case 'U':

                        days.get(0).add(course);

                        break;

                    case 'M':
                        days.get(1).add(course);

                        break;

                    case 'T':
                        days.get(2).add(course);

                        break;

                    case 'W':
                        days.get(3).add(course);

                        break;

                    case 'R':
                        days.get(4).add(course);

                        break;

                }

                System.out.println(days);

            }
        }
        sunday.getChildren().addAll(columnMaker(days.get(0), days, drawer, oldCalendar));
        monday.getChildren().addAll(columnMaker(days.get(1), days, drawer, oldCalendar));
        tuesday.getChildren().addAll(columnMaker(days.get(2), days, drawer, oldCalendar));
        wednsday.getChildren().addAll(columnMaker(days.get(3), days, drawer, oldCalendar));
        thursday.getChildren().addAll(columnMaker(days.get(4), days, drawer, oldCalendar));

        calendar.add(sunday);
        calendar.add(monday);
        calendar.add(tuesday);
        calendar.add(wednsday);
        calendar.add(thursday);

        drawer.reDraw();
        return calendar;

    }

}
