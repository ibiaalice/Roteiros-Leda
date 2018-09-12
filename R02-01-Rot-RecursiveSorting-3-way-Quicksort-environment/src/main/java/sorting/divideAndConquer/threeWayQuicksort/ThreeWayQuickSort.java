package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;
public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		 if (leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) {
			 return;
		 } 
		 
		 int[] pivots = particiona(array, leftIndex, rightIndex);
	     sort(array, leftIndex, pivots[0] - 1);
	     sort(array, pivots[1] + 1, rightIndex);
	      
	   }

	   public int[] particiona(T[] array, int leftIndex, int rightIndex) {
	      int pivot = rightIndex;
	      int maiores = rightIndex - 1;
	      int[] posPivots = { leftIndex, leftIndex };
	  
	      for (posPivots[1] = leftIndex; posPivots[1] <= maiores; posPivots[1]++) {
	         if (array[posPivots[1]].compareTo(array[pivot])> 0) {
	            Util.swap(array, posPivots[1], maiores);
	            posPivots[1]--;
	            maiores--;
	         } else if (array[posPivots[1]].compareTo(array[pivot]) < 0) {
	            Util.swap(array, posPivots[0], posPivots[1]);
	            posPivots[0]++;
	         }
	      }
	      Util.swap(array, pivot, posPivots[1]);
	      return posPivots;
	   }
	}


