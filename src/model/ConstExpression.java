package model;

import utils.IHeap;
import utils.ISymbolTable;

/**
 * Created by robyerts on 12.10.2016.
 */
public class ConstExpression implements Expression {
    private int value;
    public ConstExpression(int value)
    {
        this.value=value;
    }
    public int evaluate(ISymbolTable<String, Integer> s, IHeap<Integer> h)
    {
        return value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
