package utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by robyerts on 12.10.2016.
 */
public interface ISymbolTable<E,F> extends Serializable {
    void add(E key,F value);
    boolean contains(E key);
    F getValue(E key);
    Iterable<Map.Entry<E,F>> getAll();
    Collection<F> values();
    ISymbolTable clone();
}
