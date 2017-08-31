
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ProjectWindow extends JFrame {
    
    JTextField textTopField;
    JButton btnFirst;
    JButton btnLast;
    JButton btnComputer;
    
    public ProjectWindow(int width, int height){
        initComponents();
        
        setVisible(true);
        
    }
    
    private void initComponents(){
        textTopField = new JTextField();
        btnFirst = new JButton();
        btnLast = new JButton();
        btnComputer = new JButton();
        
        
        
        
    }
}
