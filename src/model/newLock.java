package model;

import utils.*;

/**
 * Created by robert on 25.01.2017.
 */
public class newLock implements Statement {
    private String var_name;

    public newLock(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState p)  {
        ISymbolTable<String, Integer> st = p.getSt();
        Integer lock_key = LockIdGenerator.generator();
        st.add(var_name,lock_key);

        ILockTable<Integer,Integer> lktb = p.getLocktb();
        lktb.add(lock_key,-1);

        return null;

    }

    @Override
    public String toString() {
        return "newLock{" +
                var_name +
                '}';
    }
}
