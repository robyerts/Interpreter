package service;

import model.*;
import Repository.*;

import utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 16.01.2017.
 */

public class PrgStateService implements Observable<PrgState> {
    private IRepository PrgStateRepo;
    protected List<Observer<PrgState>> observers = new ArrayList<Observer<PrgState>>();

    public PrgStateService(IRepository repo) {
        PrgStateRepo = repo;
    }

    public void save(PrgState t) {
        PrgStateRepo.addPrgState(t);
        notifyObservers();
    }

    public void setPrgStates(List<PrgState> p) {
        PrgStateRepo.setPrgStates(p);
        notifyObservers();

    }

    public List<PrgState> getPrgStates() {
        return PrgStateRepo.getPrgStates();

    }

    public void serialize(PrgState ps,String s) {
        PrgStateRepo.serialize(ps,s);
    }
    public PrgState deserialize(String s) {
        return PrgStateRepo.deserialize(s);
    }
    public String toStringPrgStateRepo() {
        return PrgStateRepo.toString();
    }
    public void logPrgStateExec(PrgState p) {
        PrgStateRepo.logPrgStateExec(p);
    }

        @Override
    public void addObserver(Observer<PrgState> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<PrgState> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer<PrgState> o : observers) {
            o.update(this);
        }
    }


        // getters and setters
    public IRepository getPrgStateRepo() {
        return PrgStateRepo;
    }

    public void setPrgStateRepo(IRepository prgStateRepo) {
        PrgStateRepo = prgStateRepo;
    }

    public List<Observer<PrgState>> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer<PrgState>> observers) {
        this.observers = observers;
    }
}
