package JFrames;

import JPanels.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    final int width = 1000;
    final int height = 800;
    public static Color mainColor = new Color(0, 59, 149);
    public static Color secondaryColor = new Color(0, 108, 227);
    public static Color whiteColor = new Color(255, 255, 255);

    public MainFrame(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);
    }
}
