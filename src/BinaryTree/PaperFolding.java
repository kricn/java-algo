package BinaryTree;

/**
 * 将一张纸条面向自己对折，对折后产生折痕，折痕凹进去的称为凹折痕，凸出来的称为凸折痕
 * 问：纸条折叠 N 次后，由上到下其折痕的凹凸情况
 */

/**
 * 除了第一一次，每次对折，都会在上一次的折痕两侧新增一个凹一个凸的折痕，就像二叉树一样
 * 由上到下打印即为中序遍历
 */
public class PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    // 递归
    /**
     *
     * @param i 节点层数
     * @param N 一共对折了几次
     * @param down down == true 凹 down == false 凸
     */
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) { // base case
            return ;
        }
        // 这里折出来上面是凹，那down就先是true,因为每次在折痕两侧产生的新折痕都是上面是凹，下面是凸
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 5;
        printAllFolds(N);
    }

}
