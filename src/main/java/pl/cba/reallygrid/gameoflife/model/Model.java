package pl.cba.reallygrid.gameoflife.model;

import pl.cba.reallygrid.gameoflife.util.Observable;
import pl.cba.reallygrid.gameoflife.util.Observer;

import java.util.Date;
import java.util.Random;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class Model implements Observable {
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
	public void addObserver(Observer o) {
		this.observer = o;
	}

	@Override
	public void notifyObserver() {
		observer.update(cells);
	}

	private void next() {
		for(int row = 1; row < DEFAULT_ROWS - 1; row++) {
			for(int column = 1; column < DEFAULT_COLUMNS; column++) {
				int aliveCount = aliveCount(row, column);
				boolean aliveCell = cells[row * DEFAULT_COLUMNS + column];
				if(aliveCell) {
					activeCells[row * DEFAULT_COLUMNS + column] = aliveCount == 2 || aliveCount == 3;
				}
				else {
					activeCells[row * DEFAULT_COLUMNS + column] = aliveCount == 3;
				}
			}
		}

		cells = activeCells;
		notifyObserver();
	}

	private int aliveCount(int r, int c) {
		int alive = 0;
		for(int i = -1; i <= 1; i++) {
			if(cells[(r - 1) * DEFAULT_COLUMNS + c + i]) {
				alive++;
			}
			if(cells[(r + 1) * DEFAULT_COLUMNS + c + i]) {
				alive++;
			}
		}
		for(int i = -1; i <= 1; i += 2) {
			if(cells[r * DEFAULT_COLUMNS + c + i]) {
				alive++;
			}
		}

		return alive;
	}

	private static final int DEFAULT_COLUMNS = 400;
	private static final int DEFAULT_ROWS = 300;
	private boolean[] cells = new boolean[DEFAULT_ROWS * DEFAULT_COLUMNS];
	private boolean[] activeCells = new boolean[DEFAULT_ROWS * DEFAULT_COLUMNS];
	private Observer observer;

	private Random random = new Random(new Date().getTime());
}
