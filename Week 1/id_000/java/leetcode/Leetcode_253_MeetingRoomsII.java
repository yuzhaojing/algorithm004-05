package leetcode;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

import java.util.*;

/**
 * 1、两数之和
 */
public class Leetcode_253_MeetingRoomsII {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30},{5, 10},{15, 20}};
        int res = method2(intervals);
        System.out.println(res);
    }

    /**
     * 排序法 时间复杂度O(NlogN)
     * 将开始时间和结束时间排序，只要开始时间小于结束时间，会议室+1
     * 如果开始时间大于结束时间，说明会议室可以重用，结束时间角标后移
     */
    private static int method1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start, (v1, v2) -> v1 - v2);
        Arrays.sort(end, (v1, v2) -> v1 - v2);

        int maxMeetingRoom = 0;
        int end_index = 0;

        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[end_index]) {
                maxMeetingRoom++;
            } else {
                end_index++;
            }
        }

        return maxMeetingRoom;
    }

    /**
     * 优先队列 时间复杂度O(NlogN)
     *
     * 按照 开始时间 对会议进行排序。
     * 初始化一个新的最小堆，将第一个会议的结束时间加入到堆中。
     * 我们只需要记录会议的结束时间，告诉我们什么时候房间会空。
     *
     * 对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲。
     *
     * 若房间空闲，则从堆顶拿出该元素，将其改为我们处理的会议的结束时间，加回到堆中。
     * 若房间不空闲。开新房间，并加入到堆中。
     * 处理完所有会议后，堆的大小即为开的房间数量。这就是容纳这些会议需要的最小房间数。
     *
     */
    private static int method2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v1 - v2);

        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        for (int i = 0; i < intervals.length; i++) {
            if (priorityQueue.isEmpty()) priorityQueue.add(intervals[i][0]);

            if (intervals[i][0] >= priorityQueue.peek()) {
                priorityQueue.poll();
            }

            priorityQueue.add(intervals[i][1]);
        }

        return priorityQueue.size();
    }
}
