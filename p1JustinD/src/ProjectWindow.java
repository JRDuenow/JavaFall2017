
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public class ProjectWindow extends JFrame {
    
    Container frameContainer;
    JTextField textTopField;
    JButton btnFirst;
    JButton btnLast;
    JButton btnComputer;
    
    public ProjectWindow(int width, int height){
        super("p1JustinD");
        setSize(width, height);
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void initComponents(){
        
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch( Exception ex){
            ex.printStackTrace();
        }
        
        frameContainer = this.getContentPane();
        textTopField = new JTextField();
        textTopField.setText("Hello, World!");
        
        //initialize the buttons
        btnFirst = new JButton("First");
        btnLast = new JButton("Last");
        btnComputer = new JButton("My Computer");
        
        //Using Lambda for ease of use
        btnFirst.addActionListener((actionEvent) -> {
            textTopField.setText("My first name is Justin");
        });
        
        btnLast.addActionListener((actionEvent) -> {
            textTopField.setText("My last name is Duenow");
        });
        
        btnComputer.addActionListener((actionEvent) -> {
            textTopField.setText("This project was created on a Surface Pro 3 running Windows 10.");
        });
        
        
        frameContainer.add(textTopField, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnFirst);
        buttonPanel.add(btnLast);
        buttonPanel.add(btnComputer);
        
        frameContainer.add(buttonPanel, BorderLayout.SOUTH);
        
    }
}
