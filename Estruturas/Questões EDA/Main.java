import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String parenteses = sc.nextLine().trim(); // o_o deu certo mas é feio
		if(parenteses.length() == 0) return;
		if(verificaParenteses(parenteses)) System.out.println("S");
		else System.out.println("N");
	}

	private static boolean verificaParenteses(String parenteses) {
		int top = -1;
		
		for(int i = 0; i < parenteses.length(); i++) {
			String parentese = parenteses.substring(i,i+1);
			if(parentese.equals("(")){
				top += 1;
			}else if(parentese.equals(")")) {
				if(top >= 0) top -= 1;
				else return false;
			}
		}
		return top == -1;
	}
	
	


}