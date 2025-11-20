package view;

import java.awt.*;
import javax.swing.*;
import interface_adapter.quit_game.QuitGameController;

public class QuitGameDialog {
    private final String viewName = "quit";
    private QuitGameController quitGameController;

    private final JDialog quitGameDialog = new JDialog();

    public QuitGameDialog() {
        final JLabel title = new JLabel("Quit Game?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton quitGame = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quitGame.addActionListener(evt -> {
                quitGameDialog.dispose();
                quitGameController.execute(); // show Save Game dialog
            }
        );

        cancel.addActionListener(evt -> quitGameDialog.dispose()
        );

        buttons.add(quitGame);
        buttons.add(cancel);
        quitGameDialog.add(title, BorderLayout.NORTH);
        quitGameDialog.add(buttons, BorderLayout.SOUTH);
    }

    public void show() {
        quitGameDialog.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }

    // set controllers
    public void setQuitGameController(QuitGameController quitGameController) {
        this.quitGameController = quitGameController;

        this.quitGameController.setShowSaveDialog(() -> {
            // This code runs when the user clicks Quit → controller.execute()
            SaveGameDialog dialog = new SaveGameDialog();
            dialog.show();
        });
    }
}
