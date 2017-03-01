package utils;

import java.io.Serializable;

/**
 * Created by robyerts on 12.10.2016.
 */
public interface IOutput<E> extends Serializable {
    void add(E a);
    Iterable<E> getAll();
}
