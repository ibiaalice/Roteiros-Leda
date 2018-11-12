import java.util.*;

class PosicoesElemento {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		String seq = sc.nextLine().trim();
		int sequencia[] = converteInteiro(seq.split(" "));
		
		System.out.println(posicoesElementos(n, sequencia));
		
	}
	
	private static int[] converteInteiro(String[] sequencia) {
		int[] convertidos = new int[sequencia.length];
		
		for(int i = 0; i< convertidos.length; i++) {
			convertidos[i] = Integer.parseInt(sequencia[i]);
		}
		
		return convertidos;
	}
	
	private static String posicoesElementos(int n , int[] sequencia) {
		String posicoes = "";
		
		for(int i = 0; i < sequencia.length; i++) {
			if(n == sequencia[i]) posicoes += i + " ";
		}
		
		if(posicoes.equals("")) return "-1";
		return posicoes.trim();
	}
	

}
