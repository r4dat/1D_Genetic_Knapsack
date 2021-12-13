package crossover;

import java.util.BitSet;

import organism.Organism;
import population.Population;

/**
 * The Class Crossover.
 * Abstract parent of crossover variations. 
 * Contains common method to crossover Organisms.
 */
public abstract class Crossover {

	/**
	 * Do crossover.
	 * Crossover subclasses must implement doCrossover.
	 * @param pop the Population
	 */
	public abstract void doCrossover(Population pop);


	/**
	 * Concatenate vectors.
	 * Common method needed by all crossover classes.
	 * @param vector_1_in the first Organism
	 * @param vector_2_in the second Organism
	 * @param lead_length - how much to keep of vector_1 after crossover.
	 * @return the new organism that is a crossover of Org_1 and Org_2.
	 */
	// Thanks SO
	// https://stackoverflow.com/questions/10495953/java-bitset-which-allows-easy-concatenation-of-bitsets
	protected static Organism concatenate_vectors(Organism vector_1_in, Organism vector_2_in, int lead_length) {
		BitSet vector_1_in_clone = (BitSet) vector_1_in.clone();
		BitSet vector_2_in_clone = (BitSet) vector_2_in.clone();
		int n = lead_length;// _desired length of the first (leading) vector
		int index = -1;
		while (index < (vector_2_in_clone.length() - 1)) {
			index = vector_2_in_clone.nextSetBit((index + 1));
			vector_1_in_clone.set((index + n));
		}
		return (Organism) vector_1_in_clone;
	}
}
