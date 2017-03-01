package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 16.11.2016.
 */
public class Heap<F> implements IHeap<F> {
    private Map<Integer,F> hs;

    public Heap() {
        this.hs = new HashMap<>();
    }


    @Override
    public void add(Integer key, F value) {
        hs.put(key,value);      // would be nice to check if contains
    }
    public void set(Integer key, F value){
        hs.put(key,value);
    }

    @Override
    public F getValue(Integer key) {
        if (!hs.containsKey(key))
            return null;                /////SUUUURE null is FINE ???
        else return hs.get(key);
    }

    @Override
    public Iterable<Map.Entry<Integer, F>> getAll() {
        return hs.entrySet();
    }

    @Override
    public String toString() {
        StringBuffer ss=new StringBuffer("");

        ss.append("{");
        for(Integer key:hs.keySet())
        {
            ss.append(""+key+" = "+getValue(key)+"; ");
        }
        ss.append("}");
        return ss.toString();
    }

    @Override
    public Map<Integer, F> getMap() {
        return hs;
    }

    @Override
    public void setMap(Map<Integer, F> m) {
        hs = m;
    }
}
