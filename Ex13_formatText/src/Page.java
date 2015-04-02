/**
 * Created by Stefan Fleck 0440930 on 12/19/14.
 */
public class Page {

    final private String blindText = "The quick brown fox jumps over the lazy dog. ";
    /* object variable = instance variable
declaring them as final makes this class an immutable class = unveränderliche Klasse */
    private Rectangle paperSize;
    private Rectangle printableArea;
    private Rectangle pictureSpace;

    // Default constructor

    Page() {
        this.paperSize = new Rectangle();
        this.printableArea = new Rectangle();
        this.pictureSpace = new Rectangle();
    }

    // Custom constructor

    Page(Rectangle paperSize, Rectangle printableArea) {
        this.paperSize = paperSize;
        this.printableArea = printableArea;
        this.pictureSpace = new Rectangle(0, 0, 0, 0);
    }

    Page(Rectangle paperSize, Rectangle printableArea, Rectangle pictureSpace) {
        checkDimensionConsistency(paperSize, printableArea, pictureSpace);

        this.paperSize = paperSize;
        this.printableArea = printableArea;
        this.pictureSpace = pictureSpace;
    }

    private static String repeatString(String string, int length) {
        return new String(new char[length]).replace("\0", string);
    }

    private void checkDimensionConsistency(Rectangle paperSize, Rectangle printableArea, Rectangle pictureSpace) {
        if (printableArea.getMaxCol() > paperSize.getMaxCol() ||
                printableArea.getMaxRow() > paperSize.getMaxRow() ||
                printableArea.getMinCol() < paperSize.getMinCol() ||
                printableArea.getMinRow() < paperSize.getMinRow()) {
            System.out.println("Druckbereich liegt Ausserhalb des Papiers");
            System.exit(0);
        }

        if ((pictureSpace.getWidth() != 0 && pictureSpace.getHeight() != 0) &&
                (pictureSpace.getMaxCol() > printableArea.getMaxCol() ||
                        pictureSpace.getMaxRow() > printableArea.getMaxRow() ||
                        pictureSpace.getMinCol() < printableArea.getMinCol() ||
                        pictureSpace.getMinRow() < printableArea.getMinRow())
                ) {
            System.out.println("Abbildung liegt ausserhalb des Druckbereichs");
            System.exit(0);
        }
    }

    public String toString() {
        StringBuilder thePage = new StringBuilder();
        thePage.append(generateBlindText(blindText, printableArea.getWidth(), printableArea.getHeight(), pictureSpace));
        thePage = addBorders(thePage, this.getTopBorder(), this.getLeftBorder(), this.getRightBorder(), this.getBottomBorder());
        return thePage.toString();
    }

    public String toString(String blindText) {
        StringBuilder thePage = new StringBuilder();
        thePage.append(generateBlindText(blindText, printableArea.getWidth(), printableArea.getHeight(), pictureSpace));
        thePage = addBorders(thePage, this.getTopBorder(), this.getLeftBorder(), this.getRightBorder(), this.getBottomBorder());
        return thePage.toString();
    }

    private String generateBlindText(String blindText, int width, int height, Rectangle picture) {

        StringBuilder blindTextFill = new StringBuilder();
        String result = "";

        while (blindTextFill.length() <= width * height) {
            blindTextFill.append(blindText);
        }

        for (int i = 1; i <= height; i++) {
            int lineEnd = width * i;
            if (i > 1) lineEnd += i - 1; // account for linebreaks
            int lineStart = lineEnd - width;

            if (i > picture.getMinRow() - this.getTopBorder() && i <= picture.getMaxRow() - this.getTopBorder()) {
                int pictureIndex = lineStart + picture.getMinCol() - this.getLeftBorder();
                int blankBeforePictureIndex = blindTextFill.lastIndexOf(" ", pictureIndex);

                if (blankBeforePictureIndex <= lineStart) {
                    blindTextFill.insert(lineStart, " ");
                    blankBeforePictureIndex = lineStart;
                }

                int pictureInsertWidth = picture.getWidth() + pictureIndex - blankBeforePictureIndex;
                String pictureFill = repeatString(" ", pictureInsertWidth);

                blindTextFill.replace(blankBeforePictureIndex, blankBeforePictureIndex + 1, pictureFill);
            }

            blindTextFill = newlineAtLastBlank(blindTextFill, lineEnd);

            if (i == height) {
                result = blindTextFill.substring(0, lineEnd);
            }
        }
        return result;//(0, endPoint);
    }

    private StringBuilder newlineAtLastBlank(StringBuilder text, int lineEnd) {
        int lastBlankIndex = text.lastIndexOf(" ", lineEnd);
        int lineEndFill = lineEnd - lastBlankIndex;
        String breakFill = repeatString(" ", lineEndFill) + "\n";
        text.replace(lastBlankIndex, lastBlankIndex + 1, breakFill);
        return text;

    }

    private StringBuilder addBorders(StringBuilder textPage, int top, int left, int right, int bottom) {
        StringBuilder result = new StringBuilder("");

        String leftBorderSpacing = repeatString(" ", left);
        String rightBorderSpacing = repeatString(" ", right);


        String[] textLines = textPage.toString().split("\\n");
        for (String s : textLines) {
            result.append(leftBorderSpacing + s + rightBorderSpacing + "\n");
        }

        for (int i = 0; i < top; i++) {
            result.insert(0, "\n");
        }

        for (int i = 0; i < bottom; i++) {
            result.append("\n");
        }

        return result;

    }

    public int getTopBorder() {
        return (Math.abs(printableArea.getMinRow() - paperSize.getMinRow()));
    }

    public int getBottomBorder() {
        return (Math.abs(paperSize.getMaxRow() - printableArea.getMaxRow()));
    }

    public int getLeftBorder() {
        return (printableArea.getMinCol());
    }

    public int getRightBorder() {
        return (paperSize.getMaxCol() - printableArea.getMaxCol());
    }

    public int getTextWidth() {
        return printableArea.getWidth();
    }

    public String info() {
        return String.format("" +
                        "Papiermaße:     %s%n" +
                        "Druckbereich:   %s%n" +
                        "Abbildung:      %s%n" +
                        "Ränder:         Oben: %s, Rechts: %s, Links: %s, Unten: %s",
                paperSize.toString(),
                printableArea.toString(),
                pictureSpace.toString(),
                this.getTopBorder(),
                this.getRightBorder(),
                this.getLeftBorder(),
                this.getBottomBorder()
        );
    }
}


