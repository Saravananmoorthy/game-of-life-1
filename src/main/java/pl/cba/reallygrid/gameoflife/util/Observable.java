package pl.cba.reallygrid.gameoflife.util;

/**
 * Created by krzysztof on 2016-09-29.
 */
public interface Observable {
	void addObserver(Observer o);
	void notifyObserver();
}
