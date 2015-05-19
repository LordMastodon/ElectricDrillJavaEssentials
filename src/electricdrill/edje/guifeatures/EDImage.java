package electricdrill.edje.guifeatures;

import javax.swing.*;

public class EDImage extends ImageIcon {

    String pathToImage;
    String name;

    public EDImage(String newPathToImage, String newName) {
        super(newPathToImage);

        pathToImage = newPathToImage;

        name = newName;

    }

    public String getName() {
        return name;
    }

    public String getPathToImage() {
        return pathToImage;
    }

}
