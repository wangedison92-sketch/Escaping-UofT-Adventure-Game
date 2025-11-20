package view;

import interface_adapter.quit_game.QuitGameController;
import interface_adapter.save_progress.SaveProgressController;

import javax.swing.*;
import java.awt.*;

public class SaveGameDialog {
    private final String viewName = "save game";
    private SaveProgressController saveProgressController;

    private final JDialog saveGameDialog = new JDialog();

    public SaveGameDialog() {
        final JLabel title = new JLabel("Save game before quitting?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        JButton quitAndSave = new JButton("Save and quit");
        JButton quitDontSave = new JButton("Quit without saving");

        quitAndSave.addActionListener(evt -> {
                saveProgressController.execute();
                closeGame();
            }
        );

        quitDontSave.addActionListener(evt -> closeGame()
        );

        buttons.add(quitAndSave);
        buttons.add(quitDontSave);
        saveGameDialog.add(title, BorderLayout.NORTH);
        saveGameDialog.add(buttons, BorderLayout.SOUTH);
    }

    private void closeGame() {
        saveGameDialog.dispose();
        System.exit(0);
    }

    public void show() {
        saveGameDialog.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }

    public void setSaveGameController(SaveProgressController saveProgressController) {
        this.saveProgressController = saveProgressController;
    }
}
