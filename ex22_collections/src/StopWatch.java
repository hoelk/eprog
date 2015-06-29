/**
 * Created by hoelk on 28.06.15.
 */
public class StopWatch {

    long lastEvent = System.currentTimeMillis();

    public void sw(String eventName){
        long currentEvent = System.currentTimeMillis();
        System.out.printf("::: Timer: %15s  : %10.3f s :::%n", eventName, ((currentEvent - lastEvent)/1000.));
    }

    public void sw(){
        long currentEvent = System.currentTimeMillis();
        System.out.printf("::: Timer: Generic  : %10.3f s :::%n", (currentEvent - lastEvent)/1000.);
    }



}
