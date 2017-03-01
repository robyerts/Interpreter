package model;


import utils.ExeStack;
import utils.ISymbolTable;

/**
 * Created by robert on 13.12.2016.
 */
public class ForkStatement implements Statement {
    private Statement stmt;

    public ForkStatement(Statement stmt) {
        this.stmt = stmt;
    }

    public PrgState execute(PrgState p){
        ISymbolTable<String, Integer> st=p.getSt();
        ISymbolTable<String, Integer> newSt= st.clone();
        PrgState newPrgState = new PrgState(new ExeStack<Statement>(), newSt, p.getOut(), p.getFlTable(), p.getHeap(),p.getLocktb(), stmt);
        return newPrgState;
    }

    @Override
    public String toString() {
        return "fork {" +
                stmt +
                '}';
    }
}
