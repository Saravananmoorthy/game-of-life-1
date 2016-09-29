package pl.cba.reallygrid.gameoflife.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cba.reallygrid.gameoflife.gui.GamePanel;
import pl.cba.reallygrid.gameoflife.gui.SettingsPanel;
import pl.cba.reallygrid.gameoflife.model.Model;

import java.util.Timer;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class Controller {
	public Controller(GamePanel gamePanel, SettingsPanel settingsPanel, Model model) {
		this.gamePanel = gamePanel;
		this.settingsPanel = settingsPanel;
		this.model = model;

		this.model.addObserver(this.gamePanel);

		settingsPanel.addGenerateBtnListener(e -> {
			model.generate(settingsPanel.getFactorValue());
			model.notifyObserver();
			LOGGER.info("Wygenerowano z gęstością " + settingsPanel.getFactorValue() + '%');
		});
	}

	private GamePanel gamePanel;
	private SettingsPanel settingsPanel;
	private Model model;

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
}
