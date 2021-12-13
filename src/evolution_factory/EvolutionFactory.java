package evolution_factory;

import crossover.Crossover;
import mutation.Mutation;


/**
 * An abstract factory for 
 * creating Evolution objects.
 */
public abstract class EvolutionFactory {

	/**
	 * Abstracted gets the crossover.
	 *
	 * @return the crossover
	 */
	public abstract Crossover getCrossover();

	/**
	 * Abstracted gets the mutation.
	 *
	 * @return the mutation
	 */
	public abstract Mutation getMutation();

}
