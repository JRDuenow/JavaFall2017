
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;




public class Square extends MyShape {
   

    public Square(Point2D.Float location, Color color, int size) {
        super(location, color, size);
        
        
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(this.getColor());
        
        int size = this.getSize();
        int x = (int)this.getLocation().x - (size / 2); //to center the square
        int y = (int)this.getLocation().y - (size / 2);
        
        g2.fillRect(x, y, size, size);
    }

}
