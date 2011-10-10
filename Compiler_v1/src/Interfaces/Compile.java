package Interfaces;

import javax.swing.JTextArea;

import Lexical.LexicalError;
import Lexical.Lexico;
import Lexical.Token;

public class Compile {

	public static void compile(String source, JTextArea output) {

		output.setText("");
		
		String[] linesSource = source.split("\n");
		boolean error = false;
		
		for (int i = 0; i < linesSource.length; i++) {

			Lexico lexico = new Lexico();
			lexico.setInput(linesSource[i]);

			Token token = null;
			try {

				token = lexico.nextToken();

				while (token != null) {
					output.setText(output.getText() + "\n" + (i + 1) + "	"  + token.getTokenClass() + "	" + token.getLexeme());
					token = lexico.nextToken();
				}

			} catch (LexicalError e) {
				output.setText("Erro na linha " + (i + 1) + " - " + e.getMessage());
				error = true;
				break;
			}
			
		}

		if (!error){
			output.setText(output.getText() + "\n" + "Código compilado com sucesso");
		}
	}
}
