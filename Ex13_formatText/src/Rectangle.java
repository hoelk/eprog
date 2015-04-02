/**
 * Created by Stefan Fleck 0440930 on 12/17/14.
 */


public class Rectangle {

    static private int numberOfRectangles;  // Static variables =  class variable, common to all the instance of the same class.
    /* object variable = instance variable
   declaring them as final makes this class an immutable class = unveränderliche Klasse */
    final private int topLeftCornerX;
    final private int topLeftCornerY;
    final private int width;
    final private int height;
    final private int id;

    // Default constructor

    Rectangle() {
        topLeftCornerX = 0;
        topLeftCornerY = 0;
        width = 0;
        height = 0;
        id = ++numberOfRectangles;
    }

    // Custom constructor
    Rectangle(int topLeftCornerX, int topLeftCornerY, int width, int height) {
        this.topLeftCornerX = topLeftCornerX; // "this" is used since method variables have same name as instance variables
        this.topLeftCornerY = topLeftCornerY;
        this.width = width;
        this.height = height;
        this.id = ++numberOfRectangles;
    }

    // Copy constructor
    Rectangle(Rectangle r) {
        topLeftCornerX = r.topLeftCornerX;
        topLeftCornerY = r.topLeftCornerY;
        width = r.width;
        height = r.height;
        id = ++numberOfRectangles;
    }

    // Methods

    // Getters

    static public int getNumberOfRectangles() {
        return (numberOfRectangles);
    }

    static Rectangle intersect(Rectangle rect1, Rectangle rect2) {

        int topLeftCornerX = Math.max(rect1.getMinCol(), rect2.getMinCol());
        int topLeftCornerY = Math.max(rect1.getMinRow(), rect2.getMinRow());

        int width = Math.min(rect1.getMaxCol(), rect2.getMaxCol()) - topLeftCornerX;
        int height = Math.min(rect1.getMaxRow(), rect2.getMaxRow()) - topLeftCornerY;

        if (height <= 0 || width <= 0) {
            return null;
        } else {
            return (new Rectangle(topLeftCornerX, topLeftCornerY, width, height));
        }
    }

    static Rectangle minimumBoundingRectangle(Rectangle... args) {
        int minX = args[0].getMinCol();
        int minY = args[0].getMinRow();
        int maxX = args[0].getMaxCol();
        int maxY = args[0].getMaxRow();

        for (Rectangle i : args) {
            minX = Math.min(minX, i.getMinCol());
            minY = Math.min(minY, i.getMinRow());

            maxX = Math.max(maxX, i.getMaxCol());
            maxY = Math.max(maxY, i.getMaxRow());
        }

        int width = maxX - minX;
        int height = maxY - minY;

        return (new Rectangle(minX, minY, width, height));
    }

    public int getId() {
        return id;
    }

    public int getTopLeftCornerX() {
        return topLeftCornerX;
    }

    public int getTopLeftCornerY() {
        return topLeftCornerY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinCol() { // for symmetry purposes only
        return (topLeftCornerX);
    }

    public int getMaxCol() {
        return (topLeftCornerX + width);
    }

    public int getMaxRow() {
        return (topLeftCornerY + height);
    }

    // Geometric Operations

    public int getMinRow() {  // for symmetry purposes only
        return (topLeftCornerY);
    }

    public String toString() {
        String s = String.format(
                "Position: linke obere Ecke: (%3d, %3d), rechte untere Ecke: (%3d, %3d), Spalten: %3d, Zeilen: %3d",
                getTopLeftCornerX(), getTopLeftCornerY(), getMaxCol(), getMaxRow(), getWidth(), getHeight());
        return (s);
    }


}
