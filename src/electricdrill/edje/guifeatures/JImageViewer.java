package electricdrill.edje.guifeatures;

import javax.swing.*;
import javax.swing.plaf.basic.BasicListUI;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JImageViewer extends JComponent {

    private static final String uiClassID = "ImageViewerUI";

    private EDImage EDImage;

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
        EDImage = newEDImage;
    }

    public void setEDImage(EDImage newEDImage) {
        EDImage = newEDImage;
    }

    public EDImage getEDImage() {
        return EDImage;
    }

}

class JImageViewerPropertyChangeListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
