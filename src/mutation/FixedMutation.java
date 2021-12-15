package mutation;

import java.util.ArrayList;
import java.util.Random;

import organism.OrganismBit;
import population.Population;

// TODO: Auto-generated Javadoc
/**
 * The Class FixedMutation.
 */
public class FixedMutation implements Mutation {

	/** The rand. */
	Random rand = new Random();

	/**
	 * In-place mutate bit with fixed probability.
	 *
	 * @param o       the incoming organism
	 * @param percent the integer percent
	 * @return the mutated organism
	 * @throws IllegalArgumentException the illegal argument exception for percentages <0, >100.
	 */
	// mutate with fixed probability P for every call
	private OrganismBit mutate_bit_with_fixed_probability(OrganismBit o, int percent) throws IllegalArgumentException {
		if ((percent < 0) | (percent > 100))
			throw new IllegalArgumentException(
					"Integer value represents percentage and" + " must be between 0 and 100");
		for (int i = 0; i < o.getOrgSize(); i++) {
			if (rand.nextInt(100) <= percent) {
				o.flip(i);
			}
		}
		return (OrganismBit) o.clone();
	}

	/**
	 * Do mutation.
	 *
	 * @param pop the pop
	 */
	// Assumes Population is partially nulled from selection process.
	@Override
	public void doMutation(Population pop) {
		ArrayList<OrganismBit> list_in = pop.getPop();
		ArrayList<OrganismBit> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		for (int i = 0; i < list_in.size(); i++) {
			if (list_in.get(i) == null) {
				OrganismBit o = orig_clone.get(rand.nextInt(orig_clone.size()));
				list_in.set(i, (mutate_bit_with_fixed_probability(o, 10)));
			}
		}
	}
}
