import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class SecondPage {

    public static Scene Scene(javafx.event.EventHandler<javafx.event.ActionEvent> arg0, List<Course> basketCourses,
            Drawer drawer, Map<String, List<Course>> schuMap) {
        BorderPane pane2 = new BorderPane();
        Scene scene2;
        HBox labelDays = new HBox();

        Button button2 = new Button("Save Schedule");
        button2.setOnAction(e -> {
            try {
                FileOutputStream output = new FileOutputStream("saved.dat");
                ObjectOutputStream obj = new ObjectOutputStream(output);
                obj.writeObject(schuMap);
                System.out.println("saved!");

            } catch (FileNotFoundException e1) {
                System.out.println("File not found");
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });

        button2.setFont(new Font(25));
        button2.setMinHeight(40);
        button2.setMaxWidth(500);
        button2.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        button2.setPadding(new Insets(20));

        BorderPane.setAlignment(button2, Pos.CENTER);

        /// schedule days labels
        labelDays.setPadding(new Insets(0, 0, 0, 110));
        labelDays.setSpacing(80);
        labelDays.getChildren().addAll(
                components.itemCard("Sunday"),
                components.itemCard("Monday"),
                components.itemCard("Tuesday"),
                components.itemCard("Wedsnday"),
                components.itemCard("Thursday"));
        /// timing column for the schedule
        VBox labalTimes = new VBox();
        labalTimes.setStyle("-fx-background-color: gray");
        labalTimes.setSpacing(80);
        labalTimes.getChildren().addAll(

                components.itemCard("7:00"),
                components.itemCard("8:00"),
                components.itemCard("9:00"),
                components.itemCard("10:00"),
                components.itemCard("11:00"),
                components.itemCard("12:00"),
                components.itemCard("1:00"),
                components.itemCard("2:00"),
                components.itemCard("3:00"),
                components.itemCard("4:00"),
                components.itemCard("5:00"));

        // explain this Abdullah

        if (!schuMap.containsKey("Sunday")) {
            List<Course> sunday = new ArrayList<>();
            List<Course> monday = new ArrayList<>();
            List<Course> tuesday = new ArrayList<>();
            List<Course> wednsday = new ArrayList<>();
            List<Course> thursday = new ArrayList<>();

            schuMap.put("Sunday", sunday);
            schuMap.put("Monday", monday);
            schuMap.put("Tuesday", tuesday);
            schuMap.put("Wednsday", wednsday);
            schuMap.put("Thursday", thursday);

        } else {
            System.out.println("lmfao");
        }

        drawer.setMapDays(schuMap);

        VBox coursesColumn = new VBox();
        drawer.setCourseBakset(basketCourses);
        ScrollPane scrollPane2 = new ScrollPane();
        ScrollPane scrollPaneBasket = new ScrollPane();
        HBox calendar = new HBox(0);

        /// page Styling
        pane2.setStyle("-fx-background-color: gray");
        scrollPaneBasket.setStyle("-fx-background-color: gray");
        scrollPane2.setStyle("-fx-background-color: gray");
        calendar.setStyle("-fx-background-color: gray ; -fx-border-color: black; -fx-border-width: 3;");
        scrollPane2.setPadding(new Insets(0, 0, 0, 0));
        scrollPaneBasket.setPadding(new Insets(20, 0, 20, 0));
        scrollPaneBasket.setMinWidth(300);
        pane2.setPadding(new Insets(15, 20, 15, 20));
        calendar.setPrefWidth(800);
        calendar.setPrefHeight(2000);
        calendar.setPadding(new Insets(0, 0, 0, 0));
        coursesColumn.setMinWidth(300);
        coursesColumn.setPadding(new Insets(20, 10, 20, 10));
        coursesColumn.setSpacing(10);
        scrollPane2.setMaxWidth(900);

        ///

        drawer.setCalendar(calendar);
        calendar.getChildren().addAll(
                components.addCourseToJadwal(null, schuMap, drawer, calendar)

        );
        HBox schedulePart = new HBox();
        schedulePart.getChildren().addAll(labalTimes, calendar);
        scrollPane2.setContent(schedulePart);

        // calendar.setPadding(new Insets(0));

        pane2.setRight(scrollPaneBasket);
        scrollPaneBasket.setContent(coursesColumn);
        drawer.setVbasket(coursesColumn);
        pane2.setBottom(button2);

        VBox schedule = new VBox();
        schedule.getChildren().addAll(labelDays, scrollPane2);
        pane2.setCenter(schedule);
        /// here the code for adding course to the basket column
        for (int i = 0; i < basketCourses.size(); i++) {
            VBox courseCard = new VBox();
            courseCard.setAlignment(Pos.CENTER);
            courseCard.setStyle("-fx-background-color: gray");
            courseCard.setPadding(new Insets(10, 10, 10, 10));

            courseCard.getChildren().addAll(
                    components.itemCard(basketCourses.get(i).getTitle() + " " + basketCourses.get(i).getActivity()),
                    components.itemCard(basketCourses.get(i).getTime()),
                    components.itemCard(basketCourses.get(i).getDays()),

                    components.AddRemoveBasket(basketCourses.get(i), basketCourses, coursesColumn, courseCard,
                            schuMap,
                            calendar, drawer));

            courseCard.setAlignment(Pos.CENTER);
            coursesColumn.getChildren().addAll(courseCard);

        }

        scene2 = new Scene(pane2, 1200, 600);
        return scene2;
    }
}
