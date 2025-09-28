import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {
    static String filelocation = "Day4/resources/xmas.txt";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = parser(filelocation);

        String[] lines = sb.toString().split("\n");
        int horizontalCounter = 0;
        int backwardCounter = 0;
        int diagnolCounter = 0;
        int verticalCounter = 0;
        int diagonalMASCounter = 0;

        for (String line : lines){
            horizontalCounter+= horizontalXMAS(line);
            backwardCounter+= backwardXMAS(line);
        }
        verticalCounter = verticalXMAS(lines);
        diagnolCounter = diagonalXMAS(lines);
        diagonalMASCounter = diagonalMAS(lines);


        System.out.println("Horizontal XMAS: " + horizontalCounter);
        System.out.println("Backwards XMAS: " + backwardCounter);
        System.out.println("Vertical XMAS: " + verticalCounter);
        System.out.println("Diagonal XMAS: " + diagnolCounter);

        int total = horizontalCounter + backwardCounter + diagnolCounter + verticalCounter;
        System.out.println("Total XMAS: " + total);

        System.out.println("Diagonal MAS: " + diagonalMASCounter);



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

    private static int horizontalXMAS(String line){
        String[] parts = line.split("XMAS", -1);
        return (parts.length-1);
    }
    private static int backwardXMAS(String line){
        String[] parts = line.split("SAMX", -1);
        return (parts.length-1);
    }

    private static int verticalXMAS(String[] line){
        String[] lines = Arrays.copyOf(line, line.length);
        int rows = lines.length;
        int counter = 0;
        for (int i = 0; i < lines.length; i++) {
            if (i <= rows - 4) {
                for (int col = 0; col < lines[i].length(); col++)
                {
                    if(lines[i].charAt(col) == 'X' &&
                            lines[i + 1].charAt(col) == 'M' &&
                            lines[i + 2].charAt(col) == 'A' &&
                            lines[i + 3].charAt(col) == 'S')
                    {
                                counter++;
                    }

                }
            }
            if (i >= 3) {
                for (int col = 0; col < lines[i].length(); col++)
                {
                    if(lines[i].charAt(col) == 'X' &&
                            lines[i - 1].charAt(col) == 'M' &&
                            lines[i - 2].charAt(col) == 'A' &&
                            lines[i - 3].charAt(col) == 'S')
                    {
                        counter++;
                    }

                }
            }
        }

        return counter;

    }

    private static int diagonalXMAS(String[] line){
        String[] lines = Arrays.copyOf(line, line.length);
        int rows = lines.length;
        int counter = 0;
        for (int i = 0; i < lines.length; i++) {
            if (i <= rows - 4) {
                for (int col = 0; col < lines[i].length(); col++)
                {
                    if(col <= lines[i].length()-4) {
                        if (lines[i].charAt(col) == 'X' &&
                                lines[i + 1].charAt(col + 1) == 'M' &&
                                lines[i + 2].charAt(col + 2) == 'A' &&
                                lines[i + 3].charAt(col + 3) == 'S') {
                            counter++;
                        }
                    }
                    if(col >= 3 ) {
                        if (lines[i].charAt(col) == 'X' &&
                                lines[i + 1].charAt(col - 1) == 'M' &&
                                lines[i + 2].charAt(col - 2) == 'A' &&
                                lines[i + 3].charAt(col - 3) == 'S') {
                            counter++;
                        }
                    }
                }
            }
            if (i >= 3) {
                for (int col = 0; col < lines[i].length(); col++)
                {
                    if(col <= lines[i].length()-4) {
                        if (lines[i].charAt(col) == 'X' &&
                                lines[i - 1].charAt(col+1) == 'M' &&
                                lines[i - 2].charAt(col+2) == 'A' &&
                                lines[i - 3].charAt(col+3) == 'S') {
                            counter++;
                        }
                    }
                    if(col >= 3) {
                        if (lines[i].charAt(col) == 'X' &&
                                lines[i - 1].charAt(col-1) == 'M' &&
                                lines[i - 2].charAt(col-2) == 'A' &&
                                lines[i - 3].charAt(col-3) == 'S') {
                            counter++;
                        }
                    }

                }
            }
        }

        return counter;
    }

    private static int diagonalMAS(String[] line){

        int counter = 0;
        for (int rows = 1; rows < line.length-1; rows++) {
                for (int col = 1; col < line[rows].length() - 1; col++) {

                    char a = line[rows].charAt(col);

                    if( a != 'A'){
                        continue;
                    }
                    char nw =  line[rows-1].charAt(col-1);
                    char ne = line[rows-1].charAt(col+1);
                    char sw = line[rows+1].charAt(col-1);
                    char se = line[rows+1].charAt(col+1);

                    if((ne == 'M' && sw == 'S') || (ne == 'S' && sw == 'M')){
                        if((se == 'M' && nw == 'S') || (se == 'S' && nw == 'M')){
                            counter++;
                        }
                    }
                }
        }

        return counter;
    }


}
