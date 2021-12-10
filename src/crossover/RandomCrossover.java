package crossover;

import population.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import organism.Organism;

public class RandomCrossover extends Crossover {
	Random rand = new Random();
	
	private Organism random_crossover(Organism A, Organism B) {
		int rand_crossover_point = rand.nextInt(A.getOrgSize());
		return Crossover.concatenate_vectors(A,B,rand_crossover_point);
	}
	private Organism get_rand_from_list(ArrayList<Organism> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}
	
	private Organism get_crosses_from_list(ArrayList<Organism> in_tmp) {
		return random_crossover(get_rand_from_list(in_tmp),get_rand_from_list(in_tmp));
	}
	
	// Assumes Population is partially nulled from selection process. 
	@Override
	public void doCrossover(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		for(int i=0; i<(list_in.size()/2);i++) {
			if(list_in.get(i)==null) {
				list_in.set(i, get_crosses_from_list(orig_clone));
			}
		}
	}

}
