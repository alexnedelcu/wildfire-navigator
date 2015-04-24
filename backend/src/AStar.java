import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class AStar extends Search {
	private ArrayList<Node> openList = new ArrayList<Node>();
	private ArrayList<Node> closedList;
	
	private Node start; 
	
	AStar(Node start) {
		this.start=start;
	}
	
	void search() {
		openList.add(start);
		
		while (!openList.isEmpty()) {
			
		}
	}
	
	Node findCheapestCostNode () {
		for (int i=0; i<openList.size(); i++) {
//			if (openList)
		}
		return null;
	}
	
	int heuristic () {
		return 0;
	}
	
}

//	public class AStar extends Search {
//	
//	private PriorityQueue<Node<Maze>> queue = new PriorityQueue<Node<Maze>> (3, new ManhattanDistanceComparator());
//	
//	/**
//	 * Solve the maze by applying breath first strategy
//	 * @return
//	 */
//	public Node<Maze> solveByAStarSearchStrategy(Maze m) {
//		
//		startTimer();
//		
//		Maze myMaze = m.cloneMaze();
//		
//		
//		visitedStates.add(myMaze);
//
//		// create the root of the tree
//		Node<Maze> root = new Node<Maze>(null);
//		root.setRootData(myMaze);
//		
//		// find the solution
//		solution = aStarSearch(root);
//		
//		stopTimer();
//		
//		return solution;
//	}
//	
//	private Node<Maze> aStarSearch(Node<Maze> startNode) {
//		queue.clear();
//		queue.add(startNode);
//		
//		int num=0;
//		// check for the final state without expanding the tree
//		while (!queue.isEmpty()) {
//			num ++;
////			System.out.println("\n\n\n============================\niteration "+num+" of the queue:\n============================\n\n\n");
//			Node<Maze> currentNode = queue.poll();
//			if (currentNode.getRootData().isSolvedPuzzle()) {
//				return currentNode;
//			} else {
//				
//				// add new moves to the end
//				Node<Maze> myNode = currentNode;
//				Maze myMaze = myNode.getRootData();
//				
//				ArrayList<Maze> moves = myMaze.getAllAvailableMoves();
////				System.out.println("Adding "+moves.size()+" to the tree");
//				
//				// add the new states to the visited states
//				for (int i=0; i<moves.size(); i++) {
//					
//					// checking to see if the state was visited
//					boolean isAVisitedState = visitMaze(moves.get(i));
//					
//					if (!isAVisitedState) {
////						System.out.println("Adding ... total:"+queue.size());
//						
//						
//						// add it to the solution tree
//						Node<Maze> newMove = myNode.addChild(moves.get(i));
//						
//						// add the node to the queue
//						queue.add(newMove);
//						//moves.get(i).showGameState();
//
//
//						// increment count of the checked states
//						numberOfStatesChecked++;
//						
//						
//					}
//				}
//
////				PriorityQueue<Node<Maze>> x = new PriorityQueue<Node<Maze>>(3, new ManhattanDistanceComparator());
////				x.addAll(queue);
////				System.out.println("size of x: "+ x.size());
////				for(int i=0; i<queue.size(); i++) {
////					System.out.println("Position "+i+"\n");
////					Node<Maze> element = x.poll();
////					element.getRootData().showGameState();
////					System.out.println("Manhattan distance:"+element.getRootData().getManhattanDistanceFromTheMaster());
////					
////				}
//			}
//			
//		}
//		
//		return null;
//	}
//	
//	
//	private static class ManhattanDistanceComparator implements Comparator<Node<Maze>> {
//
//		@Override
//		public int compare(Node<Maze> o1, Node<Maze> o2) {
//			return o1.getRootData().getManhattanDistanceFromTheMaster() - o2.getRootData().getManhattanDistanceFromTheMaster();
//		}
//
//		
//	}
//}
