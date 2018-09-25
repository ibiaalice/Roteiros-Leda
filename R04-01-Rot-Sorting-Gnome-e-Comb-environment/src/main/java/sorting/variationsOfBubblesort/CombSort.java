package sorting.variationsOfBubblesort;
import util.Util;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex
	            || leftIndex > array.length) return;
		
			int tam = rightIndex - leftIndex + 1;
			double fator = 1.25;
			int gap = (int) (tam / fator);
			int k = 0;

			while ((gap/fator) > 0) {
				if ((k + gap) > rightIndex) {
					gap = (int) (gap/fator);
					k = 0;
				} else if (array[k].compareTo(array[k + gap]) > 0) {
					Util.swap(array, k, k + gap);
					k++;
					
				} else {
					k++;
				
			}
		}

	}
	
	public static void main(String[] args) {
		Integer[] array = new Integer[] {1, 3, 2, 4, 1, 6};//lista par
		Integer[] arrayImpar = new Integer[] { 6,2, 1, 565,3,0,2}; //lista impar
		Integer[] arrayZero = new Integer[] {};
		CombSort<Integer> n = new CombSort<>();
		n.sort(array, 0, array.length -1);
		n.sort(arrayImpar);
		n.sort(arrayZero);
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(arrayImpar));
		System.out.println(Arrays.toString(arrayZero));
	}
}
