
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;




public class Triangle extends MyShape {
   

    public Triangle(Point2D.Float location, Color color, int size) {
        super(location, color, size);
        
        
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(this.getColor());
        
        Point2D.Float initPoint = this.getLocation();
        int size = this.getSize();
        
        
        int[] x = new int[3];
        int[] y = new int[3];
        
        x[0] = (int)initPoint.x;
        y[0] = (int)initPoint.y;
        
        x[1] = x[0] + size;
        y[1] = y[0];
        
        x[2] = x[0];
        y[2] = y[0] - size;
        
        g2.fillPolygon(x, y, 3);
    }

}
