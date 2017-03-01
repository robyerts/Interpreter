package model;

import com.sun.corba.se.spi.orbutil.fsm.State;
import utils.*;

/**
 * Created by robert on 25.01.2017.
 */
public class lock implements Statement {
    private String var_name;

    public lock(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState p) {

        ISymbolTable<String, Integer> st = p.getSt();
        if (!st.contains(var_name)){
            throw new InterpretorException("lock variable is not present in symbol table");
        }

        ILockTable<Integer,Integer> lktb = p.getLocktb();

        if (!lktb.contains(st.getValue(var_name))){
            throw new InterpretorException("lock key from ST is not preset in lock table");
        }

        Integer lock_state = lktb.getValue(st.getValue(var_name));

        if(lock_state == -1)
            lktb.add(st.getValue(var_name),p.getId());
        else {
            p.getExeStack().push(this);
        }

        return null;
    }

    @Override
    public String toString() {
        return "lock{" + var_name +
                '}';
    }
}
