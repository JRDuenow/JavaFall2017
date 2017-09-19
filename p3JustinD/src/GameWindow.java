
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class GameWindow extends JFrame {

    int[] key;
    int[] guesses;
    
    JTextField txtTopField;
    JPanel drawingPanel;
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

        DrawPanel panel = new DrawPanel();

        frameContainer.add(panel, "Center");
    }

    private void SetKey() {
        
        key = new int[3];
        
        key[0] = (int) (Math.random() * 10);

        do {
            key[1] = (int) (Math.random() * 10);
        } while (key[1] == key[0]);

        do {
            key[2] = (int) (Math.random() * 10);
        } while (key[2] == key[1] && key[2] == key[0]);
    }
    
    private String formatArray(int[] arr){
        return Arrays.toString(arr).replaceAll("\\[|\\]|,|\\s", "");
    }

    private class DrawPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

        }

    }

    private class ButtonListener implements ActionListener {

        
        @Override
        public void actionPerformed(ActionEvent ae) {

            JButton tmpButton = (JButton) ae.getSource();
            String txtButton = tmpButton.getText();

            if (txtButton.equals("New Game")) {
                numOfGuess = 0;
                guesses = new int[3];
                
                SetKey();
                txtTopField.setText("Key = " + formatArray(key));
                
                for (int i = 0; i < 10; i++) {
                    btnArray[i].setEnabled(true);
                }
                
                 btnArray[11].setEnabled(false);
            } else if (txtButton.equals("Clear")) {
                numOfGuess = 0;
            } else {
                tmpButton.setEnabled(false);
                
                guesses[numOfGuess] = Integer.parseInt(txtButton);
                
                numOfGuess++;
                
                txtTopField.setText("Key = " + formatArray(key) + " | Guess = " + formatArray(guesses));
                
                
                
                
            }
            
            
            
            if (numOfGuess == 3){
                for (int i = 0; i < 10; i++) {
                    btnArray[i].setEnabled(false);
                }
                
                btnArray[11].setEnabled(true);
            }
            
        }

    }

}
