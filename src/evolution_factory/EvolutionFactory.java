package evolution_factory;

import crossover.Crossover;
import mutation.Mutation;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Evolution objects.
 */
public abstract class EvolutionFactory {

	/**
	 * Gets the crossover.
	 *
	 * @return the crossover
	 */
	public abstract Crossover getCrossover();

	/**
	 * Gets the mutation.
	 *
	 * @return the mutation
	 */
	public abstract Mutation getMutation();

}
