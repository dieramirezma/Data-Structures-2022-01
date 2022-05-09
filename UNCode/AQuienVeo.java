
import java.util.Scanner;


class Node {
	private Object data;
	private Node next;
	
	public Node(Object data) {
		this.data = data;
		this.next = null;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public Node getNext() {
		return this.next;
	}

}

class Stack {
	
	Node front, top;
	int size;
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	public void push(Object data) {
		Node element = new Node(data);
		if (top == null) {
			front = element;
			top = element;
		} else {
			top.setNext(element);
			top = element;
		}
		size++;
	}
	
	public Object pop() {
		if (top == null) 
			return null;
		
		Object data = top.getData();
		
		Node x = null;
		Node piv = front;
		
		for (int i = 1; i<size; i++) {
			piv = piv.getNext();
		}
		top = piv;
		piv.setNext(null);
		
		size--;
		
		return data;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	public Object top() {
		if (top == null) 
			return null;
		else 
			return top.getData();
	}
	
	public static void display(Stack stack) {
		StringBuilder s = new StringBuilder("[");
		
		Node x = stack.front;

		while (x != null) {
			s.append(x.getData().toString() + ", ");
			x = x.getNext();
		}
		
		s.setLength(s.length() - 2);
		
		s.append("]");
		
		System.out.println(s);
		
	}	
	
	public static int min(int a, int b) {
		return (a<b) ?  a :  b;
	}
	
	public static int max(int[] array, int a, int b) {
		int maxValue = 0;
		for (int i = a; i <= b; i++) {
			if (array[i] > maxValue)
				maxValue = array[i];
		}
		
		return maxValue;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Stack stack = new Stack();
		
		String str = sc.nextLine();
		String subStr = str.substring(1, str.length()-1);

		String[] entryArray = subStr.split(",");
		int[] alturas = new int[entryArray.length];
		int[] answer = new int[entryArray.length];
		for (int i = 0; i<entryArray.length; i++) 
			alturas[i] = Integer.parseInt(entryArray[i]);
		
		int piv = 0;

		for (int i = 0; i < entryArray.length; i++) {

			for (int j = 1; j < entryArray.length; j++) {
				if (i<j && Stack.min(alturas[i],alturas[j]) > Stack.max(alturas, i+1, j-1))
					answer[i]++;
			}
		}
		
		for (int x: answer)
			stack.push(x);
		stack.display(stack);
	}
}