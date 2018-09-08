package sorting.simpleSorting;
import util.Util;
import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override // feito
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) return;
		
		for (int j = leftIndex; j < rightIndex; j++) {
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					Util.swap(array, i, i + 1);
				}
			}
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
