package Repository;

import model.*;
import utils.FileData;
import utils.InterpretorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by robyerts on 19.10.2016.
 */
public class Repository implements IRepository{
    private List<PrgState> states;
    private String fileName;

    public Repository(PrgState p,String fileN)
    {
        states = new ArrayList<>();
        fileName = fileN;
        states.add(p);
    }

    public Repository(String fileN)
    {
        states = new ArrayList<>();
        fileName = fileN;
    }

    public void addPrgState(PrgState s)
    {
        states.add(s);
    }

    public List<PrgState> getPrgStates(){
        return states;
    }
    public void setPrgStates(List<PrgState> p){
        states = p;
    }

    @Override
    public void logPrgStateExec(PrgState p) {
        try {
            try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))){
                logFile.println("---------------------------------");
                logFile.println("id: " + p.getId());
                logFile.println("ExeStack");
                for (Statement s:p.getExeStack().getAll()){
                    logFile.println(s);
                }
                logFile.println("");
                logFile.println("SymbolTable");
                for(Map.Entry<String,Integer> s:p.getSt().getAll()){
                    logFile.println(s.getKey()+" = "+s.getValue());
                }
                logFile.println("");
                logFile.println("Output");
                for(Integer s:p.getOut().getAll()){
                    logFile.println(s);
                }
                logFile.println("");
                logFile.println("FileTable");
                for(Map.Entry<Integer, FileData> s:p.getFlTable().getAll()){
                    logFile.println(s.getKey()+" = "+s.getValue());
                }
                logFile.println("");
                logFile.println("Heap");
                for(Map.Entry<Integer, Integer> s:p.getHeap().getAll()){
                    logFile.println(s.getKey()+" = "+s.getValue());
                }
                logFile.println("---------------------------------");
            }
        } catch (IOException e) {
            throw new InterpretorException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return states.toString();
    }

    @Override
    public PrgState deserialize(String s) {
        try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(s))) {
            Object o = str.readObject();
            if (o instanceof PrgState) {
                return (PrgState) o;
            }
            throw new InterpretorException("object in deserialize not an instance of PrgState");
        } catch (Exception e1) {
            throw new InterpretorException(e1.getMessage());
        }
    }

    @Override
    public void serialize(PrgState ps,String s) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(s))) {
            oos.writeObject(ps);
        } catch (Exception e1) {
            throw new InterpretorException(e1.getMessage());
        }
    }

    @Override
    public void clearFile() throws InterpretorException {
        try {
            FileWriter fileOut = new FileWriter(fileName);
            fileOut.write("");
            fileOut.close();
        } catch(Exception e){
            throw new InterpretorException(e.getMessage());
        }
    }
}
