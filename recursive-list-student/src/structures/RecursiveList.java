package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {
	private int size;
	public Node<T> head;
	
	
	public RecursiveList(){
		head = null;
		size = 0;
	}
	
	
	public RecursiveList(Node<T> head){
		this.head = head;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new LLIterator<T>(head);
	}

	
	@Override //O(1)
	public int size() {
		return size;
	}

	
	@Override //O(1)
	public ListInterface<T> insertFirst(T elem) {
		Node<T> newNode = new Node<T>(elem);
		newNode.setNext(head);
		head = newNode;
		size++;	
		return this;
	}

	
	@Override //O(n) where n is size
	public ListInterface<T> insertLast(T elem) {
		if(head == null){
			this.insertFirst(elem);
			return this;
		}
		else{
			if(head.getNext() == null){
				head.setNext(new Node<T>(elem));
			}
			else{
				RecursiveList<T> recurse = new RecursiveList<T>(head);
				recurse.makeAdd(recurse, elem, null);
			}
		}
		size++;
		return this;
	}
	public void makeAdd(RecursiveList<T> list, T data, Node<T> hold){
		if(hold == null){
			hold = list.head;
			makeAdd(list, data, hold);
		}
		else{
			if(hold.getNext() != null){
				hold = hold.getNext();
				makeAdd(list, data, hold);
			}	
			else{
				hold.setNext(new Node<T>(data));
			}
		}
	}

	
	@Override //O(n) where n is index
	public ListInterface<T> insertAt(int index, T elem) {
		if(elem == null) throw new NullPointerException();
		if(index == 0){
			insertFirst(elem);
			return this;
		}
		else if(index == size){
			this.insertLast(elem);
			return this;
		}
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		else{
			if(index <= this.size() - 1){
				add(this, index, 0, null, elem);
			}
		}
		size++;
		return this;
	}
	public void add(RecursiveList<T> list, int ind, int hold, Node<T> holdN, T elem){
		if(holdN == null){
			holdN = list.head;
			add(list, ind, hold, holdN, elem);
		}
		else{
			if(ind != hold + 1){
				holdN = holdN.getNext();
				add(list, ind, hold + 1, holdN, elem);
			}
			else{
				Node<T> save = holdN.getNext();
				Node<T> newNode = new Node<T>(elem);
				newNode.setNext(save);
				holdN.setNext(newNode);
			}
		}
	}

	
	@Override //O(1)
	public T removeFirst() {
		if(head == null) throw new IllegalStateException();
		T data = this.head.getData();
		head = head.getNext();
		size--;
		return data;
	}

	
	@Override //O(n) where n is size
	public T removeLast() {
		T data;
		if(head == null){
			throw new IllegalStateException();
		}
		if(size == 1){
			data = head.getData();
			this.removeFirst();
			return data;
		}
		size--;
		return makeRemove(this, null);
	}
	public T makeRemove(RecursiveList<T> list, Node<T> hold){
		T ret = null;
		if(hold == null){
			hold = list.head;
			return makeRemove(list, hold);
		}
		else{
			if(hold.getNext().getNext() != null){
				hold = hold.getNext();
				return makeRemove(list, hold);
			}
			else{
				ret = hold.getNext().getData();
				hold.setNext(null);
				return ret;
			}
		}
		
	}

	
	@Override //O(n) where n is index
	public T removeAt(int i) {
	//	RecursiveList<T> recurse = new RecursiveList<T>(head);
		if(i >= size || i < 0) throw new IndexOutOfBoundsException();
		if(head == null) return null;
		T data = null;
		if(i == 0){
			return this.removeFirst();
		}
		else data = doINeedSleep(this, i, null, 0);
		size--;
		return data;
	}
	public T doINeedSleep(RecursiveList<T> list, int i, Node<T> hold, int count){
		if(hold == null){
			hold = list.head;
			return doINeedSleep(list, i, hold, count);
		}
		else{	
			if(i - 1 != count){
				hold = hold.getNext();
				return doINeedSleep(list, i, hold, count + 1);
			}
			else{
				Node<T> ret = hold.getNext();
				Node<T> next = hold.getNext().getNext();
				hold.setNext(next);
				return ret.getData(); 
			}
		}
	}

	
	@Override //O(1)
	public T getFirst() {
		if(head == null) throw new IllegalStateException();
		return head.getData();
	}

	
	@Override //O(n)
	public T getLast() {
		if(head == null) throw new IllegalStateException();
		RecursiveList<T> recurse = new RecursiveList<T>(head);
		return recurse.makeSmallerUntilLast(recurse).getFirst();
	}
	public RecursiveList<T> makeSmallerUntilLast(RecursiveList<T> list){
		if(list.head.getNext() != null){
			list = new RecursiveList<T>(list.head.getNext());
			return makeSmallerUntilLast(list);
		}
		else{
			return list;
		}
	}

	
	@Override //O(n) where n is index
	public T get(int i) {
		if(head == null) return null;
		if(i == 0) return this.getFirst();
		else if(i == size - 1) return this.getLast();
		if(i < 0 || i >= size) throw new IllegalStateException();
		RecursiveList<T> recurse = new RecursiveList<T>(head);
		return recurse.makeSmallerUntilInd(recurse, i).getFirst();
	}
	public RecursiveList<T> makeSmallerUntilInd(RecursiveList<T> list, int i){
		if(i != 0){
			list = new RecursiveList<T>(list.head.getNext());
			return makeSmallerUntilInd(list, i - 1);
		}
		else{
			return list;
		}
	}

	
	@Override //O(n) where n is size
	public boolean remove(T elem) {	
		if(elem == null) throw new NullPointerException();
		if(head == null) return false;
		if(makeSmallerUntilObj(elem, this, null) == null)
			return false;
		else{
			size--;
			return true;
		}
	}
	public RecursiveList<T> makeSmallerUntilObj(T search, RecursiveList<T> list, Node<T> hold){
		if(hold == null){
			hold = list.head;
			if(hold.getData() == search){
				RecursiveList<T> newL = new RecursiveList<T>(list.head);
				list.head = list.head.getNext();
				return newL;
			}
			return makeSmallerUntilObj(search, list, hold);
		}
		else{
			if(hold.getNext() == null) return null;
			if(hold.getNext().getData() != search){
				hold = hold.getNext();
				return makeSmallerUntilObj(search, list, hold);
			}
			else if(hold.getNext().getData() == search){
				hold.setNext(hold.getNext().getNext());
				return list;
			}
			else {
				return null;
			}
		}
	}
	

	@Override //O(n) where n is size
	public int indexOf(T elem) {
		if(head == null) return -1;
		if(elem == null) throw new NullPointerException();
		RecursiveList<T> recurse = new RecursiveList<T>(head);
		if(recurse.makeSmallerUntilObjInt(recurse, elem, 0) <= -1){
			return -1;
		}
		return recurse.makeSmallerUntilObjInt(recurse, elem, 0);
	}
	public int makeSmallerUntilObjInt(RecursiveList<T> list, T data, int i){
		if(data != list.getFirst() || list.head == null){
			if(list.head.getNext() == null) return -1;
			list = new RecursiveList<T>(list.head.getNext());
			return makeSmallerUntilObjInt(list, data, i + 1);
		}
		else{
			return i;
		}
	}
	
	@Override //O(1)
	public boolean isEmpty() {
		return head == null;
	}
}