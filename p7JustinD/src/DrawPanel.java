
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class DrawPanel extends JPanel {

    private MyCar car;
    private ArrayList<MyShape> shapes;

    public DrawPanel() {
        shapes = new ArrayList<>();
        
        
        car = new MyCar();
        /*
        Input and Action maps are pretty useful in cases like games or graphical applications
        from https://docs.oracle.com/javase/8/docs/api/javax/swing/InputMap.html
        */
        InputMap inputs = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = this.getActionMap();
        
        inputs.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputs.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        inputs.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputs.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        
        actions.put("up", new CarMoveAction(0, -10));
        actions.put("down", new CarMoveAction(0, 10));
        actions.put("left", new CarMoveAction(-10, 0));
        actions.put("right", new CarMoveAction(10, 0));
        
        // creates and starts a timer that automatically repaints every 50ms
        Timer updateTimer = new Timer(50, (e) ->{
            car.update();
            repaint();
        });     
        updateTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        car.draw(g2);

    }

    private class CarMoveAction extends AbstractAction{
        private int xAmount;
        private int yAmount;

        public CarMoveAction(int xAmount, int yAmount) {
            this.xAmount = xAmount;
            this.yAmount = yAmount;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            car.move(xAmount, yAmount);
        }
        
    }

}
