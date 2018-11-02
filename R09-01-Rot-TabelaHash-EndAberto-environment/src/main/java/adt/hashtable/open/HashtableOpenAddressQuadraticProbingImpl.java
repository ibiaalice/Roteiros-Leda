package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) throw new HashtableOverflowException();

	      if (element != null) {
	         for (int i = 0; i < table.length; i++ ) {
	            int chave = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
	            if (element instanceof Storable ) {
	               T chaveAux = (T) table[chave];
	               
	               if (chaveAux == null || chaveAux.equals(deletedElement)) {
	                  super.table[chave] = element;
	                  super.elements += 1;
	                  
	                  break;
	               }
	               
	               super.COLLISIONS += 1;
	            }
	         }
	         
	      }
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			
	         for (int i = 0; i < table.length; i++ ) {
	            int chave = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element , i);
	            
	            if (table[chave] instanceof Storable ) {
	               T chaveAux = (T) table[chave];
	            
	               if (chaveAux != null) {
	                  if (chaveAux.equals(element) ) {
	                	  
	                     super.table[chave] = deletedElement;
	                     super.elements -= 1;
	                  }
	                  
	               }
	            }
	         }
	      }
	}

	@Override
	public T search(T element) {
		if (!isEmpty() && element != null) {
			
	         for (int i = 0; i < table.length; i++) {
	            int chave = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
	            
	            if (table[chave] instanceof Storable ) {
	               T chaveAux = (T) super.table[chave];
	               
					if (chaveAux != null ) {
						if (chaveAux.equals(element))
							return chaveAux;
					}
	           }
	         }
	      }
	      return null;
	}

	@Override
	public int indexOf(T element) {
		
		if (!isEmpty() && element != null) {
			
	         for (int i = 0; i < table.length; i++) {
	            int chave  = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
	            
	            if ( super.table[chave ] instanceof Storable)  {
	               T chaveAux = (T) super.table[chave ];
	               
	               if (chaveAux != null)
	                  if (!chaveAux.equals(deletedElement) && chaveAux.equals(element) )
	                     return chave ;
	            }
	         }
	      }
	      return -1;
	}
}
