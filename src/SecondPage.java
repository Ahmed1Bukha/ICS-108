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
            Drawer drawer, Map<String,List<Course>>schuMap) {
        BorderPane pane2 = new BorderPane();
        Scene scene2;

        
       if(!schuMap.containsKey("Sunday")){
        List<Course> sunday = new ArrayList<>();
        List<Course> monday = new ArrayList<>();
        List<Course> tuesday = new ArrayList<>();
        List<Course> wednsday = new ArrayList<>();
        List<Course> thursday = new ArrayList<>();

        schuMap.put("Sunday",sunday);
        schuMap.put("Monday",monday);
        schuMap.put("Tuesday",tuesday);
        schuMap.put("Wednsday",wednsday);
        schuMap.put("Thursday",thursday);
       }
       else{
        System.out.println("lmfao");
       }
       
       



        List<Course> jadwal = new ArrayList<>();
        VBox coursesColumn = new VBox();
        ScrollPane scrollPane2 = new ScrollPane();
        ScrollPane scrollPaneBasket = new ScrollPane();
        HBox calendar = new HBox();
        pane2.setStyle("-fx-background-color: gray");
        scrollPaneBasket.setStyle("-fx-background-color: gray");
        scrollPane2.setStyle("-fx-background-color: gray");
        calendar.setStyle("-fx-background-color: gray ; -fx-border-color: black; -fx-border-width: 3;");
        scrollPane2.setPadding(new Insets(20, 0, 20, 0));
        scrollPaneBasket.setPadding(new Insets(20, 0, 20, 0));

        calendar.setPrefWidth(800);
        calendar.setMinHeight(2000);
        calendar.setPadding(new Insets(0, 20, 0, 20));
        scrollPane2.setMaxWidth(900);
        calendar.getChildren().addAll(
components.addCourseToJadwal(null, schuMap, drawer, calendar)

        );
        scrollPane2.setContent(calendar);

        calendar.setPadding(new Insets(0));
        Button button2 = new Button("Save Schedule");
                
        button2.setOnAction(e ->{
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

        scrollPaneBasket.setMinWidth(300);
        pane2.setRight(scrollPaneBasket);
        coursesColumn.setMinWidth(300);
        coursesColumn.setPadding(new Insets(20, 10, 20, 10));
        coursesColumn.setSpacing(10);
        scrollPaneBasket.setContent(coursesColumn);

        System.out.println(basketCourses.size());

        // pane2.setPadding(new Insets(30));
        pane2.setPadding(new Insets(15, 20, 15, 20));

        pane2.setBottom(button2);

        pane2.setCenter(scrollPane2);
        for (int i = 0; i < basketCourses.size(); i++) {
            // BorderPane pane= new BorderPane();
            VBox courseCard = new VBox();
            courseCard.setAlignment(Pos.CENTER);
            courseCard.setStyle("-fx-background-color: gray");
            courseCard.setPadding(new Insets(10, 10, 10, 10));

            courseCard.getChildren().addAll(
                    components.itemCard(basketCourses.get(i).getTitle() + " " + basketCourses.get(i).getActivity()),
                    components.itemCard(basketCourses.get(i).getDays()),
                    // there is a problem with this button, I'll fix it
                    components.AddRemoveBasket(basketCourses.get(i), jadwal, basketCourses, coursesColumn, courseCard,
                            schuMap,
                            calendar, drawer));

            courseCard.setAlignment(Pos.CENTER);
            coursesColumn.getChildren().addAll(courseCard);

        }
        scene2 = new Scene(pane2, 1200, 600);
        return scene2;
    }
}
