import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

class Chainning {//deu certoooooo :DDD
	
	private ArrayList[] tabela;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tamanho = sc.nextInt(); sc.nextLine();
		Chainning tabela = new Chainning(tamanho);
		
		String entrada = sc.nextLine();
		
		while (true) {
			entrada = sc.nextLine();
			if(entrada.equals("end")) break;
			
			
			String[] opc = entrada.split(" ");
			String print = "";
			
			if (opc[0].equals("put")) {
				int chave =  Integer.parseInt(opc[1]);
				print = tabela.put(chave, opc[2]);
			} else if (opc[0].equals("remove")) {
				int chave =  Integer.parseInt(opc[1]);
				print = tabela.remove(chave);
			} else if (opc[0].equals("keys")) {
				print = tabela.getKeys();
			} else if (opc[0].equals("values")) {
				print = tabela.getValues();
			}
			
			System.out.println(print);
			
		}
		
		sc.close();
	}
	
	
	public Chainning(int tamanho) {
		tabela = new ArrayList[tamanho];
	}
	
	public String put(int chave, String valor) {
		pair pair = new pair(chave, valor);
		int pos = getPosicao(chave);
		if (tabela[pos] == null ) {
			tabela[pos] = new ArrayList<pair>();
		}
		if (insere( chave)){
			tabela[ pos ].add(pair);
		} else {
			this.remove(chave );
			this.put(chave, valor);
		}
		
		return this.toString();
	}
	
	public String getKeys() {
		String keys = "[";
		
		for (int i = 0; i < tabela.length; i++) {
			if (tabela[i] != null) {
				for (int j = 0; j < tabela[i].size(); j++) {
					pair pair = (pair) tabela[i].get(j);
					keys += pair.getChave();
					if (j != tabela[i].size()-1 && i != tabela.length -1) {
						keys += ", ";
					}
				}
			}
		}
		keys += "]";
		
		return keys;
	}
	
	
	@Override
	public String toString() {
		String retorno = "[";
		
		for (int i = 0; i < tabela.length; i++) {
			retorno += "[";
			if (tabela[i] != null) {
				for (int j = 0; j < tabela[i].size(); j++) {
					pair pair = (pair) tabela[i].get(j);
					retorno += pair.toString();
				}
			}
			retorno += "]";
		}
		retorno += "]";
		
		return retorno;
	}
	
	public String remove(int chave) {
		int pos = getPosicao(chave);
		
		for (int i = 0; i < tabela[pos].size(); i++) {
			pair pair = (pair) tabela[pos].get(i);
			
			if (pair.getChave() == chave ) {
				tabela[pos].remove(i);
			}
		}
		
		return this.toString();
	}
	
	
	public String getValues() {
		String valores = "[";
		
		for (int i = 0; i < tabela.length; i++) {
			if (tabela[i] != null) {
				for (int j = 0; j < tabela[i].size(); j++) {
					pair atual = (pair) tabela[i].get(j);
					valores += atual.getValor();
					if (j != tabela[i].size()-1) {
						valores += ", ";
					}
				}
			}
		}
		valores += "]";
		
		return valores;
	}
	
	private boolean insere(int keys) {
		int n = getPosicao(keys);
		
		for (int i = 0; i < tabela[n].size(); i++) {
			pair atual = (pair) tabela[n].get(i); 
			
			if (atual.getChave() == keys) return false;
		}
		
		return true;
	}

	private int getPosicao(int chave) {
		return chave % tabela.length;
	}
	
}


class pair {
	private final String S;
	private final int T;
	
	public pair(int chave, String valor) {
		this.S = valor;
		this.T= chave;
	}
	
	public int getChave() {
		return T;
	}
	
	public String getValor() {
		return S;
	}
	
	@Override
	public String toString(){
		return ("<" + this.T + ", " + this.S + ">");
	}
}