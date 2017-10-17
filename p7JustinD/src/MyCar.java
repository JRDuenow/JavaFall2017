
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Rectangle2D;



public class MyCar {
    private ArrayList<MyShape> parts;
    private final Color NORMAL_COLOR = Color.YELLOW;
    private final Color DAMAGED_COLOR = Color.RED;

    public MyCar() {
        parts = new ArrayList<>();
        
        parts.add(new Square(new Point2D.Float(45,45), NORMAL_COLOR, 30)); //front bottom body
        parts.add(new Square(new Point2D.Float(15,45), NORMAL_COLOR, 30)); // rear bottom body
        parts.add(new Square(new Point2D.Float(30,15), NORMAL_COLOR, 30)); // top body
        parts.add(new Circle(new Point2D.Float(45,70), NORMAL_COLOR, 20)); // front tire
        parts.add(new Circle(new Point2D.Float(15,70), NORMAL_COLOR, 20)); // read tire
    }
     
    public boolean checkCollision(MyShape obst){
        
        Rectangle2D.Float obstColl = obst.getCollisionBox();
        
        for (MyShape part : parts) {
            Rectangle2D.Float partColl = part.getCollisionBox();
            
            if (partColl.intersects(obstColl)){
                part.setColor(DAMAGED_COLOR);
                return true;
            }
        }
        
        return false;
    }
    
    public void fixCar(){
        for (MyShape part : parts) {
            part.setColor(NORMAL_COLOR);
        }
    }
    
    
    public void move(int x, int y){
        for (MyShape part : parts) {
            part.moveLocation(x, y);
        }
    }
    
    
    public void draw(Graphics2D g2){
       
        for (MyShape part : parts) {
            part.draw(g2);   
        }
       
    }
}
