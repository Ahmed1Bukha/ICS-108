public class Course {

    private String title;
    private String activity;
    private String CRN;
    private String instructor;
    private String days;
    private String time;


    public Course(String title,String activity,String CNR,String instructor,String days,String time){
        this.title = title;
        this.activity = activity;
        this.CRN = CRN;
        this.instructor = instructor;
        this.days = days;
        this.time = time;
        

    }


    public String getTitle(){
        return this.title;
    }
    public String getActivity(){
        return this.activity;
    }
    public String getCRN(){
        return this.CRN;
    }
    public String getInstructor(){
        return this.instructor;
    }
    public String getDays(){
        return this.days;
    }
    public String getTime(){
        return this.time;
    }
    public String toString(){
        return this.title+" "+this.activity+".";
    }

    
    
}