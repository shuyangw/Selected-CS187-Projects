package structures;

public class Node<T> {
	private T data;
	private Node<T> next;
	
	public Node(T data){
		this.data = data;
		next = null;
	}
	
	public Node(T data, Node<T> next){
		this.next = next;
		this.data = data;
	}
	
	public T getData(){
		return data;
	}
	
	public Node<T> getNext(){
		return next;
	}
	
	public void setData(T data){
		this.data = data;
	}
	
	public void setNext(Node<T> next){
		this.next = next;
	}
}
