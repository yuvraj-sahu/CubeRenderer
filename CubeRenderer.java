//To actually render the cube, this is all you need to do!
//This just uses the defaults - you have multiple options
public class CubeRenderer {

    static GraphableCube cube;

    public static void main(String[] args) {
        cube = new GraphableCubeBuilder().buildCube();
        cube.showFrame();
    }

}