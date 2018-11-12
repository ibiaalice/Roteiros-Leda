import java.util.*;

class Fila{
	
	private String[] array;
	private int tail;
	
	public Fila(int n) {
		this.array = new String[n];
		this.tail = -1;	
	}
	
	/*
	 * método de adição na fila 
	 */
	public boolean add(String num) {
		if(isFull()) return false; //caso não adicione , retorna falso
		
		this.tail += 1;
		array[tail] = num;
		
		return true; 
	}
	
	public boolean remove() {
		if (isEmpty()) return false;
		
		shiftLeft(); // diminui o tail no shiftLeft
		this.tail -= 1;
		return true;
	}
	
	/*
	 * metódo faz o afastamento de todos os elementos para o inicio do array
	 */
	private void shiftLeft() { //perguntar a jão se isso é válido. tava em Leda :<

		for(int i = 0; i < tail ; i++ ) {
			swap(i, i + 1);
		}
		
	}
	
	private void swap(int i, int j) {
		String aux = this.array[i];
		this.array[i] = this.array[j];
		this.array[j] = aux;
	}
	
	
	/*
	 * método de retorno do element
	 */
	public String head() {
		String retorno = "";
		if (isEmpty()) {retorno = "null";}
		else {
			retorno += array[0];
		}
		return retorno;
	}

	/*
	 * metódo privado pra checar se está vazio
	 */
	private boolean isEmpty() {
		return this.tail == -1;
	}

	/*
	 * método privado para checar se está cheia
	 */
	private boolean isFull() {
		return this.tail == array.length -1 ;
	}
	
	/*
	 * concatena todas os elementos da fila para impressão
	 */
	private String print() {
		String fila = "empty";
		if(!isEmpty()) {
			fila = "";
			for(int i = 0; i <= this.tail ; i++) {
				fila += array[i] + " ";
			}
		}
		return fila.trim();
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Fila fila = new Fila(n);
		
		while(true) {
			
			String ask = sc.nextLine().trim();
			String[] asked = ask.split(" ");
			
			if(ask.equals("end")) break;
			
			if(ask.equals("print")) System.out.println(fila.print());
			
			if(ask.equals("element")) {
				if(!fila.isEmpty())
					System.out.println(fila.head());
				else 
					System.out.println("empty");
				
			}
			
			if(asked[0].equals("add")) {
				String num = asked[1];
				
				if(!fila.add(num)){ System.out.println("full");
				
				}
			}	
			if(asked[0].equals("remove")) {
				if(!fila.remove()) System.out.println("empty");
					
			}	
			
		}
		
	}
	
}