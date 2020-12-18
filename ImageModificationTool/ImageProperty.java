package ImageModificationTool;

import javafx.scene.text.Text;
import java.util.HashMap;
import javaxt.io.*;

/**
 * The ImageProperty class is responsible for storing and displaying image information.
 */
public class ImageProperty {
    private Image image;
    private String format;
    private HashMap<Integer, Object> exif;

    public ImageProperty(String file) {
        image = new javaxt.io.Image(file);
        int index = file.lastIndexOf('.');
        format = file.substring(index + 1);
        exif = image.getExifTags();
    }

    public String getFormat() {
        return format;
    }
    void setFormat(String format) {
        this.format = format;
    }

    /**
     * Display image properties on the graphical user interface.
     * @param properties: a list of JavaFX Texts which are used to display image properties.
     */
    public void displayProperty(Text[] properties) {
        for (int i = 0; i < properties.length; i++) {
            String property = getProperty(properties[i]);
            if (property.equalsIgnoreCase("null")) {
                properties[i].setText("No Info");
            } else {
                properties[i].setText(property);
            }
        }
    }

    /**
     * Retrieve the corresponding image information.
     * @param text: JavaFX Text which is used to display one of the image properties.
     * @return the corresponding image information in string format
     */
    private String getProperty(Text text) {
        String id = text.getId();
        if (id.equalsIgnoreCase( "widthProperty")) {
            return String.valueOf(image.getWidth());
        } else if (id.equalsIgnoreCase( "heightProperty")) {
            return String.valueOf(image.getHeight());
        } else if (id.equalsIgnoreCase( "date")) {
            return String.valueOf(exif.get(0x0132));
        } else if (id.equalsIgnoreCase("camera")) {
            return String.valueOf(exif.get(0x0110));
        } else if (id.equalsIgnoreCase("manufacturer")) {
            return String.valueOf(exif.get(0x010F));
        } else if (id.equalsIgnoreCase("focalLength")) {
            return String.valueOf(exif.get(0x920A));
        } else if (id.equalsIgnoreCase("exposureTime")) {
            return String.valueOf(exif.get(0x829A));
        } else if (id.equalsIgnoreCase("ISOSpeedRatings")) {
            return String.valueOf(exif.get(0x8827));
        } else {
            System.out.println("Undefined image property: " + id);
            return null;
        }
    }
}
