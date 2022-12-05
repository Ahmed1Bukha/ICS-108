import java.util.List;

import javafx.scene.control.Button;;

public class AddRemoveButton {
    Course course;
     List<Course> coursesInBasket;
    public AddRemoveButton(Course course, List<Course> coursesInBasket){
        this.course = course;
        this.coursesInBasket = coursesInBasket;
    }



    public Button returnButton(){

        Button button = new Button("Add");
        button.setOnAction(e -> {
            if(button.getText().equals("Add")){
                coursesInBasket.add(course);
                button.setText("Remove");
            }

            else if(button.getText().equals("Remove")){
                coursesInBasket.remove(course);
                button.setText("Add");
            }
            
            



            System.out.println(course.getTitle());
            System.out.println(coursesInBasket);
        });

        return button;


    }
}
