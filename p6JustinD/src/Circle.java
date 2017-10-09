
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;




public class Circle extends MyShape {
   

    public Circle(Point2D.Float location, Color color, int size) {
        super(location, color, size);
        
        
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(this.getColor());
        g2.fillOval((int)this.getLocation().x - (this.getSize()/2), (int)this.getLocation().y - (this.getSize()/2), this.getSize(),  this.getSize());
    }

}
