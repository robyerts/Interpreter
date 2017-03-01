package model;

import utils.IHeap;
import utils.ISymbolTable;
import utils.InterpretorException;

import javax.management.RuntimeErrorException;

/**
 * Created by robyerts on 12.10.2016.
 */
public class ArithExpression implements  Expression {
    private char operator;
    private Expression operand1,operand2;
    public ArithExpression(char op,Expression first, Expression second)
    {
        operator=op;
        operand1=first;
        operand2=second;
    }
    public int evaluate(ISymbolTable<String, Integer> table, IHeap<Integer> h)
    {
        int res1=operand1.evaluate(table, h);
        int res2=operand2.evaluate(table, h);
        switch (operator)
        {
            case'+':
                return res1+res2;
            case'-':
                return res1-res2;
            case'*':
                return res1*res2;
            case'/':
                if(res2==0)
                    throw new ArithmeticException("can't devide by zero");
                return res1/res2;
            default:
                throw new InterpretorException("not a valid operation");
        }
    }

    @Override
    public String toString() {
        return "("+operand1+operator+operand2+")";
    }
}
