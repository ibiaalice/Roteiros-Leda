import java.util.*;

class LinkedList {
	private Node head;

	public LinkedList() {
		this.head = null;
	}
	
	public void addLast(int n) {
		
		if(isEmpty())
			this.head = new Node(n);
		else 
			addLast(n, this.head);
		
		
	}
	
	public void addLast(int n , Node node) {
		if(node.next == null) {
			Node newNode = new Node(n);
			node.next = newNode;
			newNode.prev = node;
			
		}else addLast(n, node.next);
		
	}
	
	public int search(int n) {
		if(!contains(n, this.head)) return -1;
		return search( n, this.head) ;
	}
	
	
	private int search(int n, Node node) {
		if(node.getValor() == n) return 0;
		return 1 + search(n , node.next);
	}
	
	private boolean contains(int n, Node node) {
		if(node == null) return false;
		if(node.getValor() == n) return true;
		return contains(n, node.next);
	}

	public void removeFist() {
		if(isEmpty()) return;
		else {
			if(head.next == null) {
				this.head = null;
			}else {
				Node newNode = this.head.next;
				this.head = newNode;
				this.head.prev = null;
			}
		}
	}
	
	public String toString() {
		return toString(this.head);
	}
	
	private String toString(Node node) {
		if(node == null) return "";
		
		return node.getValor() + " " + toString(node.next);
		
	}
	
	
	public int head() {
		return this.head.getValor();
	}

	public boolean isEmpty() {
		return this.head == null;
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList linked = new LinkedList();
		
		while(true) {
			String opcao = sc.nextLine().trim();
			
			if (opcao.equals("end")) break;
			String[] opcoes = opcao.split(" ");
			
			//saga dos prints
			if(opcoes[0].equals("add")) {
				int n = Integer.parseInt(opcoes[1]);
				linked.addLast(n);
			}
			if(opcoes[0].equals("remove")) {
				if(linked.isEmpty()) System.out.println("empty");
				else linked.removeFist();
			}
			if(opcoes[0].equals("print")) {
				if(linked.isEmpty()) System.out.println("empty");
				else System.out.println(linked.toString().trim());
			}
			if(opcoes[0].equals("element")) {
				if(linked.isEmpty()) System.out.println("empty");
				else System.out.println(linked.head());
			}
			if(opcoes[0].equals("search")) {
				int n = Integer.parseInt(opcoes[1]);
				System.out.println(linked.search(n));
			}
			
			
			
			
		}
		
		
		
	}

}



class Node{
	private int valor;
	Node next;
	Node prev;
	
	public Node(int n) {
		this.valor = n;
		this.next = null;
		this.prev = null;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	
	
	
}
