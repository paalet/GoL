package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates and displays an options pane with functionality for the user to set the rules of the game.
 */
public class RulesEditor extends JDialog implements ActionListener {
        private JButton okButton;
        private JButton cancelButton;
        private JPanel panel;
        private JFrame frame;


        public RulesEditor() {
            int width = 400;
            int height = 500;
            frame = new JFrame("Rules Editor");
            isModal();
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(    new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            
            JButton b0 = new JButton("0");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(b0,gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            JLabel messageLabel = new JLabel("Heeeeeello rules");
            panel.add(messageLabel, gbc);
            gbc.insets = new Insets(2,1, 2, 1);

            JButton okButton = new JButton("OK");
            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(okButton, gbc);
            okButton.addActionListener(this);

            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(width, height);
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            frame.setAlwaysOnTop(true);
            frame.setVisible(true);
            pack();




        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }

}
