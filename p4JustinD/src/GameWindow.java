
import java.awt.*;
import javax.swing.*;




public class GameWindow extends JFrame{
    
    
    JTextField txtKeyField;
    JTextField txtGuessField;
    
    public GameWindow(){
        super("p4 Justin Duenow");
        this.setSize(500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        initComponents();
        
        
        this.setVisible(true);
    }
    
    
    
    private void initComponents(){
       Container contentPane =  this.getContentPane();
    
       JPanel panel = new JPanel(new GridLayout(2,1));
       txtKeyField = new JTextField("KEY");
       txtKeyField.setEditable(false);
       
       txtGuessField = new JTextField("GUESS");
       txtGuessField.setEditable(false);
       
       panel.add(txtKeyField);
       panel.add(txtGuessField);
       
       
       
       contentPane.add(panel, "North");
    }
    
    
    
}