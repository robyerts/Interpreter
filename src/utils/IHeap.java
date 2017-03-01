package utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by robert on 16.11.2016.
 */
public interface IHeap<F> extends Serializable {
    void add(Integer key,F value);
    void set(Integer key, F value);
    F getValue(Integer key);
    //void remove(Integer key);
    Iterable<Map.Entry<Integer,F>> getAll();
    Map<Integer,F> getMap();
    void setMap(Map<Integer,F> m);
}
