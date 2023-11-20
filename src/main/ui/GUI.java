package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private int count = 0;
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private ImageIcon image;


    public GUI() {
        frame = new JFrame();
        JButton button = new JButton("Click me");
        button.addActionListener(this);
        label = new JLabel("Total clicks = 0");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        //panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);

        image = new ImageIcon("icons8-library-building-50.png");

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Library KIOSK Software");
        frame.setIconImage(image.getImage());
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Total clicks = " + this.count);

    }
}
