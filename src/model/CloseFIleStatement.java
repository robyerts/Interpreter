package model;

import utils.FileData;
import utils.InterpretorException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by robert on 09.11.2016.
 */
public class CloseFIleStatement implements  Statement {
    private String var_file;

    public CloseFIleStatement(String var_file) {
        this.var_file = var_file;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer e = p.getSt().getValue(var_file);
        if(e == null) {
            throw new InterpretorException("value doesn't match in symbol table");
        }
        FileData a = p.getFlTable().getValue(e);
        if (a == null) {
            throw new InterpretorException("value doesn't exist in FileTable");
        }
        BufferedReader br =a.getRead();
        try {
            br.close();
        } catch(IOException err) {
            throw new InterpretorException("couldn't close the file");
        }
        p.getFlTable().remove(e);
        return null;
    }

    @Override
    public String toString() {
        return "CloseFIleStatement{" +
                "var_file_id='" + var_file + '\'' +
                '}';
    }
}
