import java.io.IOException;

/**
 * Created by hoelk on 23.06.15.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Place x = new Place("AT", "Vienna", "Vienna", "VE", 999, 75, 170);

        Places cities = new Places();

        Places.readCsv("./data/worldcitiespop.txt");


    }




}
