package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		// TODO
		
		Stack<T> stack = new Stack<T>();
		List<T> path = new ArrayList<T>();
		T initial = searchProblem.getInitialState();
		visited.add(initial);
		stack.push(initial);
		path.add(initial);
		while(!stack.isEmpty()){
			T top = stack.peek();
			List<T> succ = searchProblem.getSuccessors(top);
			
			int count = 0;
			for(int i = 0; i < succ.size(); i++){
				if(!visited.contains(succ.get(i))){
					stack.push(succ.get(i));
					visited.add(succ.get(i));
					path.add(succ.get(i));
					if(searchProblem.isGoal(succ.get(i))){
						return path;
					}
					break;
				}
				else{
					count++;
				}
			}
			if(count == succ.size()){
				stack.pop();
				path.remove(path.size() - 1);
			}
		}
		
		return path;
	}
}
