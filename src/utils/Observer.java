package utils;

/**
 * Created by robert on 16.01.2017.
 */
public interface Observer<E> {
    void update(Observable<E> observable);
}
