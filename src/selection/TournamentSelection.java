package selection;

import java.util.ArrayList;
import java.util.Random;

import organism.Organism;
import population.Population;

// TODO: Auto-generated Javadoc
/**
 * The Class TournamentSelection.
 */
public class TournamentSelection implements SelectionStrategy {

	/** The rand. */
	// tournament selection match any random 2, winner, null unused.
	Random rand = new Random();

	/**
	 * Do select.
	 *
	 * @param pop the pop
	 */
	public void doSelect(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = new ArrayList<Organism>();
		orig_clone = pop.deepCopyDense();
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
