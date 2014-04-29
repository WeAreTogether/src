package actions;

import java.util.HashMap;
import java.util.Map;
import algorithm.FitnessCalculation;
import algorithm.population;
public class jack {
public static void main(String[] args) {
	double fval;//fitness value
	population p = new population(); //call population

	//population size is assumed 2 hence <2
	for (int j = 0; j < 2; j++) {
		//calculating fitness values of randomly generated population and printing
		FitnessCalculation fc = new FitnessCalculation(p, j);
		fval = fc.getFitness(p.getIndividual(j));
		System.out.println("fval:"+fval);
}
}
}
