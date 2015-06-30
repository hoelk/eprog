import javax.xml.transform.Result;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by hoelk on 30.06.15.
 */
public class ResultSet {


    HashSet<String> countriesWithCity;
    TreeSet<Place> biggestPlaces;

    ResultSet(HashSet<String> countriesWithCity, TreeSet<Place> biggestPlaces){
        this.countriesWithCity = countriesWithCity;
        this.biggestPlaces = biggestPlaces;
    }


    public String toString() {

        for (String e : countriesWithCity) {
            System.out.println(e);
        }

        for (Place e : biggestPlaces) {
            System.out.println(e);
        }

        return ("e");
    }

}






