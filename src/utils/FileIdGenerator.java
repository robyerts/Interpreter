package utils;

/**
 * Created by robyerts on 02.11.2016.
 */
public class FileIdGenerator {
    private static int counter=0;
    public static int generator(){
        return ++counter;
    }
}
