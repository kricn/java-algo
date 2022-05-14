package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 现有一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你一个项目开始的时间和结束的时间（给你一个数组，里面是一个个具体的项目）
 * 你来安排宣讲的日程，要求会议室进行的宣讲场次最多。
 * 返回这个最多的宣讲场次
 */

/**
 * 贪心策略，最先结束的优先安排
 * 若最先开始的优先安排，其时间可能很长
 * 若时间短的有限安排，其可能卡在两个不重合的时间段中
 */
public class BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 按照会议的结束时间来排序
    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * @param programs 所有的会议安排表
     * @param timePoint 会议开始时间点
     * @return int 能排多少会议
     */
    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        // 依次遍历所有会议
        for (int i = 0; i < programs.length; i ++) {
            // 时间点小于等于当前会议的开始时间，则说明该会议可以安排
            if (timePoint <= programs[i].start) {
                result ++;
                // 安排了和场会议后，时间点就来到了当前会议的结束时间
                // 其他与之重合的会议不再安排
                timePoint = programs[i].end;
            }
        }
        return result;
    }

}
