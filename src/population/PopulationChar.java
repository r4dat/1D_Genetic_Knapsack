package population;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack_config.Binary_knapsack_configuration;
import organism.*;

/**
 * The Class PopulationChar.
 * Holds all organisms with character genes for genetic processing.
 */
public class PopulationChar{

	/** The unique instance. */
	private volatile static PopulationChar uniqueInstance;

	/** The default population size. */
	private int pop_size = Binary_knapsack_configuration.DEFAULT_POPULATION_SIZE;

	ArrayList<OrganismChar> pop;
	
	/** The number items that may be added to knapsack */
	private int number_items = Binary_knapsack_configuration.WEIGHTS.length;

	/** The most recent max score for the PopulationChar */
	private double most_recent_max_score = 0;

	/** The best organism in the PopulationChar. 
	 * Initially set to a random OrganismChar just so 
	 * it's not null.*/
	private OrganismChar best_org = new OrganismChar(number_items);

	/**
	 * Instantiates a new population.
	 * Generates initial population.
	 */
	private PopulationChar() {
		// Ideally we'd do have organism related OOP for 
		// Char vs character genes. Then we could do ArrayList<T extends OrganismChar> here.
		pop = new ArrayList<OrganismChar>(pop_size);
		fillPop();
	}

	/**
	 * Generate populations.
	 */
	public void fillPop() {
		// fill Pop when nulled after selection.
		for (int i = 0; i < pop_size; i++) {
			if (pop.size() == pop_size) {
				if (pop.get(i) == null) {
					OrganismChar o = new OrganismChar(number_items);
					pop.set(i, o);
				}
			} else {
				// fill Pop during initial creation.
				pop.add(new OrganismChar(number_items));
			}
		}
		// force fitness evaluation when new organisms are added. 
		this.evaluateFitness();
	}

	/**
	 * Gets the single instance of PopulationChar.
	 * Doubly checked locking implementation 
	 * Singleton because populations are closed and non-interacting. 
	 * This way we can run a population on n cores (or machines). 
	 * @return single instance of PopulationChar
	 */
	public static PopulationChar getInstance() {
		if (uniqueInstance == null) {
			synchronized (PopulationChar.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PopulationChar();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * Gets the PopulationChar.
	 *
	 * @return the pop
	 */
	public ArrayList<OrganismChar> getPop() {
		return pop;
	}

	/**
	 * PopulationChar level evaluate fitness.
	 * In some instances we want to re-evaluate all organisms 
	 * due to in-place modifications.
	 * Set's Pop level best organism and max score.
	 * @return the maximum score from current population.
	 */
	public double evaluateFitness() {
		for (OrganismChar org : pop) {
			org.evaluateFitness();
			if (org.getFitness() > most_recent_max_score) {
				most_recent_max_score = org.getFitness();
				best_org = (OrganismChar) org.clone();
			}
		}
		return most_recent_max_score;
	}

	/**
	 * A deep, dense copy. 
	 * In some cases we want a deep copy, not references. 
	 * Some operations null out ArrayList entries, in this case we drop them.
	 * E.g. [0,1,null].deepCopyDense returns [0,1].
	 * @return the array list
	 */
	public ArrayList<OrganismChar> deepCopyDense() {
		ArrayList<OrganismChar> orig_clone = new ArrayList<OrganismChar>();
		Iterator<OrganismChar> iterator = pop.iterator();
		while (iterator.hasNext()) {
			OrganismChar tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((OrganismChar) tmp.clone());
			}
		}
		return orig_clone;
	}

	/**
	 * A deep, sparse copy. 
	 * In some cases we want a deep copy, not references. 
	 * Some operations null out ArrayList entries, in this case we keep them.
	 * E.g. [0,1,null].deepCopySparse returns [0,1,null].
	 * @return the array list
	 */
	public ArrayList<OrganismChar> deepCopySparse() {
		ArrayList<OrganismChar> orig_clone = new ArrayList<OrganismChar>();
		Iterator<OrganismChar> iterator = pop.iterator();
		while (iterator.hasNext()) {
			OrganismChar tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((OrganismChar) tmp.clone());
			}
			if (tmp == null) {
				orig_clone.add(null);
			}
		}
		return orig_clone;
	}
}
