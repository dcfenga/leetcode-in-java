package org.example.algorithm;

import java.util.HashMap;
import java.util.Map;

/*
无重复字符的最长子串

问题抽象：给定一个任意长度的字符串 s，要求找到其中不含重复字符的连续子串，并返回该子串的最大长度。

核心需求如下：
1. 子串连续性：目标子串必须是原字符串中连续的一段字符序列。
2. 字符唯一性：子串内所有字符必须互不相同，即任意字符仅允许出现一次。
3. 长度最大化：需遍历所有可能的满足条件的子串，找出其中长度最长的值作为结果。
4. 动态边界：当向右扩展子串遇到重复字符时，需动态调整子串起始位置以维持唯一性约束。
5. 高效性：需在线性时间复杂度内完成（通常要求 O(n)）。

输入：字符串 s (可能为空)
输出：最长无重复字符子串的长度（整数）

示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

算法3：

解题思路：滑动窗口 + 哈希表
使用滑动窗口维护当前无重复字符的子串，通过哈希表记录字符最近出现的位置。
时间复杂度：O（n）
空间复杂度：O（min（m，n)）
 */
public class LengthOfLongestSubstring3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    private static int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        // 滑动窗口起始位置指针
        int start = 0;
        int maxLength = 0;

        // 存储字符及其最后出现的位置
        Map<Character, Integer> map = new HashMap<>();

        // 参数end滑动窗口结束位置指针，刚开始与起始位置指针指向相同
        for (int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);

            // 如果字符已存在且在当前窗口内
            if(map.containsKey(c) && map.get(c) >= start) {
                // 移动起始位置指针到重复字符的下一位
                start = map.get(c) + 1;
            }
            // 更新字符位置，键：字符，值：相同字符只存储该字符最近一次出现的位置
            map.put(c, end);

            // 更新最大长度
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return  maxLength;
    }
}
