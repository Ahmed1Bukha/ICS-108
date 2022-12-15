import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import java.util.*;

public class SecondPage {

    public static Scene Scene(javafx.event.EventHandler<javafx.event.ActionEvent> arg0, List<Course> basketCourses,
            Drawer drawer) {
        BorderPane pane2 = new BorderPane();
        Scene scene2;

        List<Course> sunday = new ArrayList<>();
        List<Course> monday = new ArrayList<>();
        List<Course> tuesday = new ArrayList<>();
        List<Course> wednsday = new ArrayList<>();
        List<Course> thursday = new ArrayList<>();
        ArrayList<List> days = new ArrayList<>();

        days.add(sunday);
        days.add(monday);
        days.add(tuesday);
        days.add(wednsday);
        days.add(thursday);

        List<Course> jadwal = new ArrayList<>();
        VBox coursesColumn = new VBox();
        ScrollPane scrollPane2 = new ScrollPane();
        String cssLayout = "-fx-border-color: black;\n" +

                "-fx-border-width: 3;\n";
        HBox calendar = new HBox();

        calendar.setPrefWidth(900);
        calendar.setMinHeight(2000);
        scrollPane2.setMaxWidth(900);

        scrollPane2.setContent(calendar);

        calendar.setStyle(cssLayout);
        calendar.setPadding(new Insets(0));
        Button button2 = new Button("Go to scene 1");
        button2.setOnAction(arg0);

        ScrollPane scollpaneBasket = new ScrollPane();
        scollpaneBasket.setMinWidth(300);
        pane2.setRight(scollpaneBasket);
        coursesColumn.setMinWidth(300);
        scollpaneBasket.setContent(coursesColumn);

        System.out.println(basketCourses.size());

        pane2.setPadding(new Insets(10, 10, 10, 01));

        pane2.setBottom(button2);
        scene2 = new Scene(pane2, 1200, 600);

        pane2.setCenter(scrollPane2);
        for (int i = 0; i < basketCourses.size(); i++) {
            VBox courseCard = new VBox();
            courseCard.setPadding(new Insets(10, 10, 10, 10));

            courseCard.getChildren().addAll(
                    (new Label(basketCourses.get(i).getTitle() + " " + basketCourses.get(i).getActivity())),
                    new Label(basketCourses.get(i).getDays()),
                    // there is a problem with this button, I'll fix it
                    components.AddRemoveBasket(basketCourses.get(i), jadwal, basketCourses, coursesColumn, courseCard,
                            days,
                            calendar, drawer));

            courseCard.setAlignment(Pos.CENTER);
            coursesColumn.getChildren().addAll(courseCard);

        }
        return scene2;
    }
}
