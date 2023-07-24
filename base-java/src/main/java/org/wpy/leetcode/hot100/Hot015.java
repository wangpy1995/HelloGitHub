package org.wpy.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hot015 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //最小的数已经大于0,直接退出循环
            if (nums[i] > 0) {
                break;
            }
            //防止重复添加
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = i + 1;
            int j = nums.length - 1;
            while (k < j) {
                int sum = nums[i] + nums[k] + nums[j];
                if (sum == 0) {
                    List<Integer> tuple = new ArrayList<>();
                    tuple.add(nums[i]);
                    tuple.add(nums[k]);
                    tuple.add(nums[j]);
                    result.add(tuple);
                    while (k < j && nums[k] == nums[k + 1]) {
                        k++;
                    }
                    while (k < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    k++;
                    j--;
                } else if (sum < 0) {
                    k++;
                } else {
                    j--;
                }
            }
        }
        return result;
    }
}
