package algorithm;

import java.sql.SQLException;
import algorithm.Database;

public class population {

	//defining and initializing constants
	static int workingDays = 6;
	static int dailyHours = 8;
	public static int weeklyHours = workingDays * dailyHours;
	private static final int populationSize = 2;
	public static Individual[] individuals;
	static Individual JumpStartIndividual;

	public population() {
		
		//using database obj fr accessing total lec,courses and classes 
		Database db_obj = new Database();
		//individuals is an array of individuals of population size(2 here) 
		individuals = new Individual[populationSize];

		try {
			//JumpStartIndividual an individual to start with
			JumpStartIndividual = new Individual(db_obj.getLecturersSize(),db_obj.getClassSize(), db_obj.getCoursesSize());
			//this will set all the properties of the individual
			System.out.println("no of lec,classes,courses:"+ db_obj.getLecturersSize() + " " + db_obj.getClassSize()+ " " + db_obj.getCoursesSize());
			System.out.println("Size of the array is::"+size());
			//size wil gv individuals array length 
			for (int i = 0; i < size(); i++) {
				//generating and saving new individuals of population size
				Individual newIndividual = new Individual(JumpStartIndividual);
				//System.out.println("AAya"+newIndividual.NumOfLecturers);
				newIndividual.generateIndividual();
				//System.out.println(newIndividual); //we can see the issue here
				saveIndividual(i, newIndividual); //forming kind of map here
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//saves individuals in array 
	public static void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	//return the size of individuals array (population size)
	public static int size() {
		return individuals.length;
	}

	//retrieve individuals using index
	public Individual getIndividual(int index) {
		return individuals[index];
	}
}
