package Interfaces;

import javax.swing.JTextArea;

import Lexical.LexicalError;
import Lexical.Lexico;
import Semantic.SemanticError;
import Semantic.Semantico;
import Syntatic.Sintatico;
import Syntatic.SyntaticError;
import Utils.Token;

public class Compile {

	public static void compile(String source, JTextArea output) {

		output.setText("");

		Lexico lexico = new Lexico();
		lexico.setInput(source);
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		
		try {
			sintatico.parse(lexico, semantico);
		} catch (LexicalError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SyntaticError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SemanticError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				

		/**Token token;
		try {
			token = lexico.nextToken();
			while (token != null){
				output.setText(output.getText() + "\n" + token.getPosition() + " " + token.getTokenClass() + "   " + token.getLexeme());
				token = lexico.nextToken();
			}
		} catch (LexicalError e) {
			output.setText(e.getMessage());
		}
		*/
	}
}
