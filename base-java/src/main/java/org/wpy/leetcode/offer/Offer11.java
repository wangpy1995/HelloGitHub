package org.wpy.leetcode.offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。<p>
 * <p>
 * 示例 1：<p>
 * <p>
 * 输入：[3,4,5,1,2]<p>
 * 输出：1<p>
 * 示例 2：<p>
 * <p>
 * 输入：[2,2,2,0,1]<p>
 * 输出：0<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer11 {
    /**
     * 由于数组递增旋转
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else r--;
        }
        return numbers[l];
    }

    public static void main(String[] args) {
        int[] numbers = {1,3,5};
        System.out.println(new Offer11().minArray(numbers));
    }
}
