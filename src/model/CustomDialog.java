package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CustomDialog class to easily make commonly used option panels.
 */
public class CustomDialog extends JDialog implements ActionListener {

    private JButton okButton;
    private JFrame frame;

    /**
     * Constructor shows a dialog through making a new JFrame showing a message label and, if called for, an OK-button
     * @param title is the String input for the title of the frame
     * @param ok is a boolean that if true will create an OK-button in the dialog
     * @param message is the String message to be shown in the label
     */
    public CustomDialog(String title, boolean ok, String message) {

        // Create JFrame, add JPanel and create GridNagConstraints
        frame = new JFrame(title);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(    new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,50, 10, 50);

        // Create and add the message label
        JLabel messageLabel = new JLabel(message);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;;
        panel.add(messageLabel, gbc);

        // Create and add the OK-button if called for
        if(ok) {
            okButton = new JButton("OK");
            gbc.insets = new Insets(10, 50, 20, 50);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(okButton, gbc);
            okButton.addActionListener(this);
        }

        // Set frame values and show dialog
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.isAlwaysOnTop();
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);


        
        
    }

    /**
     * Dispose the frame.
     * @param e button clicked
     */
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
