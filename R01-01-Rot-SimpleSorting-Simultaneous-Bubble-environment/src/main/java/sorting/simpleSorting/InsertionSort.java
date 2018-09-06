package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override //feito
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) return;	
		
		for(int i = leftIndex + 1; i < rightIndex; i++) {
			int j = i;
			while(j > leftIndex && array[j].compareTo(array[j - 1]) < 0) {
				swap(array, j , j - 1);
			}
			
			
			
		}
	}//fdm
	
	/**
	 * Método de troca de elementos
	 * 
	 * @param array lista de elementos a serem trocados
	 * @param i     primeiro indice
	 * @param j     segundo indice
	 */
	private void swap(T[] array, int i, int j) {

		T aux = array[i];
		array[i] = array[j];
		array[j] = aux;

	}
	
	
	
}
