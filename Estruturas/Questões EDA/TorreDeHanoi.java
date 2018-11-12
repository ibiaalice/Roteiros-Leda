import java.util.*;

class TorreDeHanoi {
	private static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		int n = sc.nextInt();
		
		Pilha a = new Pilha(n, "A");
		Pilha c = new Pilha(n, "C");
		Pilha b = new Pilha(n, "B");
		insereNaPilha(a, n);
		torreHanoi(n, a, c, b);
		
	}
	 public static void torreHanoi(int discos, Pilha origem, Pilha destino, Pilha auxiliar) throws Exception {
	        if(discos > 0) {
	            torreHanoi(discos - 1, origem, auxiliar, destino);
	            System.out.println("Move o disco " + origem.peek() + " da haste " + origem.getNome() + " para a haste " + destino.getNome());
	            destino.push(origem.pop());
	            torreHanoi(discos - 1, auxiliar, destino, origem);
	        }
	 }
	
	
	private static void insereNaPilha(Pilha pilha, int nElementos) {
		
		for(int i = nElementos; i >= 1; i--) {
			pilha.push(i);
		}		
		
	}
	
}

class Pilha {
	private int pilha[];
	private int tamanho;
	private int top;
	private String nome;
	
	public Pilha(int tamanho, String nome) {
		this.tamanho = tamanho;
		this.top = -1;
		
		this.pilha = new int[tamanho];
		this.nome = nome;
	}
	
	public boolean isFull() {
		return this.top == this.tamanho - 1;
	}
	
	public boolean isEmpty() {
		return this.top == -1;
	}
	
	public void push(int n) {
		if(isFull()) {
			throw new IllegalArgumentException("full");
		}
		if(!isFull()) {
			top += 1;
			this.pilha[top] = n;
		}
	}
	
	public int pop() {
		if (isEmpty()) {
			throw new IllegalArgumentException("empty");
		}
		
		int aux = this.pilha[top];
		this.top -= 1;
		return aux;
	}
	
	public int peek() {
		if (isEmpty()) {
			throw new IllegalArgumentException("empty");
		}
		return this.pilha[top];
	}
	
	public String getNome() {
		return this.nome;
	}

}