package laioffer.CrossTrainingIII;

import java.util.HashMap;
import java.util.Map;

public class MostPointsOnALine {

    // time = O(n^2)
    // space = O(n)
    public int most(Point[] points) {
        // Write your solution here.
        if (points == null || points.length == 0) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < points.length; i++) {
            Point seed = points[i];
            int same = 1;
            int sameX = 0;
            Map<Double, Integer> slopeCounter = new HashMap<>();

            int most = 0;

            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                Point tmp = points[j];

                if (seed.x == tmp.x) {
                    if (seed.y == tmp.y) {
                        same++;
                    } else {
                        sameX++;
                    }
                } else {
                    Double slope = ((seed.y - tmp.y) + 0.0) / (seed.x - tmp.x);
                    slopeCounter.put(slope, slopeCounter.getOrDefault(slope, 0) + 1);
                    most = Math.max(most, slopeCounter.get(slope));
                }
            }

            res = Math.max(res, Math.max(most, sameX) + same);
        }

        return res;
    }

    class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
