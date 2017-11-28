package view;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import utility.GraphicsHelper;

public class GamePanel extends RenderPanel {

    public GamePanel() {

    }

    public void gameRender() {
        super.g2.clearRect(0, 0, width, height);
        super.g2.setBackground(Color.BLACK);

        Main.gameData.render(g2);

    }
    
    
    public void printReady(){
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 36));
        g2.drawString("GET READY", width / 2 - 100, height / 2 + 70);
    }
    
    public void printWon(){
        checkDBImage();
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("You Won!", width / 2 - 50, height / 2 - 70);
    }
    
    public void printLost(){
        checkDBImage();
        g2.setColor(Color.RED);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("You Lost!", width / 2 - 50, height / 2 - 70);
    }
    
    public void printInstructions() {
        checkDBImage();

        Rectangle2D.Float instRec = new Rectangle2D.Float(40, 40, width - 80, height - 80);
        
        g2.clearRect(0, 0, width, height);
        g2.setBackground(Color.BLACK);

        g2.setColor(Color.WHITE);
        //g2.drawRect((int)instRec.x, (int)instRec.y, (int)instRec.width, (int)instRec.height);
        g2.setFont(new Font("Courier New", Font.BOLD, 20));
        
        GraphicsHelper.drawCenteredString(g2, "Instructions", instRec, 0, (int)(-instRec.height / 2) + 20);
        //g2.drawString("Instructions", 325, 40);

        // g2.drawLine(150, 105, 650, 105);
        g2.setFont(new Font("Courier New", Font.PLAIN, 16));
        GraphicsHelper.drawCenteredString(g2, "Use the mouse to move the paddle left and right.", instRec, 0, (int)(-instRec.height / 2) + 40);
        GraphicsHelper.drawCenteredString(g2, "You start with 3 balls.", instRec, 0, (int)(-instRec.height / 2) + 60);
        GraphicsHelper.drawCenteredString(g2, "Press the start button when you are ready to begin", instRec, 0, (int)(-instRec.height / 2) + 120);
    }

    public void printPauseScreen() {
        checkDBImage();

        g2.clearRect(0, 0, width, height);
        g2.setBackground(Color.BLACK);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("PAUSED", width / 2 - 50, height / 2 - 70);
    }

    @Override
    public void render() {
        super.checkDBImage();
        gameRender();
        super.printScreen();
    }

}
