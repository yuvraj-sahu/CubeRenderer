import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphableCube extends GraphHelper implements KeyListener {

    private Cube cube;

    private int xDistToCenter, yDistToCenter;
    private int rotationInterval = 15; //Will be controlled by keys in future updates
    private int translationInterval = 50; //Will also be controlled by keys in future updates

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
        }
    }
}