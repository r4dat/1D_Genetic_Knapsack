package selection;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;


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
	 * @param pop the Population
	 */
	public void doSelect(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		int lsize = list_in.size();
		for (int i = 0; i < lsize / 2; i++) {
			Organism a = orig_clone.get(rand.nextInt(lsize));
			Organism b = orig_clone.get(rand.nextInt(lsize));
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
