package evolution_factory;

import crossover.Crossover;
import crossover.FixedCrossover;
import mutation.FixedMutation;
import mutation.Mutation;


/**
 * A factory for creating deterministic crossover and mutation 
 * objects.
 */
public class DeterministicEvoFactory extends EvolutionFactory {

	/**
	 * Gets the Factory crossover.
	 *
	 * @return the crossover
	 */
	@Override
	public Crossover getCrossover() {
		return new FixedCrossover();
	}

	/**
	 * Gets the Factory mutation.
	 *
	 * @return the mutation
	 */
	@Override
	public Mutation getMutation() {
		return new FixedMutation();
	}
}
