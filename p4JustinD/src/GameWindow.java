
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame {

    WordList words;

    JTextField txtKeyField;
    JTextField txtGuessField;
    DrawPanel drawPanel;
    JButton[] buttons;

    String key;
    char[] guesses;
    int remainingLetters;

    public GameWindow() {
        super("p4 Justin Duenow");

        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        initComponents();
        words = new WordList();

    }

    private void initComponents() {
        Container contentPane = this.getContentPane();

        JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
        txtKeyField = new JTextField();
        txtKeyField.setEditable(false);
        txtKeyField.setFont(new Font("Courier New", Font.BOLD, 24));
        txtKeyField.setForeground(Color.RED);

        txtGuessField = new JTextField();
        txtGuessField.setEditable(false);
        txtGuessField.setFont(new Font("Courier New", Font.BOLD, 24));

        fieldPanel.add(txtKeyField);
        fieldPanel.add(txtGuessField);

        contentPane.add(fieldPanel, "North");

        drawPanel = new DrawPanel();

        contentPane.add(drawPanel, "Center");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 7));

        buttons = new JButton[27];

        ButtonListener btnLis = new ButtonListener();
        for (int i = 0; i < 27; i++) {
            JButton tmpBtn = buttons[i] = new JButton();

            //lowercase alaphabet starts at ASCII 97 and ends at 122
            if (i != 26) {
                tmpBtn.setText(Character.toString((char) (i + 97)));
                tmpBtn.setEnabled(false);
            } else {
                tmpBtn.setText("New");
            }

            tmpBtn.addActionListener(btnLis);

            buttonPanel.add(tmpBtn);
        }

        contentPane.add(buttonPanel, "South");
    }

    public void setGuessesText() {
        txtGuessField.setText(
                new String(guesses).replace("\0", ".") // \0 is the ASCII value of null
        );
    }

    public void setEnabledAllLetters(boolean val) {
        for (int i = 0; i < 26; i++) {
            buttons[i].setEnabled(val);
        }
    }

    public void resetGame() {
        setEnabledAllLetters(true);

        key = words.getRandomWord();
        txtKeyField.setText(key);
        remainingLetters = key.length();

        guesses = new char[key.length()];

        setGuessesText();

        drawPanel.setLifeCounter(5);
        drawPanel.setGameState(DrawPanel.GameState.Playing);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            String btnText = btn.getText();

            if (btnText.equals("New")) {
                resetGame();
            } else {

                boolean foundInKey = false;
                for (int i = 0; i < key.length(); i++) {
                    char charKey = key.charAt(i);

                    if (String.valueOf(charKey).equals(btnText)) {
                        foundInKey = true;
                        guesses[i] = charKey;
                        remainingLetters--;
                    }
                }

                if (!foundInKey) {
                    drawPanel.loseLife();
                } else {
                    setGuessesText();
                }

                if (remainingLetters == 0) {
                    drawPanel.setGameState(DrawPanel.GameState.Won);
                }

                DrawPanel.GameState gameState = drawPanel.getGameState();

                if (gameState == DrawPanel.GameState.Won || gameState == DrawPanel.GameState.Lost) {
                    setEnabledAllLetters(false);
                } else {
                    btn.setEnabled(false);
                }

            }

        }

    }

}
