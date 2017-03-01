package utils;
//import model.Statement;

import java.io.Serializable;

/**
 * Created by robyerts on 12.10.2016.
 */
public interface IExeStack<E> extends Serializable {
    void push(E s);
    E pop();
    boolean isEmpty();
    Iterable<E> getAll();
}
