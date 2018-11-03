package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import java.io.*;

public class Output {
    private List<Token> tokenList;
    private String outputFileName;

    public Output(List<Token> tokenList, String outputFileName) {
        this.tokenList = tokenList;
        this.outputFileName = outputFileName;
    }

    public void post() {
        String[] splits = outputFileName.split("\\.");
        File outputFile = new File(splits[0] + "_output.txt");
        try {
            if (!outputFile.exists())
                outputFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, false));

            for (Token t : tokenList) {
//            System.out.println(t.toString());
                bw.write(t.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
