import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphableCube extends GraphHelper implements KeyListener {

    private Cube cube;

    //Constants

    //These are for the max/min rotation/translation intervals
    public static final int MAX_ROTATION = 90, MAX_TRANSLATION = 50,
                            MIN_ROTATION = 5, MIN_TRANSLATION = 5;

    //These are the increment sizes for translation/rotation
    public static final int ROTATION_INC = 5, TRANSLATION_INC = 5;

    private int xDistToCenter, yDistToCenter;
    private int rotationInterval = 15;
    private int translationInterval = 20;

    private boolean shiftPressed = false;

    //Constructor
    GraphableCube(String frameTitle, double cubeLength, int windowWidth, int windowHeight) {
        super(frameTitle, windowWidth, windowHeight);
        addKeyListener(this);
        cube = new Cube(cubeLength);
        xDistToCenter = windowWidth / 2;
        yDistToCenter = windowHeight / 2;
    }

    //Erases the entire screen
    public void erase(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    //Paints the cube outline
    public void paint(Graphics g) {
        erase(g);
        g.setColor(Color.BLACK);
        Point[] vertices = cube.getVertices();

        for (int[] edge : Cube.edges) {
            g.drawLine(
                    (int)Math.round(vertices[edge[0]].getX()) + xDistToCenter,
                    (int)Math.round(vertices[edge[0]].getY()) + yDistToCenter,
                    (int)Math.round(vertices[edge[1]].getX()) + xDistToCenter,
                    (int)Math.round(vertices[edge[1]].getY()) + yDistToCenter
            );
        }
    }

    public void rotateAndPaint(Cube.RotationalAxis axis, double degrees) {
        cube.rotate(axis, Math.toRadians(degrees));
        paint(graphics);
    }

    public void translateAndPaint(boolean isXAxis, double amount) {
        if (isXAxis) { xDistToCenter += amount; }
        else { yDistToCenter += amount; }

        paint(graphics);
    }

    public void keyTyped(KeyEvent event) {}

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }

    /*
    CURRENT KEY MAPPINGS:

    W - positive X rotation
    S - negative X rotation
    D - positive Y rotation
    A - negative Y rotation

    NOTE: down correlates to increasing Y values

    Shift+W - negative Y translation
    Shift+S - positive Y translation
    Shift+D - positive X translation
    Shift+A - negative X translation

    Right_Arrow - positive Z rotation
    Left_Arrow - negative Z rotation

    Up_Arrow - increased rotation interval
    Down_Arrow - decreased rotation interval

    Shift+Up_Arrow - increased translation interval
    Shift+Down_Arrow - decreased translation interval
     */

    public void keyPressed(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_SHIFT : {
                shiftPressed = true;
                break;
            }
            case 'W' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.X, rotationInterval); }
                else { translateAndPaint(false, -translationInterval); }
                break;
            }
            case 'S' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.X, -rotationInterval); }
                else { translateAndPaint(false, translationInterval); }
                break;
            }
            case 'D' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.Y, rotationInterval); }
                else { translateAndPaint(true, translationInterval); }
                break;
            }
            case 'A' : {
                if (!shiftPressed) { rotateAndPaint(Cube.RotationalAxis.Y, -rotationInterval); }
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
                        translationInterval += TRANSLATION_INC;
                    }
                } else {
                    if (rotationInterval < MAX_ROTATION) {
                        rotationInterval += ROTATION_INC;
                    }
                }
                break;
            }
            case KeyEvent.VK_DOWN : {
                if (shiftPressed) {
                    if (translationInterval > MIN_TRANSLATION) {
                        translationInterval -= TRANSLATION_INC;
                    }
                } else {
                    if (rotationInterval > MIN_ROTATION) {
                        rotationInterval -= ROTATION_INC;
                    }
                }
                break;
            }
        }
    }
}