package JPanels;

import DAOs.TeacherDAO;
import JFrames.Create_UpdateFrameTeachers;
import JFrames.MainFrame;

import JFrames.TeachersListFrame;
import People.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeachersListPanel extends JPanel {
    final int width = 1000;
    final int height = 800;
    TeacherDAO teacherDAO = new TeacherDAO();

    public TeachersListPanel(){
        setLayout(null);
        setBackground(MainFrame.whiteColor);

        //Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBounds(0,0,width,150);
        headingPanel.setBackground(MainFrame.mainColor);

        //heading title
        JLabel title = new JLabel("Teachers List");
        title.setFont(new Font("Arial", Font.PLAIN,50));
        title.setForeground(MainFrame.whiteColor);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        headingPanel.add(title);

        //List Panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(null);
        listPanel.setBackground(MainFrame.secondaryColor);
        listPanel.setBounds(0,150,width,height-150);

        //Table
        JTable table = new JTable();
        String [] columnNames = {"ID","First Name", "Surname", "Birth Date","Email","",""};
        DefaultTableModel model = new DefaultTableModel(columnNames,0){

            //makes the table not editable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //disables the highlight of the cells
        table.setFocusable(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        //add the data on the table by getting the students list from the studentDAO class
        List<Teacher> teachersShowedInTable = teacherDAO.getTeachers();
        for(Teacher teacher : teachersShowedInTable){
            Object[] rowData={
                    teacher.getId(),
                    teacher.getFName(),
                    teacher.getLName(),
                    formatter.format(teacher.getBirthDate()),
                    teacher.getEmail(),
                    "<html><font color='blue'>UPDATE</font>",
                    "<html><font color='red'>DELETE</font>"
            };
            model.addRow(rowData);
        }

        //Disable auto-resize
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setFont(new Font("Arial",Font.PLAIN, 16));
        table.setRowHeight(25);

        table.setModel(model);

        // Fix width of each column
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMinWidth(140);
        table.getColumnModel().getColumn(1).setMaxWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);

        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(3).setMinWidth(110);
        table.getColumnModel().getColumn(3).setMaxWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);

        table.getColumnModel().getColumn(4).setMinWidth(240);
        table.getColumnModel().getColumn(4).setMaxWidth(240);
        table.getColumnModel().getColumn(4).setPreferredWidth(240);

        table.getColumnModel().getColumn(5).setMinWidth(70);
        table.getColumnModel().getColumn(5).setMaxWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);

        table.getColumnModel().getColumn(6).setMinWidth(70);
        table.getColumnModel().getColumn(6).setMaxWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);



        table.setBounds(50,75,900,500);


        //Create the scrollPane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,75,900,500);


        //Create Button
        JButton createButton = new JButton("Create Teacher");
        createButton.setBounds(750,12, 200,50);
        createButton.setFocusable(false);
        createButton.setBackground(MainFrame.mainColor);
        createButton.setFont(new Font("Arial",Font.PLAIN, 18));
        createButton.setForeground(MainFrame.whiteColor);

        //Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(50,12, 100,50);
        backButton.setFocusable(false);
        backButton.setBackground(MainFrame.whiteColor);
        backButton.setFont(new Font("Arial",Font.PLAIN, 20));

        JLabel searchLabel = new JLabel("Search by Surname");
        JTextField searchField = new JTextField();
        searchLabel.setBounds(200,12,200,50);
        searchLabel.setForeground(MainFrame.whiteColor);
        searchLabel.setFont(new Font("Arial",Font.PLAIN, 20));

        searchField.setBounds(400,22,200,30);
        searchField.setFont(new Font("Arial",Font.PLAIN, 16));

        //Add actions
        createButton.addActionListener(e->{
            Create_UpdateFrameTeachers createUpdateFrame = new Create_UpdateFrameTeachers(null);
            SwingUtilities.getWindowAncestor(createButton).dispose();
        });

        backButton.addActionListener(e->{
            MainFrame mainFrame = new MainFrame();
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });

        //Search action

        searchField.addActionListener(e->{
            String searchText = searchField.getText();
            teachersShowedInTable.clear();

            for(Teacher t : teacherDAO.getTeachers()){
                if(t.getLName().toLowerCase().contains(searchText.toLowerCase())){
                    teachersShowedInTable.add(t);
                }
            }

            TeachersListFrame teachersListFrame = new TeachersListFrame(teachersShowedInTable);
            SwingUtilities.getWindowAncestor(searchField).dispose();

        });


        //Add listener to the Update and Delete texts
        table.addMouseListener(new MouseAdapter(){

            //Delete Function
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if(col==6){
                    int id = Integer.parseInt(table.getValueAt(row,0).toString());
                    int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the teacher with ID number " +String.valueOf(id)+ " ?","Confirm Deletion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);


                    if(confirm==JOptionPane.YES_OPTION){
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                        teacherDAO.deleteTeacher(id);
                    }
                }
                //Update Function
                else if(col==5){
                    Teacher teacher = null;
                    //get the students list and find the student with the corresponding ID value and pass it to the update form
                    for(Teacher t : teacherDAO.getTeachers()){
                        if(t.getId()==Integer.parseInt(table.getValueAt(row,0).toString())){
                            teacher = t;
                        }
                    }
                    Create_UpdateFrameTeachers createUpdateFrameTeachers = new Create_UpdateFrameTeachers(teacher);
                    SwingUtilities.getWindowAncestor(table).dispose();
                }
            }
        });





        //ADD components
        listPanel.add(scrollPane);
        listPanel.add(createButton);
        listPanel.add(backButton);
        listPanel.add(searchLabel);
        listPanel.add(searchField);

        add(headingPanel);
        add(listPanel);
    }

    public TeachersListPanel(List<Teacher> filteredTeachers){
        setLayout(null);
        setBackground(MainFrame.whiteColor);

        //Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBounds(0,0,width,150);
        headingPanel.setBackground(MainFrame.mainColor);

        //heading title
        JLabel title = new JLabel("Teachers List");
        title.setFont(new Font("Arial", Font.PLAIN,50));
        title.setForeground(MainFrame.whiteColor);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        headingPanel.add(title);

        //List Panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(null);
        listPanel.setBackground(MainFrame.secondaryColor);
        listPanel.setBounds(0,150,width,height-150);

        //Table
        JTable table = new JTable();
        String [] columnNames = {"ID","First Name", "Surname", "Birth Date","Email","",""};
        DefaultTableModel model = new DefaultTableModel(columnNames,0){

            //makes the table not editable
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //disables the highlight of the cells
        table.setFocusable(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        //add the data on the table by getting the students list from the studentDAO class
        List<Teacher> teachersShowedInTable = filteredTeachers;
        for(Teacher teacher : teachersShowedInTable){
            Object[] rowData={
                    teacher.getId(),
                    teacher.getFName(),
                    teacher.getLName(),
                    formatter.format(teacher.getBirthDate()),
                    teacher.getEmail(),
                    "<html><font color='blue'>UPDATE</font>",
                    "<html><font color='red'>DELETE</font>"
            };
            model.addRow(rowData);
        }

        //Disable auto-resize
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setFont(new Font("Arial",Font.PLAIN, 16));
        table.setRowHeight(25);

        table.setModel(model);

        // Fix width of each column
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMinWidth(140);
        table.getColumnModel().getColumn(1).setMaxWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);

        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(3).setMinWidth(110);
        table.getColumnModel().getColumn(3).setMaxWidth(110);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);

        table.getColumnModel().getColumn(4).setMinWidth(240);
        table.getColumnModel().getColumn(4).setMaxWidth(240);
        table.getColumnModel().getColumn(4).setPreferredWidth(240);

        table.getColumnModel().getColumn(5).setMinWidth(70);
        table.getColumnModel().getColumn(5).setMaxWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);

        table.getColumnModel().getColumn(6).setMinWidth(70);
        table.getColumnModel().getColumn(6).setMaxWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);



        table.setBounds(50,75,900,500);


        //Create the scrollPane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,75,900,500);


        //Create Button
        JButton createButton = new JButton("Create Teacher");
        createButton.setBounds(750,12, 200,50);
        createButton.setFocusable(false);
        createButton.setBackground(MainFrame.mainColor);
        createButton.setFont(new Font("Arial",Font.PLAIN, 18));
        createButton.setForeground(MainFrame.whiteColor);

        //Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(50,12, 100,50);
        backButton.setFocusable(false);
        backButton.setBackground(MainFrame.whiteColor);
        backButton.setFont(new Font("Arial",Font.PLAIN, 20));

        JLabel searchLabel = new JLabel("Search by Surname");
        JTextField searchField = new JTextField();
        searchLabel.setBounds(200,12,200,50);
        searchLabel.setForeground(MainFrame.whiteColor);
        searchLabel.setFont(new Font("Arial",Font.PLAIN, 20));

        searchField.setBounds(400,22,200,30);
        searchField.setFont(new Font("Arial",Font.PLAIN, 16));

        //Add actions
        createButton.addActionListener(e->{
            Create_UpdateFrameTeachers createUpdateFrame = new Create_UpdateFrameTeachers(null);
            SwingUtilities.getWindowAncestor(createButton).dispose();
        });

        backButton.addActionListener(e->{
            MainFrame mainFrame = new MainFrame();
            SwingUtilities.getWindowAncestor(backButton).dispose();
        });

        //Search action

        searchField.addActionListener(e->{
            String searchText = searchField.getText();
            teachersShowedInTable.clear();

            for(Teacher t : teacherDAO.getTeachers()){
                if(t.getLName().toLowerCase().contains(searchText.toLowerCase())){
                    teachersShowedInTable.add(t);
                }
            }

            TeachersListFrame teachersListFrame = new TeachersListFrame(teachersShowedInTable);
            SwingUtilities.getWindowAncestor(searchField).dispose();

        });


        //Add listener to the Update and Delete texts
        table.addMouseListener(new MouseAdapter(){

            //Delete Function
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if(col==6){
                    int id = Integer.parseInt(table.getValueAt(row,0).toString());
                    int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the teacher with ID number " +String.valueOf(id)+ " ?","Confirm Deletion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);


                    if(confirm==JOptionPane.YES_OPTION){
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                        teacherDAO.deleteTeacher(id);
                    }
                }
                //Update Function
                else if(col==5){
                    Teacher teacher = null;
                    //get the students list and find the student with the corresponding ID value and pass it to the update form
                    for(Teacher t : teacherDAO.getTeachers()){
                        if(t.getId()==Integer.parseInt(table.getValueAt(row,0).toString())){
                            teacher = t;
                        }
                    }
                    Create_UpdateFrameTeachers createUpdateFrameTeachers = new Create_UpdateFrameTeachers(teacher);
                    SwingUtilities.getWindowAncestor(table).dispose();
                }
            }
        });





        //ADD components
        listPanel.add(scrollPane);
        listPanel.add(createButton);
        listPanel.add(backButton);
        listPanel.add(searchLabel);
        listPanel.add(searchField);

        add(headingPanel);
        add(listPanel);
    }
}
