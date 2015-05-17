package electricdrill.edje.actions;

import com.sun.corba.se.impl.orbutil.GetPropertyAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.AccessController;

public class SubAction implements Action {

    protected boolean enabled = true;

    private transient EDArrayTable edArrayTable;

    static void setEnabledFromAction(JComponent c, Action a) {
        c.setEnabled((a != null) ? a.isEnabled() : true);
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void putValue(String key, Object value) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
