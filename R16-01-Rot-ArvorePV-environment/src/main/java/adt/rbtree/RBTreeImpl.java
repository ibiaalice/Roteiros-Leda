
package adt.rbtree;


import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return this.blackHeight((RBNode<T>) this.root);
	}
	
	private int blackHeight(RBNode<T> node) {
		int returnHeight = -1;
		if (!node.isEmpty() && this.isBlack(node)) {
			if (this.isBlack(node)) {
				returnHeight+= 1;
			}
			returnHeight = Math.max(this.blackHeight((RBNode<T>) node.getRight()), this.blackHeight((RBNode<T>) node.getLeft()));
		}
		
		return returnHeight;
	}

	private boolean isBlack(RBNode<T> node) {
		return node.getColour().equals(Colour.BLACK);
	}
	
	private boolean isRed(RBNode<T> node) {
		return node.getColour().equals(Colour.RED);
	}

	protected boolean verifyProperties() {
		return verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; 
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; 
																
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true;
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	protected boolean verifyChildrenOfRedNodes() {
		return this.verifyChildrenOfRedNodes((RBNode<T>) this.root);
	}
	
	
	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean resultado = true;
		
		if (!node.isEmpty()) {
			if (this.isBlack(node)) 	resultado = this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
			else if (this.isRed(node)) {
				if (this.isBlack((RBNode<T>) node.getLeft()) && this.isBlack((RBNode<T>) node.getRight())) 
					resultado = this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
				 else
					resultado = false;
				
			}
		}
		return resultado;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (this.blackHeight((RBNode<T>) this.root.getLeft()) != this.blackHeight((RBNode<T>) this.root.getRight())) {
			throw new RuntimeException();
		}
		
		return true;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			this.insert((RBNode<T>) this.getRoot(), value);
		} 
	}
	
	private void insert(RBNode<T> node, T value) {
		
		if (node.isEmpty()) {
			this.setNewNode(node, value);
			this.fixNode(node);
			
		} 
		else if (node.getData().compareTo(value) > 0) this.insert((RBNode<T>) node.getLeft(), value);
		
		else if (node.getData().compareTo(value) < 0) this.insert((RBNode<T>) node.getRight(), value);
	}
	
	private void fixNode(RBNode<T> node) {
		if (node.getParent() == null) this.fixUpCase1(node);
		else if (this.isBlack((RBNode<T>) node.getParent())) this.fixUpCase2(node);
		else if (this.isRed((RBNode<T>) node.getParent().getParent().getLeft()) && this.isRed((RBNode<T>) node.getParent().getParent().getRight())) {
			this.fixUpCase3(node);
		} else if (this.isCase4(node)) this.fixUpCase4(node);
	}
	
	private boolean isCase4(RBNode<T> node) {
		boolean resultado = false;
		
		if (node.getParent().getParent().getLeft().equals(node.getParent()) && node.getParent().getRight().equals(node))
			resultado = true;
		else if (node.getParent().getParent().getRight().equals(node.getParent()) && node.getParent().getLeft().equals(node)) 
			resultado = true;
		
		return resultado;
	}

	

	@Override
	public RBNode<T>[] rbPreOrder() {
		@SuppressWarnings("unchecked")
		RBNode<T>[] arrayAux = new RBNode[this.size()];
 		this.preOrder((RBNode<T>) this.getRoot(), arrayAux);
 		
 		return arrayAux;
	}
	
	private void preOrder(RBNode<T> node, RBNode<T>[] arrayAux) {
 		if (!node.isEmpty()) {
 			this.addElementToArray(arrayAux, node);
 			this.preOrder((RBNode<T>) node.getLeft(), arrayAux);
 			this.preOrder((RBNode<T>) node.getRight(), arrayAux);
 		}
 	}
	
 	public void addElementToArray(RBNode<T>[] arrayAux, RBNode<T> node) {
 		int i = 0;
 		while (arrayAux[i] != null) {
 			i++;
 		}
 		
 		arrayAux[i] = node;
 	}


	protected void fixUpCase1(RBNode<T> node) {
		if (node.getParent() == null) {
			node.setColour(Colour.BLACK);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		node.setColour(Colour.RED);
	}

	protected void fixUpCase3(RBNode<T> node) {
		node.setColour(Colour.RED);
		((RBNode<T>) node.getParent().getParent().getLeft()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent().getRight()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
		this.fixUpCase1((RBNode<T>) node.getParent().getParent());
	}

	private void setNewNode(RBNode<T> node, T value) {
		node.setData(value);
		node.setLeft(new RBNode<>());
		node.getLeft().setParent(node);
		node.setRight(new RBNode<>());
		node.getRight().setParent(node);	
		
	}
	protected void fixUpCase4(RBNode<T> node) {
		if (node.getParent().getParent().getLeft().equals(node.getParent()) && node.getParent().getRight().equals(node)) {
			Util.leftRotation((RBNode<T>) node.getParent());
		} else if (node.getParent().getParent().getRight().equals(node.getParent()) && node.getParent().getLeft().equals(node)) {
			Util.rightRotation((RBNode<T>) node.getParent());
		}
		
		this.fixUpCase5(node);
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent()).setColour(Colour.RED);
		
		if (node.getParent().getParent().getLeft().equals(node.getParent())) {
			Util.rightRotation((RBNode<T>) node.getParent().getParent());
		} else if (node.getParent().getParent().getLeft().equals(node.getParent())) {
			Util.rightRotation((RBNode<T>) node.getParent().getParent());
		}
	}
}
