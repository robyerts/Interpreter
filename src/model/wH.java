package model;

import utils.HeapIdGenerator;
import utils.IHeap;

/**
 * Created by robert on 16.11.2016.
 */
public class wH implements  Statement {
    private String var_name;
    private Expression expr;

    public wH(String var_name, Expression expr) {
        this.var_name = var_name;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer res = expr.evaluate(p.getSt(),p.getHeap());
        IHeap<Integer> heapp = p.getHeap();

        Integer indexInHeap = p.getSt().getValue(var_name);

        heapp.add(indexInHeap,res);
        return null;
    }

    @Override
    public String toString() {
        return "wh{" +
                var_name +" = "+ expr +
                '}';
    }
}
