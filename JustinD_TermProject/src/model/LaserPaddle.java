

package model;

import utility.SpriteLoader;


public class LaserPaddle extends Paddle {

    public LaserPaddle(int x, int y) {
        super(x, y);
        
        super.image = SpriteLoader.LoadImage("Paddle_Laser.png");
    }
    
    

}
