package model;
import utils.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by robyerts on 02.11.2016.
 */
public class OpenFileStatement implements Statement {
    private String fileName;
    private String varName;
    public OpenFileStatement(String fileName,String varName){
        this.fileName = fileName;
        this.varName = varName;
    }

    private boolean isOpen(String fn, IFileTable<Integer,FileData> flTable){
        for(Map.Entry<Integer,FileData>it:flTable.getAll()){
            if(fn.equals(it.getValue().getName())){
                return true ;
            }
        }
        return false;
    }

    @Override
    public PrgState execute(PrgState p) {
        if(isOpen(fileName,p.getFlTable())){
            throw new InterpretorException("file is already opened");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            FileData fd = new FileData(fileName,br);
            int id = FileIdGenerator.generator();
            p.getFlTable().add(id,fd);
            p.getSt().add(varName,id);
        } catch (FileNotFoundException e) {
            throw new InterpretorException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "OpenFileStatement{" +
                "fileName = '" + fileName + '\'' +
                ", varName = '" + varName + '\'' +
                '}';
    }
}
