package population;
import java.util.ArrayList;

import knapsack_config.*;
import organism.*;

public class PopulationFactory {
	private String gene_type = Binary_knapsack_configuration.GENE_TYPE;

	public <U> U getInstance() {
		<U extends PopulationFactory> tmp;
		if(gene_type.equals("bit")) {tmp = PopulationBit.getInstance();}
			else { tmp = PopulationChar.getInstance();}
			return tmp;
			}
	
	public <T> T getPop() {
	ArrayList<T extends Organism> generic_pop_list;
	if(gene_type.equals("bit")) { generic_pop_list = PopulationBit.getInstance().getPop();
	}
	else { generic_pop_list = PopulationChar.getInstance().getPop();
	}
	return generic_pop_list;
	}
	
}
