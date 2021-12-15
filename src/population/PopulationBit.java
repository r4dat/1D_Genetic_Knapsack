package population;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack_config.Binary_knapsack_configuration;
import organism.*;

/**
 * The Class PopulationBit.
 * Holds all organisms for genetic processing.
 */
public class PopulationBit {

	/** The unique instance. */
	private volatile static PopulationBit uniqueInstance;

	/** The default population size. */
	private int pop_size = Binary_knapsack_configuration.DEFAULT_POPULATION_SIZE;

	/** The number items that may be added to knapsack */
	private int number_items = Binary_knapsack_configuration.WEIGHTS.length;

	/** The internal PopulationBit representation. */
	private ArrayList<OrganismBit> pop = null;

	/** The most recent max score for the PopulationBit */
	private double most_recent_max_score = 0;

	/** The best organism in the PopulationBit. 
	 * Initially set to a random OrganismBit just so 
	 * it's not null.*/
	private OrganismBit best_org = new OrganismBit(number_items);

	/**
	 * Instantiates a new population.
	 * Generates initial population.
	 */
	private PopulationBit() {
		// Ideally we'd do have organism related OOP for 
		// Bit vs character genes. Then we could do ArrayList<T extends OrganismBit> here.
		pop = new ArrayList<OrganismBit>(pop_size);
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
					OrganismBit o = new OrganismBit(number_items);
					pop.set(i, o);
				}
			} else {
				// fill Pop during initial creation.
				pop.add(new OrganismBit(number_items));
			}
		}
		// force fitness evaluation when new organisms are added. 
		this.evaluateFitness();
	}

	/**
	 * Gets the single instance of PopulationBit.
	 * Doubly checked locking implementation 
	 * Singleton because populations are closed and non-interacting. 
	 * This way we can run a population on n cores (or machines). 
	 * @return single instance of PopulationBit
	 */
	public static PopulationBit getInstance() {
		if (uniqueInstance == null) {
			synchronized (PopulationBit.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PopulationBit();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * Gets the PopulationBit.
	 *
	 * @return the pop
	 */
	public ArrayList<OrganismBit> getPop() {
		return pop;
	}

	/**
	 * PopulationBit level evaluate fitness.
	 * In some instances we want to re-evaluate all organisms 
	 * due to in-place modifications.
	 * Set's Pop level best organism and max score.
	 * @return the maximum score from current population.
	 */
	public double evaluateFitness() {
		for (OrganismBit org : pop) {
			org.evaluateFitness();
			if (org.getFitness() > most_recent_max_score) {
				most_recent_max_score = org.getFitness();
				best_org = (OrganismBit) org.clone();
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
	public ArrayList<OrganismBit> deepCopyDense() {
		ArrayList<OrganismBit> orig_clone = new ArrayList<OrganismBit>();
		Iterator<OrganismBit> iterator = pop.iterator();
		while (iterator.hasNext()) {
			OrganismBit tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((OrganismBit) tmp.clone());
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
	public ArrayList<OrganismBit> deepCopySparse() {
		ArrayList<OrganismBit> orig_clone = new ArrayList<OrganismBit>();
		Iterator<OrganismBit> iterator = pop.iterator();
		while (iterator.hasNext()) {
			OrganismBit tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((OrganismBit) tmp.clone());
			}
			if (tmp == null) {
				orig_clone.add(null);
			}
		}
		return orig_clone;
	}
}
