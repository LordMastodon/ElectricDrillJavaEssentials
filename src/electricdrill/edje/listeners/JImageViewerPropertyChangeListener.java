package electricdrill.edje.listeners;

import electricdrill.edje.guifeatures.JImageViewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JImageViewerPropertyChangeListener implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("edImage")) {
            JImageViewer.getEDImage().get;
        }
    }

}