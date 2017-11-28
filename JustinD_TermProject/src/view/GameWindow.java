package view;

import controller.ButtonListener;
import controller.Main;
import controller.MouseController;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class GameWindow extends JFrame {

    public static JButton startButton;
    public static JButton quitButton;
    
    
    public GameWindow() {
        super();

        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLACK);
        Container c = this.getContentPane();

        c.add(Main.gamePanel, "Center");

        
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        //eastPanel.setLayout(new );
        
        eastPanel.add(Main.scorePanel, "North");
        
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.BLACK);
        btnPanel.add(startButton);
        btnPanel.add(quitButton);
        btnPanel.setPreferredSize(new Dimension(130, 10));
        
        eastPanel.add(btnPanel, "South");

        c.add(eastPanel, "East");
        this.pack();
        
        MouseController mc = new MouseController();

        Main.gamePanel.addMouseListener(mc);
        Main.gamePanel.addMouseMotionListener(mc);

        ButtonListener bl = new ButtonListener();
        startButton.addActionListener(bl);
        quitButton.addActionListener(bl);
    }

}
