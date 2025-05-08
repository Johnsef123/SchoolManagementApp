package DAOs;

import DBConnection.DatabaseConnection;
import People.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {


    //Create Read Update Delete (CRUD)
    //Read
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        //get the Connection and write the Select sql query
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students")){
            ResultSet result = statement.executeQuery();

            //for each result we create a teacher object and then add it to our List
            while(result.next()){
                Student student = new Student(
                        result.getInt("STUDENT_ID"),
                        result.getString("STUDENT_FNAME"),
                        result.getString("STUDENT_LNAME"),
                        result.getDate("STUDENT_BIRTHDATE").toLocalDate());
                students.add(student);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //return the list with all the objects
        return students;
    }

    //Create
    public void addStudent(Student student){
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students (STUDENT_FNAME, STUDENT_LNAME, STUDENT_BIRTHDATE) " +
                            "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            //Modify the query by adding the values at the question marks
            statement.setString(1,student.getFName());
            statement.setString(2,student.getLName());
            statement.setDate(3,Date.valueOf(student.getBirthDate()));
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Update
    public void updateStudent(Student student){
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET STUDENT_FNAME=?, STUDENT_LNAME=?, STUDENT_BIRTHDATE=? WHERE STUDENT_ID=?")){
            statement.setString(1,student.getFName());
            statement.setString(2, student.getLName());
            statement.setDate(3,Date.valueOf(student.getBirthDate()));
            statement.setInt(4,student.getId());
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //delete
    public void deleteStudent(int id){
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM students WHERE STUDENT_ID = ?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}

