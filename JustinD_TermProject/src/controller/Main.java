package controller;

import javax.swing.JFrame;
import view.*;
import model.GameData;
import utility.FontLoader;

public class Main {

    public static Animator animator;
    public static GamePanel gamePanel;
    public static ScorePanel scorePanel;
    public static GameData gameData;

    public static int WIN_WIDTH = 816;
    public static int WIN_HEIGHT = 605;

    public static void main(String[] args) {
        FontLoader.LoadFonts();
        
        gamePanel = new GamePanel();
        scorePanel = new ScorePanel();
        gameData = new GameData();
        animator = new Animator();

        JFrame game = new GameWindow();
        game.setTitle("Term Project - Justin Duenow");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocationRelativeTo(null);
        game.setResizable(false); // window size cannot change
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);

        new Thread(animator).start();
    }

}
