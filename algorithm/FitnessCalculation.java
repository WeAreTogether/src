package algorithm;

import java.util.Iterator;

import com.mysql.jdbc.NdbLoadBalanceExceptionChecker;

import mapping.InitializeHashmap;

public class FitnessCalculation implements Runnable {
	private Individual indiv;
	private population population;
	private int indivIndex;
	private static boolean debug = true;
	public static InitializeHashmap map1;

	public FitnessCalculation(population population, int i) {
		indiv = population.getIndividual(i);
		System.out.println(indiv);
		System.out.println("i came heres");
		indivIndex = i;
		this.population = population;
	}

	// Calculate individuals fitness by comparing it to our candidate solution
	public static double getFitness(Individual individual) {
		double fitness = 0;
		double hard = 0;

		hard = calcHardConstraints(individual);
		fitness = (1.0 / (1.0 + (double) hard));

		return fitness;
	}

	private static double calcHardConstraints(Individual individual) {

		double HardConstraints = 0;
		int tempCounter = 0;
		int lecID;
		int classID;
		int courseID;
		int counter;
		int courseCounter;
		map1 = new InitializeHashmap();

		// check all course are assigned. + check how many times a course has
		// been assigned in an hour

		Iterator<Integer> courseItr = map1.initcourse().keySet().iterator();
		tempCounter = 0;
		while (courseItr.hasNext()) {
			counter = 0;
			courseID = courseItr.next().intValue();
			//System.out.println(Individual.NumOfCourses);
			//System.out.println(courseID);
		if(courseID < Individual.NumOfCourses){
			int courseIndex = map1.initcourse().get(courseID).getCourseid();
			
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) {
				courseCounter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++) {
					for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++) {
						
						if ((individual.getGeneByIndex(Hours, LecturerIndex,ClassIndex, courseIndex).isGene())&& (individual.getGeneByIndex(Hours,LecturerIndex, ClassIndex, courseIndex).getIndex() == 0)) 
						{
							// check how many times a course has been assigned
							// in an hour
							//System.out.println("with course counter");
							courseCounter++;
							System.out.println("value of course counter is::"+courseCounter);
							//System.out.println(map1.initcourse().get(courseID));
							if (map1.initcourse().get(courseID).getCourseid() == courseID) {
								// check all course are assigned
								
								counter++;
							}
						}
					}
				}
//				System.out.println(courseCounter);
				if (courseCounter > 1) {
					tempCounter += courseCounter;
				}
			}
			if (counter == 0) {
				tempCounter++;
			} else {
				tempCounter += (counter - 1);
			}
		}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out.println("check all course are assigned:"+ HardConstraints);

		
		// check how many times a lecturer teaches in an hour.

		Iterator<Integer> LecItr = map1.initlecturer().keySet().iterator();
		tempCounter = 0;
		while (LecItr.hasNext() ) {
			lecID = LecItr.next().intValue();
			if(lecID < Individual.NumOfLecturers ){
			int lecturerIndex = map1.initlecturer().get(lecID).getLecid();
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) {
				counter = 0;
				for (int ClassIndex = 0; ClassIndex < Individual.NumOfClasses; ClassIndex++) {
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++) {
						if (individual.getGeneByIndex(Hours, lecturerIndex,ClassIndex, CourseIndex).isGene()) {
							counter++;
						}
					}
				}
				if (counter > 0)
					tempCounter += counter - 1;

			}
		}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out
					.println("check how many times a lecturer teaches in an hour:"
							+ HardConstraints);
		// check how many times a class is taken in an hour
		tempCounter = 0;
		Iterator<Integer> classItr = map1.initClassRoom().keySet().iterator();
		while (classItr.hasNext() ) {
			classID = classItr.next().intValue();
			if(classID<Individual.NumOfClasses){
			int classIndex = map1.initClassRoom().get(classID).getClassroomno();
			for (int Hours = 0; Hours < Individual.weeklyHours; Hours++) {
				counter = 0;
				for (int LecturerIndex = 0; LecturerIndex < Individual.NumOfLecturers; LecturerIndex++) {
					for (int CourseIndex = 0; CourseIndex < Individual.NumOfCourses; CourseIndex++) {
						if (individual.getGeneByIndex(Hours, LecturerIndex,
								classIndex, CourseIndex).isGene()) {
							counter++;
						}
					}
				}
				if (counter > 0)
					tempCounter += counter - 1;

			}
		}
		}
		HardConstraints += tempCounter;
		if (debug)
			System.out
					.println("check how many times a class is taken in an hour:"
							+ HardConstraints);

		tempCounter = 0;
		System.out.println("hc:"+HardConstraints+" ");
		return HardConstraints;
	}

	@Override
	public void run() {
		double fitness = getFitness(indiv);
		indiv.setFitness(fitness);
		saveIndividual();

	}

	private synchronized void saveIndividual() {
		population.saveIndividual(indivIndex, indiv);

	}

	public static double getdebugFitness(Individual individual) {
		debug = true;
		double debugFitness = getFitness(individual);
		debug = false;
		return debugFitness;
	}
}