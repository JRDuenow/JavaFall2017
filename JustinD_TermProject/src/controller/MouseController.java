

package controller;

import static controller.Main.animator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseController extends MouseAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        
        Main.gameData.paddle.move(e.getX());
    }
    
    

    
    
    
}
