package org.example.algorithm;

import java.util.HashSet;
import java.util.Set;

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

算法1： 用两个循环穷举所有子串，然后再用一个函数判断该子串中有没有重复的字符。

时间复杂度：
两个循环，加上判断子串满足不满足条件的函数中的循环，O（n³）。

空间复杂度：
使用了一个 Set，判断子串中有没有重复的字符。由于 Set 中没有重复的字符，所以最长就是整个字符集; 假设字符集的大小为 m ，那么 Set 最长就是 m 。
另一方面，如果字符串的长度小于 m ，是 n 。那么 set 最长也就是 n 了。
综上，空间复杂度为 O（min（m，n））。
 */
public class LengthOfLongestSubstring1 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        // 保存当前得到满足条件的子串的最大值
        int ans = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // 之所以 j<= n，是因为我们子串是 [i,j),左闭右开
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    // 更新 ans
                    ans = Math.max(ans, j - i);
                }
            }
        }

        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        // 初始化 Hash Set
        Set<Character> set = new HashSet<>();

        // 遍历每个字符
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            // 判断字符在不在 Set 中
            if (set.contains(ch)) {
                return false;
            }

            // 不在的话将该字符添加到 Set 里边
            set.add(ch);
        }

        return true;
    }
}
