package ImageModificationTool;

import javafx.scene.control.TextField;

/**
 * ImageModification is responsible for image modification: resize, rotate, gray, and crop.
 */
public class ImageModification {
    private javaxt.io.Image image;
    public ImageModification(javaxt.io.Image image) {
        this.image = image;
    }

    /**
     * Resize image.
     * @param width: JavaFX TextField containing the user-input width of the image;
     * @param height: JavaFX TextField containing the user-input height of the image;
     */
    public void resize(TextField width, TextField height) {
        String w = width.getText();
        String h = height.getText();
        //check the validity of user input
        if (w.isEmpty() && h.isEmpty()) {
            AlertDialogue.showWarning("Invalid input! \nAt least one of the sizes has to be greater than 0!");
            return;
        } else if (Integer.parseInt(h) < 0 || Integer.parseInt(w) < 0) {
            AlertDialogue.showWarning("Invalid input! \nImage size cannot be less than 0!");
            return;
        }
        //execute image resizing
        if (w.isEmpty()) {
            image.setHeight(Integer.parseInt(h));
        } else if (h.isEmpty()) {
            image.setWidth(Integer.parseInt(w));
        } else {
            image.resize(Integer.parseInt(w), Integer.parseInt(h));
        }
        //set the TextFields to empty for new input
        width.setText("");
        height.setText("");
    }

    /**
     * Rotate image.
     * @param degrees: JavaFX TextField containing the user-input rotating degrees.
     */
    public void rotate(TextField degrees) {
        String d = degrees.getText();
        image.rotate(Double.parseDouble(d));
        degrees.setText("");
    }

    /**
     * Graying image.
     * @param grayPercent: JavaFX TextField containing the user-input graying percentage.
     */
    public void gray(TextField grayPercent) {
        String percent = grayPercent.getText();
        double p = Double.parseDouble(percent);
        if (p > 100 || p < 0) {
            AlertDialogue.showWarning("Invalid input! \nThe percent value should be between 0 and 100.");
            return;
        }
        image.desaturate(p/100);
        grayPercent.setText("");
    }

    /**
     * crop image.
     * @param offsetX: pixels of horizontal offset from the top left corner;
     * @param offsetY: pixels of vertical offset from the top left corner;
     * @param width: the new width after cropping
     * @param height: the new height after cropping
     */
    public void crop(TextField offsetX, TextField offsetY, TextField width, TextField height) {
        int x, y, w, h;
        //check the input of the new width and height
        if (width.getText().isEmpty() || height.getText().isEmpty()) {
            AlertDialogue.showWarning("Please enter the new width and height.");
            return;
        } else {
            w = Integer.parseInt(width.getText());
            h = Integer.parseInt(height.getText());
        }
        if (w <= 0 || h <= 0) {
            AlertDialogue.showWarning("Invalid input! \nThe new width and height have to be greater than 0!");
            return;
        }
        //check the input of offset x and offset y
        if (offsetX.getText().isEmpty()) {
            x = 0;
        } else {
            x = Integer.parseInt(offsetX.getText());
        }
        if (offsetY.getText().isEmpty()) {
            y = 0;
        } else {
            y = Integer.parseInt(offsetY.getText());
        }
        //execute the modification
        image.crop(x, y, w, h);
        //set the text filed to empty status for new input
        offsetX.setText("");
        offsetY.setText("");
        width.setText("");
        height.setText("");
    }
}
