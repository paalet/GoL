package model;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by simenperschandersen on 04.04.2017.
 * CustomDialog class to easily make commonly used option panels.
 */
public class CustomDialog extends JDialog implements ActionListener {
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel;
    private JFrame frame;


    public CustomDialog(String title, boolean ok, String message, int width, int height) {
        frame = new JFrame(title);
        isModal();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(    new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2, 2, 2);

        JLabel messageLabel = new JLabel(message);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;;
        panel.add(messageLabel, gbc);

        if(ok) {
            okButton = new JButton("OK");
            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(okButton, gbc);
            okButton.addActionListener(this);
        }

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(width, height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);


        
        
    }

    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}
