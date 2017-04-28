package controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by simenperschandersen on 28.04.2017.
 */
public class CustomInputDialog extends JDialog implements ActionListener {
    private JButton okButton;
    private JButton cancelButton;
    private JFrame frame;
    private JTextField xField;
    private JTextField yField;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel multiplyLabel;
    public Board board;

    public CustomInputDialog(JFrame frame, String message, Board board) {

        this.board = board;
        this.frame = frame;

        //frame = new JFrame(title);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(    new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;

        JLabel messageLabel = new JLabel(message);
        gbc.insets = new Insets(50,50, 10, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(messageLabel, gbc);

        yLabel = new JLabel("<html><body><div style='text-align: center'>Width</div></body></html>");
        gbc.insets = new Insets(50, 100, 70, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(yLabel, gbc);


        yField = new JTextField();
        yField.setPreferredSize(new Dimension(50, 24));
        gbc.insets = new Insets(50, 100, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(yField, gbc);

        xLabel = new JLabel("<html><body><div style='text-align: center'>Height</div></body></html>");
        gbc.insets = new Insets(50, 20, 70, 100);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(xLabel, gbc);

        xField = new JTextField();
        xField.setPreferredSize(new Dimension(50, 24));
        gbc.insets = new Insets(50, 20, 20, 100);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(xField, gbc);


        multiplyLabel = new JLabel("<html><body><div style='text-align: center'>X</div></body></html>");
        gbc.insets = new Insets(50, 20, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(multiplyLabel, gbc);

        okButton = new JButton("OK");
        gbc.insets = new Insets(10, 20, 20, 100);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(okButton, gbc);
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        gbc.insets = new Insets(10, 100, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cancelButton, gbc);
        cancelButton.addActionListener(this);




        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.isAlwaysOnTop();
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);




    }

    public void actionPerformed(ActionEvent e) {
        boolean ok = true;
        int x = 0;
        int y = 0;
        if(e.getSource() == cancelButton) {
            frame.dispose();

        }
        else {
            String yValue = yField.getText();
            String xValue = xField.getText();

            try {
                y = Integer.parseInt(yValue);
            }
            catch(NumberFormatException yError) {
                ok = false;
            }
            try {
                x = Integer.parseInt(xValue);
            }
            catch(NumberFormatException xError) {
                ok = false;
            }

            if(ok) {
                board.setWidth(x);
                board.setHeight(y);
                board.newBoard();
                frame.dispose();
            }
            else {
                CustomDialog wrongInteger = new CustomDialog("Wrong format", true, "<html><body><div style='text-align: center'>Invalid height/width format.<br>Only integers allowed.</div></body></html>");
            }

        }
    }

}

