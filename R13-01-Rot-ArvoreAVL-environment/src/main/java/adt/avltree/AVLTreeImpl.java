package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int alturaEsquerda = super.height((BSTNode<T>) node.getLeft());
		int alturaDireita = super.height((BSTNode<T>) node.getRight());
		
		int balance = alturaEsquerda - alturaDireita;
		
		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		
		if (balance > 1) {
			this.rebalanceLeft(node);
		} else if (balance < -1) {
			this.rebalanceRight(node);
		}
	}

	private void rebalanceLeft(BSTNode<T> node) {
		int balance = calculateBalance((BSTNode<T>) node.getLeft());
		
		if (balance > 0) Util.rightRotation(node);
		else if (balance < 0) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
		}
		if (this.getRoot().equals(node)) this.root = (BSTNode<T>) node.getParent();
		
	}
	
	private void rebalanceRight(BSTNode<T> node) {
		int balance = calculateBalance((BSTNode<T>) node.getRight());
		
		if (balance < 0) Util.leftRotation(node);
		
		else if (balance > 0) {
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
		}
		
		if (this.getRoot().equals(node)) this.root = (BSTNode<T>) node.getParent();
		
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			
			this.rebalance(node);
			this.rebalanceUp((BSTNode<T>) node.getParent());
			
		}
	}
	
	
	//métodos de adição e remoção a parte
	
	@Override
	public void remove(T element) {
		if (element != null) {
 			BSTNode<T> no = this.search(element);
 			
 			if (!no.isEmpty()) {
 				
 				if (no.isLeaf()) {
 					no.setData(null);
 					this.rebalanceUp(no);
 					
 				} else if (!no.getLeft().isEmpty() && !no.getRight().isEmpty()) {
 					T predNo = this.sucessor(no.getData()).getData();
 					this.remove(predNo);
 					no.setData(predNo);
 					
 				} else if (!no.getRight().isEmpty()) 
 					this.removeChild(no, (BSTNode<T>) no.getRight());
 					
 				 else 
 					this.removeChild(no, (BSTNode<T>) no.getLeft());
 				
 			}
 		}
	}
	
	private void removeChild(BSTNode<T> no, BSTNode<T> child) {
 		if (no.getParent() == null) {
 			no.setData(child.getData());
 			no.setLeft(child.getLeft());
 			no.setRight(child.getRight());
 			
 		} else if (no.getParent().getRight().equals(no)) 
 			no.getParent().setRight(child);
 			
 		 else if (no.getParent().getLeft().equals(no)) 
 			no.getParent().setLeft(child);
 		
 		
 		child.setParent(no.getParent());
 		this.rebalanceUp(no);
 	}
	
	
	
	
}
