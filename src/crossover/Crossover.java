package crossover;
import population.Population;
import organism.Organism;
import java.util.BitSet;


public abstract class Crossover {

	public abstract void doCrossover(Population pop);
	
	// Thanks SO
	// https://stackoverflow.com/questions/10495953/java-bitset-which-allows-easy-concatenation-of-bitsets
	protected static Organism concatenate_vectors(Organism vector_1_in, Organism vector_2_in, int lead_length) {
		  BitSet vector_1_in_clone = (BitSet)vector_1_in.clone();
		  BitSet vector_2_in_clone = (BitSet)vector_2_in.clone();
		  int n = lead_length;//_desired length of the first (leading) vector
		  int index = -1;
		  while (index < (vector_2_in_clone.length() - 1)) {
		    index = vector_2_in_clone.nextSetBit((index + 1));
		    vector_1_in_clone.set((index + n));
		  }
		  return (Organism)vector_1_in_clone;
		}
}
