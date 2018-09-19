package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex
            || leftIndex > array.length)
         return;

      int k = maiorElemento(array, leftIndex, rightIndex);
      int m = menorElemento(array, leftIndex, rightIndex);

      // array auxiliar
      Integer[] b = new Integer[k - m + 1];
      completaArray(b);

      //cumulativa
      for (int i = leftIndex; i <= rightIndex; i++) {
         b[array[i] - m]++;
      }

      int i = leftIndex;
      int j = leftIndex;

      while (i < b.length) {
         while (b[i] != 0) {
            array[j] = i + m;
            j++;
            b[i]--;
         }
         i++;
      }

   }

   /**
    * método que encontra o maior elemento do array
    * @param array lista a ser procurada
    * @param leftIndex limite inferior
    * @param rightIndex limite superior
    * @return retornar o maior elemento
    */
   private int maiorElemento(Integer[] array, int leftIndex, int rightIndex) {
      int maior = -999999;

      for (int i = leftIndex; i < rightIndex + 1; i++) {
         if (array[i] >= maior)
            maior = array[i];
      }

      return maior;
   }

   /**
    * método que conpleta o array com 0, substituindo o null
    * @param b lista a ser completada
    */
   private void completaArray(Integer[] b) {
      for (int i = 0; i < b.length; i++)
         b[i] = 0;
   }

   /**
    * método que procura o menor elemento dentro do array
    * @param array array a ser procurado
    * @param leftIndex limite inferior
    * @param rightIndex limite superior
    * @return retorna o menor elemento
    */
   private int menorElemento(Integer[] array, int leftIndex, int rightIndex) {
      int menor = 99999999;

      for (int i = leftIndex; i < rightIndex + 1; i++) {
         if (array[i] < menor)
            menor = array[i];
      }

      return menor;
   }

}
