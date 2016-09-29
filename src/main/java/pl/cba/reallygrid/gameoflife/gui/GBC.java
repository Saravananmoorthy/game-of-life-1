package pl.cba.reallygrid.gameoflife.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Created by krzysztof on 2016-09-29.
 */
public class GBC extends GridBagConstraints {
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	public GBC anchor(int anchor) {
		this.anchor = anchor;

		return this;
	}

	public GBC insets(int top, int left, int bottom, int right) {
		insets = new Insets(top, left, bottom, right);

		return this;
	}
}
