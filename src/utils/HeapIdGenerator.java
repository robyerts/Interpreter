package utils;

/**
 * Created by robert on 16.11.2016.
 */
public class HeapIdGenerator {
    private static int counter=0;
    public static int generator(){
        return ++counter;
    }
}
