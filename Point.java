//3D Point
class Point {

    private double x, y, z; //Stores values of the 3 dimensions

    //Basic constructors
    Point(double x, double y, double z) { setAll(x, y, z); }
    Point() {}

    //Returns specific coordinates
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    //Sets specific coordinates
    public void setX(double value) { x = value; }
    public void setY(double value) { y = value; }
    public void setZ(double value) { z = value; }

    //Sets all coordinates
    public void setAll(double xValue, double yValue, double zValue) {
        x = xValue;
        y = yValue;
        z = zValue;
    }

    //Allows for a point to be clone so that the original point is not edited
    public Point clone() {
        return new Point(x, y, z);
    }

    //This is not used in the program but could be helpful for users
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}