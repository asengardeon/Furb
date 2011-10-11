package Lexical;

public class LexicalError extends AnalysisError {
	
	private static final long serialVersionUID = 1L;

	public LexicalError(String msg, int position) {
		super(msg, position);
	}

	public LexicalError(String msg) {
		super(msg);
	}
}
