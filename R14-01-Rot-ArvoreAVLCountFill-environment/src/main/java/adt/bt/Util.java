package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		
		right.setParent(node.getParent());
		
		if (node.getParent() != null) {
			if (node.getParent().getLeft().equals(node)) node.getParent().setLeft(right);
			 else node.getParent().setRight(right);
		}
		
		node.setParent(right);
		node.setRight(right.getLeft());
		
		if (right.getLeft() != null) right.getLeft().setParent(node);
		right.setLeft(node);
		
		
		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		left.setParent(node.getParent());
		
		if (node.getParent() != null) {
			if (node.getParent().getLeft().equals(node)) node.getParent().setLeft(left);
			else node.getParent().setRight(left);
		}
		
		node.setParent(left);
		node.setLeft(left.getRight());
		
		if (left.getRight() != null) left.getRight().setParent(node);
		
		left.setRight(node);
		
		return left;
	
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
