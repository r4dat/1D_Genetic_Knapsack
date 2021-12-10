package crossover;

import population.Population;
import java.util.Random;
import organism.Organism;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import evolution_factory.*;
import java.util.Random;

public class FixedCrossover extends Crossover {
	Random rand = new Random();
	
	private Organism half_crossover(Organism A, Organism B) {
		int crossover = A.getOrgSize()/2;
		return Crossover.concatenate_vectors(A,B,crossover);
	}
	
	private Organism fixed_point_crossover(Organism A, Organism B,int crossover_point) {
		return Crossover.concatenate_vectors(A,B,crossover_point);
	}
	
	private Organism get_rand_from_list(ArrayList<Organism> tmp) {
		return tmp.get(rand.nextInt(tmp.size()));
	}
	
	private Organism get_crosses_from_list(ArrayList<Organism> in_tmp) {
		return half_crossover(get_rand_from_list(in_tmp),get_rand_from_list(in_tmp));
	}
	
	// Assumes Population is partially nulled from selection process. 
	@Override
	public void doCrossover(Population pop) {
		ArrayList<Organism> list_in = pop.getPop();
		ArrayList<Organism> orig_clone = pop.deepCopyDense();
		orig_clone.trimToSize();
		// populate with additional N crosses
		// if select returns 10, add 10 xover
		for(int i=0; i<(orig_clone.size()/2);i++) {
			if(list_in.get(i)==null) {
				list_in.set(i, get_crosses_from_list(orig_clone));
			}
		}
	}

}
