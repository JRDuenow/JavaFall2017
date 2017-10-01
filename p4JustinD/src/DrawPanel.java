
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private int lifeCounter;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    enum GameState {
        Playing,
        Won,
        Lost,
        NewGame
    }

    public void setGameState(GameState newState) {
        gameState = newState;
        
        repaint();
    }
    
    public void loseLife(){
        this.lifeCounter--;
        
        if (lifeCounter == 0){
            this.gameState = GameState.Lost;
        }
        
        repaint();
    }

    public void setLifeCounter(int lifeCounter) {
        this.lifeCounter = lifeCounter;

        repaint();
    }

    public DrawPanel() {
        this.gameState = GameState.NewGame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        switch (this.gameState) {
            case Playing:
                paintHealth(g2);
                break;
            case Won:
                paintWon(g2);
                paintNewGame(g2);
                break;
            case Lost:
                paintLost(g2);
                paintNewGame(g2);
                break;
            case NewGame:
                paintNewGame(g2);
                break;
        }

    }

    private void paintHealth(Graphics2D g2) {
        g2.setFont(new Font("Courier New", Font.BOLD, 40));
        g2.setColor(Color.BLUE);
        g2.drawString("Health Level", 60, 60);

        for (int i = 0; i < lifeCounter; i++) {
            g2.fillRect(60 + (i * 55), 80, 50, 50);
        }
    }

    private void paintNewGame(Graphics2D g2) {
        g2.setFont(new Font("Courier New", Font.BOLD, 32));
        g2.setColor(Color.BLUE);
        g2.drawString("Press <New> to Start", 50, 150);
    }

    private void paintWon(Graphics2D g2) {
         g2.setFont(new Font("Courier New", Font.BOLD, 32));
        g2.setColor(Color.RED);
        g2.drawString("YOU WON !!!", 50, 100);
    }
    
    private void paintLost(Graphics2D g2) {
         g2.setFont(new Font("Courier New", Font.BOLD, 32));
        g2.setColor(Color.RED);
        g2.drawString("YOU LOST !!!", 50, 100);
    }

}
