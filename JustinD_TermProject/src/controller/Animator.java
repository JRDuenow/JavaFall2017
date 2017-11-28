package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Animator implements Runnable {

    private final int FRAMES_PER_SECOND = 30;
    AnimatorState state;

    public Animator() {
        state = new StateStarting(this);
    }

    @Override
    public void run() {

        while (true) {
            long startTime = System.currentTimeMillis();

            this.state.run();
            Main.scorePanel.render();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void changeState(AnimatorState state) {
        this.state = state;
        this.state.onEnter();
    }

    public void startGame() {
        this.changeState(new StateReady(this));
    }

    public void pauseGame() {
        this.changeState(new StatePaused(this));
    }
    
    public void gameOver(){
        this.changeState(new StateLost(this));
    }
    
    public void gameWon(){
        this.changeState(new StateWon(this));
    }
    
    class StateWon implements AnimatorState {

        private Animator animator;

        public StateWon(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gamePanel.printWon();
            Main.gamePanel.printScreen();
        }

        @Override
        public void onEnter() {

        }

    }
    
    class StateLost implements AnimatorState {

        private Animator animator;

        public StateLost(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gamePanel.printLost();
            Main.gamePanel.printScreen();
        }

        @Override
        public void onEnter() {

        }

    }

    class StateRunning implements AnimatorState {

        private Animator animator;

        public StateRunning(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gameData.checkCollisions();
            Main.gameData.update();
            Main.gamePanel.render();
        }

        @Override
        public void onEnter() {
            System.out.println("derp");
        }

    }

    class StateReady implements AnimatorState {

        private Animator animator;

        public StateReady(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gamePanel.gameRender();
            Main.gamePanel.printReady();
            Main.gamePanel.printScreen();
        }

        @Override
        public void onEnter() {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    
                    animator.changeState(new StateRunning(animator));

                }

            }, 3000);

        }
    }

    class StatePaused implements AnimatorState {

        private Animator animator;

        public StatePaused(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gamePanel.printPauseScreen();
            Main.gamePanel.printScreen();
        }

        @Override
        public void onEnter() {
        }

    }

    class StateStarting implements AnimatorState {

        private Animator animator;

        public StateStarting(Animator animator) {
            this.animator = animator;
        }

        @Override
        public void run() {
            Main.gamePanel.printInstructions();
            Main.gamePanel.printScreen();
        }

        @Override
        public void onEnter() {

        }

    }

}
