import java.util.*;

class PredecessorBST {
	static ArvoreBST arvore = new ArvoreBST();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] array = sc.nextLine().split(" ");
		int valor = sc.nextInt();
		adicionaElementos(arrayInteiros(array));
		System.out.println(arvore.predecessor(valor).toString());

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
						novoNo.parente = anterior;
						anterior.esquerda = novoNo;
						return;
					}
				}
				//andando pela direita
				else {
					auxiliar = auxiliar.direita;
					if(auxiliar == null) {
						novoNo.parente = anterior;
						anterior.direita = novoNo;
						return;
					}
				}
				
			}
		}
		
	}

	/**
	 * método de busca dentro da arvore
	 * @param valor o valor do nó procurado
	 * @return retorna o nó procurado, caso exista
	 */
	public No procurar(int valor) {
		
		if(raiz == null) return null;
		
		No auxiliar = this.raiz;
		
		while(auxiliar != null) {
			if(auxiliar.valor == valor) return auxiliar;
			
			//caminhando pela esquerda
			if(auxiliar.valor > valor) auxiliar = auxiliar.esquerda; 
			//caminha pela direita
			else auxiliar = auxiliar.direita;
			
		}
		
		return auxiliar;
	}

	//métodos auxiliares privados:
	
	public  ArrayList<Integer> predecessor(Integer valor) {
		ArrayList<Integer> predecessores = new ArrayList<Integer>();
		No auxiliar = procurar(valor);
		
		if (auxiliar != null) {
			predecessores.add(auxiliar.valor);
			if (auxiliar.esquerda == null) {
				auxiliar = auxiliar.parente;
				while (auxiliar != null) {
					predecessores.add(auxiliar.valor);
					if (auxiliar.valor <= valor)
						break;
					auxiliar = auxiliar.parente;
				}
			} else {
				auxiliar = auxiliar.esquerda;
				while (auxiliar != null) {
					predecessores.add(auxiliar.valor);
					auxiliar = auxiliar.direita;
				}
			}
		}
		
		return predecessores;
	}
	
	
}

/**
 * A classe nó guardará os valores dos seus filhos a direita e a esquerda, juntamente com o seu
 * próprio valor em inteiro.
 * @author ibiaalice
 *
 */
/*class No {
	public int valor; 
	public No direita;  
	public No esquerda; 
	public No parente;
	
	public No(int valor) {
		this.valor = valor;
	}
	
	
}*/