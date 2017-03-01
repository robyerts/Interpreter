package utils;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by robyerts on 02.11.2016.
 */
public class FileData implements Serializable {
    private String name;
    private BufferedReader read;

    public FileData(String name, BufferedReader read) {
        this.name = name;
        this.read = read;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getRead() {
        return read;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRead(BufferedReader read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "File: " +
                name ;
    }
}
