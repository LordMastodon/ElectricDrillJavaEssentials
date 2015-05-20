package electricdrill.edje.guifeatures;

import electricdrill.edje.listeners.JImageViewerPropertyChangeListener;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class JImageViewer extends JComponent {

    private static final String uiClassID = "ImageViewerUI";

    private static EDImage edImage;

    private PropertyChangeListener propertyChangeListener = new JImageViewerPropertyChangeListener();

    public void setPropertyChangeListener(PropertyChangeListener newPropertyChangeListener) {
        propertyChangeListener = newPropertyChangeListener;
    }

    public PropertyChangeListener getPropertyChangeListener() {
        return propertyChangeListener;
    }

    public JImageViewer() {
        this(null);
    }

    public JImageViewer(EDImage newEDImage) {
        edImage = newEDImage;
    }

    public void setEDImage(EDImage newEDImage) {
        edImage = newEDImage;
    }

    public static EDImage getEDImage() {
        return edImage;
    }

}
