
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Window extends JFrame {

    private static final int SMALL = 20,
            MEDIUM = 30,
            LARGE = 40;

    DrawPanel drawPanel;

    private int selectedSize;
    private Color selectedColor;
    private String selectedShape;

    public Window() {
        super("p6 Justin Duenow");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        drawPanel.setBackground(Color.GRAY);
        
        drawPanel.addMouseListener(new MouseDrawListener());

        JPanel panelBot = new JPanel();
        JButton btnClear = new JButton("Clear");

        btnClear.addActionListener((e) -> {
            drawPanel.clearShapes();
        });

        panelBot.add(btnClear);

        JPanel panelTop = new JPanel(new GridLayout(3, 1));

        JPanel panelSize = new JPanel();
        panelSize.setBorder(new TitledBorder("Size"));
        generateSizeRadios(panelSize);

        JPanel panelShape = new JPanel();
        panelShape.setBorder(new TitledBorder("Shape"));
        generateShapeRadios(panelShape);

        JPanel panelColor = new JPanel();
        panelColor.setBorder(new TitledBorder("Color"));
        generateColorRadios(panelColor);

        panelTop.add(panelSize);
        panelTop.add(panelShape);
        panelTop.add(panelColor);

        contentPane.add(panelTop, "North");
        contentPane.add(drawPanel, "Center");
        contentPane.add(panelBot, "South");
    }

    private void generateSizeRadios(JPanel panelSize) {
        ButtonGroup groupSize = new ButtonGroup();

        JRadioButton radioSmall = new JRadioButton("Small", true);
        radioSmall.addActionListener((e) -> {
            selectedSize = SMALL;
        });

        JRadioButton radioMedium = new JRadioButton("Medium");
        radioMedium.addActionListener((e) -> {
            selectedSize = MEDIUM;
        });
        JRadioButton radioLarge = new JRadioButton("Large");
        radioLarge.addActionListener((e) -> {
            selectedSize = LARGE;
        });

        groupSize.add(radioSmall);
        groupSize.add(radioMedium);
        groupSize.add(radioLarge);

        radioSmall.doClick();

        panelSize.add(radioSmall);
        panelSize.add(radioMedium);
        panelSize.add(radioLarge);
    }

    private void generateShapeRadios(JPanel panelShape) {
        ButtonGroup groupShape = new ButtonGroup();

        JRadioButton radioCircle = new JRadioButton("Circle", true);
        radioCircle.addActionListener((e) -> {
            selectedShape = "Circle";
        });
        JRadioButton radioSquare = new JRadioButton("Square");
        radioSquare.addActionListener((e) -> {
            selectedShape = "Square";
        });
        JRadioButton radioTriangle = new JRadioButton("Triangle");
        radioTriangle.addActionListener((e) -> {
            selectedShape = "Triangle";
        });

        groupShape.add(radioCircle);
        groupShape.add(radioSquare);
        groupShape.add(radioTriangle);

        radioCircle.doClick();

        panelShape.add(radioCircle);
        panelShape.add(radioSquare);
        panelShape.add(radioTriangle);
    }

    private void generateColorRadios(JPanel panelColor) {
        ButtonGroup groupColor = new ButtonGroup();

        JRadioButton radioRed = new JRadioButton("Red", true);
        radioRed.addActionListener((e) -> {
            selectedColor = Color.RED;
        });

        JRadioButton radioBlue = new JRadioButton("Blue");
        radioBlue.addActionListener((e) -> {
            selectedColor = Color.BLUE;
        });
        JRadioButton radioGreen = new JRadioButton("Green");
        radioGreen.addActionListener((e) -> {
            selectedColor = Color.GREEN;
        });
        JRadioButton radioYellow = new JRadioButton("Yellow");
        radioYellow.addActionListener((e) -> {
            selectedColor = Color.YELLOW;
        });

        groupColor.add(radioRed);
        groupColor.add(radioBlue);
        groupColor.add(radioGreen);
        groupColor.add(radioYellow);

        radioRed.doClick();

        panelColor.add(radioRed);
        panelColor.add(radioBlue);
        panelColor.add(radioGreen);
        panelColor.add(radioYellow);
    }

    private class MouseDrawListener implements MouseListener {

        public MouseDrawListener() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            MyShape shape = null;
            Point2D.Float location = new Point2D.Float(e.getX(), e.getY());

            switch (selectedShape) {
                case "Cirle":
                    shape = new Circle(location, selectedColor, selectedSize);
                    break;
                case "Square":
                    shape = new Square(location, selectedColor, selectedSize);
                    break;
                case "Triangle":
                    shape = new Triangle(location, selectedColor, selectedSize);
                    break;
                default:
                    shape = new Circle(location, selectedColor, selectedSize);
            }

            drawPanel.addShape(shape);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
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
