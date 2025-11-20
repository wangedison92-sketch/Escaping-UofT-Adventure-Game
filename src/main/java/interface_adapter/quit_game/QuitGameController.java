package interface_adapter.quit_game;

import view.QuitGameDialog;

/**
 * The controller for the Login Use Case.
 */
public class QuitGameController {
    private Runnable showSaveDialog;
//    private final QuitGameDialog quitGameDialog;
    private Runnable showQuitDialog;

//    public QuitGameController(QuitGameDialog quitGameDialog) {
//        this.quitGameDialog = quitGameDialog;
//    }

    public void setShowSaveDialog(Runnable r) {
        this.showSaveDialog = r;
    }

    public void setShowQuitDialog(Runnable r) {
        this.showQuitDialog = r;
    }


    public void execute() {
        showSaveDialog.run();
    }

    public void requestExit() {
        showQuitDialog.run();
    }
}