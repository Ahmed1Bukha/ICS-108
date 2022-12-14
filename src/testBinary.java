import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class testBinary {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("saved.dat"));
            ObjectInputStream input = new ObjectInputStream(file);
            Course course = (Course) input.readObject();
            System.out.println(course);
        
          } catch (FileNotFoundException e1) {
            
           System.out.println("File not found!");
          } catch (IOException e1) {
           
           System.out.println("IOException ");
          } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        }
    

}
