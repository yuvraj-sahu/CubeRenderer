import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphableCube extends GraphHelper implements KeyListener {

    private int xDistToCenter, yDistToCenter; //Will need to change later with panning
    private Cube cube;
    private int rotationInterval = 15; //Will be controlled by keys in future updates

    GraphableCube(String frameTitle, double cubeLength, int windowWidth, int windowHeight) {
        super(frameTitle, windowWidth, windowHeight);
        addKeyListener(this);
        cube = new Cube(cubeLength);
        xDistToCenter = windowWidth / 2;
        yDistToCenter = windowHeight / 2;
    }

    public void erase(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

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

    public void keyTyped(KeyEvent event) {}

    public void keyReleased(KeyEvent event) {}

    public void keyPressed(KeyEvent event) {
        switch(event.getKeyCode()) {
            case 'W' : {
                rotateAndPaint(Cube.RotationalAxis.X, rotationInterval);
                break;
            }
            case 'S' : {
                rotateAndPaint(Cube.RotationalAxis.X, -rotationInterval);
                break;
            }
            case 'D' : {
                rotateAndPaint(Cube.RotationalAxis.Y, rotationInterval);
                break;
            }
            case 'A' : {
                rotateAndPaint(Cube.RotationalAxis.Y, -rotationInterval);
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