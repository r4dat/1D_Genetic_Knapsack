package selection;

import population.PopulationBit;


/**
 * The Interface SelectionStrategy.
 * All strategies but implement doSelect.
 */
public interface SelectionStrategy {

	/**
	 * Do select.
	 *
	 * @param pop the PopulationBit.
	 */
	void doSelect(PopulationBit pop);
}
