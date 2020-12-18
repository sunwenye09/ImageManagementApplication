package ImageModificationTool;

import javafx.scene.control.Alert;

/**
 * The AlertDialogue class is responsible for:
 * (1) showing warning information to users when user inputs or commands are invalid;
 * (2) send informational message to users.
 */
public class AlertDialogue {

    private static Alert warning = new Alert(Alert.AlertType.WARNING);
    private static Alert message = new Alert(Alert.AlertType.INFORMATION);
    /**
     * Show warning dialogue.
     * @param content: the warning message to be shown.
     */
    public static void showWarning(String content) {
        warning.setContentText(content);
        warning.show();
    }

    /**
     * Communicate information to users.
     * @param info the information sent to users.
     */
    public static void showInfo(String info) {
        message.setContentText(info);
        message.show();
    }
}
