
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;



public abstract class MyShape implements CarCollision {

    private final Point2D.Float location;
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Point2D.Float getLocation() {
        return location;
    }
    
    public void moveLocation(float x, float y){
        location.x += x;
        location.y += y;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
    
    private final int size;

    public MyShape(Point2D.Float location, Color color, int size) {
        this.location = location;
        this.color = color;
        this.size = size;
    }
    
    public abstract void draw(Graphics2D g2);
}
