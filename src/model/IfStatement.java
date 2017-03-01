package model;
import utils.*;


/**
 * Created by robyerts on 12.10.2016.
 */
public class IfStatement implements  Statement {
    private Expression exp;
    private Statement thenB;
    private Statement elseB;

    public IfStatement(Expression exp, Statement thenB, Statement elseB) {
        this.exp = exp;
        this.thenB = thenB;
        this.elseB = elseB;
    }

    @Override
    public PrgState execute(PrgState p) {
        IExeStack exe=p.getExeStack();
        if(exp.evaluate(p.getSt(),p.getHeap())!=0)
            exe.push(thenB);
        else
            exe.push(elseB);
        return null;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "exp = " + exp +
                ", then = " + thenB +
                ", else = " + elseB +
                '}';
    }
}
