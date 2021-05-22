package com.Speedqber;

import java.awt.*;
import javax.swing.*;

public class Siatka extends JFrame {

    public Siatka() {
        super("Siatka");
        add(new ObszarRysunku(200, 100));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Siatka();
    }

}