package org.example.algorithm;

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

算法4：
字符的 ASCII 码值作为数组的下标，数组存储该字符所在字符串的位置。
适用于字符集比较小的情况，因为我们会直接开辟和字符集等大的数组。

时间复杂度：O（n）
空间复杂度：O（m），m 代表字符集的大小。这次不论原字符串多小，都会利用这么大的空间。
 */
public class LengthOfLongestSubstring4 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];

        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;//（下标 + 1） 代表 i 要移动的下个位置
        }

        return ans;
    }
}
