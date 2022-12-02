public class Course {

    private String title;
    private String activity;
    private String CRN;
  
    private String days;
    private String time;


    public Course(String title,String prerequist, String corequist,String days,String time){
        this.title = title;
       
        this.days = days;
        this.time = time;
        

    }
    
}