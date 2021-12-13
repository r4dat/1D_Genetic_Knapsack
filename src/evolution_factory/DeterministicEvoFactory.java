package evolution_factory;

import crossover.Crossover;
import crossover.FixedCrossover;
import mutation.FixedMutation;
import mutation.Mutation;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DeterministicEvo objects.
 */
public class DeterministicEvoFactory extends EvolutionFactory {

	/**
	 * Gets the crossover.
	 *
	 * @return the crossover
	 */
	@Override
	public Crossover getCrossover() {
		return new FixedCrossover();
	}

	/**
	 * Gets the mutation.
	 *
	 * @return the mutation
	 */
	@Override
	public Mutation getMutation() {
		return new FixedMutation();
	}
}
