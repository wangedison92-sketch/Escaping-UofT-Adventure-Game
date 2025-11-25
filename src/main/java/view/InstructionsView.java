package view;

import javax.swing.*;
import java.awt.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateViewModel;

public class InstructionsView extends JPanel {

    public static final String VIEW_NAME = "instructions";

    public InstructionsView(ViewManagerModel viewManagerModel) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("How to Play");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea text = new JTextArea(
                "Welcome to the Escaping UofT Game!\n\n" +
                        "- Make choices\n" +
                        "- Explore Buildings\n" +
                        "- Escape the campus\n\n" +
                        "Good luck!"
        );
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setBackground(Color.DARK_GRAY);
        text.setForeground(Color.WHITE);

        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 55));
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(70, 130, 180));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        this.add(title, BorderLayout.NORTH);
        this.add(text, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            viewManagerModel.setState(new NavigateViewModel().getViewName()); // ????
            viewManagerModel.firePropertyChange();
        });
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}
