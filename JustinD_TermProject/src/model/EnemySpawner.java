package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import utility.SpriteLoader;

public class EnemySpawner {

    private static final BufferedImage[] SPRITES = SpriteLoader.LoadSheet("EnemySpawner.png", 32, 8, 1, 6);

    private static final int WIDTH = 70;
    private static final int HEIGHT = 16;

    public BufferedImage image;
    private int imageIndex;
    private int x;
    private int y;
    private int animationDirection = 1;

    public EnemySpawner(int x, int y) {
        imageIndex = 0;
        image = SPRITES[imageIndex];

        this.x = x;
        this.y = y;
    }

    public void animate() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;
            final int LIMIT = 8;

            @Override
            public void run() {
                if (counter++ > LIMIT) {
                    this.cancel();
                    return;
                }

                if (imageIndex == 5) {
                    animationDirection = -1;
                } else if (imageIndex == 0) {
                    animationDirection = 1;
                }

                imageIndex += animationDirection;
                image = SPRITES[imageIndex];

            }

        }, 2000, 500);
    }

    public void render(Graphics2D g2) {
        g2.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

}
