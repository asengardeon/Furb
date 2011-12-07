package Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Utils.Constants;
import Utils.Token;

public class Semantico {

	private StringBuffer source = new StringBuffer();
	private ArrayList<String> listaIdentificadores = new ArrayList<String>();
	private Stack<Token> pilhaTipo = new Stack<Token>();
	private Map<String, Token> tableSimbles = new HashMap<String, Token>();
	private String operadorRelacional;// aqui em portuguessomente para chamar
										// atenção .o/

	// #1
	public void actionProgram() {
		source.append(".assembly extern mscorlib");
		source.append(".assembly NOMEPROGRAMA");
		source.append(".module NOMEDOPROGRAMA.exe");
		source.append(".class public NOMEDOPROGRAMA {");
		source.append(".method public static void main() {");
		source.append(".entrypoint");

	}

	// #2
	public void actionEnd() {
		source.append("ret");
		source.append("}");
		source.append("}");
	}

	/**
	 * Ação 3 Faz a verificação se a variavel já foi declarada, caso não esteja
	 * gera código de declaração
	 * 
	 * @throws SemanticError
	 */
	public void actionDeclaraID() throws SemanticError {

		Token lastType = pilhaTipo.pop();
		String type = getType(Constants.values()[lastType.getId()]);
		for (String id : listaIdentificadores) {

			if (tableSimbles.containsKey(id)) {
				throw new SemanticError(String.format("Identificador %s já declarado.", id));
			}
			tableSimbles.put(id, lastType);
			source.append(".locals(" + type + id + ")");
		}
		listaIdentificadores.clear();
	}

	private String getType(Constants type) throws SemanticError {

		switch (type) {

		case NUMBER:
			return "float64 ";

		case LITERAL:
			return "string ";

		case BOOLEAN:
			return "bool ";

		default:
			throw new SemanticError("Classe de token é inválida");
		}
	}

	/**
	 * Ação 4 Adiciona tipo na pilha de tipo
	 * 
	 * @param token
	 * @throws SemanticError
	 */
	public void actionAddTipo(Token token) throws SemanticError {
		pilhaTipo.add(token);
	}

	/**
	 * Ação 5 Adiciona o identificador na lista de identificadores
	 * 
	 * @param token
	 * @throws SemanticError
	 */
	public void actionAddID(Token token) throws SemanticError {
		listaIdentificadores.add(token.getLexeme());
	}

	/**
	 * Ação 6 Faz atribuição
	 * TODO: ta errado
	 * @param token
	 * @throws SemanticError
	 */
	public void actionAtribuir(Token token) throws SemanticError {
		source.append("ldc.r8 " + token.getLexeme());
	}

	/**
	 * Action 7 Faz a leitura de infomação do console TODO: parsear
	 * 
	 * @throws SemanticError
	 */
	public void actionLeitura() throws SemanticError {
		source.append("call String [mscorlib]System.Console::ReadLine()");
		source.append("call " + getType(Constants.NUMBER) + " [mscorlib]System.Console::Parse(String)");
	}

	/**
	 * Action 8 Escreve um comando no console
	 * 
	 * @throws SemanticError
	 * 
	 */
	public void actionEscrita() throws SemanticError {
		Token token = pilhaTipo.pop();
		source.append("call [mscorlib]System.Console::Write(" + getType(token.getTokenClass()));
	}

	/**
	 * DESCRIÇÃO ;p
	 * 
	 * @param token
	 * @throws SemanticError
	 */
	public void actionNove(Token token) throws SemanticError {
		if (token.getLexeme().equalsIgnoreCase("TRUE")) {
			source.append("ldc.i4.1");
		} else if (token.getLexeme().equalsIgnoreCase("FALSE")) {
			source.append("ldc.i4.0");
		}
	}

	public void actionDez(Token token) {
		source.append("ldc.i4.1");
		source.append("xor");
	}

	public void actionOnze(Token token) {
		source.append("ldc.i8-1");
		source.append("mul");
	}

	public void actionDoze(Token token) {
		operadorRelacional = token.getLexeme();
	}

	public void actionTreze(Token token) {
		if (operadorRelacional.equals(">")) {
			source.append("cgt");
		} else if (operadorRelacional.equals("=")) {
			source.append("ceq");
		} else if (operadorRelacional.equals("<")) {
			source.append("clt");
		}
	}

	public void actionQuatorze() {
		String tipo = pilhaTipo.pop().getLexeme();
		source.append("call void[mscorlib] System.Console::Write");
	}

	/**
	 * 
	 * @param action
	 * @param token
	 * @throws SemanticError
	 */
	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);

		switch (action) {
		case 1:
			actionProgram();
			break;

		case 2:
			actionEnd();
			break;

		case 3:
			actionDeclaraID();
			break;

		case 4:
			actionAddTipo(token);
			break;

		case 5:
			actionAddID(token);
			break;

		case 6:
			actionAtribuir(token);
			break;

		case 7:
			actionLeitura();
			break;

		case 8:
			actionEscrita();
			break;

		default:
			break;
		}
	}

}
