package crossover;

import java.util.ArrayList;
import java.util.Random;

import organism.OrganismBit;
import population.PopulationBit;


/**
 * The Class FixedCrossover.
 */
public class FixedCrossover extends Crossover {


	private Random rand = new Random();

	/**
	 * Half crossover.
	 * Create a crossover that is always 1/2 A + 1/2 B.
	 * @param A the first OrganismBit
	 * @param B the second OrganismBit
	 * @return the new organism
	 */
	private OrganismBit half_crossover(OrganismBit A, OrganismBit B) {
		int crossover = A.getOrgSize() / 2;
		return Crossover.concatenate_vectors(A, B, crossover);
	}

	/**
	 * Fixed point crossover.
	 * Crossover A and B with a specified length A.
	 * @param A the first OrganismBit
	 * @param B the second OrganismBit
	 * @param crossover_point the fixed crossover point
	 * @return the new organism
	 */
	private OrganismBit fixed_point_crossover(OrganismBit A, OrganismBit B, int crossover_point) {
		return Crossover.concatenate_vectors(A, B, crossover_point);
	}

	/**
	 * Gets the fixed crossover from 2 random entries.
	 *
	 * @param in_tmp the incoming ArrayList
	 * @return the new OrganismBit (crossed from 2 random entries.)
	 */
	private OrganismBit get_crosses_from_list(ArrayList<OrganismBit> in_tmp) {
		return half_crossover(Crossover.get_rand_from_list(in_tmp), get_rand_from_list(in_tmp));
	}

	/**
	 * Do crossover.
	 * Implementation of crossover.
	 * @param pop the PopulationBit
	 */
	// Assumes PopulationBit is partially nulled from selection process.
	@Override
	public void doCrossover(PopulationBit pop) {
		ArrayList<OrganismBit> list_in = pop.getPop();
		ArrayList<OrganismBit> orig_clone = pop.deepCopyDense();
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
