package ImageModificationTool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * UploadCommand class is mainly responsible for executing the "upload image" command;
 * Supported formats are: bmp, gif, jpe, jpeg, png, jpg.
 */
public class UploadCommand implements Command{
    private String uploadFile;
    private ImageView imageView;

    public UploadCommand(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * execute the upload image command.
     */
    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.png", "*.jpg", "*.jpe" ));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            uploadFile = selectedFile.getPath();
            try {
                FileInputStream inputStream = new FileInputStream(uploadFile);
                Image imageFX = new Image(inputStream);
                imageView.setImage(imageFX);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String getUploadFile() {
        return uploadFile;
    }
}
