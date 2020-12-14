package ImageModificationTool;

import javafx.scene.control.Alert;

/**
 * The AlertDialogue class is responsible for show warning information to users when user inputs or commands are invalid.
 */
public class AlertDialogue {

    private static Alert alert = new Alert(Alert.AlertType.WARNING);

    /**
     * Show warning dialogue.
     * @param content: the warning message to be shown.
     */
    public static void showWarning(String content) {
        alert.setContentText(content);
        alert.show();
    }
}
