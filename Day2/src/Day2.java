import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day2 {
    static String filename = "Day2/resources/test.txt";
    static ArrayList<int[]> unsafeP1 = new ArrayList<>();
    static ArrayList<Integer> issuelocations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder report = parser(filename);
        String[] lines = report.toString().split(System.lineSeparator());

        int safe = checkReport(lines);
        int dampened = dampner(unsafeP1);

        System.out.println("Safe reports: " + safe);
        System.out.println("Dampened reports: " + dampened);
        System.out.println("Combined safe/dampened reports: " + (safe + dampened));
    }

    public static StringBuilder parser(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
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

    public static int checkReport(String[] lines){
        int safe = 0;
        int i = 0;
        int damp = 0;

        for (String line : lines) {
            String[] values = line.split(" ");
            int[] value = new int[values.length];

            for (int k = 0; k < values.length; k++) {
                value[k] = Integer.parseInt(values[k]);
            }

            boolean ok = true;
            if (value[0] > value[1]) {
                for (int k = 1; k < value.length; k++) {
                    if (value[k - 1] > value[k]) {
                        int diff = Math.abs(value[k - 1] - value[k]);
                        if (diff >= 1 && diff <= 3) {
                            continue;
                        }
                    }
                    unsafeP1.add(value);
                    issuelocations.add(k-1);
                    ok = false;
                    break;
                }
            } else if (value[0] < value[1]) {
                for (int k = 1; k < value.length; k++) {
                    if (value[k - 1] < value[k]) {
                        int diff = Math.abs(value[k - 1] - value[k]);
                        if (diff >= 1 && diff <= 3) {
                            continue;
                        }
                    }
                    unsafeP1.add(value);
                    issuelocations.add(k-1);
                    ok = false;
                    break;

                }
            } else {
                unsafeP1.add(value);
                ok = false;
            }
            if (ok) {
                safe++;
                System.out.println(line);
            }

        }

        return safe;
    }

    public static int dampner(ArrayList<int[]> unsafeP1) {
        int count = 0;
        for (int i = 0; i < unsafeP1.size(); i++) {
            int[] values = unsafeP1.get(i);
            int prev =  values[0];
            boolean ok = true;

            int removed = 0;
            for (int j = 1; j < values.length; j++) {
                if (values[j] > prev) {
                    prev = values[j];
                    continue;
                }
                removed++;
                prev = values[j-1];

                if (removed > 1) {
                    ok = false;
                    break;
                }
            }
            if(!ok) {
                for (int j = 1; j < values.length; j++) {
                    if (values[j] < prev) {
                        prev = values[j];
                        continue;
                    }
                    removed++;
                    prev = values[j - 1];

                    if (removed > 1) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                printArray(values);
                count++;
            }
            }

        return count;
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}