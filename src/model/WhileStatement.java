package model;

import utils.IExeStack;

/**
 * Created by robert on 07.12.2016.
 */
public class WhileStatement implements Statement {
    private Statement stmt;
    private Expression exp;

    public WhileStatement(Statement stmt, Expression expr) {
        this.stmt = stmt;
        this.exp = expr;
    }

    @Override
    public PrgState execute(PrgState p) {
        IExeStack exe=p.getExeStack();
        if(exp.evaluate(p.getSt(),p.getHeap())!=0) {
            exe.push(this);
            exe.push(stmt);
        }

        return null;
    }

    @Override
    public String toString() {
        return
                " while" + exp + stmt +
                '}';
    }

}
