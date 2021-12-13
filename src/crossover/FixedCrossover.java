package crossover;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;

// TODO: Auto-generated Javadoc
/**
 * The Class FixedCrossover.
 */
public class FixedCrossover extends Crossover {

	/** The rand. */
	Random rand = new Random();

	/**
	 * Half crossover.
	 *
	 * @param A the a
	 * @param B the b
	 * @return the organism
	 */
	private Organism half_crossover(Organism A, Organism B) {
		int crossover = A.getOrgSize() / 2;
		return Crossover.concatenate_vectors(A, B, crossover);
	}

	/**
	 * Fixed point crossover.
	 *
	 * @param A               the a
	 * @param B               the b
	 * @param crossover_point the crossover point
	 * @return the organism
	 */
	private Organism fixed_point_crossover(Organism A, Organism B, int crossover_point) {
		return Crossover.concatenate_vectors(A, B, crossover_point);
	}

	/**
	 * Gets the rand from list.
	 *
	 * @param tmp the tmp
	 * @return the rand from list
	 */
	private Organism get_rand_from_list(ArrayList<Organism> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}

	/**
	 * Gets the crosses from list.
	 *
	 * @param in_tmp the in tmp
	 * @return the crosses from list
	 */
	private Organism get_crosses_from_list(ArrayList<Organism> in_tmp) {
		return half_crossover(get_rand_from_list(in_tmp), get_rand_from_list(in_tmp));
	}

	/**
	 * Do crossover.
	 *
	 * @param pop the pop
	 */
	// Assumes Population is partially nulled from selection process.
	@Override
	public void doCrossover(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		// populate with additional N crosses
		// if select returns 10, add 10 xover
		for (int i = 0; i < (orig_clone.size() / 2); i++) {
			if (list_in.get(i) == null) {
				list_in.set(i, get_crosses_from_list(orig_clone));
			}
		}
	}

}
