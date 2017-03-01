package utils;

/**
 * Created by robert on 25.01.2017.
 */
public class LockIdGenerator {
    private static int counter=0;
    public static int generator(){
        return ++counter;
    }
}
