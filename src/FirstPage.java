
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FirstPage {

    public static Scene scene(List<Course> basketCourses, Stage primaryStage, Drawer drawer) {
        Scene scene1;
        ScrollPane scrollPane1 = new ScrollPane();
        Button button1 = new Button("Schedule");
        button1.setFont(new Font(25));
        button1.setMinHeight(40);
        button1.setMaxWidth(500);
        button1.setPadding(new Insets(20));

        button1.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        BorderPane pane1 = new BorderPane();
        Label title = new Label("Add to Basket");

        title.setStyle("-fx-text-fill: white");
        title.setAlignment(Pos.CENTER);
        List<Course> coursesOffered = Course.coursesPossible("CourseOffering.csv", "FinishedCourses.csv",
                "DegreePlan.csv");

        VBox vbox = new VBox(coursesOffered.size() + 1);

        HBox courseOfferingTitles = new HBox(6);
        /// Coloring of the page
        pane1.setStyle("-fx-background-color: gray");
        vbox.setStyle("-fx-background-color: gray");
        scrollPane1.setStyle("-fx-background-color: gray");
        courseOfferingTitles.setStyle("-fx-background-color: black");
        /// titles of courses row
        courseOfferingTitles.getChildren().addAll(
                components.itemCard("        Title     "),
                components.itemCard("    Activity  "),
                components.itemCard(" CRN "),
                components.itemCard("           Instructor   "),
                components.itemCard("           Time     "),
                components.itemCard("    Days "));

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

            BorderPane pane = new BorderPane();
            pane.setCenter(courseRow);
            pane.setRight(components.AddRemoveButton(coursesOffered.get(i), basketCourses));
            pane.setPadding(new Insets(0, 10, 0, 0));

            ;
            vbox.getChildren().add(pane);
        }

        title.setFont(new Font("Arial", 40));

        pane1.setPadding(new Insets(15, 20, 15, 20));
        scrollPane1.fitToWidthProperty().set(true);
        scrollPane1.setPadding(new Insets(20, 0, 20, 0));
        ////// will fix and design it later
        VBox topBox = new VBox();
        topBox.getChildren().addAll(title, courseOfferingTitles);
        topBox.setSpacing(15);
        topBox.setAlignment(Pos.BASELINE_CENTER);
        pane1.setTop(topBox);
        pane1.setBottom(button1);

        scrollPane1.setContent(vbox);

        pane1.setCenter(scrollPane1);

        BorderPane.setAlignment(button1, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(scrollPane1, Pos.CENTER);
        scene1 = new Scene(pane1, 1200, 600);
        button1.setOnAction(e -> {
            primaryStage.setScene(SecondPage.Scene(ee -> {
                primaryStage.setScene(scene1);
                basketCourses.removeAll(basketCourses);
            }, basketCourses, drawer));
        });
        return scene1;
        
    }
}