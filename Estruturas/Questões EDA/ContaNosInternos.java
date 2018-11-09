import java.util.*;


class ContaNosInternos {
	static ArvoreBST arvore = new ArvoreBST();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] array = sc.nextLine().split(" ");
		adicionaElementos(arrayInteiros(array));
		System.out.println(arvore.contaNosInternos());

	}
	
	private static void adicionaElementos(int[] arrayInt) {
		for(int i = 0; i < arrayInt.length; i++) 
			arvore.inserir(arrayInt[i]);
	}
	
	private static int[] arrayInteiros(String[] array) {
		int[] arrayInt = new int[array.length];
		
		for (int i = 0; i < array.length; i++) 
			arrayInt[i] = Integer.parseInt(array[i]);
		
		return arrayInt;
	}
}
/**
 * A classe arvore representará a estrutura de dados de uma arvore binária de pesquisa (BST)
 * Possuindo um valor do nó raiz e métodos de apoio para operações realizadas em cima da sua 
 * estrutura
 * @author ibiaalice
 *
 */
class ArvoreBST {
	private No raiz;
	
	/**
	 * Construtor explicito da arvore
	 */
	public ArvoreBST() {
		this.raiz = null;
	}
	
	
	// métodos estruturais
	
	/**
	 * método de inserção na arvore. Existe 2 possiveis casos para a inserção de um 
	 * nó : primeiro caso é quando não existe nenhum nó na arvore, sendo o elemento adicionado
	 * a raiz. e o segundo caso é quando existe o elemento adicionado e que  saber onde o alocar.
	 */
	public void inserir(int valor) {
		No novoNo = new No(valor);
		
		if(this.raiz == null)
			this.raiz = novoNo;
		else {
			No auxiliar = this.raiz; //atual
			No anterior;
			while(true) {
				anterior = auxiliar;
				
				//andando pela esquerda
				if(valor <= auxiliar.valor) {
					auxiliar = auxiliar.esquerda;
					if(auxiliar == null) {
						anterior.esquerda = novoNo;
						return;
					}
				}
				//andando pela direita
				else {
					auxiliar = auxiliar.direita;
					if(auxiliar == null) {
						anterior.direita = novoNo;
						return;
					}
				}
				
			}
		}
		
	}

	
	
	/**
	 * Método recursivo de contagem de folhas
	 * @param auxiliar nó da arvore que virá a percorrer
	 * @return retorna um inteiro representando a quantidade de folhas
	 */
	public int folhas(No auxiliar) {
		//caso seja a arvore vazia
		if(auxiliar == null) return 0;
		//caso seja a ultima folha do "ramo"
		if(auxiliar.esquerda == null && auxiliar.direita == null) return 1;
		//passo recursivo
		return folhas(auxiliar.esquerda) + folhas(auxiliar.direita);
	}
	
	/**
	 * o método diminui da quantidade total de nós a quantidade de folhas
	 * @return retorna a quantidade de nós internos
	 */
	public int contaNosInternos() {
		return contaNos(this.raiz) - folhas(raiz);
	}
	
	/**
	 * Método recursivo de contagem dos nós 
	 * @param auxiliar nó da arvore que virá a percorrer
	 * @return retorna um inteiro representando a quantidade de folhas
	 */
	public int contaNos(No auxiliar) {
		//caso seja o ultimo passo ou a arvore vazia
		if(auxiliar == null) return 0;
		// passo recursivo
		else return (1 + contaNos(auxiliar.esquerda) + contaNos(auxiliar.direita));
	}
	
	
	
	
	
	
}

/**
 * A classe nó guardará os valores dos seus filhos a direita e a esquerda, juntamente com o seu
 * próprio valor em inteiro.
 * @author ibiaalice
 *
 */
class No {
	public int valor; 
	public No direita;  
	public No esquerda;  
	
	public No(int valor) {
		this.valor = valor;
	}
	
	public boolean eFolha() {
		return esquerda == null && direita == null;
	}
	
	
}