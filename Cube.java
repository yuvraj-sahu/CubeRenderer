//3D Cube
class Cube {

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

    // Constructs the cube
    public Cube(double sideLength) {
        length = sideLength;
        min = -length / 2.0;
        max = length / 2.0;

        vertices = new Point[8];

        for (int i = 0; i < 8; i++) {
            vertices[i] = new Point();
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
    }

    // Rotates the cube on a specified axis by a certain amount of radians
    // See the description above the declaration of RotationalAxis for
    // more information on how the cube rotates
    public void rotate(RotationalAxis dir, double radiansChange) {
        if (dir == RotationalAxis.Y) {
            for (Point p : vertices) {
                double dist = Math.sqrt(p.getX() * p.getX() + p.getZ() * p.getZ());
                double updatedRadians = Math.atan2(p.getZ(), p.getX()) + radiansChange;
                p.setZ(dist * Math.sin(updatedRadians));
                p.setX(dist * Math.cos(updatedRadians));
            }
        } else if (dir == RotationalAxis.X) {
            for (Point p : vertices) {
                double dist = Math.sqrt(p.getZ() * p.getZ() + p.getY() * p.getY());
                double updatedRadians = Math.atan2(p.getY(), p.getZ()) + radiansChange;
                p.setY(dist * Math.sin(updatedRadians));
                p.setZ(dist * Math.cos(updatedRadians));
            }
        } else if (dir == RotationalAxis.Z) {
            for (Point p : vertices) {
                double dist = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
                double updatedRadians = Math.atan2(p.getY(), p.getX()) + radiansChange;
                p.setY(dist * Math.sin(updatedRadians));
                p.setX(dist * Math.cos(updatedRadians));
            }
        }
    }

    //Clones vertices to avoid editing of the actual array
    public Point[] getVertices() {
        return vertices.clone();
    }

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

    public Point getVertex(int index) { return vertices[index].clone(); }

}