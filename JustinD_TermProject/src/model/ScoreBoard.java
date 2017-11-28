

package model;

public class ScoreBoard {
    private int score;
    private int highScore;

    public ScoreBoard(){
        score = 0;
        highScore = 0;
    }


    public void addScore(int score) {
        this.score += score;
        
        if (this.score > this.highScore){
            this.highScore = this.score;
        }
        
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
    
}
