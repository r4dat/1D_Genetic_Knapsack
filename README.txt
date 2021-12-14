A 1D Genetic Knapsack Implementation using design patterns.

Group member: Rob Rutherford

The following assumes you're in the top-level directory of the repository/project.

JavaDoc creation command:
javadoc -d doc -sourcepath src -subpackages crossover:evolution_factory:ga_knapsack_runner:knapsack_config:mutation:organism:population:selection

Compile command:
javac -d .\bin -cp .\bin .\src\crossover\*.java .\src\evolution_factory\*.java .\src\ga_knapsack_runner\*.java .\src\knapsack_config\*.java .\src\mutation\*.java .\src\organism\*.java .\src\population\*.java .\src\selection\*.java

Run command: 
java -cp .\bin ga_knapsack_runner.Ga_knapsack_runner

Output: 
System runs the genetic optimizer on a known binary knapsack problem for 10,000 epochs.
System prints initial population fitness, and then when a higher fitness organism is found prints the epoch, fitness, and
percentage fitness vs known maximum from branch-and-bound method. See: https://developers.google.com/optimization/bin/knapsack

__singleton pattern__



__strategy pattern__


__factory pattern__