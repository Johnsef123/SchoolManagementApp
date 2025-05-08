package People;

import java.time.LocalDate;

public class Teacher extends Person{
    //Extra attribute for a teacher object
    private String Email;

    //Constructors
    public Teacher(int id, String FName, String LName, LocalDate BirthDate, String Email){
        super(id, FName, LName, BirthDate);
        this.Email=Email;
    }
    public Teacher(String FName, String LName, LocalDate BirthDate, String Email){
        super(FName, LName, BirthDate);
        this.Email=Email;
    }

    //getter-setter
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getEmail(){
        return this.Email;
    }
}
