import java.util.*;

class arvoreBST {
	private static No raiz;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String array = sc.nextLine();
		String[] inputArray = array.split(" ");
		String escolha = sc.nextLine();

		adicionaNaArvore(inputArray);
		System.out.println(Arrays.toString(preOrder()));
		System.out.println(valorMaisProximo(Integer.parseInt(escolha)));
	}

	private static void adicionaNaArvore(String[] inputArray) {
		for (int i = 0; i < inputArray.length; i++)
			insere(Integer.parseInt(inputArray[i]));
	}

	public static Integer[] preOrder() {
		Integer[] array = new Integer[tamanho()];
		preOrder(raiz, array);
		return array;
	}

	private static void preOrder(No node, Integer[] array) {
		if (node != null) {
			int i = 0;
			while(i < array.length && array[i] != null) i++;
			array[i] = node.getValor();
		}
		
		if (node.getEsquerda() != null) preOrder(node.getEsquerda(), array);
		if (node.getDireita() != null) preOrder(node.getDireita(), array);
	}

	private static int valorMaisProximo(int valor) {
		int valorProx = Integer.MAX_VALUE;
		No aux = raiz;
		
		while (aux != null) {
			int valorNo = aux.getValor();
			
			if (Math.abs(valorNo - valor) < Math.abs(valorProx - valor)) valorProx = valorNo;
			if (valorNo < valor) aux = aux.getDireita();
			else if (valorNo > valor) 	aux = aux.getEsquerda();
			else if (valorNo == valor) break;
			
		}
		return valorProx;
	}

	
	public static void insere(Integer e) {
		No newNode = new No(e, null, null, null);

		if (isEmpty()) {
			raiz = newNode;
		} else {
			insereNaoVazio(e, newNode);
		}
		
	}
	
	private static int size(No no) {
		int result = 0;
		if (no != null) {
			result = 1 + size(no.getEsquerda()) + size(no.getDireita());
		}
		return result;
	}

	public static void insereNaoVazio(Integer i, No novoNo) {
		No auxiliar = raiz;

		while (auxiliar != null) {
			
			if (i > auxiliar.getValor()) {
				
				if (auxiliar.getDireita() == null) {
					novoNo.setParent(auxiliar);
					auxiliar.setDireita(novoNo);
					break;
				} 
				else 
					auxiliar = auxiliar.getDireita();
				
			} else if (i < auxiliar.getValor()) {
				if (auxiliar.getEsquerda() == null) {
					novoNo.setParent(auxiliar);
					auxiliar.setEsquerda(novoNo);
					break;
				} 
				else 
					auxiliar = auxiliar.getEsquerda();
			} else if (i == auxiliar.getValor()) break;
		}
		
	}
	
	public static int tamanho() {
		return size(raiz);
	}
	
	public static boolean isEmpty() {
		return raiz == null;
	}

	

}

class No {
	
	private Integer valor;
	private No direita;
	private No esquerda;
	private No parent;
	
	public No(Integer valor, No direita, No esquerda, No parent) {
		this.valor = valor;
		this.direita = direita;
		this.esquerda = esquerda;
		this.parent = parent;
	}
	
	public boolean ehFolha() {
		return (this.esquerda == null && this.direita == null);
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public No getDireita() {
		return direita;
	}
	
	public void setDireita(No direita) {
		this.direita = direita;
	}
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
	public No getParent() {
		return parent;
	}
	public void setParent(No parent) {
		this.parent = parent;
	}
}