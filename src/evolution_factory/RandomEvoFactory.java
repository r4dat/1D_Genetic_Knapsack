package evolution_factory;

import crossover.Crossover;
import crossover.RandomCrossover;
import mutation.Mutation;
import mutation.RandomMutation;

/**
 * A factory for creating Random crossover and mutate
 *  objects.
 */
public class RandomEvoFactory extends EvolutionFactory {

	/**
	 * Gets the crossover.
	 *
	 * @return the crossover
	 */
	@Override
	public Crossover getCrossover() {
		return new RandomCrossover();
	}

	/**
	 * Gets the mutation.
	 *
	 * @return the mutation
	 */
	@Override
	public Mutation getMutation() {
		return new RandomMutation();
	}
}
