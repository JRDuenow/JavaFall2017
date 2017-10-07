
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.*;

public class MainWindow extends JFrame {

    private DrawPanel drawPanel;
    private Color selectedColor;

    public MainWindow() {
        super("p5 Justin Duenow");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        Container contentPane = this.getContentPane();

        JPanel buttonPanel = new JPanel();
        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener((actionLis) -> {
            drawPanel.clearTriangles();
        });

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Pick a color"));

        ButtonGroup radioGroup = new ButtonGroup();

        JRadioButton radioRed = new JRadioButton("RED", true);
        radioRed.addActionListener((actionEvent) -> selectedColor = Color.RED);
        JRadioButton radioBlue = new JRadioButton("BLUE");
        radioBlue.addActionListener((actionEvent) -> selectedColor = Color.BLUE);
        JRadioButton radioGreen = new JRadioButton("GREEN");
        radioGreen.addActionListener((actionEvent) -> selectedColor = Color.GREEN);
        JRadioButton radioBlack = new JRadioButton("BLACK");
        radioBlack.addActionListener((actionEvent) -> selectedColor = Color.BLACK);

        radioGroup.add(radioRed);
        radioGroup.add(radioBlue);
        radioGroup.add(radioGreen);
        radioGroup.add(radioBlack);

        colorPanel.add(radioRed);
        colorPanel.add(radioBlue);
        colorPanel.add(radioGreen);
        colorPanel.add(radioBlack);

        radioRed.doClick();
        
        contentPane.add(colorPanel, "North");

        buttonPanel.add(clearButton);
        contentPane.add(buttonPanel, "South");

        drawPanel = new DrawPanel();
        drawPanel.addMouseListener(new MouseDrawListener());

        contentPane.add(drawPanel, "Center");

    }

    private class MouseDrawListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            Point point = e.getPoint();
            
            drawPanel.addPoint(new Point2D.Float(point.x, point.y), selectedColor);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

}
