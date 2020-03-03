//3D Cube
public class Cube {

    //Static data from Cube

    /*
     * For a reset cube:
     * - The X axis is horizontal
     * - The Y axis is vertical
     * - The Z axis is faced towards the camera
     *
     * As the cube rotates, the axes stay in the same place relative to the camera
     */
    public enum RotationalAxis {X, Y, Z}

    // Variables faces and edges will be used by the graphed cube
    final static int[][] faces = {
            {0, 1, 3, 2},
            {0, 1, 5, 4},
            {0, 2, 6, 4},
            {2, 3, 7, 6},
            {1, 3, 7, 5},
            {4, 5, 7, 6}
    };

    final static int[][] edges = {
            {0, 1},
            {1, 3},
            {3, 2},
            {2, 0},
            {0, 4},
            {1, 5},
            {3, 7},
            {2, 6},
            {4, 5},
            {5, 7},
            {7, 6},
            {6, 4}
    };

    // Instance variables of the cube
    // min and max are the minimum/maximum coordinate values for all points for a reset cube
    private final double length, min, max;
    private Point[] vertices;
    //Records the centers of the faces
    //Used to determine which faces are shown
    private Point[] centers;

    // Constructs the cube
    public Cube(double sideLength) {
        length = sideLength;
        min = -length / 2.0;
        max = length / 2.0;

        vertices = new Point[8];
        centers = new Point[6];

        for (int i = 0; i < 8; i++) {
            vertices[i] = new Point();
        }

        for (int i = 0; i < 6; i++) {
            centers[i] = new Point();
        }

        resetPoints();
    }

    // Sets/resets the locations of the points
    public void resetPoints() {

        vertices[0].setAll(min, min, min);
        vertices[1].setAll(max, min, min);
        vertices[2].setAll(min, max, min);
        vertices[3].setAll(max, max, min);
        vertices[4].setAll(min, min, max);
        vertices[5].setAll(max, min, max);
        vertices[6].setAll(min, max, max);
        vertices[7].setAll(max, max, max);

        centers[0].setAll(0, 0, min);
        centers[1].setAll(0, min, 0);
        centers[2].setAll(min, 0, 0);
        centers[3].setAll(0, max, 0);
        centers[4].setAll(max, 0, 0);
        centers[5].setAll(0, 0, max);
    }

    private void rotatePointOnX(Point p, double radiansChange) {
        double dist = Math.sqrt(p.getZ() * p.getZ() + p.getY() * p.getY());
        double updatedRadians = Math.atan2(p.getY(), p.getZ()) + radiansChange;
        p.setY(dist * Math.sin(updatedRadians));
        p.setZ(dist * Math.cos(updatedRadians));
    }

    private void rotatePointOnY(Point p, double radiansChange) {
        double dist = Math.sqrt(p.getX() * p.getX() + p.getZ() * p.getZ());
        double updatedRadians = Math.atan2(p.getZ(), p.getX()) + radiansChange;
        p.setZ(dist * Math.sin(updatedRadians));
        p.setX(dist * Math.cos(updatedRadians));
    }

    private void rotatePointOnZ(Point p, double radiansChange) {
        double dist = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
        double updatedRadians = Math.atan2(p.getY(), p.getX()) + radiansChange;
        p.setY(dist * Math.sin(updatedRadians));
        p.setX(dist * Math.cos(updatedRadians));
    }

    // Rotates the cube on a specified axis by a certain amount of radians
    // See the description above the declaration of RotationalAxis for
    // more information on how the cube rotates
    public void rotate(RotationalAxis dir, double radiansChange) {
        if (dir == RotationalAxis.Y) {
            for (Point p : vertices) { rotatePointOnY(p, radiansChange); }
            for (Point p : centers) { rotatePointOnY(p, radiansChange); }
        } else if (dir == RotationalAxis.X) {
            for (Point p : vertices) { rotatePointOnX(p, radiansChange); }
            for (Point p : centers) { rotatePointOnX(p, radiansChange); }
        } else if (dir == RotationalAxis.Z) {
            for (Point p : vertices) { rotatePointOnZ(p, radiansChange); }
            for (Point p : centers) { rotatePointOnZ(p, radiansChange); }
        }
    }

    //This returns the indexes of the visible faces from the faces array
    public int[] getVisibleFaces() {
        int[] tempShowedFaces = new int[6];
        int index = 0;
        for (int i = 0; i < centers.length; i++) {
            //If the center of the face is in front of the origin,
            //then it is showing

            //I used 1e-9 as a constant to account for floating-point error
            if (centers[i].getZ() > 1e-9) {
                tempShowedFaces[index] = i;
                index++;
            }
        }
        int[] showedFaces = new int[index];
        System.arraycopy(tempShowedFaces, 0, showedFaces, 0, index);
        return showedFaces;
    }

    //Clones vertices to avoid editing of the actual array
    public Point[] getVertices() { return vertices.clone(); }

    //These methods are not used anywhere but are helpful methods to have for users

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < vertices.length; i++) {
            builder.append("Point ");
            builder.append(i);
            builder.append(": ");
            builder.append(vertices[i].toString());
        }
        return builder.toString();
    }

    public double getLength() { return length; }

    //Clones vertex to avoid editing of the actual point
    public Point getVertex(int index) { return vertices[index].clone(); }

}