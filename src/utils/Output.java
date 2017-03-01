package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robyerts on 12.10.2016.
 */
public class Output<E> implements IOutput<E> {
    List<E> ints;

    public Output() {
        this.ints = new ArrayList<E>();
    }

    public void add(E a)
    {
        ints.add(a);
    }

    @Override
    public Iterable<E> getAll() {
        return ints;
    }

    @Override
    public String toString() {
        return ints.toString();
    }
}
