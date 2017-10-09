
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class MyShape {

    // location = center if Circle or Square
    //            lower-left corner if Triangle (Refer to handout)
    private final Point2D.Float location;
    private final Color color;

    // size = diameter (2 x radius) if Circle
    //        a side if Square
    //        the bottom or the left side if Triangle
    private final int size;

    public MyShape(Point2D.Float location, Color color, int size) {
        this.location = location;
        this.color = color;
        this.size = size;
    }

    public Point2D.Float getLocation() {
        return location;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public abstract void draw(Graphics2D g2);

}
