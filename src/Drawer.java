import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Drawer {
  
    private HBox calendar;  
    private VBox vBaskest;
    private Stage stage;
    private Map<String,List<Course>> mapDays;
    private List<Course> courseBasket;

        public Drawer(Stage stage){
            this.stage = stage;
     
        }



        public void reDraw(){
            this.stage.show();
        }

        public void alertMsg(){
            Alert a1 = new Alert(AlertType.NONE,
                              "Make sure that you don't have conflict courses!",ButtonType.APPLY);
 
                // show the dialog
                a1.show();
        }

        public void courseDuplicate(){
            Alert a1 = new Alert(AlertType.NONE,
                              "Make sure that you have 1 section per-course.",ButtonType.APPLY);
 
                // show the dialog
                a1.show();
        }

        public void setCourseBakset(List<Course> basketCourses){
            this.courseBasket = basketCourses;
        }

        public List<Course> getCourseBasket(){
            return this.courseBasket;
        }


        public void setMapDays(Map<String,List<Course>> mapDays){
            this.mapDays = mapDays;
        }

        public Map<String,List<Course>> getMapDays(){
            return this.mapDays;
        }
        public void setCalendar(HBox caleBox){
            this.calendar = caleBox;
        }
        public HBox getCalendar (){
            return this.calendar;
        }
        public void setVbasket(VBox box){
            this.vBaskest = box;
        }
        public VBox getVBasket()
        {return this.vBaskest;}        

}
