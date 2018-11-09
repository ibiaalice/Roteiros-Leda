

package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap Ã© definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparaÃ§Ã£o nÃ£o Ã© feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador Ã© utilizado para fazer as comparaÃ§Ãµes da heap. O ideal Ã©
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap nÃ£o precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @Override
   public T[] toArray() {
      ArrayList<T> resp = new ArrayList<T>();
      
      for (int i = 0; i <= index; i++) 
    	  if (heap[i]!=null) 
    		  resp.add(heap[i]);
      
      return (T[]) resp.toArray(new Comparable[0]);
   }

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
	   if (position >= 0 && position <= this.index) {
		   
		   int left = left(position);
		   int right = right(position);
		   int largest = position;
		   
		   if (left <= this.index && this.getComparator().compare(this.heap[largest], this.heap[left]) < 0)
			   largest = left;
		   
		   if (right <= index && this.getComparator().compare(this.heap[largest], this.heap[right]) < 0)
			   largest = right;
		   
		   if (largest != position) {
			   Util.swap(this.heap, largest, position);
			   heapify(largest);
		   }
	   }
		   
   }

   @Override
   public void insert(T element) {
      if (index == heap.length - 1) 
         this.heap = Arrays.copyOf(this.heap, this.heap.length + INCREASING_FACTOR);
      
      if (element!=null) {
    	  this.heap[++this.index] = element;
    	  int aux = this.index;
    	  
    	  while (aux > 0 && getComparator().compare(this.heap[aux], this.heap[parent(aux)] ) > 0) {
    		  
    		  Util.swap(this.heap, aux,parent(aux));
    		  aux = parent(aux);
    		  
    	  }
      }
      
   }

   @Override
   public void buildHeap(T[] array) {
	   if (array != null) {
			this.heap = array;
			this.index = array.length - 1;
			
			for (int i = this.heap.length - 1; i >= 0; i--)
				heapify(i);
	   }
   }

   @Override
   public T extractRootElement() {
	   if (isEmpty()) return null;
	   
	   T aux = rootElement();
	   Util.swap(this.heap, 0, this.index--);
	   heapify(0);
	   
	   return aux;
   }

   @Override
   public T rootElement() {
	   if (isEmpty()) return null;
	   
	   return heap[0];
   }

   @SuppressWarnings("unchecked")
   @Override
   public T[] heapsort(T[] array) {

		if (array == null) return null;
		
		buildHeap(array);
		T[] sorted = (T[]) new Comparable[size()];
		
		for (int i = 0; i < sorted.length; i++) 
			sorted[i] = extractRootElement();
		
		if (sorted[0].compareTo(sorted[1]) > 0)
			reverseToArray(sorted);
		
		return sorted;
   }
	
	

   @Override
   public int size() {
	   return index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }
   
   private static void reverseToArray(Object[] array) {
	   
		for (int i = 0; i < array.length/2; i ++) 
			Util.swap(array, i, array.length - i - 1);
		
   }
}