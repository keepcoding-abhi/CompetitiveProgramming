import java.util.*;

public class HandOfStraights {

    /*
    Time: O(n)
    Space: O(n)

    Sort the array and use the fact that the elements in the group will be consecutively next to each other.
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        boolean groupingPossible = false;

        if((hand.length % groupSize) == 0) {

            Arrays.sort(hand);
            boolean[] visited = new boolean[hand.length];

            groupingPossible = true;

            for(int index = 0 ; index < hand.length ; index++) {
                if(!visited[index]) {

                    visited[index] = true;
                    int currentEl = hand[index];

                    int nextIndex = index + 1;

                    int lastEl = currentEl + groupSize;
                    currentEl++;

                    while(currentEl < lastEl && nextIndex < hand.length) {

                        if(hand[nextIndex] > currentEl) {
                            break;
                        }
                        else if(hand[nextIndex] == currentEl && visited[nextIndex] == false) {
                            visited[nextIndex] = true;
                            currentEl++;
                        }

                        nextIndex++;
                    }

                    if(currentEl < lastEl) {
                        groupingPossible = false;
                        break;
                    }
                }
            }
        }

        return groupingPossible;
    }

    /*
    Time: O(nLog(n))
    Space: O(n)

    Store the frequency of each element in a map. ANd use a list to save the list in an ordered manner.
    Follow the list to keep track of the first element in each group.
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        boolean groupingPossible = false;

        if((hand.length % groupSize) == 0) {
            Map<Integer, Integer> elAndCounts = new HashMap<Integer, Integer>();
            Set<Integer> elementChecker = new HashSet<Integer>();
            List<Integer> seenElements = new ArrayList<Integer>();

            for(int num : hand) {
                elAndCounts.put(num, elAndCounts.getOrDefault(num, 0) + 1);
                if(!elementChecker.contains(num)) {
                    seenElements.add(num);
                    elementChecker.add(num);
                }
            }

            groupingPossible = true;

            Collections.sort(seenElements);

            for(int currentEl : seenElements) {

                while(elAndCounts.get(currentEl) != 0) {
                    for(int offset = 0 ; offset < groupSize ; offset++) {

                        int numberInHand = currentEl + offset;
                        if(elAndCounts.getOrDefault(numberInHand, 0) == 0) {
                            groupingPossible = false;
                            break;
                        }

                        elAndCounts.put(numberInHand, elAndCounts.get(numberInHand) - 1);
                    }

                    if(!groupingPossible) {
                        break;
                    }
                }
            }
        }

        return groupingPossible;
    }
}
