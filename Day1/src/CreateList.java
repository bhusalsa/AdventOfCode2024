import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CreateList {

    static int size = 1000;
    static int[] listA = new int[size];
    static int[] listB = new int[size];


    public void parser(String filelocation) throws IOException {
        FileReader fr = new FileReader(filelocation);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        int i = 0;
        int firstnum;
        int secondnum;
        String space;
        String end;
        while(line != null) {
            firstnum = Integer.parseInt(line.substring(0,5));
            secondnum = Integer.parseInt(line.substring(8,13));
            listA[i] = firstnum;
            listB[i] = secondnum;
            i++;
            line = br.readLine();
        }
        Arrays.sort(listA);
        Arrays.sort(listB);

        int diff;
        int difference = 0;
        for(int j=0;j<listA.length;j++) {
            diff = listB[j]-listA[j];
//            if(diff == 0)
//                System.out.println("Firstnum: " + listA[j] +" Secondnum: "+listB[j]);
            difference = difference + Math.abs(diff);
        }

        System.out.println("Difference:" + difference);
    }
}
