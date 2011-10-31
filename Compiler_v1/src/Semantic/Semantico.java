package Semantic;

import Utils.Token;

public class Semantico  {
	
	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);
	}
	
}
