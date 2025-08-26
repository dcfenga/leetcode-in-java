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

算法2：
判断一个字符在不在字符串中，我们需要可以遍历整个字符串，遍历需要的时间复杂度就是 O（n），加上最外层的 i 的循环，总体复杂度就是 O（n²）。
我们可以继续优化，判断字符在不在一个字符串，我们可以将已有的字符串存到 Hash 里，这样的时间复杂度是 O（1），
总的时间复杂度就变成了 O（n）。

时间复杂度：
在最坏的情况下，while 循环中的语句会执行 2n 次，例如 abcdefgg，开始的时候 j 一直后移直到到达第二个 g 的时候固定不变 ，
然后 i 开始一直后移直到 n ，所以总共执行了 2n 次，时间复杂度为 O（n）。

空间复杂度：
需要一个 Hash 保存子串，所以是 O（min（m，n））。
 */
public class LengthOfLongestSubstring2 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }

        return ans;
    }
}
