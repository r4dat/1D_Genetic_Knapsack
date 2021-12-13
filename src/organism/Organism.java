package organism;

import java.util.BitSet;
import java.util.Random;

import knapsack_config.Binary_knapsack_configuration;
// TODO: Auto-generated Javadoc

/**
 * The Class Organism.
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

	/** The rand. */
	Random rand = new Random();

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
	 * Mult arrays.
	 *
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
	 *
	 * @return the double
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
	 * Gets the org value.
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
	 * Gets the org weight.
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
	 *
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