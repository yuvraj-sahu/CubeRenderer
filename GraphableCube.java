import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphableCube extends GraphHelper implements KeyListener {

    private Cube cube;

    //Constants

    //These are for the max/min rotation/translation intervals
    public static final int MAX_ROTATION = 90, MAX_TRANSLATION = 50,
                            MIN_ROTATION = 5, MIN_TRANSLATION = 5;

    //These are the increment sizes for translation/rotation intervals
    public static final int ROTATION_INTERVAL_INC = 5, TRANSLATION_INTERVAL_INC = 5;

    private int xDistToCenter, yDistToCenter;
    private int rotationInterval = 15;
    private int translationInterval = 20;

    private boolean shiftPressed = false;

    public static final Color[] DEFAULT_FACE_COLORS = {
            Color.YELLOW,
            new Color(255, 127, 0), //Orange
            Color.RED,
            new Color(127, 0, 255), //Purple
            Color.BLUE,
            Color.GREEN
    };

    public final Color[] faceColors;

    //This does all of the setup work
    private void setup(double cubeLength) {
        addKeyListener(this);
        cube = new Cube(cubeLength);
        xDistToCenter = getWidth() / 2;
        yDistToCenter = getHeight() / 2;
    }

    //Constructors

    public GraphableCube(double cubeLength) {
        //Calls super() by default
        setup(cubeLength);
        this.faceColors = DEFAULT_FACE_COLORS;
    }

    public GraphableCube(String frameTitle, double cubeLength) {
        super(frameTitle);
        setup(cubeLength);
        this.faceColors = DEFAULT_FACE_COLORS;
    }

    public GraphableCube(int windowWidth, int windowHeight, double cubeLength) {
        super(windowWidth, windowHeight);
        setup(cubeLength);
        this.faceColors = DEFAULT_FACE_COLORS;
    }

    public GraphableCube(String frameTitle, int windowWidth, int windowHeight, double cubeLength) {
        super(frameTitle, windowWidth, windowHeight);
        setup(cubeLength);
        this.faceColors = DEFAULT_FACE_COLORS;
    }

    public GraphableCube(Color[] faceColors, double cubeLength) {
        //Calls super() by default
        setup(cubeLength);
        this.faceColors = faceColors;
    }

    public GraphableCube(String frameTitle, Color[] faceColors, double cubeLength) {
        super(frameTitle);
        setup(cubeLength);
        this.faceColors = faceColors;
    }

    public GraphableCube(int windowWidth, int windowHeight, Color[] faceColors, double cubeLength) {
        super(windowWidth, windowHeight);
        setup(cubeLength);
        this.faceColors = faceColors;
    }

    public GraphableCube(String frameTitle, int windowWidth, int windowHeight, Color[] faceColors, double cubeLength) {
        super(frameTitle, windowWidth, windowHeight);
        setup(cubeLength);
        this.faceColors = faceColors;
    }

    //End of constructors

    public void dispose() {
        frame.dispose();
    }

    //Erases the entire screen
    public void erase(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    //Paints the cube outline
    public void paint(Graphics g) {
        erase(g);

        Point[] allVertices = cube.getVertices();

        //Fills the faces
        int[] visibleFaces = cube.getVisibleFaces();
        for (int i : visibleFaces) {
            int[] face = Cube.faces[i];
            Point[] vertices = {
                    allVertices[face[0]],
                    allVertices[face[1]],
                    allVertices[face[2]],
                    allVertices[face[3]]
            };

            g.setColor(faceColors[i]);

            //First draws the face
            g.fillPolygon(
                    //X coordinates
                    new int[] {
                            (int)Math.round(vertices[0].getX()) + xDistToCenter,
                            (int)Math.round(vertices[1].getX()) + xDistToCenter,
                            (int)Math.round(vertices[2].getX()) + xDistToCenter,
                            (int)Math.round(vertices[3].getX()) + xDistToCenter
                    },
                    //Y coordinates
                    new int[] {
                            (int)Math.round(vertices[0].getY()) + yDistToCenter,
                            (int)Math.round(vertices[1].getY()) + yDistToCenter,
                            (int)Math.round(vertices[2].getY()) + yDistToCenter,
                            (int)Math.round(vertices[3].getY()) + yDistToCenter
                    },
                    4 //Number of points
            );

            //For the edges
            g.setColor(Color.BLACK);

            //Then draws the edges (to make sure that they are visible)
            drawLines(
                    //"From" points
                    new Point[] {
                            vertices[0],
                            vertices[1],
                            vertices[2],
                            vertices[3]
                    },
                    //"To" points
                    new Point[] {
                            vertices[3],
                            vertices[0],
                            vertices[1],
                            vertices[2]
                    },
                    g //Graphics
            );
        }
    }

    //This draws multiple lines from points to other points
    //NOTE: This automatically accounts for x and y distances to center
    public void drawLines(Point[] from, Point[] to, Graphics g) {
        for (int i = 0; i < from.length; i++) {
            g.drawLine(
                    (int)Math.round(from[i].getX()) + xDistToCenter,
                    (int)Math.round(from[i].getY()) + yDistToCenter,
                    (int)Math.round(to[i].getX()) + xDistToCenter,
                    (int)Math.round(to[i].getY()) + yDistToCenter
            );
        }
    }

    //Rotates the cube and paints the result
    public void rotateAndPaint(Cube.RotationalAxis axis, double degrees) {
        cube.rotate(axis, Math.toRadians(degrees));
        paint(graphics);
    }

    //Translates the cube and paints the result
    public void translateAndPaint(boolean isXAxis, double amount) {
        if (isXAxis) { xDistToCenter += amount; }
        else { yDistToCenter += amount; }

        paint(graphics);
    }

    public void disableKeyListener() {
        removeKeyListener(this);
    }

    public void addKeyListener() {
        addKeyListener(this);
    }

    //Just used to define the method from KeyListener
    public void keyTyped(KeyEvent event) {}

    //Just used to check if the space bar is pressed
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }

    /*
    CURRENT KEY MAPPINGS:

    W - negative X rotation
    S - positive X rotation
    D - negative Y rotation
    A - positive Y rotation

    NOTE: down correlates to increasing Y values

    Shift+W - negative Y translation
    Shift+S - positive Y translation
    Shift+D - positive X translation
    Shift+A - negative X translation

    Right_Arrow (and Shift+Right_Arrow) - positive Z rotation
    Left_Arrow (and Shift+Left_Arrow) - negative Z rotation

    Up_Arrow - increased rotation interval
    Down_Arrow - decreased rotation interval

    Shift+Up_Arrow - increased translation interval
    Shift+Down_Arrow - decreased translation interval

    Space Bar - Resets the cube position
    */

    public void keyPressed(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_SHIFT : {
                shiftPressed = true;
                break;
            }
            case 'W' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.X, -rotationInterval); }
                else { translateAndPaint(false, -translationInterval); }
                break;
            }
            case 'S' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.X, rotationInterval); }
                else { translateAndPaint(false, translationInterval); }
                break;
            }
            case 'D' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.Y, -rotationInterval); }
                else { translateAndPaint(true, translationInterval); }
                break;
            }
            case 'A' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.Y, rotationInterval); }
                else { translateAndPaint(true, -translationInterval); }
                break;
            }
            case KeyEvent.VK_RIGHT : {
                rotateAndPaint(Cube.RotationalAxis.Z, rotationInterval);
                break;
            }
            case KeyEvent.VK_LEFT : {
                rotateAndPaint(Cube.RotationalAxis.Z, -rotationInterval);
                break;
            }
            case KeyEvent.VK_UP : {
                if (shiftPressed) {
                    if (translationInterval < MAX_TRANSLATION) {
                        translationInterval += TRANSLATION_INTERVAL_INC;
                    }
                } else {
                    if (rotationInterval < MAX_ROTATION) {
                        rotationInterval += ROTATION_INTERVAL_INC;
                    }
                }
                break;
            }
            case KeyEvent.VK_DOWN : {
                if (shiftPressed) {
                    if (translationInterval > MIN_TRANSLATION) {
                        translationInterval -= TRANSLATION_INTERVAL_INC;
                    }
                } else {
                    if (rotationInterval > MIN_ROTATION) {
                        rotationInterval -= ROTATION_INTERVAL_INC;
                    }
                }
                break;
            }
            case KeyEvent.VK_SPACE : {
                cube.resetPoints();
            }
        }
    }
}