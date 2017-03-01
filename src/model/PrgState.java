package model;
import utils.*;

import java.io.Serializable;

/**
 * Created by robyerts on 12.10.2016.
 */
public class PrgState implements Serializable {
    private  Statement initialState;
    private IExeStack<Statement> exeStack;
    private ISymbolTable<String,Integer> st;
    private IOutput<Integer> out;
    private IFileTable<Integer,FileData> flTable;
    private IHeap<Integer> heapp;
    private ILockTable<Integer,Integer> locktb;
    private int id;

    public PrgState(IExeStack<Statement> exeStack, ISymbolTable<String,Integer> st, IOutput<Integer> out,IFileTable<Integer,FileData> flt, IHeap<Integer> h, ILockTable<Integer,Integer> lk, Statement s) {
        this.initialState = s;
        this.exeStack = exeStack;
        this.st = st;
        this.out = out;
        this.exeStack.push(s);
        this.heapp = h;
        this.locktb = lk;
        flTable = flt;
        id = PrgStateIdGenerator.generator();
    }

    public IFileTable<Integer, FileData> getFlTable() {
        return flTable;
    }

    public void setFlTable(FileTable<Integer, FileData> flTable) {
        this.flTable = flTable;
    }

    public Statement getInitialProg()
    {
        return initialState;
    }

    public IExeStack<Statement> getExeStack() {
        return exeStack;
    }

    public ISymbolTable<String,Integer> getSt() {
        return st;
    }

    public IOutput<Integer> getOut() {
        return out;
    }

    public Statement getInitialState() {
        return initialState;
    }

    public void setInitialState(Statement initialProg) {
        this.initialState = initialProg;
    }

    public void setExeStack(IExeStack<Statement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSt(ISymbolTable<String,Integer> st) {
        this.st = st;
    }

    public void setOut(IOutput<Integer> out) {
        this.out = out;
    }

    public IHeap<Integer> getHeap() {
        return heapp;
    }

    public void setFlTable(IFileTable<Integer, FileData> flTable) {
        this.flTable = flTable;
    }

    public void setHeap(IHeap<Integer> heapp) {
        this.heapp = heapp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ILockTable<Integer, Integer> getLocktb() {
        return locktb;
    }

    public void setLocktb(ILockTable<Integer, Integer> locktb) {
        this.locktb = locktb;
    }


    @Override
    public String toString() {
        return "PrgState "+id+"{" +
                "initialStatem = " + initialState +
                " exeStack = " + exeStack +
                ", st = " + st +
                ", out = " + out +
                ", flTable = "+flTable+
                ", heap = "+heapp+
                '}';
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState OneStep(){
        if (exeStack.isEmpty()){
            throw new InterpretorException("Stack is empty");
        }
        Statement crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}
