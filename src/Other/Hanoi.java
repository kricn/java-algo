package Other;

public class Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }

    // 1 ~ i 圆盘目标是 from -> to, other 是另外一个
    public static void func(int i, String start, String end, String other) {
        if (i == 1) {
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            // 先将 i - 1 个圆盘从 start 移动到 other 上
            func (i - 1, start, other, end);
            // 剩下一个 i 则可以移动到目标上
            System.out.println("Move " + i + " from " + start + " to " + end);
            // 最后再将之前的移动回来
            func(i - 1, other, end, start);
        }
    }

}
