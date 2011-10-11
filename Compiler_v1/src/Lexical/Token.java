package Lexical;


public class Token {

	private int id;
	private String lexeme;
	private int position;

	public Token(int id, String lexeme, int position) {
		this.id = id;
		this.lexeme = lexeme;
		this.position = position;
	}

	public final int getId() {
		return id;
	}

	public final String getTokenClass() {
		
		if (id == Constants.IDENTIFICADOR.getId()) {
			return Constants.IDENTIFICADOR.getClazz();
		} else if (id == Constants.REAL.getId()) {
			return Constants.REAL.getClazz();
		} else if (id == Constants.LITERAL.getId()) {
			return Constants.LITERAL.getClazz();
		} else if (id >= Constants.MAIS.getId() && id <= Constants.DOIS_PONTOS.getId()) {
			return Constants.MAIS.getClazz();
		} else if (id >= Constants.ADD.getId() && Constants.WRITE.getId() <= 46) {
			return Constants.ADD.getClazz();
		}
		
		return "";
	}

	public final String getLexeme() {
		return lexeme;
	}

	public final int getPosition() {
		return position;
	}

	public String toString() {
		return id + " ( " + lexeme + " ) @ " + position;
	};
}
