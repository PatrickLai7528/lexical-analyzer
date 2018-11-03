package output;

public class Token {
    public static enum Type {
        SYMBOL, RESERVED_WORD, NORMAL_WORD, NUMBER, ERROR
    }

    private String code;
    private Type type;
    private String error;
    private int line;

    public Token(String code, Type type, int line) {
        this.code = code;
        this.type = type;
        this.error = null;
        this.line = line;
    }


    @Override
    public String toString() {
        return "{" +
                "\tcode =\t" + code +
                "\ttype =\t" + type +
                "\terror =\t'" + error + '\'' +
                "\tline =\t" + line +
                "\t}";
    }
}
