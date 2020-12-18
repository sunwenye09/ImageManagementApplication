package ImageModificationTool;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.ByteArrayInputStream;

public class Controller {
    @FXML
    private ImageView imageView;
    @FXML
    private Text widthProperty, heightProperty, date, camera, manufacturer, focalLength, exposureTime, ISOSpeedRatings;
    @FXML
    private TextField width, height, degrees, grayPercent, offsetX, offsetY, newWidth, newHeight;

    private String uploadFile;
    private javaxt.io.Image image;
    //Command design pattern
    private ImageProperty imageProperty;        //responsible for storing and displaying image properties
    private ImageModification modification;     //responsible for image modification

    /**
     * In response to "Upload Image" button: upload the selected image and set up the initialization.
     */
    @FXML
    public void upload() {
        //Command design pattern
        UploadCommand command = new UploadCommand(imageView);
        command.execute();
        uploadFile = command.getUploadFile();
        init();
    }
    /**
     * Initialize image, imageProperty, and modification. Display image properties.
     */
    private void init() {
        if (uploadFile != null) {
            image = new javaxt.io.Image(uploadFile);
            imageProperty = new ImageProperty(uploadFile);
            displayProperty();
            modification = new ImageModification(image);
        }
    }

    /**
     * The following 8 functions, from "bmp()" to "tiff()", are in response to respective radio buttons.
     * Their job is to convert the image to selected format.
     */
    @FXML
    public void bmp() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("bmp");
        }
    }
    public void gif() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("gif");
        }
    }
    public void jpe() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("jpe");
        }
    }
    public void jpeg() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("jpeg");
        }
    }
    public void jpg() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("jpg");
        }
    }
    public void png() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("png");
        }
    }
    public void tif() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("tif");
        }
    }
    public void tiff() {
        if (checkStatus(imageProperty)) {
            imageProperty.setFormat("tiff");
        }
    }

    /**
     * In response to "Save Image" button: save the image to selected location.
     */
    @FXML
    public void save() {
        //Command design pattern
        if (checkStatus(image)) {
            Command command = new SaveCommand(image, imageProperty.getFormat());
            command.execute();
        }
    }

    /**
     * Display image properties on the user interface: width, height, date, camera, manufacturer,
     * focal length, exposure time, and ISO speed ratings.
     */
    private void displayProperty() {
        Text[] properties = new Text[] {widthProperty, heightProperty, date, camera, manufacturer,
                focalLength, exposureTime, ISOSpeedRatings};
        if (checkStatus(imageProperty)) {
            imageProperty.displayProperty(properties);
        }
    }

    /**
     * In response to the "Resize" button: resize the image to the designated size.
     */
    @FXML
    public void resize() {
        if (checkStatus(modification)) {
            modification.resize(width, height);
            refreshImage();
        }
    }

    /**
     * In response to the "Rotate" button: rotate the image by the input degrees.
     */
    @FXML
    public void rotate() {
        if (checkStatus(modification)) {
            modification.rotate(degrees);
            refreshImage();
        }
    }

    /**
     * In response to the "Gray" button: gray the image by the input percent.
     */
    @FXML
    public void gray() {
        if (checkStatus(modification)) {
            modification.gray(grayPercent);
            refreshImage();
        }
    }

    /**
     * In response to the "Crop" button: crop the image according to input parameters.
     */
    @FXML
    public void crop() {
        if (checkStatus(modification)) {
            modification.crop(offsetX, offsetY, newWidth, newHeight);
            refreshImage();
        }
    }
    /**
     * Refresh image after each modification so that users know their modification took effect.
     */
    private void refreshImage() {
        ByteArrayInputStream input = new ByteArrayInputStream(image.getByteArray(imageProperty.getFormat()));
        Image imageFX = new Image(input);
        imageView.setImage(imageFX);
    }
    /**
     * Check if the object O is initialized;
     * @param o: the object to be checked;
     * @return true if initialized, otherwise false.
     */
    private boolean checkStatus(Object o) {
        if (o == null) {
            AlertDialogue.showWarning("Please upload image first!");
            return false;
        } else {
            return true;
        }
    }
}
