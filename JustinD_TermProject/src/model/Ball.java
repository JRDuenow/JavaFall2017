package model;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import utility.SpriteLoader;
import view.GamePanel;

public class Ball extends GameEntity {

    private final Image image = SpriteLoader.LoadImage("Ball.png");
    private final int SIZE = 15;

    private double dx = 0;
    private double dy = 1;
    private double velocity = 5; // pixels per update

    public Ball(int x, int y) {
        super(x, y);
    }



    @Override
    public void update() {

        if (super.x <= 20 || super.x >= 605) {
            dx *= -1;
        }

        if (super.y <= 18) {
            dy *= -1;
        } else if (super.y >= Main.gamePanel.height){
            Main.gameData.loseBall();
            super.x = Main.gamePanel.width / 2;
            super.y = Main.gamePanel.height / 2;
            dx = 0;
            dy = 1;
        }

        super.x += dx * velocity;
        super.y += dy * velocity;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, SIZE, SIZE, null);
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, SIZE * 0.9, SIZE * 0.9);
    }

    public void collide(Collidable obj) {
        Rectangle2D.Double ballBounds = this.getBounds();
        Rectangle2D objBounds = obj.getBounds();

        if (objBounds.intersects(ballBounds)) {
            if (obj instanceof Paddle) {
                Paddle paddle = (Paddle) obj;

                int paddleWidth = paddle.width;

                int segment = paddleWidth / 5;
                int pX = (int) paddle.x;
                int ballCenter = (int) (super.x);

                int leftMost = pX + segment;
                int left = pX + segment * 2;
                int right = pX + segment * 3;
                int rightMost = pX + segment * 4;

                if (ballCenter < leftMost) {
                    dx = -1.5;
                    dy = -1;
                } else if (ballCenter >= leftMost && ballCenter < left) {
                    dx = -1;
                    dy = -dy;
                } else if (ballCenter >= left && ballCenter < right) {
                    dx = 0;
                    dy = -1;
                } else if (ballCenter >= right && ballCenter < rightMost) {
                    dx = 1;
                    dy = -dy;
                } else if (ballCenter > rightMost) {
                    dx = 1.5;
                    dy = -1;
                }

            } else if (obj instanceof Brick) {

                Brick brick = (Brick) obj;
                if (super.x < brick.x) { // left side
                    dx = -dx;
                } else if (super.x > brick.x + Brick.WIDTH) { //right side
                    dx = -dx;
                } else if (super.y > brick.y) { // bottom side
                    dy = -dy;
                } else if (super.y <= brick.y) { // top side
                    dy = -dy;
                }
                
                brick.state = 0;
            }

        }
    }

}
