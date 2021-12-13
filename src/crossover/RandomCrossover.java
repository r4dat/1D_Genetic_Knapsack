package crossover;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;


/**
 * The Class RandomCrossover.
 */
public class RandomCrossover extends Crossover {

	private Random rand = new Random();

	/**
	 * Random crossover of two organisms.
	 *
	 * @param A the first Organism
	 * @param B the second Organism
	 * @return the new organism
	 */
	private Organism random_crossover(Organism A, Organism B) {
		int rand_crossover_point = rand.nextInt(A.getOrgSize());
		return Crossover.concatenate_vectors(A, B, rand_crossover_point);
	}

	/**
	 * Gets a random entry in ArrrayList.
	 *
	 * @param tmp the incoming ArrayList.
	 * @return a random entry in the list.
	 */
	private Organism get_rand_from_list(ArrayList<Organism> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}

	/**
	 * Gets the crosses from list.
	 *
	 * @param in_tmp the incoming ArrayList
	 * @return the crossover from 2 random entries in ArrayList.
	 */
	private Organism get_crosses_from_list(ArrayList<Organism> in_tmp) {
		return random_crossover(get_rand_from_list(in_tmp), get_rand_from_list(in_tmp));
	}

	/**
	 * Do crossover.
	 * Implements abstract crossover method.
	 * @param pop the Population.
	 */
	// Assumes Population is partially nulled from selection process.
	@Override
	public void doCrossover(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		// have to trim because default internals to next nearest power of 2.
		orig_clone.trimToSize();
		for (int i = 0; i < (list_in.size() / 2); i++) {
			if (list_in.get(i) == null) {
				list_in.set(i, get_crosses_from_list(orig_clone));
			}
		}
	}

}
