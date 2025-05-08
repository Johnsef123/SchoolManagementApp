package People;

import java.time.LocalDate;

public class Student extends Person{

    //Constructors
    public Student(int id, String FName, String LName, LocalDate BirthDate){
        super(id, FName, LName, BirthDate);
    }
    public Student(String FName, String LName, LocalDate BirthDate){
        super(FName, LName, BirthDate);
    }
}
