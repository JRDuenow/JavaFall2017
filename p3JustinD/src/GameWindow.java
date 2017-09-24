
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import javax.swing.*;

public class GameWindow extends JFrame {

    int[] key;
    int[] guesses;

    JTextField txtTopField;
    DrawPanel drawingPanel;
    Container frameContainer;

    JButton[] btnArray;
    int numOfGuess = 0;

    public GameWindow() {
        super("p3 Justin Duenow");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        InitComponents();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void InitComponents() {
        frameContainer = this.getContentPane();
        txtTopField = new JTextField("Play Ball!!!");
        txtTopField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        txtTopField.setEditable(false);

        frameContainer.add(txtTopField, "North");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 4));
        ButtonListener btnListener = new ButtonListener();
        btnArray = new JButton[12];
        for (int i = 0; i < 12; i++) {
            JButton tmpButton = btnArray[i] = new JButton();
            tmpButton.setFont(new Font("Courier New", Font.BOLD, 18));
            if (i <= 9) {
                tmpButton.setText("" + i);
                tmpButton.setEnabled(false);
            } else if (i == 10) {
                tmpButton.setText("New Game");
            } else if (i == 11) {
                tmpButton.setText("Clear");
                tmpButton.setEnabled(false);
            }

            tmpButton.addActionListener(btnListener);

            buttonPanel.add(tmpButton);
        }

        frameContainer.add(buttonPanel, "South");

        drawingPanel = new DrawPanel();

        frameContainer.add(drawingPanel, "Center");
    }

    private void SetKey() {

        key = new int[3];

        key[0] = (int) (Math.random() * 10);

        do {
            key[1] = (int) (Math.random() * 10);
        } while (key[1] == key[0]);

        do {
            key[2] = (int) (Math.random() * 10);
        } while (key[2] == key[1] || key[2] == key[0]);
    }

    private class DrawPanel extends JPanel {

        int balls = 0;
        int strikes = 0;
        boolean gameEnd = false;

        public void setGameState(int balls, int strikes, boolean gameEnd) {
            this.balls = balls;
            this.strikes = strikes;
            this.gameEnd = gameEnd;

            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (gameEnd) {
                Graphics2D g2 = (Graphics2D) g;

                g2.setFont(new Font("Courier New", 0, 14));
                g2.setColor(Color.BLUE);
                g2.drawString("Ball(s)", 30, 60);

                for (int i = 0; i < 3; i++) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(120 + i * 60, 40, 40, 40);
                    if (balls >= i + 1) {
                        g2.fill(circle);
                    } else {
                        g2.draw(circle);
                    }

                }

                g2.setFont(new Font("Courier New", 0, 14));
                g2.setColor(Color.RED);
                g2.drawString("Strikes(s)", 30, 120);

                for (int i = 0; i < 3; i++) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(120 + i * 60, 100, 40, 40);
                    if (strikes >= i + 1) {
                        g2.fill(circle);
                    } else {
                        g2.draw(circle);
                    }

                }

            }
        }

    }

    private class ButtonListener implements ActionListener {

        private void setNumberButtons(boolean buttonState){
            for (int i = 0; i < 10; i++) {
                    btnArray[i].setEnabled(buttonState);
                }
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {

            JButton tmpButton = (JButton) ae.getSource();
            String txtButton = tmpButton.getText();

            if (txtButton.equals("New Game")) {
                numOfGuess = 0;
                guesses = new int[3];

                drawingPanel.setGameState(0, 0, false);

                SetKey();
                txtTopField.setText("Key = " + formatArray(key, false));

                setNumberButtons(true);

                btnArray[11].setEnabled(false);
            } else if (txtButton.equals("Clear")) {
                numOfGuess = 0;
                drawingPanel.setGameState(0, 0, false);
                
                setNumberButtons(true);
                
                guesses = new int[3];
                txtTopField.setText("Key = " + formatArray(key, false));

            } else {
                tmpButton.setEnabled(false);

                guesses[numOfGuess] = Integer.parseInt(txtButton);

                numOfGuess++;

                txtTopField.setText("Key = " + formatArray(key, false) + " | Guess = " + formatArray(guesses, true));
            }

            if (numOfGuess == 3) {
                setNumberButtons(false);

                btnArray[11].setEnabled(true);

                int balls = calcBalls();
                int strikes = calcStrikes();

                txtTopField.setText(txtTopField.getText() + " | B=" + balls + " S=" + strikes);

                drawingPanel.setGameState(balls, strikes, true);
            }

        }

        private String formatArray(int[] arr, boolean useGuessCount) {

            if (useGuessCount) {
                String returnVal = "";
                for (int i = 0; i < numOfGuess; i++) {
                    returnVal += guesses[i] + "";
                }

                return returnVal;
            }

            return Arrays.toString(arr).replaceAll("\\[|\\]|,|\\s", "");
        }

        int calcBalls() {
            int balls = 0;

            for (int i = 0; i < 3; i++) {
                int currentNum = guesses[i];

                for (int j = 0; j < 3; j++) {
                    if (key[j] == currentNum && j == i) {
                        continue; // this is a strike
                    }
                    if (key[j] == currentNum) {
                        balls += 1;
                    }

                }

            }

            return balls;
        }

        int calcStrikes() {
            int strikes = 0;
            for (int i = 0; i < 3; i++) {
                if (key[i] == guesses[i]) {
                    strikes += 1;
                }
            }
            return strikes;
        }

    }

}
