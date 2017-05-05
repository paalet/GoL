package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for creating a dialog input for setting the dimensions of the board.
 */
public class DimensionsInputDialog extends JDialog implements ActionListener {

    private JButton okButton;
    private JButton cancelButton;
    private JFrame frame;
    private JTextField xField;
    private JTextField yField;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel multiplyLabel;
    public Board board;

    /**
     * Constructor creates and displays a dialog with a layout containing input fields for height and width.
     * @param frame the frame to display
     * @param message the text of the dialog
     * @param board the current live game board
     */
    public DimensionsInputDialog(JFrame frame, String message, Board board) {

        this.board = board;
        this.frame = frame;

        // Set up frame, panel and GridBagConstraints
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(    new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.5;

        // Create main text label
        JLabel messageLabel = new JLabel(message);
        gbc.insets = new Insets(50,50, 10, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(messageLabel, gbc);

        // Create width text label
        xLabel = new JLabel("<html><body><div style='text-align: center'>Width</div></body></html>");
        gbc.insets = new Insets(50, 20, 70, 100);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(xLabel, gbc);

        // Create width textfield
        xField = new JTextField();
        xField.setPreferredSize(new Dimension(50, 24));
        gbc.insets = new Insets(50, 20, 20, 100);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(xField, gbc);

        // Create height label
        yLabel = new JLabel("<html><body><div style='text-align: center'>Height</div></body></html>");
        gbc.insets = new Insets(50, 100, 70, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(yLabel, gbc);

        // Create height textfield
        yField = new JTextField();
        yField.setPreferredSize(new Dimension(50, 24));
        gbc.insets = new Insets(50, 100, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(yField, gbc);

        // Create "X" label
        multiplyLabel = new JLabel("<html><body><div style='text-align: center'>X</div></body></html>");
        gbc.insets = new Insets(50, 20, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(multiplyLabel, gbc);

        // Create OK-button
        okButton = new JButton("OK");
        gbc.insets = new Insets(10, 20, 20, 100);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(okButton, gbc);
        okButton.addActionListener(this);

        // Create Cancel-button
        cancelButton = new JButton("Cancel");
        gbc.insets = new Insets(10, 100, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cancelButton, gbc);
        cancelButton.addActionListener(this);

        // Set display values and display frame
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.isAlwaysOnTop();
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
    }

    /**
     * Actionevent that if OK-button is pressed checks the dimensions input validity and and updates board size accordingly.
     * Simply disposes the frame if Cancel-button is pressed.
     * @param e the actionevent of either the Cancel or OK-button
     */
    public void actionPerformed(ActionEvent e) {

        boolean ok = true;
        int x = 0;
        int y = 0;

        // Dispose frame if Cancel-button is the source
        if(e.getSource() == cancelButton) {
            frame.dispose();

        // If OK-button is the source checks the input validity
        } else {
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

            // Sets new board size and closes frame
            if(ok) {

                board.setBoardSizeToDimensions(board, y, x);
                frame.dispose();
            }

            // Shows a dialog telling user that input is incorrect
            else {

                CustomDialog wrongInteger = new CustomDialog("Wrong format", true, "<html><body><div style='text-align: center'>Invalid height/width format.<br>Only integers allowed.</div></body></html>");
            }
        }
    }
}

