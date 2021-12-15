package organism;
/**
 * Interface for organism.
 * @author Rob Roy
 *
 */
public interface Organism {
abstract int getOrgSize();
abstract double evaluateFitness();
// all implementing classes should also implement comparable.
}
