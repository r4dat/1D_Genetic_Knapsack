package organism;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Random;

import knapsack_config.Binary_knapsack_configuration;

/**
 * Organism with character based genes instead of bits. 
 * 
 * @author Rob Roy
 *
 */
public class OrganismChar implements Organism, Comparable<OrganismChar>, Cloneable {
	private Random rand = new Random();
	private ArrayList<Character> internal_array = null;
	private double intFitness = 0;
	private int intSize = 0;
	
	/**
	 * Instantiate an organism with character genes sized based on knapsack config.
	 */
	public OrganismChar() {
		internal_array = new ArrayList<>(Binary_knapsack_configuration.VALUES.length);
		int default_size = Binary_knapsack_configuration.VALUES.length;
		for (int i = 0; i < default_size; i++) {
			Character randChar = (char) (rand.nextInt(26) + 'a');
			internal_array.set(i, randChar);
		}
		intFitness = this.evaluateFitness();
		intSize = default_size;
	}

	/**
	 * Instantiates a new organism with character genes of size "length". 
	 *
	 * @param length the length
	 */
	public OrganismChar(int length) {
		internal_array = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			Character randChar = (char) (rand.nextInt(26) + 'a');
			internal_array.set(i, randChar);
		}
		intFitness = this.evaluateFitness();
		intSize = length;
	}
	
	
	/**
	 * Get organism size.
	 */
	@Override
	public int getOrgSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Concrete evaluateFitness.
	 */
	@Override
	public double evaluateFitness() {
		// TODO Auto-generated method stub
		intFitness=0;
		return 0;
	}

	/**
	 * Implement Comparable
	 */
	@Override
	public int compareTo(OrganismChar o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Get character at index from internal array.
	 * @param index
	 * @return
	 */
	public Character get(int index) {
		// TODO Stub
		return internal_array.get(index);
	}
	
	/**
	 * Setter to change character at index.
	 * @param index integer
	 * @param c character to insert
	 * @return
	 */
	public Character set(int index, Character c) {
		return internal_array.set(index, c);
	}
	
	public double getFitness() {
		return intFitness;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}

}
