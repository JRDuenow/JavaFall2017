package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Brick extends GameEntity {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 20;

    private final int scoreValue;
    private final Color color;

    public int getScoreValue() {
        return scoreValue;
    }
    
    

    private Brick(Builder builder) {
        super(builder.x, builder.y);
        scoreValue = builder.scoreValue;
        color = builder.color;
        this.state = 1;
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(super.x, super.y, WIDTH, HEIGHT);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int) super.x, (int) super.y, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect((int) super.x, (int) super.y, WIDTH, HEIGHT);
    }

    //simple builder design pattern to easily create blocks of different color and score value
    public static class Builder {

        private final double x;
        private final double y;
        private int scoreValue;
        private Color color;

        public Builder(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Builder addColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder addScoreValue(int value) {
            this.scoreValue = value;
            return this;
        }

        public Brick build() {
            return new Brick(this);
        }

    }
}
