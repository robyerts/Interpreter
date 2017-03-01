package model;

import utils.IHeap;
import utils.ISymbolTable;

/**
 * Created by robert on 16.11.2016.
 */
public class rH implements Expression{
    private String var_name;

    public rH(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public int evaluate(ISymbolTable<String, Integer> s, IHeap<Integer> h) {
        Integer indexInHeap = s.getValue(var_name);
        return h.getValue(indexInHeap);
    }

    @Override
    public String toString() {
        return "rH{" + var_name +
                '}';
    }
}
