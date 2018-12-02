package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	
	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		
		if (balance > 1) {
			this.rebalanceLeft(node);
		} else if (balance < -1) {
			this.rebalanceRight(node);
		}
	}

	private void rebalanceLeft(BSTNode<T> node) {
		int balanceLeft = this.calculateBalance((BSTNode<T>) node.getLeft());
		
		if (balanceLeft > 0) {
			Util.rightRotation(node);
			this.LLcounter++;
		} else if (balanceLeft < 0) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
			this.LRcounter++;
		}
		
		if (this.getRoot().equals(node)) {
			this.root = (BSTNode<T>) node.getParent();
		}
	}
	
	private void rebalanceRight(BSTNode<T> node) {
		int balanceRight = this.calculateBalance((BSTNode<T>) node.getRight());
		
		if (balanceRight < 0) {
			Util.leftRotation(node);
			this.RRcounter++;
		} else if (balanceRight > 0) {
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
			this.RLcounter++;
		}
		
		if (this.getRoot().equals(node)) {
			this.root = (BSTNode<T>) node.getParent();
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		ArrayList<T> aux = new ArrayList<>();
		this.pivotRecursivo(array, aux, 0, array.length);
		
		if (!aux.isEmpty()) {
			int gap = array.length - 1;
			int position = 0;
			
			while (gap >= 0) {
				while (position < aux.size()) {
					this.insert(aux.remove(position));
					position += gap;
				}
				if (gap <= 1) {
					gap -= 1;
				} else {
					gap = (gap/2) - 1;
				}
				position = 0;
			}
		}
	}

	private void pivotRecursivo(T[] array, ArrayList<T> aux, int start, int end) {
		if (end - start > 0) {
			int center = (start+end) / 2;
			aux.add(array[center]);
			this.pivotRecursivo(array, aux, start, center);
			this.pivotRecursivo(array, aux, center+1, end);
		}
	}
}