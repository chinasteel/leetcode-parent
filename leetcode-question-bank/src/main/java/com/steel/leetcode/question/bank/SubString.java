package com.steel.leetcode.question.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 求一个字符串的子串
 *
 * @author steel
 * datetime 2021/4/27 15:00
 */
public class SubString {
	private final List<List<String>> lists = new ArrayList<>();
	private final List<String> ans = new ArrayList<>();

	public void dfs(String s, int index) {
		System.out.println("第" + index + "次递归开始");
		if (index == s.length()) {
			lists.add(new ArrayList<>(ans));
			return;
		}
		for (int i = index; i < s.length(); i++) {
			String subStr = s.substring(index, i + 1);
			ans.add(subStr);
			System.out.println(subStr);
			dfs(s, i + 1);
			System.out.println("第" + index + "次递归结束 ans = " + ans);
			ans.remove(ans.size() - 1);
		}
	}

	public static void main(String[] args) {
		SubString subString = new SubString();
		subString.dfs("aab", 0);
		System.out.println(subString.lists);
	}

}
