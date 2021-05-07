//
// Created by w on 2021/4/30.
//

//给你一个整数数组nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
//示例 1：
//输入：nums = [2,2,3,2]
//输出：3
//示例 2：
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
//提示：
//1 <= nums.length <= 3 * 104
//-231 <= nums[i] <= 231 - 1
//nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
//进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/single-number-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
#include "printf.h"

int singleNumber(int *nums, int numsSize) {
    // 状态转换, 第一次出现ab=01 , 第二次出现ab=10, 第三次出现时ab=00
    // ab: 01, 10, 00
    int a = 0, b = 0;
    for (int i = 0; i < numsSize; ++i) {
        b = (b ^ nums[i]) & ~a;
        a = (a ^ nums[i]) & ~b;
    }
    return b;
}

// 扩展  出现5次
int singleNumber5(int *nums, int numsSize){
    int a,b,c,d = 0;
    // 五种状态，第五次出现时abcd均变为0
    // 0001 0010 0100 1000 0000
    for (int i = 0; i < numsSize; ++i) {
        d = (d^nums[i])&(~a)&(~b)&(~c);
        c = (c^nums[i])&(~a)&(~b)&(~d);
        b = (b^nums[i])&(~a)&(~c)&(~d);
        a = (a^nums[i])&(~b)&(~c)&(~d);
    }
    return d;
}

//int main() {
//    int nums[] = {0, 1, 0, 1, 0, 1,0,0,1,1, 2,2,2,2,2,99,99,99,99,99,54};
//    printf("%d\n", singleNumber5(nums, 21));
//    return 0;
//}