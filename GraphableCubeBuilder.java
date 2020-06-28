import java.awt.*;

public class GraphableCubeBuilder {

    // Default constants
    public static final double DEFAULT_CUBE_LENGTH = 100;
    public static final Color[] DEFAULT_FACE_COLORS = {
            Color.YELLOW,
            new Color(255, 127, 0), //Orange
            Color.RED,
            new Color(127, 0, 255), //Purple
            Color.BLUE,
            Color.GREEN
    };
    public static final int DEFAULT_WINDOW_WIDTH = 500, DEFAULT_WINDOW_HEIGHT = 500;
    public static final String DEFAULT_FRAME_TITLE = "My Drawing";

    // Instance variables
    private double cubeLength = DEFAULT_CUBE_LENGTH;
    private Color[] faceColors = DEFAULT_FACE_COLORS;
    private int windowWidth = DEFAULT_WINDOW_WIDTH, windowHeight = DEFAULT_WINDOW_HEIGHT;
    private String frameTitle = DEFAULT_FRAME_TITLE;

    GraphableCubeBuilder() {}

    public void setCubeLength(double cubeLength) { this.cubeLength = cubeLength; }
    public void setFaceColors(Color[] faceColors) { this.faceColors = faceColors; }
    public void setWindowWidth(int windowWidth) { this.windowWidth = windowWidth; }
    public void setWindowHeight(int windowHeight) { this.windowHeight = windowHeight; }
    public void setFrameTitle(String frameTitle) { this.frameTitle = frameTitle; }

    public GraphableCube buildCube() {
        return new GraphableCube(
                cubeLength,
                faceColors,
                windowWidth,
                windowHeight,
                frameTitle
        );
    }

}
