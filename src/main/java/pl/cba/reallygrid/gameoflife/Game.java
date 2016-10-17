package pl.cba.reallygrid.gameoflife;

import pl.cba.reallygrid.gameoflife.controller.Controller;
import pl.cba.reallygrid.gameoflife.gui.GamePanel;
import pl.cba.reallygrid.gameoflife.gui.SettingsPanel;
import pl.cba.reallygrid.gameoflife.model.Model;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class Game extends JFrame {
	public static void main(String[] args) {
		Game game = new Game();
		SwingUtilities.invokeLater(() -> {
			game.createwindow();
			game.setVisible(true);
			game.executor.execute(game::postConstruct);
		});
	}

	private void createwindow() {
		JPanel centerComponent = new JPanel(new BorderLayout(5, 5));
		centerComponent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel eastInnerPanel = new JPanel(new BorderLayout());
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Settings panel"),
				new EmptyBorder(5, 5, 5, 5)
		));
		settingsPanel = new SettingsPanel();
		eastPanel.add(settingsPanel);
		eastInnerPanel.add(eastPanel, NORTH);
		centerComponent.add(eastInnerPanel, EAST);

		JPanel centerPanel = new JPanel(new BorderLayout());
		gamePanel = new GamePanel(Model.getDefaultRows(), Model.getDefaultColumns());
		JScrollPane scrollPane = new JScrollPane(gamePanel);
		centerPanel.add(scrollPane);
		centerComponent.add(centerPanel);

		add(centerComponent);
		pack();

		setExtendedState(MAXIMIZED_BOTH);
	}

	private void postConstruct() {
		Controller controller = new Controller(gamePanel, settingsPanel, new Model());
		controller.addObservers();
		controller.addListeners();
	}

	private Game() throws HeadlessException {
		super("Conway's Game of Life");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private Executor executor = Executors.newSingleThreadExecutor();
	private GamePanel gamePanel;
	private SettingsPanel settingsPanel;
}
