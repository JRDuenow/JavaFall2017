package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import utility.SpriteLoader;

public class GameData {

    private static final Image[] LEVEL_BACKGROUNDS = SpriteLoader.LoadSheet("Backgrounds.png", 224, 240, 1, 5);
    
    public final ArrayList<Level> levels;

    public Level currentLevel;
    public int levelCounter = 0;
    
    private int defaultBallCount;
    private int remainingBallCount;

    public Paddle paddle;
    public Ball ball;

    public ArrayList<GameEntity> entities;
    public ScoreBoard scoreBoard;

    public int highScore = 0;
    public int score = 0;

    public GameData() {
        
        remainingBallCount = defaultBallCount = 3;
        levels = new ArrayList<>();

        generateLevels();


        entities = new ArrayList<>();
        scoreBoard = new ScoreBoard();

        paddle = new LaserPaddle(300, 500);
        ball = new Ball(300, 300);

    }
    
    private void generateLevels() {
        levels.clear();

        Rectangle2D.Float levelLayout = new Rectangle2D.Float(100, 50, 600, 300);
        Level.Builder builder = new Level.Builder(levelLayout);
        builder.addBackground(LEVEL_BACKGROUNDS[0]);
        builder.addBrick(Color.RED, 90, 3);
        builder.addSpace(4);
        builder.addBrick(Color.RED, 90, 1);
        builder.newRow();
        builder.addBrick(Color.RED, 90, 2);
        builder.addSpace(2);
        builder.addBrick(Color.RED, 90, 3);
        builder.addSpawner(150, 1);
        builder.addSpawner(450, 1);
        levels.add(builder.build());
        builder.clear();

        builder.addBackground(LEVEL_BACKGROUNDS[1]);
        builder.addBrick(Color.BLUE, 100, 3);
        builder.addSpace(2);
        builder.addBrick(Color.BLUE, 100, 3);
        //builder.addSpace(2);
       // builder.addBrick(Color.BLUE, 100, 3);
        builder.newRow();
        builder.addBrick(Color.RED, 90, 2);
        builder.addSpace(2);
        builder.addBrick(Color.RED, 90, 3);
        builder.newRow();
        builder.addBrick(Color.BLUE, 100, 3);
       // builder.addSpace(2);
       // builder.addBrick(Color.BLUE, 100, 3);
      //  builder.addSpace(2);
       // builder.addBrick(Color.BLUE, 100, 3);

        levels.add(builder.build());
        builder.clear();
    }
    
    public void loseBall() {
        --remainingBallCount;

        if (remainingBallCount > 0) {
            Main.animator.startGame();
        } else {
            Main.animator.gameOver();
        }
        scoreBoard.addScore(-100);
        //MainWindow.ballsText.setText(remainingBallCount + "");

    }

    public int getBallCount() {
        return remainingBallCount;
    }
    
    public void resetGame() {
        levelCounter = 0;
        generateLevels();
        scoreBoard.setScore(0);
        remainingBallCount = defaultBallCount;

        entities.clear();

        currentLevel = levels.get(0);

        for (ArrayList<Brick> brickRow : currentLevel.getBricks()) {
            for (Brick brick : brickRow) {
                entities.add(brick);
            }
        }

        paddle = new BasicPaddle(Main.gamePanel.width / 2 - 40, Main.gamePanel.height - 60);
        ball = new Ball(Main.gamePanel.width / 2, Main.gamePanel.height / 2);

    }
    

    public void render(Graphics2D g2) {
        currentLevel.render(g2);
        paddle.render(g2);
        ball.render(g2);

        for (GameEntity entity : entities) {
            entity.render(g2);
        }

    }
    
    

    public void update() {
        
        ArrayList<GameEntity> removeEnemies = new ArrayList<>();
        GameEntity f;
        for (int i = 0; i < entities.size(); i++) {
            f = entities.get(i);
            if (f instanceof Brick) {
                Brick brick = (Brick) f;
                if (f.state == 0) {
                    scoreBoard.addScore(brick.getScoreValue());
                    removeEnemies.add(f);
                }
            }
        }
        entities.removeAll(removeEnemies);
        
        
        
        paddle.update();
        ball.update();

        for (GameEntity entity : entities) {
            entity.update();
        }
        
        
        if (entities.isEmpty()) {
            if (levelCounter + 1 != levels.size()) {
                Main.animator.startGame();
                ball = new Ball(Main.gamePanel.width / 2, Main.gamePanel.height / 2);
                levelCounter++;
                //GameWindow.levelText.setText((levelCounter + 1) + "");
                currentLevel = levels.get(levelCounter);

                for (ArrayList<Brick> brickRow : currentLevel.getBricks()) {
                    for (Brick brick : brickRow) {
                        entities.add(brick);
                    }
                }

            } else {
                Main.animator.gameWon();
            }
        }
        
    }

    public void checkCollisions() {
        ball.collide(paddle);

        for (GameEntity entity : entities) {
            ball.collide(entity);

            for (GameEntity otherEntity : entities) {
                if (entity == otherEntity) {
                    continue;
                }

            }

        }

    }

}
