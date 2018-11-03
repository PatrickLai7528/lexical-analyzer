package input;

import java.io.*;

public class Input {
    private InputResult inputResult = new InputResult();

    public Input(String[] fileNames) {
        for (String fileName : fileNames) {
            input(fileName);
        }
    }

    private void input(String inputFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFile))));
            String line;
            int index = 0;
            char input[] = new char[500];
            char[] tempChars;

            while (null != (line = br.readLine())) {

                tempChars = line.toCharArray();
                for (char c : tempChars) {
                    if (c == '\t') {
                        continue;
                    }
                    input[index++] = c;
                }
                input[index++] = '\n';
            }
//            input[index] = '#';
            br.close();

            this.inputResult.append(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputResult result() {
        return this.inputResult;
    }

//    public static void main(String[] args) {
//        Input input = new Input(new String[]{"./src/test1.txt"});
//        System.out.println(input.result());
//    }
}
