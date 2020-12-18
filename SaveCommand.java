package ImageModificationTool;

import javafx.stage.FileChooser;
import java.io.File;

/**
 * SaveCommand class is mainly responsible for executing the "save image" command;
 * Supported formats are: bmp, gif, jpe, jpeg, png, jpg, tif, and tiff.
 */
public class SaveCommand implements Command{
    private javaxt.io.Image image;
    private String format;

    public SaveCommand(javaxt.io.Image image, String format) {
        this.image = image;
        this.format = format;
    }

    /**
     * This function is to execute the save image command.
     */
    @Override
    public void execute() {
        FileChooser fileChooser = new FileChooser();
        File outFile = fileChooser.showSaveDialog(null);
        if (outFile != null) {
            String outPath = outFile.getAbsolutePath();
            String out = outPath + "." + format;
            /* "bmp" can't be converted from some of other formats, so convert the image to "jpg" first
             * to ensure successful conversion to "bmp" format.
             */
            if (format.equalsIgnoreCase("bmp")) {
                image = new javaxt.io.Image(image.getByteArray("jpg"));
            }
            image.saveAs(out);
        }
    }
}
