package ImageManagement;

import javafx.scene.control.TextField;


public class ImageModification {
    javaxt.io.Image image;
    public ImageModification(javaxt.io.Image image) {
        this.image = image;
    }
    public void resize(TextField width, TextField height) {
        String w = width.getText();
        String h = height.getText();
        if (w.length() == 0) {
            image.setHeight(Integer.parseInt(h));
        } else if (h.length() == 0) {
            image.setWidth(Integer.parseInt(w));
        } else {
            image.resize(Integer.parseInt(w), Integer.parseInt(h));
        }
        width.setText("");
        height.setText("");
    }
    public void rotate(TextField degrees) {
        String d = degrees.getText();
        image.rotate(Double.parseDouble(d));
        degrees.setText("");
    }
    public void gray(TextField grayPercent) {
        String percent = grayPercent.getText();
        double p = Double.parseDouble(percent);
        if (p > 100 || p < 0) {
            System.out.println("Illegal input: gray percent.");
            return;
        }
        image.desaturate(p/100);
        grayPercent.setText("");
    }
    public void crop(TextField offsetX, TextField offsetY, TextField width, TextField height) {
        int x = Integer.parseInt(offsetX.getText());
        int y = Integer.parseInt(offsetY.getText());
        int w = Integer.parseInt(width.getText());
        int h = Integer.parseInt(height.getText());
        image.crop(x, y, w, h);
        offsetX.setText("");
        offsetY.setText("");
        width.setText("");
        height.setText("");
    }

}
