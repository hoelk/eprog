/**
 * Created by Stefan Fleck 0440930 on 1/1/15.
 */
public class formatText {

    public static void main(String[] args) {

        final String blindText = "The quick brown fox jumps over the lazy dog. ";
        final Rectangle paperSize = new Rectangle(0, 0, 80, 100);

        if (args.length != 4 && args.length != 8) {
            System.out.println("Falsche Anzahl and Paramtern");
            System.exit(0);
        }

        final Rectangle printableArea = new Rectangle(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        );
        final Rectangle pictureSpace;


        if (args.length == 4) {
            pictureSpace = new Rectangle(0, 0, 0, 0);
        } else {
            pictureSpace = new Rectangle(
                    Integer.parseInt(args[4]),
                    Integer.parseInt(args[5]),
                    Integer.parseInt(args[6]),
                    Integer.parseInt(args[7])
            );
        }

        Page thePage = new Page(paperSize, printableArea, pictureSpace);

        System.out.println();
        System.out.println(thePage.info());
        System.out.println(thePage.toString(blindText));

    }
}
