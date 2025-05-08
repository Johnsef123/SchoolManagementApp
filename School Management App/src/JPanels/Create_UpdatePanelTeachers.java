package JPanels;

import DAOs.TeacherDAO;
import JFrames.MainFrame;
import JFrames.TeachersListFrame;

import People.Teacher;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;

public class Create_UpdatePanelTeachers extends JPanel {
    final int width = 1000;
    final int height = 800;


    public Create_UpdatePanelTeachers(Teacher teacher) {
        setSize(width, height);
        setLayout(null);

        //Heading Panel
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBounds(0, 0, width, 150);
        headingPanel.setBackground(MainFrame.mainColor);

        //heading title
        JLabel title = new JLabel((teacher == null ? "Create Teacher" : "Update Teacher"));
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        title.setForeground(MainFrame.whiteColor);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        headingPanel.add(title);

        //Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(MainFrame.secondaryColor);
        formPanel.setBounds(0,150,width,height-150);

        //Create the Labels, TextFields, Buttons and Datepicker from external Library
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel surnameLabel = new JLabel("Surname");
        JLabel dateOfBirthLabel = new JLabel("Birth Date");
        JLabel emailLabel = new JLabel("Email");

        JButton backButton = new JButton("BACK");
        JButton saveButton = new JButton("SAVE");

        JTextField firstNameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();

        DatePickerSettings settings = new DatePickerSettings();
        settings.setAllowEmptyDates(false);
        settings.setFontValidDate(new Font("Arial",Font.PLAIN,25));
        settings.setFontInvalidDate(new Font("Arial", Font.PLAIN,25));
        DatePicker datePicker = new DatePicker(settings);

        //get the button from the datePicker for styling purposes
        JButton datePickerButton = datePicker.getComponentToggleCalendarButton();

        //Style each component from top to bottom
        backButton.setBounds(50,50,100,50);
        backButton.setFocusable(false);
        backButton.setBackground(MainFrame.whiteColor);
        backButton.setFont(new Font("Arial",Font.PLAIN,20));

        firstNameLabel.setBounds(width/4,50,(width/4)*3,40);
        firstNameLabel.setFont(new Font("Arial",Font.PLAIN,25));
        firstNameLabel.setForeground(MainFrame.whiteColor);

        firstNameField.setBounds(width/4,100,width/2,40);
        firstNameField.setFont(new Font("Arial",Font.PLAIN,25));

        surnameLabel.setBounds(width/4,150,(width/4)*3,40);
        surnameLabel.setFont(new Font("Arial",Font.PLAIN,25));
        surnameLabel.setForeground(MainFrame.whiteColor);

        surnameField.setBounds(width/4,200,width/2,40);
        surnameField.setFont(new Font("Arial",Font.PLAIN,25));

        dateOfBirthLabel.setBounds(width/4,250,(width/4)*3,40);
        dateOfBirthLabel.setFont(new Font("Arial",Font.PLAIN,25));
        dateOfBirthLabel.setForeground(MainFrame.whiteColor);

        datePicker.setBounds(width/4,300,width/2,40);
        datePicker.setBackground(MainFrame.whiteColor);
        datePicker.setBorder(null);

        datePickerButton.setFocusable(false);
        datePickerButton.setBackground(MainFrame.whiteColor);
        datePickerButton.setSize(20,50);

        emailLabel.setBounds(width/4,350,width/2,40);
        emailLabel.setFont(new Font("Arial",Font.PLAIN,25));
        emailLabel.setForeground(MainFrame.whiteColor);

        emailField.setBounds(width/4,400,width/2,40);
        emailField.setFont(new Font("Arial",Font.PLAIN,25));


        saveButton.setBounds((width-width/4)/2,470,width/4,50);
        saveButton.setBackground(MainFrame.mainColor);
        saveButton.setForeground(MainFrame.whiteColor);
        saveButton.setFocusable(false);
        saveButton.setFont(new Font("Arial",Font.PLAIN,26));

        //Check if there is an existing Student and then pass the values from that object
        if(teacher!=null){
            firstNameField.setText(teacher.getFName());
            surnameField.setText(teacher.getLName());
            datePicker.setDate(teacher.getBirthDate());
            emailField.setText(teacher.getEmail());
        }

        //Add actions to the buttons
        backButton.addActionListener(e->{
            TeachersListFrame teachersListFrame = new TeachersListFrame();
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });

        saveButton.addActionListener(e->{
            TeacherDAO teacherDAO = new TeacherDAO();
            //Check if we are updating or creating
            if(teacher==null){
                teacherDAO.addTeacher(new Teacher(firstNameField.getText(),surnameField.getText(),datePicker.getDate(),emailField.getText()));
                JOptionPane.showMessageDialog(null,"You created a new Teacher","Successful creation",JOptionPane.INFORMATION_MESSAGE);
                TeachersListFrame teachersListFrame = new TeachersListFrame();
                SwingUtilities.getWindowAncestor(saveButton).dispose();
            }
            else {
                teacherDAO.updateTeacher(new Teacher(teacher.getId(),firstNameField.getText(),surnameField.getText(),datePicker.getDate(),emailField.getText()));
                JOptionPane.showMessageDialog(null,"You updated the teacher","Successful update",JOptionPane.INFORMATION_MESSAGE);
                TeachersListFrame teachersListFrame = new TeachersListFrame();
                SwingUtilities.getWindowAncestor(saveButton).dispose();
            }
        });




        //Add components to the form Panel
        formPanel.add(backButton);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(surnameLabel);
        formPanel.add(surnameField);
        formPanel.add(dateOfBirthLabel);
        formPanel.add(datePicker);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(saveButton);
        //Add components
        add(headingPanel);
        add(formPanel);

    }
}
