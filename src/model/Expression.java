package model;
import utils.*;

import java.io.Serializable;

/**
 * Created by robyerts on 12.10.2016.
 */
public interface Expression extends Serializable {
    int evaluate(ISymbolTable<String,Integer> s, IHeap<Integer> h);
}
