import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class components {

    public static Label itemCard(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setPadding(new Insets(0, 0, 10, 0));
        titleLabel.setFont(new Font("Roboto", 18));

        return titleLabel;
    }

    // This is the method for the buttons in page1.
    public static Button AddRemoveButton(Course course, List<Course> coursesInBasket, Map<String, List<Course>> schuMap) {

        Button button = new Button("Add");
        button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        button.setMinWidth(60);

        button.setOnAction(e -> {

            boolean isBreak = false;

            for (Map.Entry<String, List<Course>> entry : schuMap.entrySet()){

                List<Course> tempo = entry.getValue();

                for(int i=0;i < tempo.size(); i++){
                    if(tempo.get(i).getTitle().equals(course.getTitle())){
                        Alert a1 = new Alert(AlertType.NONE,
                        "The coures is already in the schedule!",ButtonType.APPLY);
                  
                        a1.show();
                        isBreak =true;
                        break;
                        

          
                    }
                    
                }
                if(isBreak){
                    break;
                }
                }


            if (button.getText().equals("Add") && !isBreak) {
               
           

                coursesInBasket.add(course);
                button.setText("Remove");
                button.setStyle("-fx-background-color: red; -fx-text-fill: white;");

            }

            else if (button.getText().equals("Remove" )&& !isBreak
            
            ) {
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
    public static boolean duplicateCourses(Course course, Map<String, List<Course>> days) {
        for (Map.Entry<String, List<Course>> entry : days.entrySet()) {
            {
                List<Course> tempCourses = entry.getValue();

                for (int j = 0; j < tempCourses.size(); j++) {
                    if (course.getTitle().split("-")[0].equals(tempCourses.get(j).getTitle().split("-")[0])
                            && course.getActivity().equals(tempCourses.get(j).getActivity())) {
                        System.out.println();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // This method for creating a card that will be added to the basket with the
    // functinoality of adding to schudgle.
    public static Button AddRemoveBasket(Course course, List<Course> coursesInBasket,
            VBox card, VBox cardsmol, Map<String, List<Course>> dayLists, HBox calendar, Drawer drawer) {

        Button button = new Button("Add");
        button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        button.setMinWidth(70);
        button.setMinHeight(20);

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

                    System.out.println(dayLists);
                }

                // Setstate.
                drawer.reDraw();
            }

        });

        return button;

    }

    // This method will create the card in jadawa, it will take in considration the
    // lenght of the course and what time it should be, so it takes in perspictive
    // the breaks between classes.
    public static VBox jadwalCard(Course course, double height, Drawer drawer, Map<String, List<Course>> days,
            HBox caledar) {

        Button button = new Button("Delete");
        button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        button.setPrefHeight(70);
        button.setMaxHeight(20);
        button.setOnAction(e -> {
            // Remove from jadwal when clicked.
            for (Map.Entry<String, List<Course>> entry : days.entrySet()) {
                if (entry.getValue().contains(course)) {
                    entry.getValue().remove(course);

                }
            }
            VBox courseCard = new VBox(0);

            courseCard.setAlignment(Pos.CENTER);
            courseCard.setStyle("-fx-background-color: gray");
            courseCard.setPadding(new Insets(10, 10, 10, 10));
            courseCard.getChildren().addAll(
                    components.itemCard(course.getTitle() + " " + course.getActivity()),
                    components.itemCard(course.getDays()),
                    components.itemCard(course.getTime()),
                    components.AddRemoveBasket(course, drawer.getCourseBasket(), drawer.getVBasket(), courseCard,
                            drawer.getMapDays(),
                            drawer.getCalendar(), drawer));

            courseCard.setAlignment(Pos.CENTER);
            drawer.getVBasket().getChildren().addAll(courseCard);

            caledar.getChildren().clear();
            System.out.println("Delete");
            caledar.getChildren().addAll(addCourseToJadwal(null, days, drawer, caledar));

        });
        VBox vBox = new VBox(0);
        vBox.setMinHeight(height);

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(components.itemCard(course.getTitle()),
                components.itemCard(course.getTime()),

                button);
        // String cssLayout = "-fx-border-color: black;\n" +
        // "-fx-border-insets: 5;\n" +
        // "-fx-border-width: 3;\n";
        vBox.setStyle("-fx-background-color: green");

        VBox pane = new VBox();
        pane.setPadding(new Insets(0));
        pane.getChildren().add(vBox);

        return pane;
    }

    // This method is for checking if 2 courses will be conflict, this has been done
    // using comparator in class Course.
    public static boolean checkConflict(Map<String, List<Course>> days, Course course) {
        boolean isConflict = false;
        char[] courseDays = course.getDays().toCharArray();

        for (Character day : courseDays) {
            // Check for each day.
            switch (day) {
                case 'U':
                    List<Course> tempoCourses1 = days.get("Sunday");
                    for (int i = 0; i < tempoCourses1.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses1.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'M':
                    List<Course> tempoCourses2 = days.get("Monday");
                    for (int i = 0; i < tempoCourses2.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses2.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'T':
                    List<Course> tempoCourses3 = days.get("Tuesday");
                    for (int i = 0; i < tempoCourses3.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses3.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;

                case 'W':
                    List<Course> tempoCourses4 = days.get("Wednsday");
                    for (int i = 0; i < tempoCourses4.size(); i++) {
                        isConflict = course.courseConflict(tempoCourses4.get(i));
                        if (isConflict) {
                            return true;
                        }
                    }
                    break;
                case 'R':
                    List<Course> tempoCourses5 = days.get("Thursday");
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
    public static List<VBox> columnMaker(List<Course> courses, Map<String, List<Course>> days, Drawer drawer,
            HBox calendar) {
        Collections.sort(courses);
        List<VBox> columnFinal = new ArrayList<>();
        int base = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            VBox spacing = new VBox(0);
            spacing.setPadding(new Insets(0, 0, 0, 0));
            VBox.setMargin(spacing, new Insets(0, 0, 0, 0));
            spacing.setPrefHeight((course.getBeginHour() - base) * 2);

            columnFinal.add(spacing);
            VBox courseBox = jadwalCard(course, (course.getEndHour() - course.getBeginHour()) * 2, drawer, days,
                    calendar);
            VBox.setMargin(courseBox, new Insets(0, 0, 0, 0));
            columnFinal.add(courseBox);
            base = course.getEndHour();

        }

        return columnFinal;

    }

    // This method will draw the calendar again with the new changes, either delete
    // or add course.
    public static List<VBox> addCourseToJadwal(Course course, Map<String, List<Course>> days, Drawer drawer,
            HBox oldCalendar) {

        VBox sunday = new VBox(0);
        VBox monday = new VBox(0);
        VBox tuesday = new VBox(0);
        VBox wednsday = new VBox(0);
        VBox thursday = new VBox(0);

        String cssLayout = "-fx-border-color: black;\n" +

                "-fx-border-width: 3;\n";

        sunday.setPrefWidth(200);

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

                        days.get("Sunday").add(course);

                        break;

                    case 'M':
                        days.get("Monday").add(course);

                        break;

                    case 'T':
                        days.get("Tuesday").add(course);

                        break;

                    case 'W':
                        days.get("Wednsday").add(course);

                        break;

                    case 'R':
                        days.get("Thursday").add(course);

                        break;

                }

                System.out.println(days);

            }
        }
        sunday.setPadding(new Insets(0, 0, 0, 0));
        monday.setPadding(new Insets(0, 0, 0, 0));
        tuesday.setPadding(new Insets(0, 0, 0, 0));
        wednsday.setPadding(new Insets(0, 0, 0, 0));
        thursday.setPadding(new Insets(0, 0, 0, 0));

        sunday.setSpacing(0);

        System.out.println(columnMaker(days.get("Sunday"), days, drawer, oldCalendar));
        sunday.getChildren().addAll(columnMaker(days.get("Sunday"), days, drawer, oldCalendar));
        monday.getChildren().addAll(columnMaker(days.get("Monday"), days, drawer, oldCalendar));
        tuesday.getChildren().addAll(columnMaker(days.get("Tuesday"), days, drawer, oldCalendar));
        wednsday.getChildren().addAll(columnMaker(days.get("Wednsday"), days, drawer, oldCalendar));
        thursday.getChildren().addAll(columnMaker(days.get("Thursday"), days, drawer, oldCalendar));

        calendar.add(sunday);
        calendar.add(monday);
        calendar.add(tuesday);
        calendar.add(wednsday);
        calendar.add(thursday);

        drawer.reDraw();
        return calendar;

    }

}
