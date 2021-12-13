package organism;

import java.util.BitSet;
import java.util.Random;

import knapsack_config.Binary_knapsack_configuration;


/**
 * The Class Organism.
 * Extends BitSet since that is well adapted to 
 * binary knapsack problems. 
 * 
 * If this were more general purpose, I'd 
 * make Organism an interface, then an OrgBitSet or similar
 * so in the future we could extend Organism to use 
 * strings/characters as the DNA representation. 
 * E.g. currently [0,1,0,0] 
 * Some problems more naturally resolve to [A,G,T,C] etc.
 */
@SuppressWarnings("serial")
public class Organism extends BitSet implements Comparable<Organism> {

	/** The Constant weights. */
	private final static double[] weights = Binary_knapsack_configuration.WEIGHTS;

	/** The Constant values. */
	private final static double[] values = Binary_knapsack_configuration.VALUES;

	/** The k size. */
	private final double k_size = Binary_knapsack_configuration.KNAPSACK_CAPACITY;

	/** The int fitness. */
	private double intFitness;

	/** The int size. */
	private int intSize;

	private Random rand = new Random();

	/**
	 * Instantiates a new organism.
	 */
	public Organism() {
		super(Binary_knapsack_configuration.VALUES.length);
		int default_size = Binary_knapsack_configuration.VALUES.length;
		for (int i = 0; i < default_size; i++) {
			this.set(i, rand.nextBoolean());
		}
		intFitness = this.evaluateFitness();
		intSize = default_size;
	}

	/**
	 * Instantiates a new organism.
	 *
	 * @param length the length
	 */
	public Organism(int length) {
		super(length);
		for (int i = 0; i < length; i++) {
			this.set(i, rand.nextBoolean());
		}
		intFitness = this.evaluateFitness();
		intSize = length;
	}

	/**
	 * Multiply Arrays fucntion
	 * Internal implementation for BitSets.
	 * @param gaState      the ga state
	 * @param secondMatrix the second matrix
	 * @return the double
	 */
	private double mult_arrays(BitSet gaState, double[] secondMatrix) {
		double result = 0.0;
		for (int i = 0; i < secondMatrix.length; i++) {
			int val = gaState.get(i) ? 1 : 0;
			result += (val * secondMatrix[i]);
		}
		return result;
	}

	/**
	 * Evaluate fitness.
	 * Loss function could be strategized.
	 * @return the double fitness.
	 */
	public double evaluateFitness() {
		BitSet tmp_bit = (BitSet) this.clone();
		double tot_wt, org_val, org_wt;
		tot_wt = org_val = org_wt = 0;
		for (int i = 0; i < weights.length; i++)
			tot_wt += weights[i];
		org_val = mult_arrays(tmp_bit, values);
		org_wt = mult_arrays(tmp_bit, weights);
		double penalty = tot_wt * Math.abs((org_wt - k_size));
		double fitness = org_val - penalty;
		intFitness = fitness;
		return fitness;
	}

	/**
	 * Calc and return the org value.
	 *
	 * @return the org value
	 */
	double getOrgValue() {
		BitSet tmp_bit = (BitSet) this.clone();
		return mult_arrays(tmp_bit, values);
	}

	/**
	 * Gets the fitness.
	 *
	 * @return the fitness
	 */
	public double getFitness() {
		return intFitness;
	}

	/**
	 * Calculates organism weight.
	 *
	 * @return the org weight
	 */
	double getOrgWeight() {
		BitSet tmp_bit = (BitSet) this.clone();
		return mult_arrays(tmp_bit, weights);
	}

	/**
	 * Gets the org size.
	 *
	 * @return the org size
	 */
	public int getOrgSize() {
		return intSize;
	}

	/**
	 * Compare to.
	 * Compares two organisms returns -1,0,1 per interface. 
	 * @param o the o
	 * @return the int
	 */
	public int compareTo(Organism o) {
		if (this.evaluateFitness() > o.evaluateFitness())
			return 1;
		else if (this.evaluateFitness() < o.evaluateFitness())
			return -1;
		else
			return 0;
	}

}