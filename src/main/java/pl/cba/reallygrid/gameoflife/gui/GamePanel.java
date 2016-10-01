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
		setPreferredSize(new Dimension(columns * CELL_SIZE + 1, rows * CELL_SIZE + 1));
	}

	@Override
	public void update(ModelApi model) {
		if(model != null) {
			cells = model.getCells();
		}
		repaint();
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
						g.fillRect(column * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					}
				}
			}
		}
	}

	private void drawGrid(Graphics g) {
		g.setColor(LIGHT_GRAY);
		for(int r = 0; r <= rows; r++) {
			g.drawLine(0, r * CELL_SIZE, columns * CELL_SIZE, r * CELL_SIZE);
		}
		for(int c = 0; c <= columns; c++) {
			g.drawLine(c * CELL_SIZE, 0, c * CELL_SIZE, rows * CELL_SIZE);
		}
	}

	private static final int CELL_SIZE = 6;
	private int rows;
	private int columns;

	private boolean[] cells;

	private static final Logger LOGGER = LoggerFactory.getLogger(GamePanel.class);
}
