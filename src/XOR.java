
/**
 * 1）已知一个整形数组，其中只有一种数出现了奇数次，其他的所有数都出现了偶数次，找出出现奇数次的数
 * 2）接上，有两种数出现了奇数次，其它所有数都是偶数次，找出奇数次数的数
 * 要求 时间复杂度 O(N), 空间复杂度O(1)
 * */

public class XOR {
    // 1）通过异或进行，自己和自己异或得 0，任何数和0异或得回自己，奇数和1异或-1，偶数和1异或+1
    public static void printOddTimesNum1(int[] arr) {
        // 创建一个异或初始变量 0
        // 将数组的所有数和初始变量一起异或，出现偶数次数的数字都会变为0
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    public static void printOddTimesNum2(int[] arr) {
        // 创建一个异或初始变量 0
        // 将数组的所有数和初始变量一起异或，出现偶数次数的数字都会变为0
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        // 循环完后得到和 eor 是这两种数的异或，先称为 a ^ b，且eor不为0，因为 a != b
        // 所以 eor 在二进制的位上必然有一位是1，因为如果全为0的话那就a和b就相等了
        int rightOne = eor & (~eor + 1); // 提取最右侧的1
        int onlyOne = 0;
        for (int cur : arr) {
            // 只有与 eor 指定位置的为1的数才进行异或
            // 这样就区分开了两种数
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        // 异或完后 onlyOne 就是两种数中的其中一个a或者b
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,2,2,2,1,1,1,3,3,3};
        int[] arr2 = {1,2,2,2,2,1,1,1,3,3,3,8,8,8};
        printOddTimesNum1(arr1);
        printOddTimesNum2(arr2);
    }
}
