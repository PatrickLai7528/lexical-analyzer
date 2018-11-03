package analyzer;

import java.util.ArrayList;
import java.util.List;

import output.Token;

public class AnalyzerResult {
    private List<Token> tokenList = new ArrayList<>();

    public void addReservedWord(String word, int line) {
        this.tokenList.add(new Token(word, Token.Type.RESERVED_WORD, line));
    }

    public void addNormalWord(String word, int line) {
        this.tokenList.add(new Token(word, Token.Type.NORMAL_WORD, line));
    }

    public void addNumber(String n, int line) {
        this.tokenList.add(new Token(n, Token.Type.NUMBER, line));
    }

    public void addSymbol(String c, int line) {
        this.tokenList.add(new Token(c, Token.Type.SYMBOL, line));
    }

    public void addError(String error, int line) {
        this.tokenList.add(new Token(error, Token.Type.ERROR, line));
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for (Token t : tokenList) {
//            ret += t.toString();
            ret.append(t.toString() + '\n');
        }
        return ret.toString();
    }
}
