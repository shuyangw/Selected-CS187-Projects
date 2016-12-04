package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		List<T> pred = new ArrayList<T>();
		List<T> actpath = new ArrayList<T>();
		T initial = searchProblem.getInitialState();
		queue.add(initial);
		visited.add(initial);
		pred.add(initial);
		T a; 
		while(!queue.isEmpty()){
			T top = queue.remove();
			while((a = nextUnvisited(top)) != null){
				pred.add(top);
				visited.add(a);
				queue.add(a);
				if(searchProblem.isGoal(a)){
					T curr = a;
					actpath.add(curr);
					int currInd = visited.size() - 1;
					while(curr != initial){
						currInd = visited.indexOf(curr);
						curr = pred.get(currInd);
						actpath.add(curr);
					}
					
					Collections.reverse(actpath);
					return actpath;
				}
			}
		}
		return actpath;
	}
	
	public int numUnvisited(T node){
		List<T> succ = searchProblem.getSuccessors(node);
		Iterator<T> iter = succ.iterator();
		int count = 0;
		while(iter.hasNext()){
			T data = iter.next();
			if(!visited.contains(data)){
				count++;
			}
		}
		return count;
	}
	
	public T nextUnvisited(T node){
		List<T> succ = searchProblem.getSuccessors(node);
		Iterator<T> iter = succ.iterator();
		while(iter.hasNext()){
			T data = iter.next();
			if(!visited.contains(data)){
				return data;
			}
		}
		return null;
	}
}
