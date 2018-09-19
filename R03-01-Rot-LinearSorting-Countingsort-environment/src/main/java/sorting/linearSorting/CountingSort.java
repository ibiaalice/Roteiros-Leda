package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex
            || leftIndex > array.length)
         return;
      int k = maiorElemento(array, leftIndex, rightIndex);

      Integer[] c = new Integer[k + 1];
      completaArray(c);

      //contador
      for (int i = 0; i < rightIndex + 1; i++) {
         c[array[i]]++;
      }
      
      //frequencia
      for (int i = 1; i < c.length; i++) {
         c[i] = c[i] + c[i - 1];
      }
      //array auxiliar
      Integer[] b = new Integer[array.length];
      //ordenacao
      for (int i = rightIndex; i >= 0; i--) {
         b[c[array[i]] - 1] = array[i];
         c[array[i]]--;
      }
      copiavetor(array, b);
   }

   // 

   private void copiavetor(Integer[] array, Integer[] b) {
      for (int i = 0; i < array.length; i++) {
         array[i] = b[i];
      }

   }

   private void completaArray(Integer[] b) {
      for (int i = 0; i < b.length; i++)
         b[i] = 0;
   }

   private int maiorElemento(Integer[] array, int leftIndex, int rightIndex) {
      int maior = -999999;

      for (int i = leftIndex; i < rightIndex + 1; i++) {
         if (array[i] >= maior)
            maior = array[i];
      }

      return maior;
   }

}
