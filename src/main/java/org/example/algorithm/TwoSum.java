package org.example.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/*
两数之和

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。你可以按任意顺序返回答案。

示例 1：
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

示例 2：
输入：nums = [3,2,4], target = 6
输出：[1,2]

示例 3：
输入：nums = [3,3], target = 6
输出：[0,1]

解题思路：
• 一次遍历：边遍历边检查，避免预先存储所有元素
• 延迟存储：先检查补数再存当前元素，避免自重复
• 容量预分配：设置 HashMap 初始容量为 nums.length，避免扩容开销

时间复杂度：O（n）
空间复杂度：O（n）
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 11, 3, 7, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                continue;
            } else {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
            }
            map.put(nums[i], i);
        }
        return new int[0]; // 题目保证有解，此句不会执行
    }
}
