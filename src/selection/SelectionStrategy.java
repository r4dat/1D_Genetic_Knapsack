package selection;

import population.Population;


/**
 * The Interface SelectionStrategy.
 * All strategies but implement doSelect.
 */
public interface SelectionStrategy {

	/**
	 * Do select.
	 *
	 * @param pop the Population.
	 */
	void doSelect(Population pop);
}
