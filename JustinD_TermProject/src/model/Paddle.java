

package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import utility.SpriteLoader;


public abstract class Paddle extends GameEntity {

    private static final Image[] EXPLOSION_ANIMATION = SpriteLoader.LoadSheet("Paddle_Explosion.png", 36, 21, 2, 4);
    
    protected Image image;
    public final int height = 15;
    public int width = 80;
    
    public Paddle(int x, int y) {
        super(x, y);
    }

    
    
    public void move(int x){
        
        if (x < 55){
           x = 55;
        }else if (x > 580){
            x = 580;
        }
        
        super.x = x - (this.width / 2);
    }

    @Override
    public void update() {
        
    }

    
    

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, (int)super.x, (int)super.y, this.width, this.height, null);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, this.width, this.height);
    }

}
