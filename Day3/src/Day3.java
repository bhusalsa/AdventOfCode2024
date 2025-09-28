import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    static String fileLocation = "Day3/resources/memory.txt";
    public static void main(String[] args) throws IOException {
        StringBuilder sb = parser(fileLocation);
        String text = sb.toString();
        String[] instructions = unCorrupt(text);
        int sum = multiply(instructions);

        System.out.println("Sum of all multiplication is: " + sum);
    }

    private static int multiply(String[] instructions) {
        int sum = 0;
        for (String instruction : instructions) {
            int firstNum =  Integer.parseInt(instruction.split(",")[0]);
            int secondNum = Integer.parseInt(instruction.split(",")[1]);
            System.out.println(firstNum + " * " + secondNum);
            sum += mul(firstNum, secondNum);
        }
        return sum;
    }

    private static String[] unCorrupt(String corrupted) {
        ArrayList<String> mul = new ArrayList<>();
        String[] splitCorrupted = corrupted.split("\\)", -1);
        for (int i = 0; i < splitCorrupted.length; i++) {
            System.out.println(splitCorrupted[i]);
            if (splitCorrupted[i].contains("mul(")) {
                String[] mulSplit = splitCorrupted[i].split("mul\\(");
                String string = null;
                for (int j = 0; j < mulSplit.length; j++) {
                    if (mulSplit[j].matches("[0-9]{1,3},[0-9]{1,3}")) {
                        string = mulSplit[j];
                        System.out.println(string);
                        mul.add(string);
                    }
                }
            }
        }
        return mul.toArray(new String[0]);
    }


    private static int mul(int a, int b) {
        return a * b;
    }

    private static int sumArray(int[] numbers)
    {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    private static StringBuilder parser(String filelocation) throws IOException {
        FileReader fr = new FileReader(filelocation);
        BufferedReader br = new BufferedReader(fr);
        String line;
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return sb;
    }
}

