import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Aurko Nandi on 2018-06-7.
 * Makes a functional interface (an interface with only one method), because I don't need to listen for when focus is gained.
 * Functional interfaces are able to be implemented using lambda, which makes code much cleaner.
 */
public interface FocusRemovedListener extends FocusListener {
    void focusLost(FocusEvent e);

    //default lets you implement what to do for all implementations of the interface. Therefore, it does not need to be explicitly implemented on every implementation.
    default void focusGained(FocusEvent e){

    }
}
