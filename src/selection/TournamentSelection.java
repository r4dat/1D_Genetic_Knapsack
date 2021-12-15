package selection;

import java.util.ArrayList;
import java.util.Random;

import organism.OrganismBit;
import population.PopulationBit;


/**
 * The Class TournamentSelection.
 * Selection strategy picking any 2 organisms and returning the best.
 */
public class TournamentSelection implements SelectionStrategy {

	// tournament selection match any random 2, winner, null unused.
	private Random rand = new Random();

	/**
	 * Implements the doSelect method.
	 * Tournament selection with random organisms.
	 * @param pop the PopulationBit
	 */
	public void doSelect(PopulationBit pop) {
		ArrayList<OrganismBit> list_in = pop.getPop();
		ArrayList<OrganismBit> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		int lsize = list_in.size();
		for (int i = 0; i < lsize / 2; i++) {
			OrganismBit a = orig_clone.get(rand.nextInt(lsize));
			OrganismBit b = orig_clone.get(rand.nextInt(lsize));
			if (a.compareTo(b) > 0) {
				list_in.set(i, a);
			} else {
				list_in.set(i, b);
			}
		}
		for (int i = lsize / 2; i < lsize; i++) {
			list_in.set(i, null);
		}
	}
}
