package com.steel.leetcode.question.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xaxi62/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/3/19 17:56
 */
public class PalindromeSplitDemo {

	boolean[][] f;
	List<List<String>> ret = new ArrayList<>();
	List<String> ans = new ArrayList<>();
	int n;

	public List<List<String>> partition(String s) {
		n = s.length();
		f = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			Arrays.fill(f[i], true);
		}

		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j) {
				f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
			}
		}

		dfs(s, 0);
		return ret;
	}

	public void dfs(String s, int i) {
		if (i == n) {
			ret.add(new ArrayList<>(ans));
			return;
		}
		for (int j = i; j < n; ++j) {
			if (f[i][j]) {
				ans.add(s.substring(i, j + 1));
				dfs(s, j + 1);
				ans.remove(ans.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		PalindromeSplitDemo palindromeSplitDemo = new PalindromeSplitDemo();
		List<List<String>> result = palindromeSplitDemo.partition("aab");
		System.out.println(result);

	}
}
