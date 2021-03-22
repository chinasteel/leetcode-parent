package com.steel.leetcode.interview.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 提示：
 *
 *     1 <= s.length <= 16
 *     s 仅由小写英文字母组成
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xaxi62/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/3/19 17:56
 */
public class PalindromeSplit {

	public static List<List<String>> partition(String s) {
		char[] chars = s.toCharArray();
		List<String> fullChars = new ArrayList<>();
		for (char aChar : chars) {
			fullChars.add(String.valueOf(aChar));
		}
		return getAllSubList(fullChars);
	}

	public static List<List<String>> getAllSubList(List<String> chars) {
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i < chars.size(); i++) {
			List<String> subList = new ArrayList<>();

		}

		return list;
	}

	public static void main(String[] args) {
		List<List<String>> list = PalindromeSplit.partition("");
	}
}
