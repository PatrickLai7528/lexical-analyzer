import analyzer.Analyzable;
import analyzer.Analyzer;
import analyzer.AnalyzerResult;
import input.Input;
import input.InputResult;
import output.Output;


public class Main {
    public static void main(String[] args) {
        String file1 = "src/test1.txt";

        String[] fileNames = new String[]{file1};
        Input input = new Input(fileNames);
        Analyzer analyzer = new Analyzer(input.result());
        AnalyzerResult analyzerResult = analyzer.analysis();
        Output output = new Output(analyzerResult.getTokenList(), file1);
        output.post();
    }
}
