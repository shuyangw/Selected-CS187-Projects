  package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	public StackBasedHanoiPeg board[] = new StackBasedHanoiPeg[3];
	private int maxsize;
	
	public ArrayBasedHanoiBoard(int n) {
		maxsize = n;
		if(n < 0) throw new IllegalArgumentException();
		
		for(int i = 0; i < 3; i++){
			board[i] = new StackBasedHanoiPeg();
		}
		for(int i = n; i >= 1; i--){
			board[0].addRing(new HanoiRing(i));
		}
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if(move == null) throw new NullPointerException();
		
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}
		board[move.getToPeg()].addRing(board[move.getFromPeg()].remove());
		
	}

	@Override
	public boolean isSolved() {
		if(maxsize == 0) return true;
		if(!board[0].hasRings() && !board[1].hasRings())
			return true;
		return false;
	}

	@Override
	public boolean isLegalMove(HanoiMove move) {
		int to = move.getToPeg();
		int from = move.getFromPeg();
		if(!board[from].hasRings())
			return false;
		if(!board[to].hasRings())
			return true;
		if(board[to].getTopRing().getSize() <= board[from].getTopRing().getSize())
			return false;
		else
			return true;
	}
}
