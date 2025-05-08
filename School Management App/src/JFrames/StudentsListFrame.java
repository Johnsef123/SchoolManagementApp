package JFrames;

import JPanels.StudentsListPanel;
import People.Student;

import javax.swing.*;
import java.util.List;

public class StudentsListFrame extends JFrame {
    final int width = 1000;
    final int height = 800;

    public StudentsListFrame(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        StudentsListPanel panel = new StudentsListPanel();
        add(panel);
        setVisible(true);
    }

    //this constructor is only used when you load the frame via the search by surname operation
    public StudentsListFrame(List<Student>filteredstudents){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        StudentsListPanel panel = new StudentsListPanel(filteredstudents);
        add(panel);
        setVisible(true);
    }

}
