package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListInterfaceTest {

	private RecursiveList<String> list;
	
	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}
	
	@Test (timeout = 1000)
	public void custom() {
		list.insertFirst("asd");
		assertEquals(1, list.size());
		assertEquals("asd", list.getFirst());
		list.insertFirst("asdf");
		list.insertFirst("asdfg");
		//assertEquals("asd", list.head.getNext().getNext().getData());
		assertEquals(3, list.size());
		assertEquals("asdf", list.get(1));
		assertEquals(1, list.indexOf("asdf"));
		assertEquals("asd", list.getLast());
		//print();
		assertEquals("asdfg", list.removeFirst());
	}
	
	@Test (timeout = 1000)
	public void custom2(){
		list.insertLast("a");
		list.insertLast("b");
		list.insertLast("c");
		list.insertLast("d");
		list.insertLast("e");
		list.insertLast("f");
		list.insertLast("g");
		list.insertLast("h");
		//print();
	}
	
	@Test
	public void testInsertFirst(){
		list.insertFirst("T");
		
		list.insertLast("A");
		
		list.insertLast("B");
		
		list.insertLast("C");
		
		list.insertLast("D");
		
		list.insertLast("E");
		
		//System.out.println(list.head.getData());
		assertTrue(list.remove("T"));
		
		assertTrue(list.remove("E"));
		
		list.insertAt(0, "P");
		//System.out.println(list.size());
		
		//list.insertAt(4, "Z");
		//list.removeFirst();
		//print();
		//print();
	}
	
	@Test
	public void testInsertAt(){
		list.insertFirst("A");
		assertEquals("A",list.removeFirst());
		assertEquals(0, list.size());
		list.insertFirst("A");
		assertEquals(1, list.size());
		assertEquals("A", list.removeLast());
		assertEquals(0, list.size());
		list.insertLast("B");
		assertEquals(1, list.size());
		list.insertLast("C");
		assertEquals(2, list.size());
		list.insertLast("D");
		assertEquals(3, list.size());
		assertEquals("D", list.removeAt(2));
		assertEquals("B", list.get(0));
		assertEquals("C", list.get(1));
		assertEquals("C", list.removeAt(1));
		list.insertLast("A");
		list.insertLast("D");
		list.insertLast("C");
		//print();
		assertEquals(true, list.remove("C"));
		assertEquals("D", list.removeLast());
		assertEquals(-1, list.indexOf("osdo"));
		assertEquals("A", list.getLast());
		assertEquals("B", list.getFirst());
		assertEquals("A", list.removeLast());
		assertEquals("B", list.getLast());
		
		list.insertAt(0, "Z");
		assertEquals("Z", list.get(0));
		assertEquals("B", list.get(1));
		list.insertAt(1, "X");
		list.insertAt(1, "T");
		assertEquals(4, list.size());
		assertEquals("X", list.get(2));
		assertEquals("B", list.get(3));
		//list.insertAt(0, "ZC");
		
		//print();
	}
	
	@Test
	public void cust3(){
		 list.insertAt(0, "A");
		 list.insertAt(0, "B");
		 list.insertAt(0, "C");
		 list.insertLast("A-1");
		 list.removeAt(0);
		 list.removeAt(0);
	//	 System.out.println(list.size());
		 list.removeFirst();
		 assertTrue(list.remove("A-1"));
		 list.insertAt(0, "A");
		 list.insertAt(0, "B");
		 list.remove("Y");

		 print();
	}
	
	public void print(){
		Node<String> cN = list.head;
		while(cN != null){
			System.out.println(cN.getData());
			cN = cN.getNext();
		}
		System.out.println();
	}
	
	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
}
