package utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by robert on 25.01.2017.
 */
public interface ILockTable<E,F> extends Serializable {
    void add(E key,F value);
    boolean contains(E key);
    F getValue(E key);
    Iterable<Map.Entry<E,F>> getAll();
    Collection<F> values();
    ILockTable clone();
}
