package model;

import utils.ISymbolTable;

/**
 * Created by robyerts on 12.10.2016.
 */
public class AssignStatement implements Statement{
    private String var;
    private Expression expr;

    public AssignStatement(String var, Expression expr) {
        this.var = var;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState p) {
        ISymbolTable t = p.getSt();
        int res=expr.evaluate(t, p.getHeap());
        t.add(var,res);
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                  var +"="+
                  expr +
                '}';
    }
}
