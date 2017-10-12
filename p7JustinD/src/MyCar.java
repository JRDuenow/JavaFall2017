
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Color;



public class MyCar {
    private ArrayList<MyShape> parts;
    private Point2D.Float location;
    private final Color NORMAL_COLOR = Color.YELLOW;
    private final Color DAMAGED_COLOR = Color.YELLOW;

    public MyCar() {
        parts = new ArrayList<>();
        
        parts.add(new Square(new Point2D.Float(45,45), NORMAL_COLOR, 30)); //front bottom body
        parts.add(new Square(new Point2D.Float(15,45), NORMAL_COLOR, 30)); // rear bottom body
        parts.add(new Square(new Point2D.Float(30,15), NORMAL_COLOR, 30)); // top body
        parts.add(new Circle(new Point2D.Float(45,70), NORMAL_COLOR, 20)); // front tire
        parts.add(new Circle(new Point2D.Float(15,70), NORMAL_COLOR, 20)); // read tire
        
        location = new Point2D.Float(50,50);
    }
    
    
    public void update(){
        
    }
    
    
    public void move(int x, int y){
        location.x += x;
        location.y += y;
    }
    
    
    public void draw(Graphics2D g2){
       Graphics2D g3 = (Graphics2D)g2.create(); // so that any transforms are not kept in the original object
       g3.translate(location.getX(), location.getY());
       
        for (MyShape part : parts) {
            part.draw(g3);
        }
       
    }
}
