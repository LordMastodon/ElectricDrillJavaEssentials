package electricdrill.edje.guifeatures;

import javax.swing.*;

public class JImageViewer extends JComponent {

    private static final String uiClassID = "ImageViewerUI";

    private EDImage EDImage;

    public JImageViewer() {
        this(null);
    }

    public JImageViewer(EDImage newEDImage) {
        EDImage = newEDImage;
    }

    public void setEDImage(EDImage newEDImage) {
        EDImage = newEDImage;
    }

    public EDImage getEDImage() {
        return EDImage;
    }

}
