
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ProjectWindow extends JFrame {
    
    // using Hungarian notation for Swing class variables
    Container containerContent;
    JTextField textTopOutput;
    JButton btnFirst;
    JButton btnLast;
    JButton btnComputer;
    
    public ProjectWindow(int width, int height){
        super("p1JustinD");
        setSize(width, height);
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //center the frame
        this.setVisible(true);
    }
    
    private void initComponents(){
        
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch( Exception ex){
            ex.printStackTrace();
        }
        
        containerContent = this.getContentPane();
        textTopOutput = new JTextField();
        textTopOutput.setText("Hello, World!");
        
        //initialize the buttons
        btnFirst = new JButton("First");
        btnLast = new JButton("Last");
        btnComputer = new JButton("My Computer");
        
        
        //3 different ways to add an action to a button
        
        
        //Using Lambda for ease of use and less coding
        btnFirst.addActionListener(actionEvent -> {
            textTopOutput.setText("My first name is Justin");
        });
        
        //Using an Anonymous Class that overrides actionPerformed
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textTopOutput.setText("My last name is Duenow");
            }
        });
        
        //Using a custom action listener class that overloads actionPerformed
        ButtonListener listener = new ButtonListener();
        btnComputer.addActionListener(listener);
        
        
        containerContent.add(textTopOutput, BorderLayout.NORTH);
        
        JPanel panelButtons = new JPanel();
        panelButtons.add(btnFirst);
        panelButtons.add(btnLast);
        panelButtons.add(btnComputer);
        
        containerContent.add(panelButtons, BorderLayout.SOUTH);
        
    }
    
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton btnSource = (JButton)ae.getSource();
            
            if (btnSource == btnFirst){
                textTopOutput.setText("My first name is Justin");
            }else if (btnSource == btnLast){
                textTopOutput.setText("My last name is Duenow");
            }else if (btnSource == btnComputer){
                textTopOutput.setText("This project was created on a Surface Pro 3 running Windows 10.");
            }
        }
        
    }
}
