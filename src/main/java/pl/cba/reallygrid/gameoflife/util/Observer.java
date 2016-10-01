package pl.cba.reallygrid.gameoflife.util;

import pl.cba.reallygrid.gameoflife.model.ModelApi;

/**
 * Created by krzysztof on 2016-09-29.
 */
public interface Observer {
	void update(ModelApi model);
}
