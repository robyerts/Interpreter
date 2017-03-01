package utils;

/**
 * Created by robert on 13.12.2016.
 */
public class PrgStateIdGenerator {
    private static int counter=0;
    public static int generator(){
        return ++counter;
    }
}
