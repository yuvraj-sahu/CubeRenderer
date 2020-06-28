import javax.swing.*;
import java.awt.*;

public class GraphHelper extends Canvas {

    JFrame frame; //This is not private so that classes that extend this class can have more choice
    Graphics2D graphics; //This will be used by the subclass

    GraphHelper(String frameTitle, int windowWidth, int windowHeight) {
        frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        frame.add(this);
        frame.pack();
        graphics = (Graphics2D)getGraphics();
    }

    public void showFrame() { frame.setVisible(true); }
    public void hideFrame() { frame.setVisible(false); }
    public void close() { frame.dispose(); }

}