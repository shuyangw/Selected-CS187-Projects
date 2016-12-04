package hanoi;

import java.util.NoSuchElementException;

import structures.*;

/**
 * A {@link StackBasedHanoiPeg} is an implementation of {@link HanoiPeg}.
 * 
 * @author jcollard
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	private Node<HanoiRing> top;
	private int size;

	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	public StackBasedHanoiPeg() {
		size = 0;
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if (ring != null) {
			if (size == 0) {
				top = new Node<HanoiRing>(ring, top);
				size++;
			} else if (ring.getSize() < top.getData().getSize()) {
				top = new Node<HanoiRing>(ring, top);
				size++;
			} else {
				throw new IllegalHanoiMoveException(null);
			}
		}
	}
	
	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if (top == null)
			throw new IllegalHanoiMoveException(null);
		Node<HanoiRing> oldTop = top;
		top = top.getNext();
		size -= 1;
		return oldTop.getData();
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if (top == null)
			throw new IllegalHanoiMoveException(null);
		return top.getData();
	}

	@Override
	public boolean hasRings() {
		return top != null;
	}
}
