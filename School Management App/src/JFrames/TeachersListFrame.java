package JFrames;

import JPanels.TeachersListPanel;
import People.Teacher;

import javax.swing.*;
import java.util.List;

public class TeachersListFrame extends JFrame {
    final int width = 1000;
    final int height = 800;

    public TeachersListFrame(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        TeachersListPanel panel = new TeachersListPanel();
        add(panel);
        setVisible(true);
    }

    public TeachersListFrame(List<Teacher> filteredTeachers){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        TeachersListPanel panel = new TeachersListPanel(filteredTeachers);
        add(panel);
        setVisible(true);
    }

}
