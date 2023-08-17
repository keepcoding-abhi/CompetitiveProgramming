public class CtciComparatorSorting {
    /*
    Time : O(1)
    Space : O(1)
     */
    class Checker implements Comparator<Player> {
        // complete this method
        public int compare(Player a, Player b) {
            int result;
            if(a.score > b.score) {
                result = -1;
            }
            else if(a.score < b.score) {
                result = 1;
            }
            else {
                int strComparison = a.name.compareTo(b.name);

                if(strComparison == 0) {
                    result = 0;
                }
                else if(strComparison > 0) {
                    result = 1;
                }
                else {
                    result = -1;
                }
            }

            return result;
        }
    }
}
