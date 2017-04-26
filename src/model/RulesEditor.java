package model;

import controller.MainScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates and displays an options pane with functionality for the user to set the rules of the game.
 */
public class RulesEditor extends JDialog {

    private static RulesEditor rulesEditor = new RulesEditor();
    private JButton okButton;
    private JButton cancelButton;
    private JPanel panel;
    private JFrame frame;
    private Color highlightColor = new Color(89, 154, 224);
    private ArrayList<Integer> birthRules = new ArrayList<>();
    private ArrayList<Integer> survivalRules = new ArrayList<>();


    public static RulesEditor getInstance() {
        return rulesEditor;
    }

    /**
     * Constructor that creates a JFrame with description of rules and options to add/remove rules
     * from the game through checking and un-checking buttons for each viable birth/survival rule.
     */
    private RulesEditor() {

        int width = 550;
        int height = 500;

        int[] birthRulesArray = GoL.getBornAmount();
        for (int rule : birthRulesArray) {
            birthRules.add(rule);
        }
        int[] survivalRulesArray = GoL.getSurviveAmount();
        for (int rule : survivalRulesArray) {
            survivalRules.add(rule);
        }

        frame = new JFrame("Rules Editor");
        isModal();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,1, 2, 1);

        // Rules header label
        gbc.gridy = 0;
        gbc.gridwidth = 9;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel rulesHeaderLabel = new JLabel("<html><span style='font-size:10px'>" +
                "The behavior of Conway's Game of Life is determined<br/>" +
                "by it's set of rules for population control.</span><br/><br/>" +
                "Below you can choose how many living neighbors are required<br/>" +
                "for a cell's birth and survival. Multiple conditions can<br/>" +
                "be activated for each category.</html>");
        panel.add(rulesHeaderLabel, gbc);

        // Birth rules label
        gbc.gridy++;
        gbc.insets = new Insets(20,1,2,1);
        JLabel birthDescLabel = new JLabel("<html><h3>Birth rules</h3>Select the amounts of live neighbors that should lead to a dead cell<br/>" +
                "being born in the next generation.</html>");
        panel.add(birthDescLabel, gbc);

        // Button for birth rule 0
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2,1, 2, 1);
        JButton b0Button = new JButton("0");
        panel.add(b0Button,gbc);
        b0Button.setFocusPainted(false);
        if (birthRules.contains(0)) {
            b0Button.setBackground(highlightColor);
        }
        b0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(0)) {
                    birthRules.remove(birthRules.indexOf(0));
                    b0Button.setBackground(null);
                } else {
                    birthRules.add(0);
                    b0Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 1
        gbc.gridx = 1;
        JButton b1Button = new JButton("1");
        panel.add(b1Button, gbc);
        b1Button.setFocusPainted(false);
        if (birthRules.contains(1)) {
            b1Button.setBackground(highlightColor);
        }
        b1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(1)) {
                    birthRules.remove(birthRules.indexOf(1));
                    b1Button.setBackground(null);
                } else {
                    birthRules.add(1);
                    b1Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 2
        gbc.gridx = 2;
        JButton b2Button = new JButton("2");
        panel.add(b2Button, gbc);
        b2Button.setFocusPainted(false);
        if (birthRules.contains(2)) {
            b2Button.setBackground(highlightColor);
        }
        b2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(2)) {
                    birthRules.remove(birthRules.indexOf(2));
                    b2Button.setBackground(null);
                } else {
                    birthRules.add(2);
                    b2Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 3
        gbc.gridx = 3;
        JButton b3Button = new JButton("3");
        panel.add(b3Button, gbc);
        b3Button.setFocusPainted(false);
        if (birthRules.contains(3)) {
            b3Button.setBackground(highlightColor);
        }
        b3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(3)) {
                    birthRules.remove(birthRules.indexOf(3));
                    b3Button.setBackground(null);
                } else {
                    birthRules.add(3);
                    b3Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 4
        gbc.gridx = 4;
        JButton b4Button = new JButton("4");
        panel.add(b4Button, gbc);
        b4Button.setFocusPainted(false);
        if (birthRules.contains(4)) {
            b4Button.setBackground(highlightColor);
        }
        b4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(4)) {
                    birthRules.remove(birthRules.indexOf(4));
                    b4Button.setBackground(null);
                } else {
                    birthRules.add(4);
                    b4Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 5
        gbc.gridx = 5;
        JButton b5Button = new JButton("5");
        panel.add(b5Button, gbc);
        b5Button.setFocusPainted(false);
        if (birthRules.contains(5)) {
            b5Button.setBackground(highlightColor);
        }
        b5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(5)) {
                    birthRules.remove(birthRules.indexOf(5));
                    b5Button.setBackground(null);
                } else {
                    birthRules.add(5);
                    b5Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 6
        gbc.gridx = 6;
        JButton b6Button = new JButton("6");
        panel.add(b6Button, gbc);
        b6Button.setFocusPainted(false);
        if (birthRules.contains(6)) {
            b6Button.setBackground(highlightColor);
        }
        b6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(6)) {
                    birthRules.remove(birthRules.indexOf(6));
                    b6Button.setBackground(null);
                } else {
                    birthRules.add(6);
                    b6Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 7
        gbc.gridx = 7;
        JButton b7Button = new JButton("7");
        panel.add(b7Button, gbc);
        b7Button.setFocusPainted(false);
        if (birthRules.contains(7)) {
            b7Button.setBackground(highlightColor);
        }
        b7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(7)) {
                    birthRules.remove(birthRules.indexOf(7));
                    b7Button.setBackground(null);
                } else {
                    birthRules.add(7);
                    b7Button.setBackground(highlightColor);
                }
            }
        });

        // Button for birth rule 8
        gbc.gridx = 8;
        JButton b8Button = new JButton("8");
        panel.add(b8Button, gbc);
        b8Button.setFocusPainted(false);
        if (birthRules.contains(8)) {
            b8Button.setBackground(highlightColor);
        }
        b8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (birthRules.contains(8)) {
                    birthRules.remove(birthRules.indexOf(8));
                    b8Button.setBackground(null);
                } else {
                    birthRules.add(8);
                    b8Button.setBackground(highlightColor);
                }
            }
        });

        // Survival rules description
        gbc.gridx = 0;
        gbc.gridwidth = 9;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10,1, 2, 1);
        JLabel survivalDescLabel = new JLabel("<html><h3>Survival rules</h3>Select the amounts of live neighbors that should lead to a live cell<br/>" +
                "surviving to the next generation.</html>");
        panel.add(survivalDescLabel, gbc);

        // Button for survival rule 0
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2,1, 2, 1);
        JButton s0Button = new JButton("0");
        panel.add(s0Button,gbc);
        s0Button.setFocusPainted(false);
        if (survivalRules.contains(0)) {
            s0Button.setBackground(highlightColor);
        }
        s0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(0)) {
                    survivalRules.remove(survivalRules.indexOf(0));
                    s0Button.setBackground(null);
                } else {
                    survivalRules.add(0);
                    s0Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 1
        gbc.gridx = 1;
        JButton s1Button = new JButton("1");
        panel.add(s1Button, gbc);
        s1Button.setFocusPainted(false);
        if (survivalRules.contains(1)) {
            s1Button.setBackground(highlightColor);
        }
        s1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(1)) {
                    survivalRules.remove(survivalRules.indexOf(1));
                    s1Button.setBackground(null);
                } else {
                    survivalRules.add(1);
                    s1Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 2
        gbc.gridx = 2;
        JButton s2Button = new JButton("2");
        panel.add(s2Button, gbc);
        s2Button.setFocusPainted(false);
        if (survivalRules.contains(2)) {
            s2Button.setBackground(highlightColor);
        }
        s2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(2)) {
                    survivalRules.remove(survivalRules.indexOf(2));
                    s2Button.setBackground(null);
                } else {
                    survivalRules.add(2);
                    s2Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 3
        gbc.gridx = 3;
        JButton s3Button = new JButton("3");
        panel.add(s3Button, gbc);
        s3Button.setFocusPainted(false);
        if (survivalRules.contains(3)) {
            s3Button.setBackground(highlightColor);
        }
        s3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(3)) {
                    survivalRules.remove(survivalRules.indexOf(3));
                    s3Button.setBackground(null);
                } else {
                    survivalRules.add(3);
                    s3Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 4
        gbc.gridx = 4;
        JButton s4Button = new JButton("4");
        panel.add(s4Button, gbc);
        s4Button.setFocusPainted(false);
        if (survivalRules.contains(4)) {
            s4Button.setBackground(highlightColor);
        }
        s4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(4)) {
                    survivalRules.remove(survivalRules.indexOf(4));
                    s4Button.setBackground(null);
                } else {
                    survivalRules.add(4);
                    s4Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 5
        gbc.gridx = 5;
        JButton s5Button = new JButton("5");
        panel.add(s5Button, gbc);
        s5Button.setFocusPainted(false);
        if (survivalRules.contains(5)) {
            s5Button.setBackground(highlightColor);
        }
        s5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(5)) {
                    survivalRules.remove(survivalRules.indexOf(5));
                    s5Button.setBackground(null);
                } else {
                    survivalRules.add(5);
                    s5Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 6
        gbc.gridx = 6;
        JButton s6Button = new JButton("6");
        panel.add(s6Button, gbc);
        s6Button.setFocusPainted(false);
        if (survivalRules.contains(6)) {
            s6Button.setBackground(highlightColor);
        }
        s6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(6)) {
                    survivalRules.remove(survivalRules.indexOf(6));
                    s6Button.setBackground(null);
                } else {
                    survivalRules.add(6);
                    s6Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 7
        gbc.gridx = 7;
        JButton s7Button = new JButton("7");
        panel.add(s7Button, gbc);
        s7Button.setFocusPainted(false);
        if (survivalRules.contains(7)) {
            s7Button.setBackground(highlightColor);
        }
        s7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(7)) {
                    survivalRules.remove(survivalRules.indexOf(7));
                    s7Button.setBackground(null);
                } else {
                    survivalRules.add(7);
                    s7Button.setBackground(highlightColor);
                }
            }
        });

        // Button for survival rule 8
        gbc.gridx = 8;
        JButton s8Button = new JButton("8");
        panel.add(s8Button, gbc);
        s8Button.setFocusPainted(false);
        if (survivalRules.contains(8)) {
            s8Button.setBackground(highlightColor);
        }
        s8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (survivalRules.contains(8)) {
                    survivalRules.remove(survivalRules.indexOf(8));
                    s8Button.setBackground(null);
                } else {
                    survivalRules.add(8);
                    s8Button.setBackground(highlightColor);
                }
            }
        });


        // Cancel-button
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.insets = new Insets(40,0,0,0);
        gbc.ipady = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(cancelButton, gbc);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
            }
        });

        // OK-button
        JButton okButton = new JButton("OK");
        gbc.gridx = 7;
        panel.add(okButton, gbc);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Convert birth rule list back to array and implement it
                int[] birthRulesArray = new int[birthRules.size()];
                for (int i = 0; i < birthRulesArray.length; i++) {
                    birthRulesArray[i] = birthRules.get(i);
                }
                Arrays.sort(birthRulesArray);
                GoL.setBornAmount(birthRulesArray);

                // Convert survival rule list back to array and implement it
                int[] survivalRulesArray = new int[survivalRules.size()];
                for (int i = 0; i < survivalRulesArray.length; i++) {
                    survivalRulesArray[i] = survivalRules.get(i);
                }
                Arrays.sort(survivalRulesArray);
                GoL.setSurviveAmount(survivalRulesArray);

                // Display updated rule set
                // @TODO update rulesLabel through mainsScreenController

                // Hide the rules editor
                frame.setVisible(false);
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(width, height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setAlwaysOnTop(true);
        frame.setVisible(false);
        pack();
    }

    public void setVisible(boolean b) {

        frame.setVisible(b);
    }
}
