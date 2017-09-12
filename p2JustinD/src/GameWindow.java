
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame {

    enum State {
        X, O
    };

    enum GameState {
        Playing, Draw, X_Won, O_Won
    }

    State currentPlayer;
    JTextField textTopField;
    Container frameContainer;
    GamePiece[] buttonArray;

    public GameWindow() {
        super("p2 Justin Duenow");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponents();
        this.setLocationRelativeTo(null); //center the frame
        this.setVisible(true);
        this.setResizable(false);
    }

    private void InitComponents() {
        currentPlayer = State.O;
        frameContainer = this.getContentPane();
        textTopField = new JTextField(String.format("Play Tic-Tac-Toe: %s’s turn!", currentPlayer));
        buttonArray = new GamePiece[9];

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(3, 3));
        ButtonListener listener = new ButtonListener();
        Font btnFont = new Font("Courier New", Font.BOLD, 20);
        for (int i = 0; i < 9; i++) {

            GamePiece tmpButton = buttonArray[i] = new GamePiece();
            tmpButton.addActionListener(listener);
            tmpButton.setFont(btnFont);
            panelButtons.add(tmpButton);

        }

        frameContainer.add(textTopField, "North");
        frameContainer.add(panelButtons, "Center");
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            GamePiece tempPiece;
            for (int i = 0; i < buttonArray.length; i++) {

                if ((tempPiece = buttonArray[i]) == ae.getSource()) {
                    tempPiece.setEnabled(false);
                    tempPiece.setPieceState(currentPlayer);

                    switch (checkBoardState()) {
                        case Draw:
                            textTopField.setText("Draw!");
                            break;
                        case X_Won:
                            textTopField.setText("X Won!");
                            break;
                        case O_Won:
                            textTopField.setText("O Won!");
                            break;
                        case Playing:
                            currentPlayer = currentPlayer == State.O ? State.X : State.O;
                            textTopField.setText(String.format("%s’s turn", currentPlayer));
                            break;
                    }

                    break;
                }
            }

        }

    }

    private GameState checkBoardState() {
        
        return GameState.Playing;
    }
    
    private boolean checkCells(GamePiece p1, GamePiece p2, GamePiece p3){
        
        if (p1.getPieceState() == null || p2.getPieceState() == null || p3.getPieceState() == null)
            return false;
        
        
        return p1.getPieceState().equals(p2.getPieceState()) && p2.getPieceState().equals(p3.getPieceState());
    }

    private class GamePiece extends JButton {

        private State pieceState;

        public GamePiece() {
            pieceState = null;
        }

        public State getPieceState() {
            return pieceState;
        }

        public void setPieceState(State pieceState) {
            this.pieceState = pieceState;
            this.setText(pieceState.name());
        }

    }
}
