package sorting.test;
import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.CombSort;
import sorting.variationsOfBubblesort.GnomeSort;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class CombsortTest {

	private Integer[] arrayImpar;
	private Integer[] arrayPar;
	private Integer[] arrayZero;
	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() throws Exception {
		implementation = new GnomeSort<>();
		arrayImpar = new Integer[]{ 2 , 1 , 3 , 4, 5};
		arrayPar = new Integer[] { 3 , 1 , 2 , 5};
		arrayZero = new Integer[] {};
	}

	@Test
	public void testArrayimpar() {
		Integer[] copy = Arrays.copyOf(arrayImpar, arrayImpar.length);
		Arrays.sort(copy);
		implementation.sort(arrayImpar, 0, arrayImpar.length - 1 );
		
		assertArrayEquals(arrayImpar,copy );
	}
	
	@Test
	public void testArrayPar() {
		Integer[] copy = Arrays.copyOf(arrayPar, arrayPar.length);
		Arrays.sort(copy);
		implementation.sort(arrayPar, 0 , arrayPar.length - 1);
		assertArrayEquals(arrayPar, copy);
	}
	
	@Test
	public void testArrayZero() {
		Integer[] copy = Arrays.copyOf(arrayZero, arrayZero.length);
		Arrays.sort(copy);
		implementation.sort(arrayZero);
		assertArrayEquals(arrayZero, copy);
	}

}
