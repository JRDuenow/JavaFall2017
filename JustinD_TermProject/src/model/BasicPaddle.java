

package model;

import utility.SpriteLoader;


public class BasicPaddle extends Paddle {

    public BasicPaddle(int x, int y) {
        super(x, y);
        
        super.image = SpriteLoader.LoadImage("Paddle.png");
    }
    
    

}
