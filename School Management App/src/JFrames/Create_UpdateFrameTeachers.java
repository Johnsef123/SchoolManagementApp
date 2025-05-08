package JFrames;

import JPanels.Create_UpdatePanelTeachers;
import People.Teacher;

import javax.swing.*;

public class Create_UpdateFrameTeachers extends JFrame {
    final int width = 1000;
    final int height = 800;

    public Create_UpdateFrameTeachers(Teacher teacher){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Create_UpdatePanelTeachers panel = new Create_UpdatePanelTeachers(teacher);
        add(panel);
        setVisible(true);
    }
}

