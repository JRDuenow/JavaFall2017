package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import view.GamePanel;

public class Level {

    public Image background;

    private ArrayList<EnemySpawner> spawners;

    private ArrayList<ArrayList<Brick>> bricks;

    public ArrayList<ArrayList<Brick>> getBricks() {
        return bricks;
    }

    private Rectangle2D layout;

    private Level(Builder builder) {
        this.spawners = builder.spawners;
        this.background = builder.background;

        this.bricks = builder.bricks;
        this.layout = builder.layout;
    }

    public Level(Level clone) {
        this.bricks = clone.bricks;
        this.layout = clone.layout;
        this.spawners = clone.spawners;
        this.background = clone.background;
    }

    public Level copy() {
        return new Level(this);
    }

    public void render(Graphics2D g2) {
        g2.drawImage(background, 0, 0, Main.gamePanel.width, Main.gamePanel.height, null);

        for (EnemySpawner spawner : spawners) {
            spawner.render(g2);
        }

    }

    public static class Builder {

        private Image background;
        private ArrayList<EnemySpawner> spawners;

        // row x columns
        private ArrayList<ArrayList<Brick>> bricks;
        private ArrayList<Brick> currentRow;

        private int currentX;
        private int currentY;

        private Rectangle2D layout;

        public Builder(Rectangle2D brickRec) {

            spawners = new ArrayList<>();

            layout = brickRec;

            currentX = (int) layout.getX();
            currentY = (int) layout.getY();

            bricks = new ArrayList<>();
            currentRow = new ArrayList<>();
        }

        public Builder addSpawner(int x, int y) {
            spawners.add(new EnemySpawner(x, y));
            return this;
        }

        public Builder addBackground(Image background) {
            this.background = background;
            return this;
        }

        public Builder newRow() {

            currentX = (int) layout.getX();
            currentY += Brick.HEIGHT;

            bricks.add(currentRow);
            currentRow = new ArrayList<>();
            return this;
        }

        public Builder addBrick(Color color, int scoreValue) {

            Brick brick = new Brick.Builder(currentX, currentY).addColor(color).addScoreValue(scoreValue).build();
            currentRow.add(brick);

            currentX += Brick.WIDTH;

            if (currentX + Brick.WIDTH > Main.gamePanel.width) {
                newRow();
            }

            return this;
        }

        public Builder addBrick(Color color, int scoreValue, int count) {

            for (int i = 0; i < count; i++) {
                addBrick(color, scoreValue);
            }

            return this;
        }

        public Builder addSpace(int count) {

            currentX += count * Brick.WIDTH;

            return this;
        }

        public Level build() {
            bricks.add(currentRow);
            return new Level(this);
        }

        public void clear() {
            currentX = (int) layout.getX();
            currentY = (int) layout.getY();

            bricks = new ArrayList<>();
            currentRow = new ArrayList<>();
        }

    }

}
