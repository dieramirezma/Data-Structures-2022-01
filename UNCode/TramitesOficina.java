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

class Queue {
	Node front, rear;
	int size;
	
	public Queue() {
		rear = null;
		size = 0;
	}
	
	public void enqueue (Object data) {
		Node node = new Node(data);
		
		if (front == null) {
			front = node;
			rear = node;			
		} else {
			rear.setNext(node);
			rear = node;
		}
		size++;
	}
	
	public Object dequeue() {
		if (front == null)
			return null;
		Object data = front.getData();
		front = front.getNext();
		size--;
		return data;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	public Object front() {
		if (front == null)
			return null;
		else 
			return front.getData();
	}
	
	public Object getIndex(int index, Queue queue) {
		Object o = 0;
		Node piv = queue.front;
		for (int i = 0; i<=index; i++) {
			if (i == index)
				piv.setData(piv.getData().toString());
			o = piv.getData();
			piv = piv.getNext();
		}
		return o;
	}
	
	public static void display(Queue queue) {
		StringBuilder s = new StringBuilder("[");
		Node x = queue.front;
        
		while (x != null) {
			s.append(x.getData().toString() + ", ");
			x = x.getNext();
		}
		
		s.setLength(s.length() - 2);
		s.append("]");
		System.out.println(s);
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		int n = sc.nextInt();
		String subStr = str.substring(1, str.length()-1);
		Queue queue = new Queue();
		
		String[] entryArray = subStr.split(",");
		for (int i = 0; i<entryArray.length; i++) 
			queue.enqueue(Integer.parseInt(entryArray[i]));
		
		int pivoteInt = Integer.parseInt(queue.getIndex(n, queue).toString());
		int count = 0;
		while (pivoteInt != 0) {
			if (queue.front().equals(queue.front().toString())) {
				queue.dequeue();
				pivoteInt--;
				queue.enqueue(String.valueOf(pivoteInt));
				count++;
			} else {
				int x = Integer.parseInt(queue.dequeue().toString()) - 1;
				if (x != 0) {
					queue.enqueue(x);					
				}
				count++;
			}
		}
		System.out.println(count);
	}
}
