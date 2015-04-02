/**
 * Created by Stefan Fleck 0440930 on 12/19/14.
 */
public class tests {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(-3, -2, 6, 5);
        Rectangle q1 = new Rectangle(1, 1, 2, 3);
        Rectangle q2 = new Rectangle(-5, 1, 4, 1);
        Rectangle q3 = new Rectangle(-3, -6, 1, 5);
        Rectangle q4 = new Rectangle(1, -3, 3, 2);

        System.out.println("--------------------------------------------------\nTeste Rectangle - to.String() und toString(args):\n");
        System.out.println(r.toString());
        System.out.println(q1.toString());
        System.out.println(q2.toString());
        System.out.println(q3.toString());
        System.out.println(q4.toString());

        System.out.println("--------------------------------------------------\nTeste Rectangle - Minimum Bounding Rectangles\n");
        Rectangle mbr = Rectangle.minimumBoundingRectangle(q1, q2, q3, q4);
        checkDimensions(mbr, -5, -6, 9, 10);

        System.out.println("--------------------------------------------------\nTeste Rectangle - Verschneidungen\n");
        Rectangle intQ1 = Rectangle.intersect(q1, r);
        Rectangle intQ2 = Rectangle.intersect(q2, r);
        Rectangle intQ3 = Rectangle.intersect(q3, r);
        Rectangle intQ4 = Rectangle.intersect(q4, r);
        Rectangle intQ5 = Rectangle.intersect(q4, q2);

        checkDimensions(intQ1, 1, 1, 2, 2);
        checkDimensions(intQ2, -3, 1, 2, 1);
        checkDimensions(intQ3, -3, -2, 1, 1);
        checkDimensions(intQ4, 1, -2, 2, 1);
        checkDimensions(intQ5, -3, 2, 1, 1);


        Rectangle paper = new Rectangle(0, 0, 80, 25);
        Rectangle print = new Rectangle(5, 3, 60, 20);
        Rectangle picture = new Rectangle(25, 8, 20, 10);

        System.out.println("--------------------------------------------------\nTeste Page - toString (Leere Seite)\n");
        Page testPageEmtpy = new Page();
        System.out.println(testPageEmtpy.info());
        System.out.println(testPageEmtpy.toString());

        System.out.println("--------------------------------------------------\nTeste Page - toString (ohne Bild)\n");
        Page testPage = new Page(paper, print);
        System.out.println(testPage.info());
        System.out.println(testPage.toString());

        System.out.println("--------------------------------------------------\nTeste Page - toString (mit Bild)\n");
        Page testPagePic = new Page(paper, print, picture);
        System.out.println(testPagePic.info());
        System.out.println(testPagePic.toString());


    }

    static void checkDimensions(Rectangle r, int expTopLeftCornerX, int expTopLeftCornerY, int expWidth, int expHeight) {

        if (r == null) {
            System.out.println("Rechteck hat keine erlaubten dimensionen");
            return;
        }

        String check;

        if (expTopLeftCornerX == r.getTopLeftCornerX() &&
                expTopLeftCornerY == r.getTopLeftCornerY() &&
                expWidth == r.getWidth() &&
                expHeight == r.getHeight()
                ) check = " -- OK";
        else check = " #####";

        System.out.printf(
                "Erwartet:     linke obere Ecke: (%3d, %3d), Breite: %3d, Höhe: %3d%n" +
                        "Tatsächlich:  linke obere Ecke: (%3d, %3d), Breite: %3d, Höhe: %3d" + check + "%n",
                expTopLeftCornerX, expTopLeftCornerY, expWidth, expHeight,
                r.getTopLeftCornerX(), r.getTopLeftCornerY(), r.getWidth(), r.getHeight()
        );
    }


}
