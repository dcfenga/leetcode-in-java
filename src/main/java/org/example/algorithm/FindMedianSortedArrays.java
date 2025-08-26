package org.example.algorithm;

/*
寻找两个正序数组的中位数

给定两个非降序（（从小到大））排列的整数数组 nums1 和 nums2，其长度分别为 m 和 n（可能为空）。要求找到这两个数组合并后的中位数，并满足：
1. 有序合并：需基于数组已排序的特性高效计算，禁止显式合并整个数组（避免 O(m+n) 空间）。
2. 中位数定义：
   • 若合并数组长度为奇数，中位数为正中间的元素；
   • 若长度为偶数，中位数为中间两个元素的平均值（可能为小数）。
3. 复杂度约束：需设计时间复杂度 O(log(min(m,n))) 的算法（通常使用二分搜索）。
4. 边界处理：需兼容任一数组为空、所有元素为负值、或合并后数组长度为 1 的场景。

输入：两个升序整数数组 nums1、nums2
输出：合并数组中位数（浮点数或整数）

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

解题思路:
二分查找（分治算法）: 将问题转化为寻找两个有序数组中第 k 小的数（k 为中间位置），通过每次排除 k/2 个元素实现对数级时间复杂度。
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4,5};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;

        if(total %2 ==1) {
            return findKth(nums1, nums2, (total+1) /2);
        } else{
            int start = findKth(nums1, nums2, total / 2+1);
            int end = findKth(nums1, nums2, total / 2);
            return (start + end) / 2.0;
        }
    }

    private static int findKth(int[] nums1, int[] nums2, int k) {
        // 确保 nums1 是较短的数组（优化关键）
        if (nums1.length > nums2.length) {
            return findKth(nums2, nums1, k);
        }

        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;

        while(true) {
            // 边界情况处理
            if (i == m) {
                return nums2[j + k -1];
            }
            if (j == n) {
                return nums1[i + k -1];
            }
            if (k == 1) {
                return Math.min(nums1[i], nums2[j]);
            }

            // 计算检查位置（避免数组越界）
            int mid1 = Math.min(i + k / 2 - 1, m -1);
            int mid2 = Math.min(j + k / 2 - 1, n -1);

            // 比较并排除较小元素所在数组的前半部分
            if (nums1[mid1] <= nums2[mid2]) {
                // 更新剩余查找长度
                k -= (mid1 - i + 1);
                i = mid1 + 1;
            } else {
                k -= (mid2 - j + 1);
                j = mid2 + 1;
            }
        }
    }
}
