package mutation;

import population.Population;


/**
 * The Mutation interface.
 * All implementors must have doMutation method.
 */
public interface Mutation {

	/**
	 * Abstracted mutation.
	 *
	 * @param pop the Population
	 */
	public abstract void doMutation(Population pop);
}
