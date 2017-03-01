package model;

import utils.ILockTable;
import utils.ISymbolTable;
import utils.InterpretorException;
import utils.LockIdGenerator;

/**
 * Created by robert on 25.01.2017.
 */
public class unlock implements Statement {
    private String var_name;

    public unlock(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState p) {
        ISymbolTable<String, Integer> st = p.getSt();
        ILockTable<Integer,Integer> lktb = p.getLocktb();

        if(!st.contains(var_name)){
            throw new InterpretorException("lock variable is not present in symbol table");
        }

        Integer lock_key = st.getValue(var_name);

        if(!lktb.contains(lock_key)){
            throw new InterpretorException("lock key from ST is not preset in lock table");
        }

        Integer lock_state= lktb.getValue(lock_key);

        if(lock_state == p.getId()){
            lktb.add(lock_key,-1);
        }

        return null;
    }

    @Override
    public String toString() {
        return "unlock{" + var_name +
                '}';
    }
}
