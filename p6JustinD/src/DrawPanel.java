
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private final ArrayList<MyShape> shapes;

    public DrawPanel() {
        shapes = new ArrayList<>();
    }

    public void clearShapes() {
        shapes.clear();
        
        repaint();
    }

    public void addShape(MyShape shape) {
        shapes.add(shape);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (MyShape shape : shapes) {
            shape.draw(g2);
        }

    }

}
