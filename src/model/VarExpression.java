package model;

import utils.IHeap;
import utils.ISymbolTable;
import utils.InterpretorException;

/**
 * Created by robyerts on 12.10.2016.
 */
public class VarExpression implements  Expression{
    private String name;
    public VarExpression(String name)
    {
        this.name=name;
    }
    public int evaluate(ISymbolTable<String,Integer> s, IHeap<Integer> h)
    {
        if(s.contains(name))
            return s.getValue(name);
        else
        {
            throw new InterpretorException("no such variable exists");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
