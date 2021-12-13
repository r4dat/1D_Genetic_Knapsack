package crossover;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;


/**
 * The Class FixedCrossover.
 */
public class FixedCrossover extends Crossover {


	private Random rand = new Random();

	/**
	 * Half crossover.
	 * Create a crossover that is always 1/2 A + 1/2 B.
	 * @param A the first Organism
	 * @param B the second Organism
	 * @return the new organism
	 */
	private Organism half_crossover(Organism A, Organism B) {
		int crossover = A.getOrgSize() / 2;
		return Crossover.concatenate_vectors(A, B, crossover);
	}

	/**
	 * Fixed point crossover.
	 * Crossover A and B with a specified length A.
	 * @param A the first Organism
	 * @param B the second Organism
	 * @param crossover_point the fixed crossover point
	 * @return the new organism
	 */
	private Organism fixed_point_crossover(Organism A, Organism B, int crossover_point) {
		return Crossover.concatenate_vectors(A, B, crossover_point);
	}

	/**
	 * Gets a random Organism from the ArrayList.
	 *
	 * @param tmp the incoming ArrayList
	 * @return the random entry from ArrayList
	 */
	private Organism get_rand_from_list(ArrayList<Organism> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}

	/**
	 * Gets the fixed crossover from 2 random entries.
	 *
	 * @param in_tmp the incoming ArrayList
	 * @return the new Organism (crossed from 2 random entries.)
	 */
	private Organism get_crosses_from_list(ArrayList<Organism> in_tmp) {
		return half_crossover(get_rand_from_list(in_tmp), get_rand_from_list(in_tmp));
	}

	/**
	 * Do crossover.
	 * Implementation of crossover.
	 * @param pop the Population
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
