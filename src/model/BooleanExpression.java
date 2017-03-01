package model;

import utils.IHeap;
import utils.ISymbolTable;
import utils.InterpretorException;

/**
 * Created by robert on 07.12.2016.
 */
public class BooleanExpression implements Expression {
    private String operator;
    private Expression operand1,operand2;

    public BooleanExpression(String operator, Expression operand1, Expression operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public int evaluate(ISymbolTable<String, Integer> table, IHeap<Integer> h)
    {
        int res1=operand1.evaluate(table, h);
        int res2=operand2.evaluate(table, h);
        switch (operator)
        {
            case "==":
                if (res1 == res2) {
                    return 1;
                } else {
                    return 0;
                }
            case "!=":
                if (res1 != res2){
                    return 1;
                } else {
                    return 0;
                }
            case "<":
                if (res1 < res2) {
                    return 1;
                } else {
                    return 0;
                }
            case"<=":
                if(res1 <= res2){
                    return 1;
                } else {
                    return 0;
                }
            case ">":
                if (res1 > res2) {
                    return 1;
                } else {
                    return 0;
                }
            case ">=":
                if (res1 >= res2) {
                    return 1;
                } else {
                    return 0;
                }
            default:
                throw new InterpretorException("not a valid operation");
        }
    }

    @Override
    public String toString() {
        return "("+operand1+operator+operand2+")";
    }
}
