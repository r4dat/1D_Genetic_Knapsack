package ga_knapsack_runner;
import knapsack_config.Binary_knapsack_configuration;
import population.Population;
import evolution_factory.*;
import selection.*;
import mutation.*;
import selection.*;
import crossover.*;
import population.Population;



public class Ga_knapsack_runner {

	public static void main(String[] args) {
	
	// Use abstract factory
	EvolutionFactory EvolutionSystem = new DeterministicEvoFactory();

	//EvolutionFactory EvolutionSystem = new RandomEvoFactory();
	
	Mutation mutate = EvolutionSystem.getMutation(); 
	Crossover crossover = EvolutionSystem.getCrossover();
	
	// Utilize strategy method for selection determination.
	SelectionStrategy TournStrat = new TournamentSelection();
	SelectionStrategy EliteStrat = new EliteSelection();	
	SelectionStrategy ActiveStrategy = TournStrat;
	
	Population bigPop = Population.getInstance();
	
	System.out.println("Initial Population Fitness:"+bigPop.evaluateFitness());
	
	double fit_track=0;
	int epoch_limit = 10000;
	for(int epoch=0;epoch<epoch_limit;epoch++) {
		ActiveStrategy.doSelect(bigPop);
		crossover.doCrossover(bigPop);
		mutate.doMutation(bigPop);
		double max_fit = bigPop.evaluateFitness();
		if(fit_track!=max_fit) {
		System.out.println("Epoch: "+epoch+" max fitness: "+max_fit+ " Percent max: "+(max_fit/7534.0));
		fit_track=max_fit;
		}
		if(epoch==epoch_limit/2) {ActiveStrategy=EliteStrat;}
	}
	System.out.println("Complete");
	}

}
