import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public interface MenuClickListener extends MenuListener {
    default void menuCanceled(MenuEvent e) {
    }

    default void menuDeselected(MenuEvent e) {
    }

    void menuSelected(MenuEvent e);
}