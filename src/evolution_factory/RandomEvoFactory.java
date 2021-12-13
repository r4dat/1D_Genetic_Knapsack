package evolution_factory;

import crossover.Crossover;
import crossover.RandomCrossover;
import mutation.Mutation;
import mutation.RandomMutation;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating RandomEvo objects.
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
