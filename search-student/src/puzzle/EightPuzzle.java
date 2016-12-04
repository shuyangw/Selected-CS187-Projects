package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */

public class EightPuzzle implements SearchProblem<List<Integer>> {
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	
	Integer[][] boardarr = new Integer[3][3];
	List<Integer> board = new ArrayList<Integer>();
	List<Integer> initial;
	List<Integer> newL;
	
	public EightPuzzle(List<Integer> startingValues) {
		// TODO
		for(Integer x: startingValues){
			if(x.compareTo(new Integer(8)) > 0 || x.compareTo(new Integer(0)) < 0){
				throw new IllegalArgumentException();
			}
		}
		generateboard(startingValues);
		newL = new ArrayList<Integer>();
		initial = startingValues;
		for(int i = 0; i < 9; i++){
			board.add(initial.get(i));
		}
		printboard();
	}
	
	private void generateboard(List<Integer> list){
		int count = 0;
 		for(int i = 0; i < 3; i++){
 			for(int j = 0; j < 3; j++){
 				boardarr[j][i] = list.get(count);
 				count++;
 			}
 		}
	}
	
	private void revert(){
		for(int i = 0; i < 9; i++){
			board.set(i, initial.get(i));
		}
		generateboard(initial);
	}

	public void printboard(){
		for(int j = 0; j < 3; j++){
 			for(int i = 0; i < 3; i++){
 				System.out.print(boardarr[i][j]);
 			}
 			System.out.println();
 		}
		System.out.println();
	}
	
	@Override
	public List<Integer> getInitialState() {
		// TODO
		return initial;
	}

	private void swap(int a, int b){
		Integer temp = board.get(a);
		board.set(a, board.get(b));
		board.set(b, temp);
		generateboard(board);
	}
	
	private void putin(){
		newL.clear();
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				newL.add(boardarr[i][j]);
			}
		}
	}
	
	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> out = new ArrayList<List<Integer>>();
		generateboard(currentState);
		int pos = currentState.indexOf(new Integer(0));
		if(pos == 0){
			swap(0,1); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(0,3); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 1){
			swap(1,0); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(1,2); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(1,4); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 2){
			swap(2,1); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(2,5); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 3){
			swap(3,0); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(3,4); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(3,6); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 4){
			swap(4,1); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(4,3); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(4,5); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(4,7); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 5){
			swap(5,2); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(5,4); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(5,8); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 6){
			swap(6,3); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(6,7); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 7){
			swap(7,8); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(7,6); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(7,4); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		if(pos == 8){
			swap(8,7); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
			swap(8,5); putin(); revert(); out.add(newL); newL = new ArrayList<Integer>();
		}
		return out;
	}
	

	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		List<Integer> finalstate = Arrays.asList(new Integer[] { 1, 2, 3,
				4, 5, 6, 7, 8, 0 });
		for(int i = 0; i < state.size(); i++){
			if(state.get(i) != finalstate.get(i)){
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
