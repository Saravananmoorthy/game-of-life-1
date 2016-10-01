package pl.cba.reallygrid.gameoflife.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cba.reallygrid.gameoflife.util.Observable;
import pl.cba.reallygrid.gameoflife.util.Observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class Model extends TimerTask implements ModelApi, Observable {
	public void generate(int factor) {
		for(int i = 0; i < cells.length; i++) {
			cells[i] = random.nextInt(101) <= factor;
		}
	}

	public static int getDefaultColumns() {
		return DEFAULT_COLUMNS;
	}

	public static int getDefaultRows() {
		return DEFAULT_ROWS;
	}

	@Override
	public boolean[] getCells() {
		return cells;
	}

	@Override
	public int getAlive() {
		return alive;
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer observer : observers) {
			observer.update(this);
		}
	}

	@Override
	public void run() {
		next();
	}

	public void click(int row, int column) {
		LOGGER.info("column:" + column + ", row:" + row);
		if(cells[row * DEFAULT_COLUMNS + column]) {
			cells[row * DEFAULT_COLUMNS + column] = false;
			alive--;
		}
		else {
			cells[row * DEFAULT_COLUMNS + column] = true;
			alive++;
		}
		notifyObserver();
	}

	private void next() {
		int aliveCells = 0;
		for(int row = 1; row < DEFAULT_ROWS - 1; row++) {
			for(int column = 1; column < DEFAULT_COLUMNS - 1; column++) {
				int aliveCount = aliveCount(row, column);
				boolean aliveCell = cells[row * DEFAULT_COLUMNS + column];
				if(aliveCell) {
					activeCells[row * DEFAULT_COLUMNS + column] = aliveCount == 2 || aliveCount == 3;
				}
				else {
					activeCells[row * DEFAULT_COLUMNS + column] = aliveCount == 3;
				}
				if(activeCells[row * DEFAULT_COLUMNS + column]) {
					aliveCells++;
				}
			}
		}

		alive = aliveCells;
		switchArrays();
		notifyObserver();
	}

	private void switchArrays() {
		boolean[] tmp = cells;
		cells = activeCells;
		activeCells = tmp;
	}

	private int aliveCount(int r, int c) {
		int aliveNeighborCell = 0;
		for(int i = -1; i <= 1; i++) {
			if(cells[(r - 1) * DEFAULT_COLUMNS + c + i]) {
				aliveNeighborCell++;
			}
			if(cells[(r + 1) * DEFAULT_COLUMNS + c + i]) {
				aliveNeighborCell++;
			}
			if(i != 0 && cells[r * DEFAULT_COLUMNS + c + i]) {
				aliveNeighborCell++;
			}
		}

		return aliveNeighborCell;
	}

	private static final int DEFAULT_COLUMNS = 400;
	private static final int DEFAULT_ROWS = 300;
	private int alive;
	private boolean[] cells = new boolean[DEFAULT_ROWS * DEFAULT_COLUMNS];
	private boolean[] activeCells = new boolean[DEFAULT_ROWS * DEFAULT_COLUMNS];
	private List<Observer> observers = new ArrayList<>();

	private Random random = new Random(new Date().getTime());
	private static final Logger LOGGER = LoggerFactory.getLogger(Model.class);
}
