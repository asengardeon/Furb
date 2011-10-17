package Lexical;

import java.util.Stack;
import Lexical.ParserConstants;
import Lexical.Constants;

public class Sintatico 
{
    private Stack stack = new Stack();
    private Token currentToken;
    private Token previousToken;
    private Lexico scanner;
    private Semantico semanticAnalyser;

    private static final boolean isTerminal(int x)
    {
        return x < Lexical.ParserConstants.FIRST_NON_TERMINAL;
    }

    private static final boolean isNonTerminal(int x)
    {
        return x >= Lexical.ParserConstants.FIRST_NON_TERMINAL && x < Lexical.ParserConstants.FIRST_SEMANTIC_ACTION;
    }

    private static final boolean isSemanticAction(int x)
    {
        return x >= Lexical.ParserConstants.FIRST_SEMANTIC_ACTION;
    }

    private boolean step() throws LexicalError, SyntaticError, SemanticError
    {
        if (currentToken == null)
        {
            int pos = 0;
            if (previousToken != null)
                pos = previousToken.getPosition()+previousToken.getLexeme().length();

            Constants enume = Lexical.Constants.DOLLAR;
            currentToken = new Token(enume.getId(), "$", pos);
        }

        int x = ((Integer)stack.pop()).intValue();
        int a = currentToken.getId();

        if (x == Lexical.Constants.EPSILON.ordinal())
        {
            return false;
        }
        else if (isTerminal(x))
        {
            if (x == a)
            {
                if (stack.empty())
                    return true;
                else
                {
                    previousToken = currentToken;
                    currentToken = scanner.nextToken();
                    return false;
                }
            }
            else
            {
                throw new SyntaticError(Lexical.ParserConstants.PARSER_ERROR[x], currentToken.getPosition());
            }
        }
        else if (isNonTerminal(x))
        {
            if (pushProduction(x, a))
                return false;
            else
                throw new SyntaticError(Lexical.ParserConstants.PARSER_ERROR[x], currentToken.getPosition());
        }
        else // isSemanticAction(x)
        {
        	//Não implementado Ainda
            //semanticAnalyser.executeAction(x- FIRST_SEMANTIC_ACTION, previousToken);
            return false;
        }
    }

    private boolean pushProduction(int topStack, int tokenInput)
    {
        int p = Lexical.ParserConstants.PARSER_TABLE[topStack-Lexical.ParserConstants.FIRST_NON_TERMINAL][tokenInput-1];
        if (p >= 0)
        {
            int[] production = Lexical.ParserConstants.PRODUCTIONS[p];
            //empilha a produção em ordem reversa
            for (int i=production.length-1; i>=0; i--)
            {
                stack.push(new Integer(production[i]));
            }
            return true;
        }
        else
            return false;
    }

    public void parse(Lexico scanner, Semantico semanticAnalyser) throws LexicalError, SyntaticError, SemanticError
    {
        this.scanner = scanner;
        this.semanticAnalyser = semanticAnalyser;

        stack.clear();
        stack.push(new Integer(Lexical.Constants.DOLLAR.getId()));
        stack.push(new Integer(Lexical.ParserConstants.START_SYMBOL));

        currentToken = scanner.nextToken();

        while ( ! step() )
            ;
    }
}
