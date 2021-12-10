package selection;
import java.util.ArrayList;
import java.util.Collections;
import organism.Organism;
import population.Population;

public class EliteSelection implements SelectionStrategy {
	//best n selection: pick top 10%
	public void doSelect(Population pop){
		int n = (pop.getPop().size()/10);
		ArrayList<Organism> list_in = pop.getPop();
		Collections.sort(list_in); // sorts ascending
		int lsize = list_in.size();
		for(int i = 0; i<(lsize-n); i++) {
			list_in.set(i, null);
			}
	}
}

