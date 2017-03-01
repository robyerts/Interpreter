package utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by robyerts on 02.11.2016.
 */
public interface IFileTable<E,F> extends Serializable {
    void add(E key, F value);
    F getValue(E key);
    void remove(E key);
    Iterable<Map.Entry<E,F>> getAll();
}
