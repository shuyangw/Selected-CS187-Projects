package search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import mazes.Cell;
import mazes.Maze;
import mazes.MazeGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class SearcherTestDFS {
	@Rule
	public Timeout globalTimeout = new Timeout(500L, TimeUnit.MILLISECONDS);

	private Maze maze;

	@Before
	public void before() {
		maze = new MazeGenerator(5, 7, 21312).generateDFS();
		/* maze should now be:
		#0#1#2#
		0  S  0
		# # # #
		1     1
		# ### #
		2  G  2
		#0#1#2#
		*/
	}
	
	@Test
	public void testIsValidSolution() {
		List<Cell> solution = new ArrayList<Cell>();
		Searcher<Cell> s = new StackBasedDepthFirstSearcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
		solution = s.findSolution();
		for(Cell x: solution){
			System.out.println(x);
		}
		System.out.println(maze);
		assertTrue(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionStartsNotAtInitialState() {
		List<Cell> solution = new ArrayList<Cell>();
		Searcher<Cell> s = new StackBasedDepthFirstSearcher<Cell>(maze);
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
//		solution = s.findSolution();
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionDoesNotReachGoal() {
		List<Cell> solution = new ArrayList<Cell>();
		Searcher<Cell> s = new StackBasedDepthFirstSearcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
//		solution = s.findSolution();
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionSkipsState() {
		List<Cell> solution = new ArrayList<Cell>();
		Searcher<Cell> s = new StackBasedDepthFirstSearcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
//		solution = s.findSolution();
		assertFalse(s.isValidSolution(solution));
	}

	@Test
	public void testSolutionNotAdjancentStates() {
		List<Cell> solution = new ArrayList<Cell>();
		Searcher<Cell> s = new StackBasedDepthFirstSearcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(1, 1));
		solution.add(new Cell(1, 2));
//		solution = s.findSolution();
		assertFalse(s.isValidSolution(solution));
	}
}
