package model;
import utils.*;

import java.io.Serializable;

/**
 * Created by robyerts on 12.10.2016.
 */
public interface Statement extends Serializable{
     PrgState execute(PrgState p);
}
