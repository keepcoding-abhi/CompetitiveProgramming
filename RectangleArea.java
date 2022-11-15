public class RectangleArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        int overlappingArea = 0;

        int xOverlap = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int yOverlap = Math.min(ay2, by2) - Math.max(ay1, by1);

        if(xOverlap > 0 && yOverlap > 0) {
            overlappingArea = xOverlap * yOverlap;
        }

        int rect1Area = findArea(ax1, ay1, ax2, ay2);
        int rect2Area = findArea(bx1, by1, bx2, by2);

        return rect1Area + rect2Area - overlappingArea;
    }

    private int findArea(int x1, int y1, int x2, int y2) {
        int width = Math.abs(x1 - x2);
        int length = Math.abs(y1 - y2);

        return length * width;
    }

}
