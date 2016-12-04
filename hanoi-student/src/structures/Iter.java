package structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iter<E> implements Iterator<E>{
	Node<E> head;
	
	public Iter(Node<E> head){
		this.head = head;
	}
	
	@Override
	 public boolean hasNext() {
	    // TODO (3)
		  return head != null;
	  }

	@Override
	public E next() {
	    // TODO (4)
		 if(head != null){
			 E data = head.getData();
			 head = head.getNext();
			 return data;
		 }
		 throw new NoSuchElementException();
	  }
}
