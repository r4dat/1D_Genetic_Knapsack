package population;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack_config.Binary_knapsack_configuration;
import organism.Organism;

// TODO: Auto-generated Javadoc
/**
 * The Class Population.
 */
public class Population {

	/** The unique instance. */
	private volatile static Population uniqueInstance;

	/** The pop size. */
	private int pop_size = Binary_knapsack_configuration.DEFAULT_POPULATION_SIZE;

	/** The number items. */
	private int number_items = Binary_knapsack_configuration.WEIGHTS.length;

	/** The pop. */
	private ArrayList<Organism> pop = null;

	/** The most recent max score. */
	private double most_recent_max_score = 0;

	/** The best org. */
	private Organism best_org = null;

	/**
	 * Instantiates a new population.
	 */
	private Population() {
		// Only generate population once so sticking in constructor vs a method.
		pop = new ArrayList<Organism>(pop_size);
		fillPop();
	}

	/**
	 * Fill pop.
	 */
	public void fillPop() {
		// fill Pop when nulled after selection/
		for (int i = 0; i < pop_size; i++) {
			if (pop.size() == pop_size) {
				if (pop.get(i) == null) {
					Organism o = new Organism(number_items);
					pop.set(i, o);
				}
			} else {
				pop.add(new Organism(number_items));
			}
		}
		this.evaluateFitness();
	}

// Singleton because populations are closed and non-interacting. 
// This way we can run a population on n cores (or machines). 
	/**
	 * Gets the single instance of Population.
	 *
	 * @return single instance of Population
	 */
// Benefit of distributed compute and pick a best option.
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
	 * Gets the pop.
	 *
	 * @return the pop
	 */
	public ArrayList<Organism> getPop() {
		return pop;
	}

	/**
	 * Evaluate fitness.
	 *
	 * @return the double
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
	 * Deep copy dense.
	 *
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
	 * Deep copy sparse.
	 *
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
