package knapsack_config;

/**
 * In practice this would be a config file, not class. 
 * So it can be edited without re-compiling.
 * 
 * Weights, values, knapsack-size
 * for the problem domain. 
 * 1d knapsack problem. 
 * Optimal solution value: 7534
 * 
 * Sample data:
 * https://developers.google.com/optimization/bin/knapsack
 * 
 * @author Rob Roy
 *
 */
public class Binary_knapsack_configuration {

	/** The Constant VALUES. */
	public static final double[] VALUES = { 360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147, 78,
			256, 63, 17, 120, 164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15, 26, 78, 210, 36, 85, 189,
			274, 43, 33, 10, 19, 389, 276, 312 };

	/** The Constant WEIGHTS. */
	public static final double[] WEIGHTS = { 7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0,
			42, 47, 52, 32, 26, 48, 55, 6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0, 79, 20, 65,
			52, 13 };

	/** The Constant KNAPSACK_CAPACITY. */
	public static final double KNAPSACK_CAPACITY = 850;

	/** The Constant DEFAULT_POPULATION_SIZE. */
	public static final int DEFAULT_POPULATION_SIZE = 500;
	
	/** The Organism gene-type */
	public static final String GENE_TYPE = "bit";

}

/* Known best solution for google's 1d knapsack.
 * Basic implementation to BitSet below.
 * int[] solution = {0, 1, 3, 4, 6, 10, 11, 12, 14, 15, 16, 17, 18, 19, 21, 22,
 * 24, 27, 28, 29, 30, 31, 32, 34, 38, 39, 41, 42, 44, 47, 48, 49}; 
 * BitSet actual_sln = new BitSet(50); 
 * for (int i=0; i<solution.length; i++) { int idx
 * = solution[i]; actual_sln.set(idx,true); }
 */