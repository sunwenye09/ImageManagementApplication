package ImageModificationTool;

import javafx.stage.FileChooser;
import java.io.File;

/**
 * SaveCommand class is mainly responsible for executing the "save image" command;
 * Supported formats are: bmp, gif, jpe, jpeg, png, jpg, tif, tiff, and wbmp.
 */
public class SaveCommand implements Command{
    private javaxt.io.Image image;
    private String format;

    public SaveCommand(javaxt.io.Image image, String format) {
        this.image = image;
        this.format = format;
    }

    /**
     * execute the save image command.
     */
    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        File outFile = fileChooser.showSaveDialog(null);
        if (outFile != null) {
            String outPath = outFile.getAbsolutePath();
            String out = outPath + "." + format;
            image.saveAs(out);
        }
    }
}
