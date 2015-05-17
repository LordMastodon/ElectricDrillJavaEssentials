package electricdrill.edje.guifeatures;

import electricdrill.edje.actions.SubAction;
import electricdrill.edje.utils.PlacementArray;

import javax.swing.*;

/**
 * A class allowing you to create a button helper to control Actions and such of all
 * the button in JFrames, JPanels, and any such container in which you intend to
 * add buttons. Also remember that using the addActionsToButtons sets the actionCommands
 * of the buttons to ensure continuity, so don't really bother setting them in the
 * declarations.
 *
 * Small Con: You can use your own subclasses of JButton or JButton itself, but
 * you can not use your own custom button class if it doesn't extend JButton.
 * That's because this uses methods from the JButton class, and therefore by
 * extension classes from subclasses of JButton.
 *
 * Small Con #2: It's better if you create separate classes for your actions as well,
 * because otherwise it gets incredibly confusing.
 *
 * Small Con #3: Does require you to set two values in your subAction (which, by the way, should be a class extending my own
 * SubAction class, as that provides you with the necessary methods and values.
 */
public class CustomJButtonHelper<E extends JButton> {

    PlacementArray<E> buttons = new PlacementArray<E>();

    public void addActionsToButtons(SubAction... actions) {
        for (int i = 0; i < actions.length; i++) {
            if (buttons.get(actions[i].getButton()).getActionCommand().equals(null) || buttons.get(actions[i].getButton().equals(null))) {
                throw new IllegalArgumentException("You have not provided either an actionCommand or a button for your SubAction to work with in " + actions[i].getButton.toString());
            } else {
                buttons.get(actions[i].getButton()).getActionMap().put(actions[i].getActionCommand, actions[i]);
                buttons.get(actions[i].getButton()).setActionCommand(actions[i].getActionCommand());
            }
        }
    }

}
