package People;

import java.time.LocalDate;

public class Person {
    private int id;
    private String FName;
    private String LName;
    private LocalDate BirthDate;


    //Constructors
    public Person(int id, String FName, String LName, LocalDate BirthDate){
        this.id = id;
        this.FName = FName;
        this.LName = LName;
        this.BirthDate = BirthDate;
    }

    public Person(String FName, String LName, LocalDate BirthDate){
        this.FName = FName;
        this.LName = LName;
        this.BirthDate = BirthDate;
    }


    //getters
    public int getId() {
        return id;
    }
    public String getFName(){
        return FName;
    }
    public String getLName(){
        return LName;
    }
    public LocalDate getBirthDate(){
        return BirthDate;
    }


    //Setters
    public void setFName(String FName){
        this.FName=FName;
    }
    public void setLName(String LName){
        this.LName=LName;
    }
    public void setBirthDate(LocalDate BirthDate){
        this.BirthDate=BirthDate;
    }
}
