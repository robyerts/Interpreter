package model;

/**
 * Created by robert on 12.10.2016.
 */
public class StartStatement implements  Statement {
    @Override
    public PrgState execute(PrgState p) {
        System.out.println("INTERPRETER STARTED");
        return null;
    }

    @Override
    public String toString() {
        return "StartStatement{}";
    }
}
