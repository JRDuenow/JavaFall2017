
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;




public class DrawPanel extends JPanel {
    ArrayList<Triangle> triangles;
    Triangle currentTriangle;
    
    
    public DrawPanel() {
        triangles = new ArrayList<>();
    }
    
    public void addPoint(Point2D.Float point, Color color){
        if (currentTriangle == null){
            currentTriangle = new Triangle(color);
        }
            
        if (currentTriangle.isComplete()){
            triangles.add(currentTriangle);
            currentTriangle = new Triangle(color);
        }
        
        currentTriangle.addPoint(point);
        
        
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        triangles.forEach((triangle) -> {
            triangle.draw(g2);
        });
        
        if (currentTriangle != null){
            currentTriangle.draw(g2);
        }
    }
    
    public void clearTriangles(){
        currentTriangle = null;
        triangles.clear();
        repaint();
    }
    
    
    
    
    
}
