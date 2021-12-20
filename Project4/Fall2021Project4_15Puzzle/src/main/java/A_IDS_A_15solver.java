import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

// TODO DO NOT modify this class except for one line

public class A_IDS_A_15solver {

	/*
	 * @author Mark Hallenbeck
	 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
	 */
	Node startState;
	boolean test = true;

	public A_IDS_A_15solver(int h, boolean check){

		UserInterface puzzle = new UserInterface();			//class for reading in puzzle from user

		startState = new Node(puzzle.getPuzzle());		//node contains the start state of puzzle

		startState.setDepth(0);
		test = check;
		if (h == 1) {
			System.out.println("\nStarting A* Search with heuristic #1....This may take a while\n\n");
			A_Star(startState, "heuristicOne");//A* search with heuristic 1 (misplaced tiles)

		} else if (h == 2) {
			System.out.println("\nStarting A* Search with heuristic #2....This may take a while\n\n");
			A_Star(startState, "heuristicTwo");
			//* search with heuristic 2 (manhattan)
		}

		System.out.println("\nThanks for using me to solve your 15 puzzle......Goodbye");
		// System.exit(1);

	}

	/**
	 * Method takes node with the start state as well as which heuristic to use and initializes a DB_Solver2 object(A* search).
	 * It then solves the puzzle and prints out some metadata and the solution path
	 * @param startState
	 * @param heuristic
	 */
	public ArrayList<Node> A_Star(Node startState, String heuristic) {

		DB_Solver2 start_A_Star = new DB_Solver2(startState, heuristic);    //DB_Solver class initialized with startState node


		Long start = System.currentTimeMillis();

		Node solution = start_A_Star.findSolutionPath();    //returns the node that contains the solved puzzle

		Long end = System.currentTimeMillis();

		System.out.println("\n******Run Time for A* " + heuristic + " is: " + (end - start) + " milliseconds**********");
		ArrayList<Node> solutionPath = new ArrayList<Node>();
		System.out.println(test);
		if (solution == null)                                //no solution was found
		{
			System.out.println("\nThere did not exist a solution to your puzzle with A* search\n");
		}
		else											//found a solution so, get the path and print it
		{
			//creates ArrayList of solution path
			solutionPath = start_A_Star.getSolutionPath(solution);
			if(!test) {
				printSolution(solutionPath);
			}

			System.out.println("\n$$$$$$$$$$$$$$ the solution path is "+ solutionPath.size()+ " moves long\n");
		}

		//ArrayList<Node> solutionPath = start_A_Star.getSolutionPath(solution);
		return solutionPath;

	}

	public void printSolution(ArrayList<Node> path){
		PauseTransition pause;

		System.out.print("\n\n");
		System.out.println("**************Initial State******************");
		for(int i=0; i < 10; i++){
			pause = new PauseTransition(Duration.seconds(i+1));

			printState(path.get(i));
			JavaFXTemplate.remake = true;

			int finalI1 = i;
			pause.setOnFinished(event -> {
				GameLogic.changeBoard(printState(path.get(finalI1)));
				JavaFXTemplate.run.setText("Showing the solution for the next 10 steps...");
			});
			pause.play();

			System.out.println(Arrays.toString(JavaFXTemplate.numbers));
			if(i != (path.size() - 1))
				System.out.print("\nNext State => "+i+"\n\n");
		}
		System.out.println("\n**************Goal state****************");
		JavaFXTemplate.run.setText("");

	}


	public Integer[] stringToIntArray(String puzzle)
	{
		String[] values = puzzle.split("[ ]+");		//split string into array of strings(numbers)
		Integer[] intArray = new Integer[values.length];	//int array for converted strings
		for(int x = 0; x < intArray.length; x++)			//convert to int array
		{
			intArray[x] = Integer.parseInt(values[x]);
		}
		return intArray;
	}

	public Integer[] printState(Node node){
		int[] puzzleArray = node.getKey();
		String str = Arrays.toString(puzzleArray);
		str = str.replace("[","");		// Replace '[' with ''
		str = str.replace(",","");
		str = str.replace("]","");
		return stringToIntArray(str);
	}
}



