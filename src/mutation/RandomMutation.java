package mutation;

import java.util.ArrayList;
import java.util.Random;

import organism.OrganismBit;
import population.Population;

/**
 * The Class RandomMutation.
 * Implements fully random mutation methods.
 */
public class RandomMutation implements Mutation {

	private Random rand = new Random();

	/**
	 * In-place: Mutate a single bit/gene with random probability. 
	 * @param o the organism
	 * @return the mutated organism
	 */
	// mutate with random P for every call
	private OrganismBit mutate_bit_with_random_probability(OrganismBit o) {
		int percent = rand.nextInt(100);
		for (int i = 0; i < o.getOrgSize(); i++) {
			if (rand.nextInt(100) <= percent) {
				o.flip(i);
			}
		}
		return (OrganismBit) o.clone();
	}

	/**
	 * Implement the abstract mutation.
	 *
	 * @param pop the Population
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
				list_in.set(i, (mutate_bit_with_random_probability(o)));
			}
		}
	}
}
