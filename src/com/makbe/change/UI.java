package com.makbe.change;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (screen.width / 2) - 50;
    int height = screen.height / 2;

    private final JTextField charged_field = new JTextField();
    private final JTextField paid_field = new JTextField();
    JTextArea details_area = new JTextArea();

    UI() {
        super("Change Calculator");
        setSize(width, height + 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font font = new Font("Iosevka Term", Font.PLAIN, 20);

        JLabel label = new JLabel("Amount charged");
        label.setBounds(80, 50, 150, 40);
        label.setFont(font);
        add(label);

        charged_field.setBounds(250, 50, 300, 40);
        charged_field.setFont(font);
        add(charged_field);

        label = new JLabel("Amount paid");
        label.setBounds(80, 120, 150, 40);
        label.setFont(font);
        add(label);

        paid_field.setFont(font);
        paid_field.setBounds(250, 120, 300, 40);
        add(paid_field);

        details_area.setBounds(80, 190, 470, 320);
        details_area.setFont(font);
        details_area.setEditable(false);
        add(details_area);

        JButton calculate_button = new JButton("Calculate");
        add(calculate_button);
        calculate_button.setBounds((width / 2) - 75, 540, 150, 40);
        calculate_button.setFont(font);
        calculate_button.addActionListener(this);

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(looks[1].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (charged_field.getText().isBlank() || paid_field.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Empty input field", null, JOptionPane.ERROR_MESSAGE);
            erase();
        } else {
            String charged = charged_field.getText();
            String paid = paid_field.getText();

            char[] arr = charged.toCharArray();
            for (char c : arr) {
                if (Character.isLetter(c) || Character.isWhitespace(c)) {
                    JOptionPane.showMessageDialog(this, "Letters & spaces are not allowed", null, JOptionPane.ERROR_MESSAGE);
                    charged_field.requestFocus();
                    return;
                }
            }

            char[] arr2 = paid.toCharArray();
            for (char c : arr2) {
                if (Character.isLetter(c) || Character.isWhitespace(c)) {
                    JOptionPane.showMessageDialog(this, "Letters & spaces are not allowed", null, JOptionPane.ERROR_MESSAGE);
                    paid_field.requestFocus();
                    return;
                }
            }

            int amount_charged = Integer.parseInt(charged);
            int amount_paid = Integer.parseInt(paid);
            new Change_Calculator(this, amount_charged, amount_paid);
        }
        erase();
    }

    public void erase() {
        charged_field.setText("");
        paid_field.setText("");
    }

}
