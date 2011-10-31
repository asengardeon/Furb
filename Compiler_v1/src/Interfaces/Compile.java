package Interfaces;

import javax.swing.JTextArea;

import Lexical.LexicalError;
import Lexical.Lexico;
import Utils.Token;

public class Compile {

	public static void compile(String source, JTextArea output) {

		output.setText("");

		Lexico lexico = new Lexico();
		lexico.setInput(source);

		Token token;
		try {
			token = lexico.nextToken();
			while (token != null){
				output.setText(output.getText() + "\n" + token.getPosition() + " " + token.getTokenClass() + "   " + token.getLexeme());
				token = lexico.nextToken();
			}
		} catch (LexicalError e) {
			output.setText(e.getMessage());
		}

	}
}
