
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FirstPage {

    public static Scene scene(List<Course> basketCourses, Stage primaryStage, Drawer drawer) {
        Scene scene1;
        ScrollPane scrollPane1 = new ScrollPane();
        BorderPane pane1 = new BorderPane();
        VBox coursesDataColumn = new VBox(6);
        Map<String, List<Course>> schuMap = new LinkedHashMap<String, List<Course>>();

        // Schedule button and its style
        Button button1 = new Button("Schedule");
        button1.setFont(new Font(25));
        button1.setMinHeight(40);
        button1.setMaxWidth(500);
        button1.setPadding(new Insets(20));
        button1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        /// this button is for saving the schedule data in dat file
        Button startWithSaved = new Button("Start with saved Schedule");
        startWithSaved.setFont(new Font(15));
        startWithSaved.setMinHeight(40);
        startWithSaved.setPadding(new Insets(10));
        startWithSaved.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        Label title = new Label("Add to Basket");
        title.setFont(new Font("Arial", 40));
        title.setStyle("-fx-text-fill: white");
        startWithSaved.setOnAction(e -> {
            try {
                FileInputStream file = new FileInputStream("saved.dat");
                ObjectInputStream inputObj = new ObjectInputStream(file);

                Map<String, List<Course>> newMap = (Map<String, List<Course>>) inputObj.readObject();
                for (String key : newMap.keySet()) {
                    schuMap.put(key, newMap.get(key));
                    System.out.println(newMap);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        });

        HBox courseOfferingTitles = new HBox(6);
        /// Coloring of the page
        pane1.setStyle("-fx-background-color: gray");
        coursesDataColumn.setStyle("-fx-background-color: gray");
        scrollPane1.setStyle("-fx-background-color: gray");
        courseOfferingTitles.setStyle("-fx-background-color: black");
        /// alignments

        BorderPane.setAlignment(button1, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(scrollPane1, Pos.CENTER);
        /// titles of courses row
        courseOfferingTitles.getChildren().addAll(
                components.itemCard("        Title     "),
                components.itemCard("    Activity  "),
                components.itemCard(" CRN "),
                components.itemCard("           Instructor   "),
                components.itemCard("           Time     "),
                components.itemCard("    Days "));

        /// this the list of courses which will be used in List view

        List<Course> coursesOffered = Course.coursesPossible("CourseOffering.csv", "FinishedCourses.csv",
                "DegreePlan.csv");
        /// Courses List view
        for (int i = 0; i < coursesOffered.size(); i++) {
            HBox courseRow = new HBox(8);

            courseRow.getChildren().addAll(
                    components.itemCard("  " + coursesOffered.get(i).getTitle()),
                    components.itemCard("   " + coursesOffered.get(i).getActivity() + "   "),
                    components.itemCard("   " + coursesOffered.get(i).getCRN()),
                    components.itemCard(coursesOffered.get(i).getInstructor()),
                    components.itemCard(coursesOffered.get(i).getTime()),
                    components.itemCard("      " + coursesOffered.get(i).getDays()),
                    components.itemCard("                                    ")

            );
            /// the row which contains the course data and the add/remove button
            BorderPane pane = new BorderPane();
            pane.setCenter(courseRow);
            pane.setRight(components.AddRemoveButton(coursesOffered.get(i), basketCourses));
            pane.setPadding(new Insets(0, 10, 0, 0));

            ;
            coursesDataColumn.getChildren().add(pane);
        }

        pane1.setPadding(new Insets(15, 20, 15, 20));
        scrollPane1.fitToWidthProperty().set(true);
        scrollPane1.setPadding(new Insets(20, 0, 20, 0));
        ////// this the Fist row that contains the title and saving button
        BorderPane pane = new BorderPane();
        pane.setRight(startWithSaved);
        pane.setCenter(title);
        pane.setPadding(new Insets(0, 10, 0, 0));
        /// top Box is the title and button and course offering titles
        VBox topBox = new VBox();
        topBox.getChildren().addAll(pane, courseOfferingTitles);
        topBox.setSpacing(25);
        topBox.setAlignment(Pos.BASELINE_CENTER);
        pane1.setTop(topBox);

        /// setting button at the bottom
        pane1.setBottom(button1);

        /// here we are setting the whole courses data to the main pane
        scrollPane1.setContent(coursesDataColumn);

        pane1.setCenter(scrollPane1);

        scene1 = new Scene(pane1, 1200, 600);
        button1.setOnAction(e -> {
            primaryStage.setScene(SecondPage.Scene(ee -> {
                primaryStage.setScene(scene1);
                basketCourses.removeAll(basketCourses);
            }, basketCourses, drawer, schuMap));
        });

        return scene1;

    }

}