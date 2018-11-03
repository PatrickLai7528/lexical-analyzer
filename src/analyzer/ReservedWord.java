package analyzer;

public enum ReservedWord {
    CLASS("Class"), PUBLIC("public"), PROTECTED("protected"), PRIVATE("private"),
    VOID("void"), STATIC("static"), INT("int"), CHAR("char"), FLOAT("float"),
    DOUBLE("double"), STRING("String"), IF("if"), ELSE("else"), SWITCH("switch"),
    CASE("case"), FOR("for"), DO("do"), WHILE("while"), TRY("try"), CATCH("catch");

    private String word;

    ReservedWord(String word) {
        this.word = word;
    }

    String getString() {
        return word;
    }

    boolean isSame(char[] chars) {
        String stringFromChars = String.valueOf(chars).trim();
        boolean ret = this.word.equals(stringFromChars);
        return ret;
    }

    boolean isSame(String string) {
        return this.word.equals(string);
    }
}
