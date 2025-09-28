import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {
    static String filename = "Day2/resources/report.txt";
    static ArrayList<int[]> unsafeP1 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder report = parser(filename);
        String[] lines = report.toString().split(System.lineSeparator());

        int safe = checkReport(lines);
        System.out.println();
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

        for (String line : lines) {
            String[] values = line.split(" ");
            int[] value = new int[values.length];

            for (int k = 0; k < values.length; k++) {
                value[k] = Integer.parseInt(values[k]);
            }

            //check each rule separately
            boolean ok = false;
            boolean increasing = checkIncreasing(value);
            boolean decreasing = checkDecreasing(value);
            boolean difference = checkDifference(value);

            //decide whether it follows the rules
            if ((increasing && difference) || (decreasing && difference)) {
                ok = true;
            }

            if (ok) {
                safe++;
                System.out.println(line);
            }else {unsafeP1.add(value);}
        }
        return safe;
    }

    public static int dampner(ArrayList<int[]> unsafeP1) {

        int increasingError = 0;
        int decreasingError = 0;
        int differenceError = 0;
        int dampened = 0;

        //remove the value causing problem
        for (int i = 0; i < unsafeP1.size(); i++) {
            int[] values = unsafeP1.get(i);

            //check whether the array is showing increasing or decreasing pattern +1 = increasing -1= decreasing
            int trend = trend(values);
            int error = -1;

            //if increasing check error in increasing
            if(trend == 1) {
                error = checkIncreasingError(values);
            }else if(trend == -1) { //check decreasing error
                error = checkDecreasingError(values);
            }

            //if both increasing and decreasing don't find an error check for error in difference
            if(error == -1) {
            error = checkDifferenceError(values);
            }

            //error check on difference is done second as we can only fix one error if there is an error
            // already in increasing or decreasing sequence then we don't need to find another error in difference,
            // we are only looking for 1 error

            //error might be in i or i-1 so create two arrays and check both
            int[] dampenedArrayi =  new int[values.length-1];
            int[] dampenedArrayi1 = new int[values.length-1];
            int position = 0;
            int position1 = 0;

            //create two array by removing error and error-1 respectively
            for (int j = 0; j < values.length; j++) {
                if (j == error) {
                    continue;
                }
                dampenedArrayi[position++] = values[j];
            }
            for (int j = 0; j < values.length; j++) {
                if (j == error - 1 ) {
                    continue;
                }
                dampenedArrayi1[position1++] = values[j];
            }

            //check if they are now correct
            boolean dampenedi = checkDampenedReport(dampenedArrayi);
            boolean dampednedi1 = checkDampenedReport(dampenedArrayi1);

            //if either one of the array is correct increase the dampened number
            if(dampenedi || dampednedi1){
                dampened++;
            }
        }

        //return the total number of dampened arrays
        return dampened;
    }

    public static boolean checkDampenedReport(int[] line) {

            boolean ok = false;
            boolean increasing = checkIncreasing(line);
            boolean decreasing = checkDecreasing(line);
            boolean difference = checkDifference(line);
            if ((increasing && difference) || (decreasing && difference)) {
                //printArray(line);
                return true;
            }

        return false;
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static boolean checkIncreasing(int[] values){
        boolean increasing = true;
        for (int i = 1; i < values.length; i++) {
            if (values[i-1] > values[i]) {
                return false;
            }
        }
        return increasing;
    }

    public static boolean checkDecreasing(int[] values){
        boolean decreasing = true;
        for (int i = 1; i < values.length; i++) {
            if (values[i-1] < values[i]) {
                return false;
            }
        }
        return decreasing;
    }

    public static boolean checkDifference(int[] values){
        boolean difference = true;
        for (int i = 1; i < values.length; i++) {
            int diff = Math.abs(values[i-1] - values[i]);
            if (diff < 1 || diff >3) {
                return false;
            }
        }
        return difference;
    }

    public static int checkIncreasingError(int[] values){
        boolean increasing = true;
        for (int i = 1; i < values.length; i++) {
            if (values[i-1] > values[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int checkDecreasingError(int[] values){
        for (int i = 1; i < values.length; i++) {
            if (values[i-1] < values[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int checkDifferenceError(int[] values){

        for (int i = 1; i < values.length; i++) {
            int diff = Math.abs(values[i-1] - values[i]);
            if (diff < 1 || diff >3) {
                return i;
            }
        }
        return -1;
    }

    public static int trend(int[] values){
        int increase = 0;
        int decrease = 0;
        int previous = values[0];

        //loop thru all the values and check if there are more
        // increasing or decreasing values to decide the trend
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            if (current > previous) {
                increase++;
            }else if (current < previous) {
                decrease++;
            }
            previous = current;
        }

        //return +1 or -1 based on whether increasing or decreasing
        return (increase > decrease) ? +1 : -1;
    }
}