import java.util.HashSet;
import java.util.Set;

public class StackStabilization {

    public int getMinimumDeflatedDiscCount(int N, int[] R) {
        // Write your code here

        int currentIndex = 0;
        Set<Integer> deflatedDiscs = new HashSet<Integer>();

        while(currentIndex < N - 1) {

            if(currentIndex == -1) {
                currentIndex = 0;
            }

            int currentDiscSize = R[currentIndex];
            int nextDiscSize = R[currentIndex + 1];

            if(currentDiscSize >= nextDiscSize) {
                int newSize = nextDiscSize - 1;
                if(newSize == 0) {
                    deflatedDiscs = null;
                    break;
                }

                R[currentIndex] = newSize;
                deflatedDiscs.add(currentIndex);
                currentIndex--;
            }
            else {
                currentIndex++;
            }
        }

        int numberOfDeflatedDiscs;
        if(deflatedDiscs == null) {
            numberOfDeflatedDiscs = -1;
        }
        else {
            numberOfDeflatedDiscs = deflatedDiscs.size();
        }

        return numberOfDeflatedDiscs;
    }

}
