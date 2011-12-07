package Syntatic;

import java.util.Stack;

import Lexical.LexicalError;
import Lexical.Lexico;
import Semantic.SemanticError;
import Semantic.Semantico;
import Utils.Constants;
import Utils.Token;
import Utils.ParserConstants;

public class Sintatico {

	private Stack stack = new Stack();
	private Token currentToken;
	private Token previousToken;
	private Lexico scanner;
	private Semantico semanticAnalyser;

	private static final boolean isTerminal(int x) {
		return x < Utils.ParserConstants.FIRST_NON_TERMINAL;
	}

	private static final boolean isNonTerminal(int x) {
		return x >= Utils.ParserConstants.FIRST_NON_TERMINAL
				&& x < Utils.ParserConstants.FIRST_SEMANTIC_ACTION;
	}

	private static final boolean isSemanticAction(int x) {
		return x >= Utils.ParserConstants.FIRST_SEMANTIC_ACTION;
	}

	private boolean step() throws LexicalError, SyntaticError, SemanticError {
		if (currentToken == null) {
			int pos = 0;
			if (previousToken != null) {
				pos = previousToken.getPosition()
						+ previousToken.getLexeme().length();
			}
			Constants enume = Utils.Constants.DOLLAR;
			currentToken = new Token(enume.getId(), "$", pos);
		}

		int x = ((Integer) stack.pop()).intValue();
		int a = currentToken.getId();

		if (x == Utils.Constants.EPSILON.ordinal()) {
			return false;

		} else if (isTerminal(x)) {

			if (x == a) {

				if (stack.empty())
					return true;
				else {
					previousToken = currentToken;
					currentToken = scanner.nextToken();
					return false;
				}

			} else {
				throw new SyntaticError("Encontrado " + getLexemeToException()
						+ " " + Utils.ParserConstants.PARSER_ERROR[x],
						currentToken.getPosition());
			}

		} else if (isNonTerminal(x)) {

			if (pushProduction(x, a)) {
				return false;
			} else {
				throw new SyntaticError("encontrado " + getLexemeToException()
						+ ", " + Utils.ParserConstants.PARSER_ERROR[x],
						currentToken.getPosition());
			}

		} else {
			isSemanticAction(x);
			semanticAnalyser.executeAction(x - ParserConstants.FIRST_SEMANTIC_ACTION, previousToken);
			return false;
		}
	}

	private String getLexemeToException() {
		String lexeme = currentToken.getLexeme();

		if (lexeme.contains("$")) {
			lexeme = lexeme.replace("$", "fim de arquivo ou programas");
		}
		return lexeme;
	}

	private boolean pushProduction(int topStack, int tokenInput) {
		int p = Utils.ParserConstants.PARSER_TABLE[topStack
				- Utils.ParserConstants.FIRST_NON_TERMINAL][tokenInput - 1];

		if (p >= 0) {
			int[] production = Utils.ParserConstants.PRODUCTIONS[p];
			// empilha a produção em ordem reversa
			for (int i = production.length - 1; i >= 0; i--) {
				stack.push(new Integer(production[i]));
			}
			return true;
		} else
			return false;
	}

	public void parse(Lexico scanner, Semantico semanticAnalyser) throws LexicalError, SyntaticError, SemanticError {
		this.scanner = scanner;
		this.semanticAnalyser = semanticAnalyser;

		stack.clear();
		stack.push(new Integer(Utils.Constants.DOLLAR.getId()));
		stack.push(new Integer(Utils.ParserConstants.START_SYMBOL));

		currentToken = scanner.nextToken();
		
		while (!step());
	}
}
