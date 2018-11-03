package analyzer;

import output.Token;

import java.util.List;

public class Analyzer {
    private final int MAX_NUMBER_LENGTH = 20;
    private int maxReservedWordLength;
    private List<char[]> contents;
    private AnalyzerResult analyzerResult = new AnalyzerResult();
    private int lineCounter = 1;

    public Analyzer(Analyzable analyzable) {
        int maxLength = 0;
        for (ReservedWord e : ReservedWord.values()) {
            int reservedWordLength = e.getString().length();
            if (reservedWordLength > maxLength) {
                maxLength = reservedWordLength;
            }
        }
        this.maxReservedWordLength = maxLength;
        this.contents = analyzable.getContent();
    }

    public AnalyzerResult analysis() {
        for (char[] content : this.contents) {
            analysis(content);
        }
        return this.analyzerResult;
    }

    private void analysis(char[] content) {
        for (int i = 0; i < content.length; i++) {

            char currentChar = content[i];
            if (currentChar == '\0' || currentChar == ' ' || currentChar == '\t') {
                continue;
            } else if (currentChar == '\n') {
                this.lineCounter++;
                continue;
            }


            if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')) {
                i += checkReservedWord(content, i, currentChar);
            } else if ((currentChar >= '0' && currentChar <= '9')) {
                i += checkNumber(content, i, currentChar);
            } else {
                i += checkSymbol(content, i, currentChar);
            }

        }
    }

    private int checkReservedWord(char[] content, int i, char currentChar) {
        char[] maybeWord = new char[this.maxReservedWordLength];
        maybeWord[0] = currentChar;
        for (int j = 1; j < this.maxReservedWordLength; j++) {
            char nextChar = content[j + i];
            if ((nextChar >= '0' && nextChar <= '9') || (nextChar >= 'a' && nextChar <= 'z') || (nextChar >= 'A' && nextChar <= 'Z')) {
                maybeWord[j] = content[j + i];
            } else {
                break;
            }
        }

        return findReservedWord(maybeWord) - 1;
    }

    private int findReservedWord(char[] maybeWord) {
        for (ReservedWord reservedWord : ReservedWord.values()) {
            if (reservedWord.isSame(maybeWord)) {
                this.handleReservedWordFound(reservedWord);
                return reservedWord.getString().length();
            }
        }
        String stringFromChar = String.valueOf(maybeWord).trim();
        this.handleNormalWordFound(stringFromChar);
        return stringFromChar.length();
    }

    private int checkNumber(char[] content, int i, char currentChar) {
        char[] maybeNumber = new char[MAX_NUMBER_LENGTH];
        maybeNumber[0] = currentChar;
        for (int j = 1; j < content.length; j++) {
            char nextChar = content[j + i];
            if ((nextChar >= '0' && nextChar <= '9')) {
                maybeNumber[j] = content[j + i];
            } else {
                break;
            }
        }
        String stringFromChar = String.valueOf(maybeNumber).trim();
        this.handleNumberFound(stringFromChar);
        return stringFromChar.length() - 1;
    }

    private int checkSymbol(char[] content, int i, char currentChar) {
        char[] maybeSymbol = new char[3];
        boolean isEmpty = false;
        switch (currentChar) {
            case '+':
                maybeSymbol[0] = currentChar;
                checkMathSymbol(content, i, maybeSymbol);
                break;
            case '-':
                maybeSymbol[0] = currentChar;
                checkMathSymbol(content, i, maybeSymbol);
                break;
            case '*':
                maybeSymbol[0] = currentChar;
                checkMathSymbol(content, i, maybeSymbol);
                break;
            case '/':
                maybeSymbol[0] = currentChar;
                checkMathSymbol(content, i, maybeSymbol);
                break;
            case '=':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '=')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '&':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '&')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '|':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '|')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '!':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '=')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '<':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '=')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '>':
                maybeSymbol[0] = currentChar;
                if (content.length != i - 1) {
                    char nextChar = content[i + 1];
                    if (nextChar == '=')
                        maybeSymbol[1] = nextChar;
                }
                break;
            case '(':
                maybeSymbol[0] = currentChar;

                break;
            case ')':
                maybeSymbol[0] = currentChar;
                break;
            case '[':
                maybeSymbol[0] = currentChar;
                break;
            case ']':
                maybeSymbol[0] = currentChar;
                break;
            case '{':
                maybeSymbol[0] = currentChar;
                break;
            case '}':
                maybeSymbol[0] = currentChar;
                break;
            case ',':
                maybeSymbol[0] = currentChar;
                break;
            case ':':
                maybeSymbol[0] = currentChar;
                break;
            case ';':
                maybeSymbol[0] = currentChar;
                break;
            case '\'':
                maybeSymbol[0] = currentChar;
                break;
            case '\"':
                maybeSymbol[0] = currentChar;
                break;
            default:
                this.handleErrorFound("Unknown character " + currentChar);
                isEmpty = true;
        }
        if (isEmpty) return 0;

        String ret = String.valueOf(maybeSymbol).trim();
        handlerSymbolFound(ret);
        return ret.length() - 1;
    }


    private void checkMathSymbol(char[] content, int i, char[] maybeSymbol) {
        if (content.length != i - 1) {
            char nextChar = content[i + 1];
            switch (nextChar) {
                case '+':
                    maybeSymbol[1] = nextChar;
                    break;
                case '=':
                    maybeSymbol[1] = nextChar;
            }
        }
    }

    private void handlerSymbolFound(String symbol) {
//        System.out.println("Is Symbol " + symbol);
        this.analyzerResult.addSymbol(symbol, this.lineCounter);
    }

    private void handleReservedWordFound(ReservedWord reservedWord) {
//        System.out.println("Is Reserved Word " + reservedWord.getString());
        this.analyzerResult.addReservedWord(reservedWord.getString(), this.lineCounter);
    }

    private void handleNormalWordFound(String normalWord) {
//        System.out.println("Is NormalWord " + normalWord);
        this.analyzerResult.addNormalWord(normalWord, this.lineCounter);
    }

    private void handleNumberFound(String number) {
        try {
            Integer.parseInt(number);

//            System.out.println("Is Number " + number);
            this.analyzerResult.addNumber(number, this.lineCounter);
        } catch (NumberFormatException e) {
            this.handleErrorFound("Integer Overflow ");
        }
    }

    private void handleErrorFound(String error) {
//        System.out.println("Is Error " + error);
        this.analyzerResult.addError(error, this.lineCounter);
    }
}
