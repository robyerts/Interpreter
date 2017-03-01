package Repository;
import model.*;

import java.util.List;

/**
 * Created by robyerts on 19.10.2016.
 */
public interface IRepository {
    void addPrgState(PrgState s);
    void logPrgStateExec(PrgState p);
    PrgState deserialize(String s);
    void serialize(PrgState ps, String s);

    void clearFile();

    List<PrgState> getPrgStates();
    void setPrgStates(List<PrgState> l);
}
