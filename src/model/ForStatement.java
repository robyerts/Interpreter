package model;

import utils.IExeStack;

/**
 * Created by robert on 25.01.2017.
 */
public class ForStatement implements Statement{
    private Statement stmt;
    private Expression expr1;
    private Expression expr2;
    private Expression expr3;

    Expression v = new VarExpression("v");

    public ForStatement(Statement stmt, Expression expr1, Expression expr2, Expression expr3) {
        this.stmt = stmt;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
    }

    @Override
    public PrgState execute(PrgState  p) {
        IExeStack exe=p.getExeStack();

        Statement s1 = new AssignStatement("v",expr1);

        Statement s2 = new AssignStatement("v",expr3);
        Statement insideWhile = new CompStatement(stmt,s2);

        Statement whilee = new WhileStatement(insideWhile,new BooleanExpression("<",new VarExpression("v"),expr2));

        exe.push(whilee);
        exe.push(s1);


        return null;
    }

    @Override
    public String toString() {
        return
                "{for(v=" + expr1 + "; v<" + expr2 + "; v=" + expr3 + " )"+ stmt +
                        '}';
    }
}
