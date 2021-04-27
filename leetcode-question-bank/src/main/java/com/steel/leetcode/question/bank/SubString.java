package com.steel.leetcode.question.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steel
 * datetime 2021/4/27 15:00
 */
public class SubString {
	private List<List<String>> lists = new ArrayList<>();

	public void dfs(String s, int index, List<String> list) {
		if (index == s.length()) {
			lists.add(list);
			list = new ArrayList<>();
		}
		for (int i = index; i < s.length(); i++) {
			String subStr = s.substring(index, i + 1);
			System.out.println(subStr);
			list.add(subStr);
			dfs(s, i + 1, list);
		}
	}

	public static void main(String[] args) {
		SubString subString = new SubString();
		subString.dfs("aab", 0, new ArrayList<>());
		System.out.println(subString.lists);
	}

}
