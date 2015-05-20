package electricdrill.edje.guifeatures;

import electricdrill.edje.dependencies.EDGraphicsEnvironment;
import sun.awt.image.OffScreenImageSource;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class EDImage extends Image {

    String pathToImage;
    String name;
    Hashtable properties;
    WritableRaster raster;
    OffScreenImageSource osis;

    public EDImage(String newPathToImage, String newName) {
        pathToImage = newPathToImage;

        name = newName;
    }

    public EDImage(int width, int height) {

    }

    public String getName() {
        return name;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public Graphics getGraphics() {
        return createGraphics();
    }

    public Graphics createGraphics() {
        EDGraphicsEnvironment env = EDGraphicsEnvironment.getLocalGraphicsEnvironment();

        return env.createGraphics(this);
    }

    public Object getProperty(String name, ImageObserver observer) {
        return getProperty(name);
    }

    public Object getProperty(String name) {
        if (name.equals(null)) {
            throw new NullPointerException("null property name is not allowed");
        }

        if (properties.equals(null)) {
            return Image.UndefinedProperty;
        }
        Object o = properties.get(name);

        if (o.equals(null)) {
            o = Image.UndefinedProperty;
        }

        return o;
    }

    public int getWidth() {
        return raster.getWidth();
    }

    public int getWidth(ImageObserver observer) {
        return raster.getWidth();
    }

    public int getHeight() {
        return raster.getHeight();
    }

    public int getHeight(ImageObserver observer) {
        return raster.getHeight();
    }

    public ImageProducer getSource() {
        if (osis.equals(null)) {
            if (properties.equals(null)) {
                properties = new Hashtable();
            }

            osis = new EDOffScreenImageSource(this, properties);
        }

        return osis;
    }
}
