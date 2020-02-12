import javax.swing.*;
import java.awt.*;

public class GraphHelper extends Canvas {

    JFrame frame; //This is not private so that classes that extend this class can have more choice
    Graphics2D graphics;

    final static int DEFAULT_WINDOW_DIMENSION = 500;
    final static String DEFAULT_WINDOW_NAME = "My Drawing";

    GraphHelper(String frameTitle, int windowWidth, int windowHeight) {
        frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        graphics = (Graphics2D)getGraphics();
    }

    //These constructors are not used but are helpful constructors for users
    GraphHelper() {
        this(DEFAULT_WINDOW_NAME, DEFAULT_WINDOW_DIMENSION, DEFAULT_WINDOW_DIMENSION);
    }

    GraphHelper(int windowWidth, int windowHeight) {
        this(DEFAULT_WINDOW_NAME, windowWidth, windowHeight);
    }

    GraphHelper(String name) {
        this(name, DEFAULT_WINDOW_DIMENSION, DEFAULT_WINDOW_DIMENSION);
    }

}