
import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle {

    private final Point2D.Float[] p;
    private final Color color;
    
    
    public Triangle(){
        this(Color.RED);
    }
    
    public Triangle(Color color) {
        p = new Point2D.Float[3];
        this.color = color;
    }
    
    public void draw(Graphics2D g){
        g.setColor(color);
        switch(getValidPointCount()){
            case 1:
                g.fillOval((int)p[0].x, (int)p[0].y, 2, 2);
                break;
            case 2:
                g.drawLine((int)p[0].x, (int)p[0].y, (int)p[1].x, (int)p[1].y);
                break;
            case 3:
                g.drawPolygon(getXPoints(), getYPoints(), 3);
                
                break;
            default:
                break;
        }
    }
    
    public void addPoint(Point2D.Float point){
        p[getValidPointCount()] = point;
    }
    
    public boolean isComplete(){
        return getValidPointCount() == 3;
    }
    
    private int[] getXPoints(){
        int validPointCount = getValidPointCount();
        
        int[] xPoints = new int[validPointCount];
        for (int i = 0; i < validPointCount; i++) {
            xPoints[i] = (int)p[i].x;
        }
        
        return xPoints;
    }
    
    private int[] getYPoints(){
        int validPointCount = getValidPointCount();
        
        int[] yPoints = new int[validPointCount];
        for (int i = 0; i < validPointCount; i++) {
            yPoints[i] = (int)p[i].y;
        }
        
        return yPoints;
    }
    
    private int getValidPointCount(){
        int validPoints = 0;
        for (Point2D.Float point: p) {
            if (point != null) {
                validPoints++;
            }
        }
        
        return validPoints;
    }
    
    

    
}
