package model;
import utils.*;

/**
 * Created by robyerts on 12.10.2016.
 */
public class CompStatement implements  Statement {
    private Statement first,second;

    public CompStatement(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState p) {
        IExeStack exe=p.getExeStack();
        exe.push(second);
        exe.push(first);
        return null;
    }

    @Override
    public String toString() {
        return "{"  +first
                 + ";"+ second +
                '}';
    }
}
