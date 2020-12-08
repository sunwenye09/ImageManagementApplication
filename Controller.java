package ImageManagement;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    @FXML
    private ImageView imageView;
    /*@FXML
    private RadioButton bmp, gif, jpe, jpeg, jpg, png, tif, tiff, wbmp;*/
    @FXML
    private Text widthProperty, heightProperty, date, camera, manufacturer, focalLength, exposureTime, ISOSpeedRatings;
    @FXML
    private TextField width, height, degrees, grayPercent, offsetX, offsetY, newWidth, newHeight;

    private String uploadFile;
    private javaxt.io.Image image;
    //Composite design pattern
    private ImageProperty imageProperty;        //responsible for displaying image properties
    private ImageModification modification;     //responsible for image modification

    @FXML
    public void uploadClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.png", "*.jpg", "*.jpe" ));
        File selectedFile = fileChooser.showOpenDialog(null);
        uploadFile = selectedFile.getPath();
        if (selectedFile != null) {
            try {
                FileInputStream inputStream = new FileInputStream(uploadFile);
                Image imageFX = new Image(inputStream);
                imageView.setImage(imageFX);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            init();
        }
    }
    //initializing variables: image, imageProperty, and modification
    private void init() {
        image = new javaxt.io.Image(uploadFile);
        imageProperty = new ImageProperty(uploadFile);
        displayProperty();
        modification = new ImageModification(image);
    }
    @FXML
    public void bmp() {
        imageProperty.setFormat("bmp");
    }
    public void gif() {
        imageProperty.setFormat("gif");
    }
    public void jpe() {
        imageProperty.setFormat("jpe");
    }
    public void jpeg() {
        imageProperty.setFormat("jpeg");
    }
    public void jpg() {
        imageProperty.setFormat("jpg");
    }
    public void png() {
        imageProperty.setFormat("png");
    }
    public void tif() {
        imageProperty.setFormat("tif");
    }
    public void tiff() {
        imageProperty.setFormat("tiff");
    }
    public void wbmp() {
        imageProperty.setFormat("wbmp");
    }
    @FXML
    public void save() {
        FileChooser fileChooser = new FileChooser();
        File outFile = fileChooser.showSaveDialog(null);
        String outPath = outFile.getAbsolutePath();
        String out = outPath + "." + imageProperty.getFormat();
        image.saveAs(out);
    }

    private void displayProperty() {
        Text[] properties = new Text[] {widthProperty, heightProperty, date, camera, manufacturer, focalLength, exposureTime, ISOSpeedRatings};
        imageProperty.displayProperty(properties);
    }
    @FXML
    private void resize() {
        modification.resize(width, height);
        refreshImage();
    }
    @FXML
    private void rotate() {
        modification.rotate(degrees);
        refreshImage();
    }
    @FXML
    private void gray() {
        modification.gray(grayPercent);
        refreshImage();
    }
    @FXML
    private void crop() {
        modification.crop(offsetX, offsetY, newWidth, newHeight);
        refreshImage();
    }
    //refresh image after each modification so that users know their modification took effect.
    private void refreshImage() {
        try {
            image.saveAs("TempImage/temp." + imageProperty.getFormat());
            FileInputStream inputStream = new FileInputStream("TempImage/temp." + imageProperty.getFormat());
            Image imageFX = new Image(inputStream);
            imageView.setImage(imageFX);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
