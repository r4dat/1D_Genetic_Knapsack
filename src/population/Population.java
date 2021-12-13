package population;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack_config.Binary_knapsack_configuration;
import organism.Organism;

/**
 * The Class Population.
 * Holds all organisms for genetic processing.
 */
public class Population {

	/** The unique instance. */
	private volatile static Population uniqueInstance;

	/** The default population size. */
	private int pop_size = Binary_knapsack_configuration.DEFAULT_POPULATION_SIZE;

	/** The number items that may be added to knapsack */
	private int number_items = Binary_knapsack_configuration.WEIGHTS.length;

	/** The internal Population representation. */
	private ArrayList<Organism> pop = null;

	/** The most recent max score for the Population */
	private double most_recent_max_score = 0;

	/** The best organism in the Population. 
	 * Initially set to a random Organism just so 
	 * it's not null.*/
	private Organism best_org = new Organism(number_items);

	/**
	 * Instantiates a new population.
	 * Generates initial population.
	 */
	private Population() {
		// Using generic for internal ArrayList
		// make sure nothing odd sneaks in.
		pop = new ArrayList<Organism>(pop_size);
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
					Organism o = new Organism(number_items);
					pop.set(i, o);
				}
			} else {
				// fill Pop during initial creation.
				pop.add(new Organism(number_items));
			}
		}
		// force fitness evaluation when new organisms are added. 
		this.evaluateFitness();
	}

	/**
	 * Gets the single instance of Population.
	 * Doubly checked locking implementation 
	 * Singleton because populations are closed and non-interacting. 
	 * This way we can run a population on n cores (or machines). 
	 * @return single instance of Population
	 */
	public static Population getInstance() {
		if (uniqueInstance == null) {
			synchronized (Population.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Population();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * Gets the Population.
	 *
	 * @return the pop
	 */
	public ArrayList<Organism> getPop() {
		return pop;
	}

	/**
	 * Population level evaluate fitness.
	 * In some instances we want to re-evaluate all organisms 
	 * due to in-place modifications.
	 * Set's Pop level best organism and max score.
	 * @return the maximum score from current population.
	 */
	public double evaluateFitness() {
		for (Organism org : pop) {
			org.evaluateFitness();
			if (org.getFitness() > most_recent_max_score) {
				most_recent_max_score = org.getFitness();
				best_org = (Organism) org.clone();
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
	public ArrayList<Organism> deepCopyDense() {
		ArrayList<Organism> orig_clone = new ArrayList<Organism>();
		Iterator<Organism> iterator = pop.iterator();
		while (iterator.hasNext()) {
			Organism tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((Organism) tmp.clone());
			}
		}
		return orig_clone;
	}

	/**
	 * A deep, sparse copy. 
	 * In some cases we want a deep copy, not references. 
	 * Some operations null out ArrayList entries, in this case we keep them.
	 * E.g. [0,1,null].deepCopyDense returns [0,1,null].
	 * @return the array list
	 */
	public ArrayList<Organism> deepCopySparse() {
		ArrayList<Organism> orig_clone = new ArrayList<Organism>();
		Iterator<Organism> iterator = pop.iterator();
		while (iterator.hasNext()) {
			Organism tmp = (iterator.next());
			if (tmp != null) {
				orig_clone.add((Organism) tmp.clone());
			}
			if (tmp == null) {
				orig_clone.add(null);
			}
		}
		return orig_clone;
	}
}
