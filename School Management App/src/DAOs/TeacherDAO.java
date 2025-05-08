package DAOs;

import DBConnection.DatabaseConnection;
import People.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

    //Create Read Update Delete (CRUD)
    //Read
    public List<Teacher> getTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        //get the Connection and write the Select sql query
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teachers")){
            ResultSet result = statement.executeQuery();

            //for each result we create a teacher object and then add it to our List
            while(result.next()){
                Teacher teacher = new Teacher(
                        result.getInt("TEACHER_ID"),
                        result.getString("TEACHER_FNAME"),
                        result.getString("TEACHER_LNAME"),
                        result.getDate("TEACHER_BIRTHDATE").toLocalDate(),
                        result.getString("TEACHER_EMAIL")
                );
                teachers.add(teacher);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //return the list with all the objects
        return teachers;
    }

    //Create
    public void addTeacher(Teacher teacher){
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO teachers (TEACHER_FNAME, TEACHER_LNAME, TEACHER_BIRTHDATE, TEACHER_EMAIL) " +
                        "VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
            //Modify the query by adding the values at the question marks
            statement.setString(1,teacher.getFName());
            statement.setString(2,teacher.getLName());
            statement.setDate(3,Date.valueOf(teacher.getBirthDate()));
            statement.setString(4,teacher.getEmail());
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Update
    public void updateTeacher(Teacher teacher){
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
        "UPDATE teachers SET TEACHER_FNAME=?, TEACHER_LNAME=?, TEACHER_BIRTHDATE=?, TEACHER_EMAIL=? WHERE TEACHER_ID=?")){
            statement.setString(1,teacher.getFName());
            statement.setString(2, teacher.getLName());
            statement.setDate(3,Date.valueOf(teacher.getBirthDate()));
            statement.setString(4,teacher.getEmail());
            statement.setInt(5,teacher.getId());
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //delete
    public void deleteTeacher(int id){
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM teachers WHERE TEACHER_ID = ?")){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}

