package com.alexnedelcu.wildfirenavigator;
import java.util.ArrayList;


public class Search {

	protected ArrayList<SquareUnit> visitedStates = new ArrayList<SquareUnit>();
	protected Node<SquareUnit> solution;
	protected int numberOfStatesChecked = 0;
	private long startTime, stopTime, executionTime;
	
	protected boolean visitMaze(SquareUnit movement) {
		boolean isAVisitedState = false;
		for (int j=0; j<visitedStates.size(); j++) {
//			if (visitedStates.get(j).compare(movement)) 
//				return true;
		}
		
		// add the state to the visited states array if not already there
		visitedStates.add(movement);
		
		return false;
	}
	
	protected void printMovements() {
		
		// find the path of the solution
//		ArrayList<Node<Maze>> mazeStatePath = new ArrayList<Node<SquareUnit>>();
//		String movements = "";
//		Node<Maze> temp = solution;
//		while (temp.getParent() != null) {
//			movements = temp.getParent().getRootData().getMovement(temp.getRootData()) + "\n" + movements;
//			temp = temp.getParent();
//		}
		
		// print the set of moves and the final state of the maze
//		System.out.println(movements);
//		solution.getRootData().showGameState();
	}
	
	public int getNumberOfCheckedStates () {
		return numberOfStatesChecked;
	}

	protected void startTimer () {
		startTime = System.nanoTime();
	}
	protected void stopTimer () {
		stopTime = System.nanoTime();
		executionTime = stopTime - startTime;
	}
	public long getExecutionTime() {
		return executionTime / 1000 ;
	}
	
//	public int getSolutionLength() {
//		Node<Maze> n = solution;
//		int count = 0;
//		while(n.getParent() != null) {
//			count ++;
//			n = n.getParent();
//		}
//		
//		return count;
//	}
}
