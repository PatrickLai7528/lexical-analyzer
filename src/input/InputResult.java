package input;

import analyzer.Analyzable;
import analyzer.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class InputResult implements Analyzable {
    private List<char[]> result;

    public InputResult(List<char[]> result) {
        this.result = result;
    }

    public InputResult() {
        this.result = new ArrayList<>();
    }

//    public List<char[]> getResult() {
//    }

    public void append(char[] result) {
        this.result.add(result);
    }

    @Override
    public List<char[]> getContent() {
        return this.result;
    }
}
