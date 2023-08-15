import java.util.List;

public class NewYearChaos {
    /*
    Time : O(n^2)
    Space : O(1)
    The number of people bribed by current person is determined by how many positions ahead of its correct
    position it really is.
    To calculate the total number of bribes count the number of greater element ahead of each number.
     */
    public static void minimumBribes(List<Integer> q) {
        // Write your code here

        boolean chaotic = false;
        int totalBribes = 0;
        for(int index = 0, qSize = q.size() ; index < qSize ; index++) {
            int currentNumber = q.get(index);
            int expectedNumber = index + 1;

            int bribesByCurrent = 0;
            if(currentNumber > expectedNumber) {
                bribesByCurrent = currentNumber - expectedNumber;
            }

            if(bribesByCurrent > 2) {
                chaotic = true;
                break;
            }
            else {
                for(int indexPrev = 0 ; indexPrev < index ; indexPrev++) {
                    if(q.get(indexPrev) > currentNumber) {
                        totalBribes++;
                    }
                }
            }
        }

        if(chaotic) {
            System.out.println("Too chaotic");
        }
        else {
            System.out.println(totalBribes);
        }
    }

    /*
    Time : O(n^2)
    Space : O(n^2)
    Optimized version of the previous algorithm.
    Using the fact that any number which bribed a person will not go more than two places ahead of the original
    position.
     */
    public static void minimumBribes(List<Integer> q) {
        // Write your code here

        boolean chaotic = false;
        int totalBribes = 0;
        for(int index = 0, qSize = q.size() ; index < qSize ; index++) {
            int currentNumber = q.get(index);
            int expectedNumber = index + 1;

            int bribesByCurrent = 0;
            if(currentNumber > expectedNumber) {
                bribesByCurrent = currentNumber - expectedNumber;
            }

            if(bribesByCurrent > 2) {
                chaotic = true;
                break;
            }
            else {
                for(int indexPrev = Math.max(0, currentNumber - 2) ; indexPrev < index ; indexPrev++) {
                    if(q.get(indexPrev) > currentNumber) {
                        totalBribes++;
                    }
                }
            }
        }

        if(chaotic) {
            System.out.println("Too chaotic");
        }
        else {
            System.out.println(totalBribes);
        }
    }
}
