package algorithm;

import java.util.ArrayList;
import java.util.Map;

import algorithm.Database;
import beans.Course;

public class PrintSolution extends Thread {
	// public CreateExcelFile cls;
	Individual solution;
	private Map<Integer, Map<Integer, ArrayList<Course>>> individualMap;
	private Course idcalss;
	private static boolean debug = false;
	Database collageDB;

	public static void main(String[] args) {
	}
	
	public PrintSolution(Individual solution) {
		this.solution = solution;

	}

	public void run() {
		if (solution.isPrinted())
			return;
		solution.setPrinted(true);
		if (debug) {
			System.out.println("fitness=(" + solution.getFitness() + ")");
			for (int H = 0; H < Individual.weeklyHours; H++)
				// weeklyHours
				for (int L = 0; L < Individual.NumOfLecturers; L++)
					// NumOfLecturers
					for (int R = 0; R < Individual.NumOfClasses; R++)
						// NumOfClasses
						for (int C = 0; C < Individual.NumOfCourses; C++)
							if (solution.getGeneByIndex(H, L, R, C).isGene())
								System.out.println("hour = "
										+ H
										+ "| Lecturer = "
										+ L
										+ "| Class = "
										+ R
										+ "| Course = "
										+ C
										+ " | "
										+ solution.getGeneByIndex(H, L, R, C)
												.getIndex());
			System.out.println("found!!!!");
		}
		try {
			// IndividualToMap();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}