package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUItest implements ActionListener {
    private JFrame frame;
    private JLabel label;
    //private JPanel panel;
    private ImageIcon image;


    public GUItest() {
        image = new ImageIcon("ui/libraryImage.png");
        label = new JLabel();
        label.setText("test"); // set the text on the label
        label.setIcon(image); // set the image on the label
        label.setHorizontalTextPosition(JLabel.CENTER); // set the text position horizontally
        label.setVerticalTextPosition(JLabel.TOP); // set the text position vertically
        label.setForeground(new Color(000000)); // set the text color
        label.setIconTextGap(50); // set the gap of the text from the image
        label.setBackground(Color.WHITE); // set the background color
        label.setOpaque(true); // display the background color

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.add(label);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}