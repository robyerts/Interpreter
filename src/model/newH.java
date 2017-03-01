package model;
import utils.*;

/**
 * Created by robert on 16.11.2016.
 */
public class newH implements  Statement {
    private String var_name;
    private Expression expr;

    public newH(String var_name, Expression expr) {

        this.var_name = var_name;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer res = expr.evaluate(p.getSt(),p.getHeap());
        IHeap<Integer> heapp = p.getHeap();
        Integer key = HeapIdGenerator.generator();
        heapp.add(key,res);
        p.getSt().add(var_name,key);
        return null;
    }

    @Override
    public String toString() {
        return "newH{" +
                var_name +" = "+ expr +
                '}';
    }
}
