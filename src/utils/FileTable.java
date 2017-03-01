package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robyerts on 02.11.2016.
 */
public class FileTable<E,F> implements IFileTable<E,F> {
    private Map<E,F> files;

    public FileTable() {
        this.files = new HashMap<E, F>();
    }

    @Override
    public void add(E key, F value) {
        files.put(key,value);
    }

    @Override
    public F getValue(E key) {
        if (!files.containsKey(key))
            return null;                /////SUUUURE null is FINE ???
        else return files.get(key);
    }

    @Override
    public void remove(E key) {
        files.remove(key);
    }

    @Override
    public Iterable<Map.Entry<E, F>> getAll() {
        return files.entrySet();
    }

    @Override
    public String toString() {
        StringBuffer ss=new StringBuffer("");

        ss.append("{");
        for(E key:files.keySet())
        {
            ss.append(""+key+" = "+getValue(key)+"; ");
        }
        ss.append("}");
        return ss.toString();
    }

}
