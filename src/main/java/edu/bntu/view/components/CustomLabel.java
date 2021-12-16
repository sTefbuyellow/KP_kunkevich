package edu.bntu.view.components;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text){
        setHorizontalAlignment(JLabel.LEFT);
        Font font = new Font("Bold", Font.BOLD, 20);
        setFont(font);
        setText(text);
    }
}