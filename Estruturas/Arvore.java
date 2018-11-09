/**
 * A classe arvore representará a estrutura de dados de uma arvore binária de pesquisa (BST)
 * Possuindo um valor do nó raiz e métodos de apoio para operações realizadas em cima da sua 
 * estrutura
 * @author ibiaalice
 *
 */
class Arvore {
	private No raiz;
	
	/**
	 * Construtor explicito da arvore
	 */
	public Arvore() {
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
	 * método de busca dentro da arvore
	 * @param valor o valor do nó procurado
	 * @return retorna o nó procurado, caso exista
	 */
	public No procurar(int valor) {
		
		if(raiz == null) return null;
		
		No auxiliar = this.raiz;
		
		while(auxiliar.valor != valor || auxiliar != null) {
			//caminhando pela esquerda
			if(auxiliar.valor > valor) auxiliar = auxiliar.esquerda; 
			//caminha pela direita
			else auxiliar = auxiliar.direita;
			
		}
		
		return auxiliar;
	}
	
	/**
	 * Método de remoção de um nó qualquer, contendo 4 casos explicitos:
	 * no primeiro caso o nó da remoção é o raiz e não possui folhas
	 * no segundo caso é um nó folha 
	 * no terceiro caso é um nó que possui um filho apenas , ou filho a esquerda ou a direita
	 * no ultimo caso , o nó possui os dois filhos e utiliza a presença do sucessor.
	 * @param valor recebe o valor do nó que se quer remover.
	 */
	public void remover(int valor) {
		if(this.raiz == null) return;
		
		//auxiliares
		No auxiliar = raiz;
		No pai = raiz;
		boolean filhoEsquerda = true;
		
		
		//procurando o valor
		while(auxiliar.valor != valor) {
			pai = auxiliar;
			
			//caminhando para a esquerda
			if(auxiliar.valor > valor) {
				auxiliar = auxiliar.esquerda;
			}
			else {
				auxiliar = auxiliar.direita;
				filhoEsquerda = false;
			}
			
			if(auxiliar == null) return;
		}
		
		/*
		 * se sair do while temos que o valor existe e sabemos se ele 
		 * da esquerda ou da direita.
		 */
		
		//caso seja uma folha:
		if(auxiliar.esquerda == null && auxiliar.direita == null) {
			//caso seja uma raiz
			if(auxiliar == this.raiz) this.raiz = null;
			
			else if(filhoEsquerda)
				pai.esquerda = null;
			
			else pai.direita = null;
		}
		
		//se não possui um filho a direita 
		else if(auxiliar.direita == null) {
			//caso seja raiz
			if(auxiliar == this.raiz) raiz = auxiliar.esquerda;
			//caso seja na esquerda 
			else if(filhoEsquerda) pai.esquerda = auxiliar.esquerda;
			//caso o filho seja na direira
			else pai.direita = auxiliar.esquerda;
		}
		
		
		//se não possui filho a esquerda
		else if( auxiliar.esquerda == null){
			//caso seja raiz
			if(auxiliar == this.raiz) this.raiz = auxiliar.direita;
			//caso seja filho da esquerda
			else if(filhoEsquerda) pai.esquerda = auxiliar.direita;
			//caso seja filho da direita
			else pai.direita = auxiliar.direita;
		}
		
		//se possui os dois filhos 
		else {
			No sucessor = sucessor(auxiliar);
			
			//se o auxiliar for raiz
			if(auxiliar == this.raiz) this.raiz = sucessor;
			
			else if(filhoEsquerda) pai.esquerda = sucessor;
			else pai.direita = sucessor;
			
			sucessor.esquerda = auxiliar.esquerda;
			
		}
		
	}
	
	//métodos quantitativos:
	
	/**
	 * Método recursivo do cálculo da altura de uma arvore
	 * @param auxiliar nó auxiliar do cálculo
	 * @return retorna um inteiro representando a altura
	 */
	public int altura(No auxiliar) {
		// caso seja uma arvore vazia ou caso seja uma arvore com somente a raiz
		if(auxiliar == null || (auxiliar.esquerda == null && auxiliar.direita == null)) return 0;
		else {
			//passos recursivos 
			if(altura(auxiliar.esquerda) > altura(auxiliar.direita)) 
				return 1 + (altura(auxiliar.esquerda));
			else
				return (1 + altura(auxiliar.direita));
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


	/**
	 * o método diminui da quantidade total de nós a quantidade de folhas
	 * @return retorna a quantidade de nós internos
	 */
	public int contaNosInternos() {
		return contaNos(this.raiz) - folhas(raiz);
	}

	
	/**
	 * Método de procura do máximo nó na arvore
	 * @return retorna o maior nó 
	 */
	public No max() {
		No auxiliar = this.raiz;
		No anterior = null;
		
		//percorre todo o lado a esquerda
		while(auxiliar != null) {
			anterior = auxiliar;
			auxiliar = auxiliar.direita;
		}
		
		return anterior;
	}
	 
	
	/**
	 * método de procura do minimo nó 
	 * @return retorna o menor nó 
	 */
	public No min() {
		No auxiliar = this.raiz;
		No anterior = null;
		
		//percorre todo o lado esquerdo da arvore e seus galhos a esquerda
		while(auxiliar != null) {
			anterior = auxiliar;
			auxiliar = auxiliar.esquerda;
		}
		
		return anterior;
	}
	
	
	//metodos de demostração:
	
	/**
	 * método de demostração da organização da arvore inOrder
	 * @param auxiliar primeiro nó 
	 */
	public void inOrder(No auxiliar) {
		if(auxiliar != null) {
			inOrder(auxiliar.esquerda);
			System.out.println(auxiliar.valor + " ");
			inOrder(auxiliar.direita);
		}
	}
	
	/**
	 * método de demostração da organização da arvore preOrder
	 * @param auxiliar primeiro nó a demostrar
	 */
	public void preOrder(No auxiliar) {
		if(auxiliar != null) {
			System.out.println(auxiliar.valor + " ");
			preOrder(auxiliar.esquerda);
			preOrder(auxiliar.direita);
		}
	}
	
	/**
	 * método de demostração da organização da arvore posOrder
	 * @param auxiliar
	 */
	public void posOrder(No auxiliar) {
		if(auxiliar != null) {
			posOrder(auxiliar.esquerda);
			posOrder(auxiliar.direita);
			System.out.println(auxiliar.valor + " ");
		}
	}
	
	
	//métodos auxiliares privados:
	
	
	/**
	 * método privado que encontra o sucessor e exclui o elemento
	 * @param excluir elemento a excluir
	 * @return retorna o sucessor.
	 */
	private No sucessor(No excluir) {
		No paiSucessor = excluir;
		No sucessor = excluir;
		No auxiliar = excluir.direita; //garante que vai para a subarvore da direita
		
		while (auxiliar != null){
			paiSucessor = sucessor;
			sucessor = auxiliar;
			auxiliar = auxiliar.esquerda;
		} //quando acaba o while o sucessor é o nó mais a esquerda da arvore
		
		if(sucessor != excluir.direita) {
			//o pai herda os filhos do sucessor a direita
			paiSucessor.esquerda = sucessor.direita;
			
			sucessor.direita = excluir.direita; //guarda a posição para quando ele assumir na arvore
		}
		
		return sucessor;
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
	
	
}
