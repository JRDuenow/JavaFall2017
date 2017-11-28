

package model;

import java.awt.Graphics2D;


public abstract class GameEntity implements Collidable {

    public double x;
    public double y;
    public int state;
    
    
    public GameEntity(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public abstract void update();
    public abstract void render(Graphics2D g);
}
