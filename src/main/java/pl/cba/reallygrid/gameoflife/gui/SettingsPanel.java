package pl.cba.reallygrid.gameoflife.gui;

import pl.cba.reallygrid.gameoflife.model.ModelApi;
import pl.cba.reallygrid.gameoflife.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
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
		add(new JLabel("Factor:"), new GBC(0, 0).insets(0, 0, 5, 0));
		add(factorSpi, new GBC(1, 0).insets(0, 5, 5, 0).anchor(EAST));
		add(generateBtn, new GBC(0, 1, 2, 1).insets(0, 0, 5, 0));
		add(clearBtn, new GBC(0, 2, 2, 1).fill(HORIZONTAL).insets(0, 0, 5, 0));
		add(startBtn, new GBC(0, 3, 2, 1).fill(HORIZONTAL).insets(0, 0, 5, 0));
		add(stopBtn, new GBC(0, 4, 2, 1).fill(HORIZONTAL).insets(0, 0, 5, 0));
		sizeSld.setPreferredSize(new Dimension(20, 43));
		sizeSld.setMajorTickSpacing(8);
		sizeSld.setMinorTickSpacing(2);
		sizeSld.setPaintTicks(true);
		sizeSld.setPaintLabels(true);
		sizeSld.setSnapToTicks(true);
		add(sizeSld, new GBC(0, 5, 2, 1).fill(HORIZONTAL).insets(0, 0, 5, 0));
		add(new JLabel("Alive:"), new GBC(0, 6));
		add(aliveLbl, new GBC(1, 6).anchor(EAST));
	}

	public void addGenerateBtnListener(ActionListener listener) {
		generateBtn.addActionListener(listener);
	}

	public void addClearBtnListener(ActionListener listener) {
		clearBtn.addActionListener(listener);
	}

	public void addStartBtnListener(ActionListener listener) {
		startBtn.addActionListener(listener);
	}

	public void addStopBtnListener(ActionListener listener) {
		stopBtn.addActionListener(listener);
	}

	public void addSizeSldListener(ChangeListener listener) {
		sizeSld.addChangeListener(listener);
	}

	public int getFactorValue() {
		return (int)factorSpi.getValue();
	}

	@Override
	public void update(ModelApi model) {
		int alive = model.getAlive();
		aliveLbl.setText(Integer.toString(alive));
	}

	private JSpinner factorSpi = new JSpinner(new SpinnerNumberModel(20, 0, 100, 1));
	private JButton generateBtn = new JButton("Generate life");
	private JButton clearBtn = new JButton("Clear");
	private AbstractButton startBtn = new JButton("Start");  //todo może JToggleButton zamiast JButton? I wtedy bez stopBtn
	private JButton stopBtn = new JButton("Stop");
	private JLabel aliveLbl = new JLabel("0");
	private JSlider sizeSld = new JSlider(4, 20, GamePanel.CELL_SIZE);
}
