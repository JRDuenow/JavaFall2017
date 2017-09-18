
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GameWindow extends JFrame {

    int key1;
    int key2;
    int key3;

    JTextField txtTopField;
    JPanel drawingPanel;
    Container frameContainer;
    
    JButton[] btnArray;

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
            }

            tmpButton.addActionListener(btnListener);

            buttonPanel.add(tmpButton);
        }

        frameContainer.add(buttonPanel, "South");

        frameContainer.add(new DrawPanel(), "Center");
    }

    private void SetKey() {
        key1 = (int) (Math.random() * 10);

        do {
            key2 = (int) (Math.random() * 10);
        } while (key2 == key1);

        do {
            key3 = (int) (Math.random() * 10);
        } while (key3 == key1 && key3 == key2);
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
                SetKey();
                txtTopField.setText("Key = " + key1 + "" + key2 + "" + key3);
            } else if (txtButton.equals("Clear")) {
                
            } else {
                
            }
        }

    }

}
