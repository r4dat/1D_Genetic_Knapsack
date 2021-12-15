package selection;

import java.util.ArrayList;
import java.util.Random;

import organism.OrganismBit;
import population.Population;

/**
 * The Class RandomSelection.
 * Return random organisms from population.
 */
public class RandomSelection implements SelectionStrategy {


	private Random rand = new Random();

	/**
	 * Implements doSelect.
	 * Return random organism from population.
	 * @param pop the Population
	 */
	// random selection, non-selected are null
	public void doSelect(Population pop) {
		ArrayList<OrganismBit> list_in = pop.getPop();
		for (int i = 0; i < list_in.size(); i++) {
			if (rand.nextBoolean()) {
				list_in.set(i, null);
			}

		}
	}
}
