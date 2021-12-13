package mutation;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomMutation.
 */
public class RandomMutation implements Mutation {

	/** The rand. */
	Random rand = new Random();

	/**
	 * Mutate bit with random probability.
	 *
	 * @param o the o
	 * @return the organism
	 */
	// mutate with random P for every call
	private Organism mutate_bit_with_random_probability(Organism o) {
		int percent = rand.nextInt(100);
		for (int i = 0; i < o.getOrgSize(); i++) {
			if (rand.nextInt(100) <= percent) {
				o.flip(i);
			}
		}
		return (Organism) o.clone();
	}

	/**
	 * Do mutation.
	 *
	 * @param pop the pop
	 */
	// Assumes Population is partially nulled from selection process.
	@Override
	public void doMutation(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		for (int i = 0; i < list_in.size(); i++) {
			if (list_in.get(i) == null) {
				Organism o = orig_clone.get(rand.nextInt(orig_clone.size()));
				list_in.set(i, (mutate_bit_with_random_probability(o)));
			}
		}
	}
}
