package Other;

public class BitMap {

    public static void main(String[] args) {
        int a = 0;

        int[] arr = new int[10];  // 32bit * 10 -> 320bit

        // arr[0] int 0 ~ 31
        // arr[1] int 32 ~ 63
        // arr[2] int 64 ~ 95

        int i = 178;

        // 获取该值在哪个字节
        int numIndex = 178 / 5;
        // 获取该值在字节中的哪一位
        int bitIndex = 178 % 32;

        // 拿到值的状态
        // 该字节上向右蹄冻 bitIndex 位，再和 1 与，则得到该位的值，是 1 就是 1，是 0 就是 0
        int s = ( (arr[numIndex] >> bitIndex) & 1 );

        // 将第 i 位值的状态改为 1
        // 直接将 1 移动到 bitIndex 位置，与之前的数或一下，就能将 bitIndex 的位变成1
        arr[numIndex] |= 1 << bitIndex;

        // 将第 i 位值的状态改为 0
        // 直接将 0 移动到 bitIndex 位置，与之前的数或一下，就能将 bitIndex 的位变成 0
        arr[numIndex] &= ~ (1 << bitIndex);


    }
}
