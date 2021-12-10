package evolution_factory;
import crossover.Crossover;
import mutation.Mutation;
import population.Population;

public abstract class EvolutionFactory {
	
	public abstract Crossover getCrossover();
	public abstract Mutation getMutation();

	
}
