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
     * Resize image. One of the fields (width, height) is allowed to be empty, it will be calculated by ratio.
     * @param width: JavaFX TextField containing the user-input width of the image;
     * @param height: JavaFX TextField containing the user-input height of the image;
     */
    public void resize(TextField width, TextField height) {
        int w, h;
        if (width.getText().isEmpty() && height.getText().isEmpty()) {
            AlertDialogue.showWarning("Please provide the width/height or both of them!");
            return;
        } else if (width.getText().isEmpty()) {
            h = parseSize(height);
            if (h <= 0) {
                return;
            }
            image.setHeight(h);
        } else if (height.getText().isEmpty()) {
            w = parseSize(width);
            if (w <= 0) {
                return;
            }
            image.setWidth(w);
        } else {
            h = parseSize(height);
            w = parseSize(width);
            if (h <= 0 || w <= 0) {
                return;
            }
            image.resize(w, h);
        }
        AlertDialogue.showInfo("Image was resized successfully.\nClick \"Save Image\" to save resized image.");

        //set the TextFields to empty for new input
        width.setText("");
        height.setText("");
    }

    /**
     * Rotate image.
     * @param degrees: JavaFX TextField containing the user-input rotating degrees.
     */
    public void rotate(TextField degrees) {
        double d;
        try {
            d = Double.parseDouble(degrees.getText());
        } catch (NumberFormatException e) {
            AlertDialogue.showWarning("Invalid input!");
            return;
        }
        image.rotate(d);
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
     * @param offsetX: pixels of horizontal offset from the top left corner, allowed to be empty;
     * @param offsetY: pixels of vertical offset from the top left corner, allowed to be empty;
     * @param width: the new width after cropping, must be provided;
     * @param height: the new height after cropping, must be provided.
     */
    public void crop(TextField offsetX, TextField offsetY, TextField width, TextField height) {
        //check the input of offset x and offset y
        int x = parseOffset(offsetX);
        int y = parseOffset(offsetY);
        if (x == Integer.MIN_VALUE || y == Integer.MIN_VALUE) {
            return;
        }

        //check the input of the new width and new height
        int w = parseSize(width);
        int h = parseSize(height);
        if (w < 0 || h < 0) {
            return;
        }

        //execute the modification
        image.crop(x, y, w, h);

        //set the text filed to empty status for new input
        offsetX.setText("");
        offsetY.setText("");
        width.setText("");
        height.setText("");
    }

    /**
     * Check the validity of user inputs and parse the offset values.
     * @param offset JavaFX TextField containing the user-input offset from the top-left corner.
     * @return the number of pixels offset from the top-left corner.
     */
    private int parseOffset(TextField offset) {
        int result;
        if (offset.getText().isEmpty()) {
            return 0;
        } else {
            try {
                result = Integer.parseInt(offset.getText());
            } catch (NumberFormatException e) {
                AlertDialogue.showWarning("Invalid input! \nThe offset has to be an integer!");
                return Integer.MIN_VALUE;
            }
        }
        return result;
    }

    /**
     * Check the validity of user inputs and parse the new width and height values.
     * @param size JavaFX TextField containing the size of new width or new height.
     * @return the size of new width or new height.
     */
    private int parseSize(TextField size) {
        int result;
        if (size.getText().isEmpty()) {
            AlertDialogue.showWarning("Please enter the width and height.");
            return -1;
        } else {
            try {
                result = Integer.parseInt(size.getText());
            } catch (NumberFormatException e) {
                AlertDialogue.showWarning("Invalid input! \nThe width and height have to be integers!");
                return -1;
            }
        }
        if (result <= 0) {
            AlertDialogue.showWarning("Invalid input! \nThe width and height have to be greater than 0!");
            return -1;
        }
        return result;
    }
}
