package BINGO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Loterija {

	public static void main(String[] args) {

		String izvele;
		Stack<Integer> LaimBumbinas = new Stack<>();
		Stack<Integer> Bumbinas = new Stack<>();
		
	    Long Pauze = (long) 60000;
	    Long Darbiba =(long) 0;
	    
		String[] darbibas = {"Pieteikties AZARTSPELES", "Apskatīties laimīgos skaitļus", "Apturēt"};
		
		do {
			izvele = (String)JOptionPane.showInputDialog(null, "Izveleies darbību cuhh", "Darbibu saraksts twin", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);
			
			if(izvele == null)
				izvele = "Apturēt";
			
			switch(izvele) {
			
			case "Pieteikties AZARTSPELES":

			    long now1 = System.currentTimeMillis(); // UPDATE TIME HERE

			    if (now1 - Darbiba < Pauze) {
			        long palikusais = (Pauze - (now1 - Darbiba)) / 1000;
			        JOptionPane.showMessageDialog(null,
			                "Darbību var darīt tikai reizi minūtē!\nPalikušas: " + palikusais + " sekundes");
			        break;
			    }


			    Darbiba = now1;

			    LocalDateTime IzmetBumbinas = LocalDateTime.now();
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			    String Datums = IzmetBumbinas.format(myFormatObj);

			    Bumbinas.clear();
			    LaimBumbinas.clear();

			    for (int i = 0; i < 3; i++) {
			        for (int j = 1; j <= 10; j++) {
			            Bumbinas.push(j);
			        }
			        Collections.shuffle(Bumbinas);
			        LaimBumbinas.push(Bumbinas.peek());
			        Bumbinas.clear();
			    }

			    JOptionPane.showMessageDialog(null,
			            "Tu bumbiņas izvilki šajā datumā un laikā:\n" + Datums);
			    break;

				
				
			case "Apskatīties laimīgos skaitļus":
				if(!LaimBumbinas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tavas laimīgās bumbiņas: "+LaimBumbinas, "WOOHOOO", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Tu neesi vel izvilcis bumbiņas", "Gudriniek", JOptionPane.WARNING_MESSAGE);
				}
				break;
				
			case"Apturēt":
				JOptionPane.showMessageDialog(null, "Programma aptureta!", "Traki", JOptionPane.INFORMATION_MESSAGE);
				break;
				
			}
			
		}while(!izvele.equals("Apturēt"));
		
	}

}
