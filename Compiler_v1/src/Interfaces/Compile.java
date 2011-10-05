package Interfaces;

import javax.swing.JTextArea;

import Lexical.LexicalError;
import Lexical.Lexico;
import Lexical.Token;

public class Compile {

	public static void compile(String source, JTextArea output) {

		String[] linesSource = source.split("\n");

		for (int i = 0; i < linesSource.length; i++) {

			Lexico lexico = new Lexico();
			lexico.setInput(linesSource[i]);

			Token token;
			try {

				token = lexico.nextToken();
				
				while (token != null) {
					output.setText(output.getText() + "\n" + i + "	" + token.getTokenClass() + "	" + token.getLexeme());
					token = lexico.nextToken();
				}
			} catch (LexicalError e) {
				output.setText(output.getText() + "\n" + e.getMessage());
			}
		}
		
		output.setText(output.getText() + "\n" + "Código compilado com sucesso");
	}
}
