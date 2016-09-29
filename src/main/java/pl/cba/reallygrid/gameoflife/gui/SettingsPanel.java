package pl.cba.reallygrid.gameoflife.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.EAST;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class SettingsPanel extends JPanel {
	public SettingsPanel() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		factorSpi = new JSpinner(new SpinnerNumberModel(20, 0, 100, 1));
		generateBtn = new JButton("Generate life");
		add(new JLabel("Factor:"), new GBC(0, 0).insets(0, 0, 5, 0));
		add(factorSpi, new GBC(1, 0).insets(0, 5, 5, 0).anchor(EAST));
		add(generateBtn, new GBC(0, 1, 2, 1));
	}

	public void addGenerateBtnListener(ActionListener listener) {
		generateBtn.addActionListener(listener);
	}

	public int getFactorValue() {
		return (int)factorSpi.getValue();
	}

	private JSpinner factorSpi;
	private JButton generateBtn;
}
