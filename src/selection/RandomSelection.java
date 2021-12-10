package selection;
import java.util.ArrayList;
import java.util.Random;
import organism.Organism;
import population.Population;

public class RandomSelection implements SelectionStrategy {
	Random rand = new Random();
	//random selection, non-selected are null
	public void doSelect(Population pop){
		ArrayList<Organism> list_in = pop.getPop();
		for(int i=0;i<list_in.size();i++) {
			if(rand.nextBoolean()) {
				list_in.set(i, null);
			}

	}
	}
}

