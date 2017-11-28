package view;

import controller.Main;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import utility.GraphicsHelper;
import utility.SpriteLoader;

public class ScorePanel extends RenderPanel {

    final Image BALL_IMAGE = SpriteLoader.LoadImage("Ball.png");
    public ScorePanel() {
        super.setPreferredSize(new Dimension(170, 500));
    }

    private void printScores() {
        Rectangle2D.Float highScoreRec = new Rectangle2D.Float(0, 40, 160, 60);
        Rectangle2D.Float scoreRec = new Rectangle2D.Float(20, 100, 120, 60);

        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("ArcadeClassic", Font.BOLD, 36));

        GraphicsHelper.drawCenteredString(g2, "High", highScoreRec, -20, -41);
        GraphicsHelper.drawCenteredString(g2, "Score", highScoreRec, 20, -20);

        GraphicsHelper.drawCenteredString(g2, "Score", scoreRec, 0, -20);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));

        GraphicsHelper.drawCenteredString(g2, Main.gameData.scoreBoard.getScore() + "", scoreRec, 0, 5);
        GraphicsHelper.drawCenteredString(g2, Main.gameData.scoreBoard.getHighScore() + "", highScoreRec, 0, 5);
    }
    
    private void printBallCount(){
        Rectangle2D.Float ballRec = new Rectangle2D.Float(20, 160, 120, 60);
        
        //g2.drawRect((int)ballRec.x, (int)ballRec.y, (int)ballRec.width, (int)ballRec.height);
        g2.drawImage(BALL_IMAGE, (int)(ballRec.x + 10), (int)(ballRec.y + 20), 20, 20, null);
        g2.setFont(new Font("Courier New", Font.BOLD, 26));
        GraphicsHelper.drawCenteredString(g2, ": " + Main.gameData.getBallCount() + "", ballRec, -8, 0);
    }

    

    @Override
    public void render() {
        super.checkDBImage();
        
        super.g2.clearRect(0, 0, width, height);
        super.g2.setBackground(Color.BLACK);
        
        printScores();
        printBallCount();
        super.printScreen();
    }

}
