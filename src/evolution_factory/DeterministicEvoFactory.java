package evolution_factory;
import mutation.*;
import crossover.*;

public class DeterministicEvoFactory extends EvolutionFactory {
	
	@Override
	public Crossover getCrossover() {
		return new FixedCrossover();
	}
	@Override
	public Mutation getMutation() {
		return new FixedMutation();
	}
}
