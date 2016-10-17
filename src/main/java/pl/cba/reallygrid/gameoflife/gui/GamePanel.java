package pl.cba.reallygrid.gameoflife.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cba.reallygrid.gameoflife.model.ModelApi;
import pl.cba.reallygrid.gameoflife.util.Observer;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;

import static java.awt.Color.BLACK;
import static java.awt.Color.LIGHT_GRAY;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class GamePanel extends JComponent implements Observer {
	public GamePanel(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		setPreferredSize(new Dimension(columns * currentCellSize + 1, rows * currentCellSize + 1));
	}

	@Override
	public void update(ModelApi model) {
		if(model != null) {
			cells = model.getCells();
		}
		repaint();
	}

	public int getCellSize() {
		return currentCellSize;
	}

	public void changeGridSize(int size) {
		currentCellSize = size;
		getPreferredSize().setSize(columns * size + 1, rows * size + 1);
		repaint();
		LOGGER.info("Now cell size is " + size);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		drawCells(g);
		drawGrid(g);
	}

	private void drawCells(Graphics g) {
		g.setColor(BLACK);
		if(cells != null) {
			for(int row = 1; row < rows - 1; row++) {
				for(int column = 1; column < columns - 1; column++) {
					if(cells[row * columns + column]) {
						g.fillRect(column * currentCellSize, row * currentCellSize, currentCellSize, currentCellSize);
					}
				}
			}
		}
	}

	private void drawGrid(Graphics g) {
		g.setColor(LIGHT_GRAY);
		for(int r = 0; r <= rows; r++) {
			g.drawLine(0, r * currentCellSize, columns * currentCellSize, r * currentCellSize);
		}
		for(int c = 0; c <= columns; c++) {
			g.drawLine(c * currentCellSize, 0, c * currentCellSize, rows * currentCellSize);
		}
	}

	static final int CELL_SIZE = 6;
	private int currentCellSize = CELL_SIZE;
	private int rows;
	private int columns;

	private boolean[] cells;

	private static final Logger LOGGER = LoggerFactory.getLogger(GamePanel.class);
}
