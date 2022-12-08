import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    public static Button AddRemoveBasket(Course course, List<Course> coursesInJadwal,List<Course> coursesInBasket) {

        Button button = new Button("Add");
        
        button.setOnAction(e -> {
            if (button.getText().equals("Add")) {
                coursesInJadwal.add(course); 
                coursesInBasket.remove(course);
            }
            

            System.out.println(coursesInJadwal.size());
        });

        return button;

    }

}
