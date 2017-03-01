package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by robert on 25.01.2017.
 */
public class LockTable<E,F> implements ILockTable<E,F> {
    ConcurrentMap<E,F> hs;

    public LockTable() {
        this.hs = new ConcurrentHashMap<>();
    }

    @Override
    public void add(E key, F value) {
        hs.put(key,value);
    }

    @Override
    public boolean contains(E key) {
        return hs.containsKey(key);
    }

    @Override
    public F getValue(E key){
        if (!hs.containsKey(key))
            return null;                /////SUUUURE null is FINE ???
        else return hs.get(key);
    }

    @Override
    public Iterable<Map.Entry<E, F>> getAll() {
        return hs.entrySet();
    }

    @Override
    public Collection<F> values() {
        return hs.values();
    }

    @Override
    public String toString() {
        StringBuffer ss=new StringBuffer("");

        ss.append("{");
        for(E key:hs.keySet())
        {
            ss.append(""+key+" = "+getValue(key)+"; ");
        }
        ss.append("}");
        return ss.toString();
    }
    public ILockTable clone(){

        ILockTable newL=new LockTable();
        for(Map.Entry<E,F> entry: getAll()){
            newL.add(entry.getKey(),entry.getValue());
        }
        return newL;
    }
}
