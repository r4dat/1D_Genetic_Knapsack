package ga_knapsack_runner;

import crossover.Crossover;
import evolution_factory.DeterministicEvoFactory;
import evolution_factory.EvolutionFactory;
import mutation.Mutation;
import population.Population;
import selection.EliteSelection;
import selection.SelectionStrategy;
import selection.TournamentSelection;

public class Ga_knapsack_runner {

	public static void main(String[] args) {

		// Use abstract factory
		EvolutionFactory EvolutionSystem = new DeterministicEvoFactory();

		// EvolutionFactory EvolutionSystem = new RandomEvoFactory();

		// Get mutation/crossover style from the factory.
		Mutation activeMutate = EvolutionSystem.getMutation();
		Crossover activeCrossover = EvolutionSystem.getCrossover();

		// Utilize strategy method for selection determination.
		SelectionStrategy TournStrat = new TournamentSelection();
		SelectionStrategy EliteStrat = new EliteSelection();
		SelectionStrategy activeSelection = TournStrat;
		
		// Create population (singleton).
		Population bigPop = Population.getInstance();

		System.out.println("Initial Population Fitness:" + bigPop.evaluateFitness());

		double fit_track = 0;
		int epoch_limit = 10000;
		for (int epoch = 0; epoch < epoch_limit; epoch++) {
			activeSelection.doSelect(bigPop);
			activeCrossover.doCrossover(bigPop);
			activeMutate.doMutation(bigPop);
			double max_fit = bigPop.evaluateFitness();
			if (fit_track != max_fit) {
				System.out.println(
						"Epoch: " + epoch + " max fitness: " + max_fit + " Percent max: " + (max_fit / 7534.0));
				fit_track = max_fit;
			}
			if (epoch == epoch_limit / 2) {
				// Switch to strategy focusing on 
				// existing organisms.
				activeSelection = EliteStrat;
			}
		}
		System.out.println("Complete");
	}

}
