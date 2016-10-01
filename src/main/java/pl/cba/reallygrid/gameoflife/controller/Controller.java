package pl.cba.reallygrid.gameoflife.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cba.reallygrid.gameoflife.gui.GamePanel;
import pl.cba.reallygrid.gameoflife.gui.SettingsPanel;
import pl.cba.reallygrid.gameoflife.model.Model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class Controller {
	public Controller(GamePanel gamePanel, SettingsPanel settingsPanel, Model model) {
		this.gamePanel = gamePanel;
		this.settingsPanel = settingsPanel;
		this.model = model;
	}

	public void addObservers() {
		model.addObserver(gamePanel);
		model.addObserver(settingsPanel);
	}

	public void addListeners() {
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cellSize = GamePanel.getCellSize();
				int row = e.getY() / cellSize;
				int column = e.getX() / cellSize;
				model.click(row, column);
				LOGGER.info("(" + e.getX() + ", " + e.getY() + ")\tcolumn: " + column + "row: " + row);
			}
		});

		settingsPanel.addGenerateBtnListener(e -> {
			model.generate(settingsPanel.getFactorValue());
			model.notifyObserver();
			LOGGER.info("Wygenerowano z gęstością " + settingsPanel.getFactorValue() + '%');
		});

		settingsPanel.addStartBtnListener(e -> timer.scheduleAtFixedRate(model, 0, 200));

		settingsPanel.addStopBtnListener(e -> timer.cancel());
	}

	private GamePanel gamePanel;
	private SettingsPanel settingsPanel;
	private Model model;
	private Timer timer = new Timer("Model timer");

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
}
