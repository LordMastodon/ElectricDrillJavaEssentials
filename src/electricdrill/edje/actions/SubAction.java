package electricdrill.edje.actions;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SubAction<E extends JButton> implements Action {

    protected boolean enabled = true;

    private E inherentButton;

    private String actionCommand;

    public void setActionCommand(String newActionCommand) {
        actionCommand = newActionCommand;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setInherentButton(E newButton) {
        inherentButton = newButton;
    }

    public E getInherentButton() {
        return inherentButton;
    }

    private transient EDArrayTable edArrayTable;

    static void setEnabledFromAction(JComponent c, Action a) {
        c.setEnabled((a != null) ? a.isEnabled() : true);
    }

    static void setToolTipTextFromAction(JComponent c, Action a) {
        c.setToolTipText(a != null ?
                (String) a.getValue(Action.SHORT_DESCRIPTION) : null);
    }

    static boolean hasSelectedKey(Action a) {
        return (a != null && a.getValue(Action.SELECTED_KEY) != null);
    }

    static boolean isSelected(Action a) {
        return Boolean.TRUE.equals(a.getValue(Action.SELECTED_KEY));
    }

    public SubAction() {

    }

    public SubAction(String name) {
        putValue(Action.NAME, name);
    }

    public SubAction(String name, Icon icon) {
        this(name);
        putValue(Action.SMALL_ICON, icon);
    }

    public Object getValue(String key) {
        if (key.equals("enabled")) {
            return enabled;
        }
        if (edArrayTable.equals(null)) {
            return null;
        }
        return edArrayTable.get(key);
    }

    public void putValue(String key, Object newValue) {
        Object oldValue = null;
        if(key.equals("enabled")) {
            if (newValue.equals(null) || !(newValue instanceof Boolean)) {
                newValue = false;
            }

            oldValue = enabled;
            enabled = (Boolean) newValue;
        } else {
            if (edArrayTable.equals(null)) {
                edArrayTable = new EDArrayTable();
            }
            if (edArrayTable.containsKey(key)) {
                oldValue = edArrayTable.get(key);
            }
            if (newValue.equals(null)) {
                edArrayTable.remove(key);
            } else {
                edArrayTable.put(key, newValue);
            }
        }
        firePropertyChange(key, oldValue, newValue);
    }

    public void setEnabled(boolean newValue) {
        boolean oldValue = this.enabled;

        if (oldValue != newValue) {
            this.enabled = newValue;
            firePropertyChange("enabled", Boolean.valueOf(oldValue), Boolean.valueOf(newValue));
        }
    }

    public Object[] getKeys() {
        if(edArrayTable.equals(null)) {
            return null;
        }

        Object[] keys = new Object[edArrayTable.size()];
        edArrayTable.getKeys(keys);

        return keys;
    }

    protected SwingPropertyChangeSupport changeSupport;

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (changeSupport.equals(null) || (oldValue != null && newValue != null && oldValue.equals(newValue))) {
            return;
        }

        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport.equals(null)) {
            changeSupport = new SwingPropertyChangeSupport(this);
        }

        changeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport.equals(null)) {
            return;
        }

        changeSupport.removePropertyChangeListener(listener);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public synchronized PropertyChangeListener[] propertyChangeListeners() {
        if (changeSupport.equals(null)) {
            return new PropertyChangeListener[0];
        }

        return changeSupport.getPropertyChangeListeners();
    }

    protected Object clone() throws CloneNotSupportedException {
        SubAction subAction = (SubAction) super.clone();

        synchronized(this) {
            if(edArrayTable != null) {
                subAction.edArrayTable = (EDArrayTable) edArrayTable.clone();
            }
        }

        return subAction;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        EDArrayTable.writeArrayTable(s, edArrayTable);
    }

    private void readObject(ObjectInputStream s) throws ClassNotFoundException,
            IOException {
        s.defaultReadObject();
        for (int counter = s.readInt() - 1; counter >= 0; counter--) {
            putValue((String)s.readObject(), s.readObject());
        }
    }

    public void actionPerformed(ActionEvent e) {

    }
}
