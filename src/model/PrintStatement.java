package model;

import utils.*;

/**
 * Created by robyerts on 12.10.2016.
 */
public class PrintStatement implements  Statement {
    private Expression exp;

    public PrintStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState p) {
        IOutput o=p.getOut();
        int res=exp.evaluate(p.getSt(),p.getHeap());
        o.add(res);
        return null;
    }

    @Override
    public String toString() {
        return "Print(" +
                exp +
                ')';
    }
}
