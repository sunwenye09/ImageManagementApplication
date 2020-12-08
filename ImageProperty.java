package ImageManagement;

import javafx.scene.text.Text;

import java.util.HashMap;
import javaxt.io.*;

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
    //strategy design pattern
    private String getProperty(Text text) {
        if (text.getId().equalsIgnoreCase( "widthProperty")) {
            return String.valueOf(image.getWidth());
        } else if (text.getId().equalsIgnoreCase( "heightProperty")) {
            return String.valueOf(image.getHeight());
        } else if (text.getId().equalsIgnoreCase( "date")) {
            return String.valueOf(exif.get(0x0132));
        } else if (text.getId().equalsIgnoreCase("camera")) {
            return String.valueOf(exif.get(0x0110));
        } else if (text.getId().equalsIgnoreCase("manufacturer")) {
            return String.valueOf(exif.get(0x010F));
        } else if (text.getId().equalsIgnoreCase("focalLength")) {
            return String.valueOf(exif.get(0x920A));
        } else if (text.getId().equalsIgnoreCase("exposureTime")) {
            return String.valueOf(exif.get(0x829A));
        } else if (text.getId().equalsIgnoreCase("ISOSpeedRatings")) {
            return String.valueOf(exif.get(0x8827));
        } else {
            System.out.println("Undefined image property: " + text.getId());
            return null;
        }
    }
    public void displayProperty(Text[] properties) {
        for (int i = 0; i < properties.length; i++) {
            String property = getProperty(properties[i]);
            if (property.equalsIgnoreCase("null")) {
                properties[i].setText("N/A");
            } else {
                properties[i].setText(property);
            }
        }

    }
}
