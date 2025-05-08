package JPanels;

import JFrames.MainFrame;
import JFrames.StudentsListFrame;
import JFrames.TeachersListFrame;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    final int width = 1000;
    final int height = 800;

    public MainPanel(){
        setBackground(MainFrame.whiteColor);
        setLayout(null);

        //Heading Panel
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBounds(0,0,width,150);
        headingPanel.setBackground(MainFrame.mainColor);

            //heading title
            JLabel title = new JLabel("School Management app");
            title.setFont(new Font("Arial", Font.PLAIN,50));
            title.setForeground(MainFrame.whiteColor);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setVerticalAlignment(JLabel.CENTER);
            headingPanel.add(title);

        //Buttons-Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);
        optionsPanel.setBounds(0,150,width,height-150);
        optionsPanel.setBackground(MainFrame.secondaryColor);

            //Buttons
            JButton studentsList = new JButton();
            studentsList.setText("Students List");
            studentsList.setFont(new Font("Arial", Font.PLAIN, 32));
            studentsList.setForeground(MainFrame.whiteColor);
            studentsList.setFocusable(false);
            studentsList.setBackground(MainFrame.mainColor);
            studentsList.setBorderPainted(false);
            studentsList.setBounds(width/6, 300,1000/4 ,800/5);
            optionsPanel.add(studentsList);

            JButton teachersList = new JButton();
            teachersList.setText("Teachers List");
            teachersList.setFont(new Font("Arial",Font.PLAIN, 32));
            teachersList.setForeground(MainFrame.whiteColor);
            teachersList.setFocusable(false);
            teachersList.setBackground(MainFrame.mainColor);
            teachersList.setBorderPainted(false);
            teachersList.setBounds(width-(width/6)-(1000/4),300,1000/4,800/5);
            optionsPanel.add(teachersList);

        //Actions on click
        studentsList.addActionListener(e->{
            StudentsListFrame studentsListFrame = new StudentsListFrame();
            SwingUtilities.getWindowAncestor(studentsList).dispose();
        });

        teachersList.addActionListener(e->{
            TeachersListFrame teachersListFrame = new TeachersListFrame();
            SwingUtilities.getWindowAncestor(teachersList).dispose();
        });



        add(headingPanel);
        add(optionsPanel);

    }
}
