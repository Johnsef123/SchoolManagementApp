package JFrames;

import JPanels.Create_UpdatePanelStudents;
import People.Student;

import javax.swing.*;

public class Create_UpdateFrameStudents extends JFrame {
    final int width = 1000;
    final int height = 800;

    public Create_UpdateFrameStudents(Student student){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Create_UpdatePanelStudents panel = new Create_UpdatePanelStudents(student);
        add(panel);
        setVisible(true);
    }
}
