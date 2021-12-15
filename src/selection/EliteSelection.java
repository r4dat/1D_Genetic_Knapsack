package selection;

import java.util.ArrayList;
import java.util.Collections;

import organism.OrganismBit;
import population.Population;

// TODO: Auto-generated Javadoc
/**
 * The Class EliteSelection.
 * Keep the best 10% of Population.
 */
public class EliteSelection implements SelectionStrategy {

	/**
	 * Implement doSelect
	 * Keep the best 10% of the Population.
	 * Null the other organisms.
	 * @param pop the Population
	 */
	// best n selection: pick top 10%
	public void doSelect(Population pop) {
		int n = (pop.getPop().size() / 10);
		ArrayList<OrganismBit> list_in = pop.getPop();
		Collections.sort(list_in); // sorts ascending 0 lowest, size-1 highest.
		int lsize = list_in.size();
		for (int i = 0; i < (lsize - n); i++) {
			list_in.set(i, null);
		}
	}
}
