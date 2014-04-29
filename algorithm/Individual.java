package algorithm;

import java.sql.SQLException;
import java.util.Arrays;

import algorithm.Database;
import algorithm.FitnessCalculation;
import algorithm.Individual;
import algorithm.Gene;

public class Individual {
	
	//defining and initializing constants
	static int workingDays =6;
	static int dailyHours = 8;
	public static int weeklyHours = workingDays * dailyHours; 

	public static int NumOfLecturers = 0;
	public static int NumOfClasses = 0;
	public static int NumOfCourses = 0;

	static public final int selection_prefered = 0;
	static public final int selection_available = 1;
	static public final int selection_not_available = 2;
	public static int populationSize = 500;

	private Gene[][][][] genes; //why we havnt used this.gene property at line 157
	private double fitness = -1;
	private double selection = 0;
	private boolean printed;
	
	Database DB_obj=new Database();

	/*
	public Individual() {
		Gene[][][][] genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		resetIndividual();
		setPrinted(false);
	}*/

	public Individual(Individual indv)
	{
		//generate 4D array for every individual
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		copyIndividual(indv);
	}

	public Individual(int NumOfLecturers, int NumOfClasses, int NumOfCourses)
	{
		//assign total number of lecturers, classes and courses
		genes = new Gene[weeklyHours][NumOfLecturers][NumOfClasses][NumOfCourses];
		Individual.NumOfLecturers = NumOfLecturers;
		Individual.NumOfClasses = NumOfClasses;
		Individual.NumOfCourses = NumOfCourses;
		setPrinted(false);
		resetIndividual();
	/*	System.out.println("New number of lectureres "+Individual.NumOfLecturers);
		System.out.println("New number of classes "+Individual.NumOfClasses);
		System.out.println("New number of courses "+Individual.NumOfCourses);*/

	}
	public void resetIndividual() {
	//assigning 	
		for (int H = 0; H < weeklyHours; H++) {
			for (int L = 0; L < NumOfLecturers; L++) {
				for (int R = 0; R < NumOfClasses; R++) {
					for (int C = 0; C < NumOfCourses; C++) {
						genes[H][L][R][C] = new Gene();
					}
				}
			}
		}
	}


	public void copyIndividual(Individual indv) //this piece of code copies the individual property and reset the property
	{
		for (int H = 0; H < weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < NumOfCourses; C++)
					{ // NumOfCourses
		
							genes[H][L][R][C] = new Gene();
							genes[H][L][R][C].setIndex(indv.getGeneByIndex(H, L, R, C).getIndex());

							if (indv.getGeneByIndex(H, L, R, C).isEditable())
								genes[H][L][R][C].setEditable();
							else
							{
								genes[H][L][R][C].setUnEditable();
							}
							if (indv.getGeneByIndex(H, L, R, C).isGene())
								genes[H][L][R][C].setGene(indv.getGeneByIndex(H, L, R, C).getIndex());
							else
							{
								genes[H][L][R][C].clrGene();
							}
					}
		
		setPrinted(indv.isPrinted());
	}
	
	public boolean isPrinted()
	{
		return printed;
	}

	public Gene getGeneByIndex(int weeklyHour, int LecturerIndex, int ClassIndex, int CourseIndex)
	{
		return genes[weeklyHour][LecturerIndex][ClassIndex][CourseIndex];
	}

	public void setPrinted(boolean printed)
	{
		this.printed = printed;
	}

	public void generateIndividual() //this is the function called by population constructor
	{

		boolean geneSet = false;
		int i;
		//System.out.println("inside Generate Individual::"+NumOfLecturers);
		for (int C = 0; C < NumOfCourses; C++)
		{
			geneSet = false;
			// NumOfCourses
			for (int L = 0; L < NumOfLecturers; L++)
			{
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
				{
					// NumOfClasses
					for (int H = 0; H < weeklyHours; H++)
					// weeklyHours
					{
						double gene = Math.random();
						int editableHours = 0;
						
						int hoursForCourse=0;
						try {
							hoursForCourse = DB_obj.getAcademicHours(C+1);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (gene > 0.5) //this gene comes from the random number generation
						{
							if ((H + hoursForCourse) < Individual.weeklyHours)
							{
								
								for (i = 0; i < hoursForCourse; i++)
								{
									if (genes[H + i][L][R][C].isEditable())
										editableHours++;
								}
								/*System.out.println(editableHours);
								System.out.println(hoursForCourse);
							*/	if (editableHours == hoursForCourse)
									for (i = 0; i < hoursForCourse; i++)
									{
										genes[H + i][L][R][C].setGene(i);
										//System.out.println("Editable Hours::"+genes[H + i][L][R][C]);
										geneSet = true;
									}
							}
							if (geneSet)
								break; //why we are breaking the loop once gene is Set

						}

					} //weekly hours
					if (geneSet)
						break;
				} //Numofclasses
				if (geneSet)
					break;
			}//num of lectureres
		}//number of courses
			//printgene();	
	}//main function closing loop

	@Override
	public String toString() {
		return "Individual " + ", fitness="
				+ fitness + ", selection=" + selection + ", printed=" + printed
				+ ", DB_obj=" + DB_obj + "]";
	}

	public void printgene(){
		for (int C = 0; C < NumOfCourses; C++)
		{
			// NumOfCourses
			for (int L = 0; L < NumOfLecturers; L++)
			{
				// NumOfLecturers
				for (int R = 0; R < NumOfClasses; R++)
				{
					// NumOfClasses
					for (int H = 0; H < weeklyHours; H++)
					// weeklyHours
					{
					System.out.println(genes[H][L][R][C]);
					}
				}
			}
		}
		
	}
	
	
	public void setFitness(double fitness)
	{

		this.fitness = fitness;

	}
	public double getFitness()
	{
		if (fitness == -1)
		{
			fitness = FitnessCalculation.getFitness(this);
		}

		return fitness;
	}

}
