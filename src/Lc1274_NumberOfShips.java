/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

class Sea {
    public boolean hasShips(int[] topRight, int[] bottomLeft) {
        return false;
    }
}

class Lc1274_NumberOfShips {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        // there's no ships in this area, return 0
        if (! sea.hasShips(topRight, bottomLeft)) return 0;
        // If there exists ship but this area forms to one point, return 1
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) return 1;

        // get the middle point and divide the area to 4 quaters
        int mid_x = (topRight[0] + bottomLeft[0]) /2;
        int mid_y = (topRight[1] + bottomLeft[1]) /2;

        // sum the counts from bottom-left, top-left, bottom-right, top-right
        return countShips(sea, new int[]{mid_x, mid_y}, bottomLeft)
                + countShips(sea, new int[]{mid_x, topRight[1]}, new int[]{bottomLeft[0], mid_y+1})
                + countShips(sea, new int[]{topRight[0], mid_y}, new int[]{mid_x+1, bottomLeft[1]})
                + countShips(sea, topRight, new int[]{mid_x+1, mid_y+1});

    }
}

/**
 We divide the current rectangle into 4 pieces in the middle.

 Base case: when topRight == bottomLeft, meaning our rectangle reduces into a point in the map. We return 1 if hasShips(topRight, bottomLeft)

 Time complexity: O(n) where n is total number of points inside the rectangle
 T(n) = 4xT(n/4) + O(1)
 Apply master theorem: n^(log(4)4) = n is O(O(1)). So T(n) = O(n)
 */
