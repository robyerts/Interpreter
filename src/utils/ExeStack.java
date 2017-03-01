package utils;

//import model.Statement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by robert on 12.10.2016.
 */
public class ExeStack<E> implements IExeStack<E> {
    Deque<E> statements;

    public ExeStack() {
        statements=new ArrayDeque<E>();
    }

    @Override
    public void push(E s) {
        statements.push(s);
    }

    @Override
    public boolean isEmpty() {
        return statements.isEmpty();
    }

    @Override
    public E pop() {
        return statements.pop();
    }

    @Override
    public Iterable<E> getAll() {
        return statements;
    }

    @Override
    public String toString() {
        return
                "" + statements
                ;
    }
}
