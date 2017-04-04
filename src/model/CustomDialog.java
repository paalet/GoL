package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by simenperschandersen on 04.04.2017.
 * CustomDialog class to easily make commonly used optionpanels.
 */
public class CustomDialog {

    public CustomDialog(JFrame frame, boolean ok, boolean cancel, String message) {
        JOptionPane pane = new JOptionPane();
        pane.add(new JLabel(message));
        if(ok) {
            JButton okButton = new JButton("OK");
            pane.add(okButton);
        }
        if(cancel) {
            JButton cancelButton = new JButton("Cancel");
            pane.add(cancelButton);
        }
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(400, 200);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        
        
    }

}
