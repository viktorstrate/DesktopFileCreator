package dk.qpqp;

import javax.swing.*;

/**
 * Created by viktorstrate on 3/9/15.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Using L&F: "+UIManager.getLookAndFeel().toString());
        } catch (Exception e) {
            System.out.println("Unable to set native look and feel: " + e);
        }
        new MainWindow();
    }
}
