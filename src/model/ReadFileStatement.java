package model;

import utils.FileData;
import utils.InterpretorException;

import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Created by robert on 09.11.2016.
 */
public class ReadFileStatement implements Statement {
    private String var_file, newVar_name ;

    public ReadFileStatement(String var_file, String var_name){
        this.var_file = var_file;
        this.newVar_name = var_name;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer e = p.getSt().getValue(var_file);
        if (e == null) {
            throw new InterpretorException("value doesn't match in symbol table");
        }
        FileData d =p.getFlTable().getValue(e);
        if (d == null)
            throw new InterpretorException("value doesn't exist in FileTable");
        BufferedReader br = d.getRead();
        try {
            String s = br.readLine();
            int v;
            if (s == null) {
                v = 0;
            }
            else {
                v = parseInt(s);
            }
            p.getSt().add(newVar_name,v);

        } catch (IOException | NumberFormatException err) {
            throw new InterpretorException("error at reading from the file");
        }
        return null;
    }

    @Override
    public String toString() {
        return "ReadFileStatement{" +
                "var_file_id='" + var_file + '\'' +
                ", var_name='" + newVar_name + '\'' +
                '}';
    }
}
