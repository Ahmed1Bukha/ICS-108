import java.util.Calendar;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Drawer {
  
  
    private Stage stage;
    private HBox calendar;

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

}
