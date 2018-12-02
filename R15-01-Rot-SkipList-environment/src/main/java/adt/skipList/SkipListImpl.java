package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;
	protected int height;// me dá a altura total utilizada
	
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.height = maxHeight;
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = gap(key);
		SkipListNode<T> node = update[0].getForward(0);

		if (node.getKey() == key) {
			contemChave(update, node, height, key, newValue);
		} else {
			naoContemC(update, node, key, newValue, height);
		}
	}
	
	
	
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = gap(key);
		SkipListNode<T> node = update[0].getForward(0);

		if (node.getKey() == key) {
			
			for (int i = 0; i < height; i++) {
				if (!update[i].getForward(i).equals(node))	break;
				update[i].forward[i] = node.forward[i];
				
				while (height > 1 && root.forward[height - 1].equals(NIL))
					height--;
			}
			
		}
	}

	@Override
	public int height() {
		return height(0);
	}
	
	//método recursivo height()
	private int height(int height) {
		if (root.forward[height] == NIL)
			return height;
		return height(++height);
	}
	
	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = this.root;

		for (int i = (height - 1); i >= 0; i--) {
			
			while (node.forward[i].getKey() < key) {
				node = node.forward[i];
			}	
		}
		node = node.forward[0];
		if (node.getKey() != key) node = null;
		
		return node;
	}

	@Override
	public int size() {
		return size(root.getForward(0), 0);
	}
	
	// método recursivo size
	private int size(SkipListNode<T> node, int size) {
		if (node == NIL)return size;
		
		return size(node.getForward(0), ++size);
	}
	
	

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		return toArray(array, root, 0);
	}
	
	private SkipListNode<T>[] toArray(SkipListNode<T>[] array, SkipListNode<T> node, int indice) {
		if (node == null) return array;
		array[indice] = node;
		
		return toArray(array, node.getForward(0), ++indice);
	}
	
	

	
	//método de apoio privados
	
	private void contemChave(SkipListNode<T>[] up, SkipListNode<T> node, int altura, int chave, T novoValor) {
		if (node.height() == altura) node.setValue(novoValor);
		else {
			remove(chave);
			naoContemC(up, node, chave, novoValor, altura);
		}
	}
	
	private void naoContemC(SkipListNode<T>[] up, SkipListNode<T> node, int chave, T novoValor, int altura) {
		
		if (altura > this.height) {
			
			for (int i = this.height; i < altura; i++)
				up[i] = root;
			this.height = altura;
		}

		node = new SkipListNode<T>(chave, altura, novoValor);
		
		for (int i = 0; i < altura; i++) {
			
			if (up[i].getForward(i) == null) node.getForward()[i] = NIL;
			else node.getForward()[i] = up[i].getForward(i);
			
			up[i].getForward()[i] = node;
		}
	}
	
	@SuppressWarnings("unchecked")
	private SkipListNode<T>[] gap(int chave) {
		SkipListNode<T>[] up = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;

		for (int i = (height - 1); i >= 0; i--) {
			
			while (node.getForward(i) != null && node.getForward(i).getKey() < chave)
				node = node.getForward(i);
			up[i] = node;
			
		}
		return up;
	}


	
	
}
