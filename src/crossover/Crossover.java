package crossover;

import java.util.ArrayList;
import java.util.BitSet;

import organism.*;
import population.Population;
import java.util.Random;

/**
 * The Class Crossover.
 * Abstract parent of crossover variations. 
 * Contains common method to crossover Organisms.
 */
public abstract class Crossover {
	private static Random rand = new Random();
	/**
	 * Do crossover.
	 * Crossover subclasses must implement doCrossover.
	 * @param pop the Population
	 */
	public abstract void doCrossover(Population pop);


	/**
	 * Concatenate vectors.
	 * Common method needed by all crossover classes.
	 * @param vector_1_in the first OrganismBit
	 * @param vector_2_in the second OrganismBit
	 * @param lead_length - how much to keep of vector_1 after crossover.
	 * @return the new organism that is a crossover of Org_1 and Org_2.
	 */
	// Thanks SO
	// https://stackoverflow.com/questions/10495953/java-bitset-which-allows-easy-concatenation-of-bitsets
	protected static OrganismBit concatenate_vectors(OrganismBit vector_1_in, OrganismBit vector_2_in, int lead_length) {
		BitSet vector_1_in_clone = (BitSet) vector_1_in.clone();
		BitSet vector_2_in_clone = (BitSet) vector_2_in.clone();
		int n = lead_length;// _desired length of the first (leading) vector
		int index = -1;
		while (index < (vector_2_in_clone.length() - 1)) {
			index = vector_2_in_clone.nextSetBit((index + 1));
			vector_1_in_clone.set((index + n));
		}
		return (OrganismBit) vector_1_in_clone;
	}
	
	/**
	 * Gets a random entry in ArrrayList.
	 *
	 * @param tmp the incoming ArrayList.
	 * @return a random entry in the list.
	 */
	protected static OrganismBit get_rand_from_list(ArrayList<OrganismBit> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}
	
	protected static OrganismChar concatenate_vectors(OrganismChar vector_1_in, OrganismChar vector_2_in, int lead_length) {
		//TODO implement character based crossover. 
		return new OrganismChar();
	}
	
	protected static Organism concatenate_vectors(Organism vector_1_in, Organism vector_2_in, int lead_length) {
		//TODO implement generic based crossover. 
		// Call Char or Bitset xover based on instanceof.
		OrganismBit Organism_out = new OrganismBit();
		return (Organism)Organism_out;
	}
}
