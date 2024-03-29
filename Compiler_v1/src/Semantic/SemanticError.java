package Semantic;

import Utils.AnalysisError;

@SuppressWarnings("serial")
public class SemanticError extends AnalysisError {
	
	public SemanticError(String msg, int position) {
		super(msg, position);
	}

	public SemanticError(String msg) {
		super(msg);
	}
}
