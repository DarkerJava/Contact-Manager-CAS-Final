import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    private int duration;

    public SplashScreen(int d) {
        duration = d;
    }

    /**********
     *name: showSplash
     *description: shows a splash screen
     *input/output: no input, no output (void)
     ***************************/
    public void showSplash() {
        //creates a new JPanel
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = 500;
        int height = 250;

        //gets screen size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        //positions the window in the center of the screen
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("NewIcons/splash.png")));

        //adds the image to the panel
        content.add(label, BorderLayout.CENTER);


        //makes it visible
        setVisible(true);

        //adds the delay
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }

        //makes it hidden
        setVisible(false);

    }


}