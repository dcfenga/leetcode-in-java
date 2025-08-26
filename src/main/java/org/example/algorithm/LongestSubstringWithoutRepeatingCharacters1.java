package org.example.algorithm;

import java.util.HashSet;
import java.util.Set;

/*
算法1： 用两个循环穷举所有子串，然后再用一个函数判断该子串中有没有重复的字符。

时间复杂度：
两个循环，加上判断子串满足不满足条件的函数中的循环，O（n³）。

空间复杂度：
使用了一个 Set，判断子串中有没有重复的字符。由于 Set 中没有重复的字符，所以最长就是整个字符集; 假设字符集的大小为 m ，那么 Set 最长就是 m 。
另一方面，如果字符串的长度小于 m ，是 n 。那么 set 最长也就是 n 了。
综上，空间复杂度为 O（min（m，n））。
 */
public class LongestSubstringWithoutRepeatingCharacters1 {
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
