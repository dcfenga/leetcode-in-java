package org.example.algorithm;

/*
字符的 ASCII 码值作为数组的下标，数组存储该字符所在字符串的位置。
适用于字符集比较小的情况，因为我们会直接开辟和字符集等大的数组。

时间复杂度：O（n）
空间复杂度：O（m），m 代表字符集的大小。这次不论原字符串多小，都会利用这么大的空间。
 */
public class LongestSubstringWithoutRepeatingCharacters4 {
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
