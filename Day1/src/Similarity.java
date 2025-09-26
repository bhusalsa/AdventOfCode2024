public class Similarity
{
    public static int findSimilarity(int a, int[] listB)
    {
        int index = 0;
        int count = 0;
        if(a == 15499)
            System.out.println(a);
        while (a >= listB[index]){
            if (a == listB[index]){
                count++;
            }
            index++;

            if(index == listB.length){
                break;
            }
        }
        return count;
    }
}
