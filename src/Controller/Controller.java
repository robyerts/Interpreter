package Controller;
import javafx.scene.control.Alert;
import model.*;

import Repository.IRepository;
import utils.IExeStack;
import utils.InterpretorException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by robyerts on 19.10.2016.
 */
public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public IRepository getRepo() {
        return repo;
    }

    public Controller(IRepository rep)
    {
        repo=rep;
    }


    public List<PrgState> removeCompletedPrg(List<PrgState> l){
        return l.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }


    public void oneStepForAllPrg(List<PrgState> l){

        List<Callable<PrgState>> callList= l.stream().map((PrgState prg)->(Callable<PrgState>) (()->prg.OneStep())).collect(Collectors.toList());
        try {
            List<PrgState> newPrg = executor
                    .invokeAll(callList)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception er) {
                            throw new InterpretorException(er.getMessage());
                        }
                        //return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            l.addAll(newPrg);
            repo.setPrgStates(l);
            l.forEach(prg->this.repo.logPrgStateExec(prg));
        }
        catch(InterruptedException e){
            throw new InterpretorException(e.getMessage());
        }

    }

    public void oneStepGUI() {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgStates());

        if (prgList.size() == 0) {
            //display a window message saying that the execution terminates
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End of execution.");
            alert.showAndWait();

            executor.shutdownNow();
        } else {
            oneStepForAllPrg(prgList);
            executor.shutdownNow();
        }
    }

    public void allStepGUI() {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgStates());

        while(prgList.size() != 0) {
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgStates());
        }
        executor.shutdownNow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of execution.");
        alert.showAndWait();
    }



    public void serialize(String fileName) {
        this.repo.serialize(repo.getPrgStates().get(1),fileName);
    }

    public PrgState deserialize(String filename) {
        return this.repo.deserialize(filename);
    }

    public void addPrgState(PrgState s)
    {
        repo.addPrgState(s);
    }
}
