
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

public class Window extends JFrame {

    DrawPanel drawPanel;

    public Window() {
        super("p7 Justin Duenow");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 700);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initComponents();
    }

    private void initComponents() {
        Container contentPane = this.getContentPane();

        drawPanel = new DrawPanel();
        drawPanel.setBackground(Color.BLACK);
        
        contentPane.add(drawPanel, "Center");

        JPanel bottomPanel = new JPanel();
        
        JButton btnAddTen = new JButton("Add 10");
        btnAddTen.setFocusable(false);
        JButton btnClearAll = new JButton("Clear ALL");
        btnClearAll.setFocusable(false);
        JButton btnFixCar = new JButton("Fix Car");
        btnClearAll.setFocusable(false);
        
        bottomPanel.add(btnAddTen);
        bottomPanel.add(btnClearAll);
        bottomPanel.add(btnFixCar);
        
        contentPane.add(bottomPanel, "South");
    }

}

