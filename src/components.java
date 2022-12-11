import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class components {
  
    public static Label itemCard(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(new Font("Roboto", 18));

        return titleLabel;
    }

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


            //System.out.println(course.getTitle());
            //System.out.println(coursesInBasket);

            
        });

        return button;

    }

    public static Button AddRemoveBasket(Course course, List<Course> coursesInJadwal,List<Course> coursesInBasket, Stage primaryStage,Scene scene,VBox card,VBox cardsmol) {

        Button button = new Button("Add");
        
        button.setOnAction(e -> {
            if (button.getText().equals("Add")) {
                coursesInJadwal.add(course);
                coursesInBasket.remove(course);

                card.getChildren().remove(cardsmol);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            

            System.out.println(coursesInJadwal.size());
        });

        return button;

    }

    public static VBox jadwalButton(Course course, double height){
       
        Button button = new Button("Del");
        button.setOnAction(e->{
            System.out.println("Course");
        });
        VBox vBox = new VBox(0);
        vBox.setMinHeight(height);
        vBox.getChildren().addAll(new Label(course.getTitle()),
        button
        );
        String cssLayout = "-fx-border-color: black;\n" +
        "-fx-border-insets: 5;\n" +
        "-fx-border-width: 3;\n";
        vBox.setStyle(cssLayout);
        
        VBox.setMargin(vBox, new Insets(0));
        return vBox;
    }

    public static List<VBox> columnMaker(List<Course> courses){
        List<VBox> columnFinal = new ArrayList<>();
        int base=0;
        for(int i=0; i< courses.size(); i++){
            Course course = courses.get(i);

                VBox vBox = new VBox();
                vBox.setPrefHeight((course.getBeginHour() - base)*2);
                columnFinal.add(vBox);
                VBox courseBox = jadwalButton(course, (course.getEndHour()-course.getBeginHour())*2);
                columnFinal.add(courseBox);
                base = course.getEndHour();


            


        }



        return columnFinal;

    }


 

}
