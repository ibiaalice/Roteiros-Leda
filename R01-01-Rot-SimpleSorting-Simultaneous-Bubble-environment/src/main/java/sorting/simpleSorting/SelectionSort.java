package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) return;
		for (int i = leftIndex; i < rightIndex + 1; i++) {
			int menor = leftIndex;
			for (int j = leftIndex; j <rightIndex + 1;j++) {
				if (array[menor].compareTo(array[j]) < 0) {
					menor = j;
				}
			}
			swap(array, i, menor);
		}
	}
	
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
