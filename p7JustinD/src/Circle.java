
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Circle extends MyShape {

    public Circle(Point2D.Float location, Color color, int size) {
        super(location, color, size);
    }

    @Override
    public Rectangle2D.Float getCollisionBox() {
        
        Point2D.Float location = this.getLocation();
        float size = (float)(this.getSize() * 0.9);
        
        int x = (int)(location.getX() - (size / 2));
        int y = (int)(location.getY() - (size / 2));
        
        return new Rectangle2D.Float(x, y, size,size);
    }

    @Override
    public void draw(Graphics2D g2) {
        Point2D.Float location = this.getLocation();
        Color color = this.getColor();
        int size = this.getSize();
        
        g2.setColor(color);
        int x = (int)(location.getX() - (size / 2));
        int y = (int)(location.getY() - (size / 2));
        
        g2.fillOval(x, y, size, size);
    }
    
    

}
