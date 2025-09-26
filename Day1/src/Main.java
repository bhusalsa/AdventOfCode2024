import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String filelocation = "locations.txt";
    static int difference;
    static int[] listA;
    static int[] listB;


    public static void main(String[] args) throws IOException {
        CreateList createList = new CreateList();
        createList.parser(filelocation);

        listA = createList.listA;
        listB = createList.listB;


        int score;
        int similarity = 0;
        for (int i = 0; i < listA.length; i++) {
            int count = Similarity.findSimilarity(listA[i],listB);
            score = listA[i] * count;
            similarity+= score;
//            if(score > 0){
//                System.out.println(listA[i] + " " + listB[i] + " " + score + " " + similarity);
//            }
        }

        System.out.println("Similarity:" + similarity);
    }

}