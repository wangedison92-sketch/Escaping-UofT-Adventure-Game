package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Could not set look and feel: " + e.getMessage());
            }

            AppBuilder appBuilder = new AppBuilder();
            JFrame app = null;
            try {
                app = appBuilder.build("game_save.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }


            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}