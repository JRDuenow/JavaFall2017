
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame {

    JTextField textTopField;
    Container frameContainer;
    JButton[] buttonArray;

    public GameWindow() {
        super("p2 Justin Duenow");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponents();
        this.setLocationRelativeTo(null); //center the frame
        this.setVisible(true);
    }

    private void InitComponents() {
        frameContainer = this.getContentPane();
        textTopField = new JTextField("Play Tic-Tac-Toe: O’s turn!");
        buttonArray = new JButton[9];

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(3, 3));
        ButtonListener listener = new ButtonListener();
        for (int i = 0; i < 9; i++) {
            JButton tmpButton = buttonArray[i] = new JButton();
            tmpButton.addActionListener(listener);

            panelButtons.add(tmpButton);
        }

        frameContainer.add(textTopField, "North");
        frameContainer.add(panelButtons, "Center");
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < buttonArray.length; i++) {
                if (buttonArray[i] == ae.getSource()){
                    System.out.println("Pressed at " + i);
                    buttonArray[i].setEnabled(false);
                }
            }
        }

    }

}
