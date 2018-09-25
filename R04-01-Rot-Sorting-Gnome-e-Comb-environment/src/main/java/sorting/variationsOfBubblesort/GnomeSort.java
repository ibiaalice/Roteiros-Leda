package sorting.variationsOfBubblesort;

import util.Util;
import sorting.AbstractSorting;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int pivo = leftIndex + 1;

		
		while (pivo <= rightIndex) { //bubble com O(n)
			
			if (pivo == leftIndex) {
				pivo++;
			} else if (array[pivo].compareTo(array[pivo - 1]) < 0) {
				Util.swap(array, pivo, pivo - 1);
				pivo--;
			} else {
				pivo++;
				
				
			}
		}
	}
}
