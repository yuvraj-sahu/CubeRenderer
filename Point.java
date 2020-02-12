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

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Point clone() {
        return new Point(x, y, z);
    }

}