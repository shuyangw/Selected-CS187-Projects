package structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LLIterator<T> implements Iterator<T> {
	private Node<T> head;
	
	public LLIterator(Node<T> head){
		this.head = head;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return head != null;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(head != null){
			T data = head.getData();
			head = head.getNext();
			return data;
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public void remove(){
		throw new UnsupportedOperationException();
	}

}
