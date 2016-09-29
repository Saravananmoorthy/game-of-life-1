package pl.cba.reallygrid.gameoflife.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;

import static java.awt.Color.LIGHT_GRAY;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class GamePanel extends JComponent {
	public GamePanel(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		setPreferredSize(new Dimension(columns * CELL_SIZE + 1, rows * CELL_SIZE + 1));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		drawGrid(g);
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

	private static final Logger LOGGER = LoggerFactory.getLogger(GamePanel.class);
}
