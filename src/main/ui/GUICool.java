package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICool implements ActionListener {
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JButton button;
    private JLabel success;

    public GUICool() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("user");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordLabel = new JLabel("password");
        passwordLabel.setBounds(10,70,80,25);
        panel.add(passwordLabel);

        passwordText = new JTextField(20);
        passwordText.setBounds(100,70,165,25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.addActionListener(this);
        button.setBounds(102,100,80,25);
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10,140,300,25);
        panel.add(success);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + " " + password);
        success.setText("yay");
    }

}
