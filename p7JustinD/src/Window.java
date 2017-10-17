
import java.awt.Color;
import java.awt.Container;
import java.awt.geom.Point2D;
import java.util.Random;
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
        btnAddTen.addActionListener((e) -> {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int x = random.nextInt(this.getWidth());
                int y = random.nextInt(this.getHeight());
                Point2D.Float location = new Point2D.Float(x, y);
                
                MyShape shape = new Circle(location, Color.BLUE, 20);
                drawPanel.addShape(shape);
            }
            
            for (int i = 0; i < 5; i++) {
                int x = random.nextInt(this.getWidth());
                int y = random.nextInt(this.getHeight());
                Point2D.Float location = new Point2D.Float(x, y);
                
                MyShape shape = new Square(location, Color.GREEN, 20);
                drawPanel.addShape(shape);
            }
        });
        btnAddTen.setFocusable(false);
        JButton btnClearAll = new JButton("Clear ALL");
        btnClearAll.addActionListener((e) -> {
            drawPanel.clearShapes();
        });
        btnClearAll.setFocusable(false);
        JButton btnFixCar = new JButton("Fix Car");
        btnFixCar.addActionListener((e) -> {
            drawPanel.fixCar();
            
            
            
        });
        btnClearAll.setFocusable(false);
        
        bottomPanel.add(btnAddTen);
        bottomPanel.add(btnClearAll);
        bottomPanel.add(btnFixCar);
        
        contentPane.add(bottomPanel, "South");
    }

}

