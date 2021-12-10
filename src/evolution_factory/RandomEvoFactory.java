package evolution_factory;
import mutation.Mutation;
import mutation.RandomMutation;
import crossover.Crossover;
import crossover.RandomCrossover;

public class RandomEvoFactory extends EvolutionFactory {

	@Override
	public Crossover getCrossover() {
		return new RandomCrossover();
	}
	@Override
	public Mutation getMutation() {
		return new RandomMutation();
	}
}
