package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 1、两数之和
 */
public class Leetcode_406_QueueReconstructionByHeight {

    public static void main(String[] args) {

    }

    private static int[][] method1(int[][] people) {
        Arrays.sort(people, (v1, v2) -> v1[0] == v2[0] ? v1[1] - v2[1] : v2[0] - v1[0]);

        LinkedList<int[]> res = new LinkedList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }

        return res.toArray(new int[people.length][2]);
    }
}
