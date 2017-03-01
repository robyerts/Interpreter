package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 12.10.2016.
 */
public class SymbolTable<E,F> implements ISymbolTable<E,F> {
    private HashMap<E,F> hs;

    public SymbolTable() {
        this.hs = new HashMap<E,F>();
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
    public ISymbolTable clone(){

        ISymbolTable newSt=new SymbolTable();
        for(Map.Entry<E,F> entry: getAll()){
            newSt.add(entry.getKey(),entry.getValue());
        }
        return newSt;
    }
}
