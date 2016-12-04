package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementation<T> implements ListInterface<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;
	
	public ListImplementation(){
		size = 0;
		head = null; tail = null;
	}
	
	public ListImplementation(Node<T> head){
		this.head = head; this.tail = head;
		
	}
	
	public ListImplementation(T e){
		this.head = new Node<T>(e, null);
		//size++;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	@Override
	public T get(int n) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(n > size - 1 || n < 0)
			throw new NoSuchElementException();
		
		Node<T> cN = head;
		for(int i = 0; i < n; i++){
			cN = cN.getNext();
		}
		
		return cN.getData();
	}

	@Override
	public ListInterface<T> append(T elem) {
		// TODO Auto-generated method stub		
		//System.out.println(elem);
		if(elem == null)
			throw new NullPointerException();
		
		ListImplementation<T> returned = this;
		//Node<T> currHead = head;
		Node<T> newEle = new Node<T>(elem, null);

		
		if(size == 0){
			head = newEle;
			//tail = newEle;
			size++;
			return returned;
		}
		if(size == 1){
			head.setNext(newEle);
			tail = newEle;
			size++;
			return returned;
		}
		
		tail.setNext(newEle);
		tail = newEle;
		size++;
		return returned;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iter<T>(head);
	}
}

