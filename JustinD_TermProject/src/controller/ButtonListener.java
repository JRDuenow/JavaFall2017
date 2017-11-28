package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.GameWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == GameWindow.startButton) {
            Main.gameData.resetGame();
            
            Main.animator.startGame();
            GameWindow.startButton.setText("Restart");
        } else if (ae.getSource() == GameWindow.quitButton) {
            Main.animator.pauseGame();
            int option = JOptionPane.showConfirmDialog(Main.gamePanel, "Are you sure you wish to quit the game?", "Quit Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else if (option == JOptionPane.NO_OPTION) {
                Main.animator.startGame();
            }
        }
    }

}
