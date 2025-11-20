package view;

import javax.swing.*;

import interface_adapter.quit_game.QuitGameController;
import view.QuitGameDialog;

public class NavigateView extends javax.swing.JFrame {
    // QUIT GAME CONTROLLER
    private QuitGameController quitGameController;

    // QUIT BUTTON ; feel free move the code to wherever makes sense
    private void createQuitButton() {
        JButton quit = new JButton("Quit");
        quit.addActionListener(evt -> quitGameController.requestExit());
    }

    // QUIT GAME CONTROLLER
    public void setQuitGameController(QuitGameController quitGameController) {
        this.quitGameController = quitGameController;

        this.quitGameController.setShowQuitDialog(() -> {
            // This code runs when the user clicks Quit → controller.execute()
            QuitGameDialog dialog = new QuitGameDialog();
            dialog.show();
        });
    }
}