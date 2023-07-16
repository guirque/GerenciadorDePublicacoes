import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI implements ActionListener
{
    //GUI Setup
    public GUI()
    {
        //Setting frame
        JFrame window = new JFrame("Gerenciador de Publicações");
        window.pack();
        window.setSize(new Dimension(400, 400));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Setting Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        
        //Integrating Panels with Frame
        window.add(mainPanel);
        
        //Visibility
        window.setVisible(true);
    }

    //Events
    public void actionPerformed(ActionEvent event)
    {

    }
}