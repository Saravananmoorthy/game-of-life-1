package pl.cba.reallygrid.gameoflife.gui;

import pl.cba.reallygrid.gameoflife.model.ModelApi;
import pl.cba.reallygrid.gameoflife.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class SettingsPanel extends JPanel implements Observer {
	public SettingsPanel() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		factorSpi = new JSpinner(new SpinnerNumberModel(20, 0, 100, 1));
		add(new JLabel("Factor:"), new GBC(0, 0).insets(0, 0, 5, 0));
		add(factorSpi, new GBC(1, 0).insets(0, 5, 5, 0).anchor(EAST));
		generateBtn = new JButton("Generate life");
		add(generateBtn, new GBC(0, 1, 2, 1).insets(0, 0, 5, 0));
		startBtn = new JButton("Start");
		add(startBtn, new GBC(0, 2, 2, 1).fill(HORIZONTAL).insets(0, 0, 5, 0));

		add(new JLabel("Alive:"), new GBC(0, 3));
		aliveLbl = new JLabel("0");
		add(aliveLbl, new GBC(1, 3).anchor(EAST));
	}

	public void addGenerateBtnListener(ActionListener listener) {
		generateBtn.addActionListener(listener);
	}

	public void addStartBtnListener(ActionListener listener) {
		startBtn.addActionListener(listener);
	}

	public int getFactorValue() {
		return (int)factorSpi.getValue();
	}

	@Override
	public void update(ModelApi model) {
		int alive = model.getAlive();
		aliveLbl.setText(Integer.toString(alive));
	}

	private JSpinner factorSpi;
	private JButton generateBtn;
	private AbstractButton startBtn;
	private JLabel aliveLbl;
}
