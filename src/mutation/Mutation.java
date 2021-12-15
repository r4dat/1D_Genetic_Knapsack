package mutation;

import population.PopulationBit;


/**
 * The Mutation interface.
 * All implementors must have doMutation method.
 */
public interface Mutation {

	/**
	 * Abstracted mutation.
	 *
	 * @param pop the PopulationBit
	 */
	public abstract void doMutation(PopulationBit pop);
}
