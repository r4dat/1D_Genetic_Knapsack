CS613
A 1D Genetic Knapsack Implementation using design patterns.
Github: https://github.com/r4dat/1D_Genetic_Knapsack

Group member: Rob

Example Individual: [0,1,0,0...]
Example Target: [1,1,1,...]
Fitness: see src/organism/OrganismBit for evaluateFitness function.
Maximize value, subject to knapsack size. 
Weights and Values are in src/knapsack_config/Binary_knapsack_configuration


The following assumes you're in the top-level directory of the repository/project, and have java sdk bin in path. 

JavaDoc creation command:
javadoc -d doc -sourcepath src -subpackages crossover:evolution_factory:ga_knapsack_runner:knapsack_config:mutation:organism:population:selection

Compile command:
javac -d .\bin -cp .\bin .\src\crossover\*.java .\src\evolution_factory\*.java .\src\ga_knapsack_runner\*.java .\src\knapsack_config\*.java .\src\mutation\*.java .\src\organism\*.java .\src\population\*.java .\src\selection\*.java

Run command: 
java -cp .\bin ga_knapsack_runner.Ga_knapsack_runner

Output: 
System runs the genetic optimizer on a known binary knapsack problem for 10,000 epochs.
System prints initial population fitness, and then when a higher fitness organism is found prints the epoch, fitness, and
percentage fitness vs known maximum from branch-and-bound solution. See: https://developers.google.com/optimization/bin/knapsack

Sample Output: 
Initial Population Fitness:2951.0
Epoch: 0 max fitness: 2951.0 Percent max: 0.3916910007963897
Epoch: 1 max fitness: 5095.0 Percent max: 0.676267586939209
Epoch: 54 max fitness: 5943.0 Percent max: 0.7888239978762941

__singleton pattern__
src/population/PopulationBit.java

Population is a singleton using double checked locking implementation. 
This reduces synchronization overhead if we want to expand this system in a multi-threaded way. (Head First Design Patterns, p182)
Our problem is one amenable to a single small pool, but we could make a very large population singleton and alter it to farm out 
mutation, crossover, and evaluation on chunks of the population.

While not currently fully implemented a Factory design could be used with an abstracted population calling PopulationBit
or PopulationChar depending on knapsack configuration settings.

__strategy pattern__
src/selection/SelectionStrategy.java
src/selection/TournamentSelection.java
src/ga_knapsack_runner/Ga_knapsack_runner.java

Selection has been implemented with the Strategy pattern. 
SelectionStrategy is the base interface, and various selection classes (e.g. TournamentSelection, line 28 of runner) 
implement that interface. This allows to transparently swap strategies as desired with no impact on the client
(Ga_knapsack_runner in this case). We start initially with the Tournament Strategy, and then half-way through the 
set epoch_limit we switch the activeStrategy (an instance of SelectionStrategy) to the Elite Strategy (runner, line 50).
In more complex problem domains we may switch strategies multiple times - from an initial strategy that emphasizes 
exploration of the solution space to one that focuses on existing solutions, and then to something more exploratory if 
the increase in fitness stalls. 
Thanks to the strategy pattern this is a relatively easy thing to do, as shown by the strategy switch in the runner file.


__factory pattern__
Abstract Factory: src/evolution_factory/EvolutionFactory.java
Concrete Factory: src/evolution_factory/DeterministicEvoFactory.java
Abstract Product: src/crossover/Crossover.java  
Concrete Product: src/crossover/FixedCrossover.java

Crossover and mutation styles were created using the abstract factory pattern. 
The interface Evolution Factory has concrete factories creating concrete "products" Crossover and Mutation. 
The "products" being created implement the abstract interface Crossover and Mutation. 
(interface)/Evolution Factory/ -> (Concrete) Deterministic and Random Evolution Factories. 
(interface)/Crossover,Mutate classes/ -> (Concrete) Fixed and Random Crossover,Mutate classes. 
So we have the parallel abstract to concrete structure found in the abstract factory pattern. 
This allows us to delegate the Crossover algorithm creation to the sublcass concrete factory, while not caring 
from the client perspective what's going on under the hood.

From the client perspective we're interacting with the EvolutionFactory interface, and the Crossover and Mutation
interfaces.

As a future state the factories could take in a run-time Organism and return appropriate mutate/crossover functions
as these would differ depending on gene type (bits vs letter sets as in DNA for example). 
Or a more generic implementation of the crossover/mutate. The current implementation still depends heavily on 
methods from the BitSet inherited methods in OrganismBit.

__generics__
Currently the desired Population is created directly (PopulationBit). 
The desired result is to have a population factory creating the proper Population sub-type (Char vs Bit).
We use generics to bound the factory return types to sub-types of the Population and Organism related types. 

__Future State__
Use more generic mutate and crossover functions that handle both bit and char genes. 
Implement factory to produce correct populations or alternatively abstract population 
creation so they're interchangeable at a client level.

