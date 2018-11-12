import java.util.Arrays;
import java.util.Scanner;

class BuildHeap {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] array = sc.nextLine().split(" ");
		MaxHeap heap = new MaxHeap();
		int[] buildHeap = heap.buildHeap(arrayInteiros(array));
		System.out.println(Arrays.toString(buildHeap));
		
		
	}
	

	
	
	private static int[] arrayInteiros(String[] array) {
		int[] arrayInt = new int[array.length];
		
		for (int i = 0; i < array.length; i++) 
			arrayInt[i] = Integer.parseInt(array[i]);
		
		return arrayInt;
	}
		
}

class MaxHeap {
	private int[] heap;
	private int tail;
	
	/**
	 * método de adição na buildHeap
	 * @param array recebe um array de inteiros para ser adicionados
	 * @return retorna o array de demonstração dos elementos na heap
	 */
	public int[] buildHeap(int[] array) {
		heap = array;
		tail = array.length - 1;
		
		// sai dando heapify em todos os elementos até o fim
		for (int i = parent(tail); i >= 0; i--) {
			heapify(i);
		}
		return this.heap;
	}

	/**
	 * método de Heapify nos elementos indicados na posição
	 * @param i indice atual
	 */
	private  void heapify(int i) {
		int maximo = indexMax(i, esquerda(i), direita(i));

		if (maximo != i) {
			swap(heap, i, maximo);
			heapify(maximo);
		}
	}
	/**
	 * procura o indice do maor elemento
	 * @param indice indice atual
	 * @param esquerda indice da sua esquerda
	 * @param direita indice da sua direita
	 * @return retorna o maior indice
	 */
	private int indexMax(int indice, int esquerda, int direita) {
		int posicao = indice;
		
		if (esquerda < tamanho()) 
			posicao = (this.heap[indice] > this.heap[esquerda]) ? indice : esquerda;
		if (direita < tamanho()) 
			posicao = (this.heap[posicao]> this.heap[direita]) ? posicao : direita;
		
		return posicao;
	}

	/**
	 * método que retorna o tamanho do heap
	 * @return o tamanho do heap
	 */
	public  int tamanho() {
		return tail + 1;
	}

	/**
	 * encontra o pai do elemento indicado pelo indice
	 * @param i indice do elemento a ter o pai procurado
	 * @return retorna o indice do pai do elemento
	 */
	public  int parent (int i) {
		return (i + 1) / 2;
	}
	
	/**
	 * encontra o filho do elemento a esquerda
	 * @param i indice do elemento que deseja se encontrar o filho
	 * @return retorna o indice do filho a esquerda
	 */
	public  int esquerda (int i) {
		return (i * 2 + 1);
	}
	
	/**
	 * encontra o filho do elemento a direita
	 * @param i indice do elemento que deseja se encontrar o filho
	 * @return retorna o indice  do filho a direita
	 */
	public int direita (int i) {
		return (i * 2 + 1) + 1;
	}
	
	
	/**
	 * Método de auxilio com as trocas de alocação de elementos
	 * @param array array a ser manipulado
	 * @param i indice 1
	 * @param j indice 2
	 */
	private void swap(int[] array, int i, int j) {
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}
	
	
}
