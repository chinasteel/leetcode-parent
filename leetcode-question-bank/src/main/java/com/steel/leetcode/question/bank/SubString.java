package com.steel.leetcode.question.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 求一个字符串的子串
 *
 * @author steel
 * datetime 2021/4/27 15:00
 */
public class SubString {
	private final List<List<String>> lists = new ArrayList<>();
	private final List<String> ans = new ArrayList<>();

	/**
	 * 递归求子串
	 *
	 * @param s 长度超过16，性能下降严重
	 * @param index no desc
	 * @author steel
	 * datetime 2021/5/19 14:57
	 */
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

	public List<List<String>> dynamic(String str) {
		char[] chars = str.toCharArray();
		List<List<String>> lastResult = new ArrayList<>();
		lastResult.add(Collections.singletonList(String.valueOf(chars[chars.length - 1])));
		if (chars.length == 1) {
			return lastResult;
		}
		List<List<String>> currentResult = null;
		for (int i = chars.length - 2; i >= 0; i--) {
			currentResult = new ArrayList<>();
			for (List<String> stringList : lastResult) {
				List<String> resultList = new ArrayList<>();
				resultList.add(String.valueOf(chars[i]));
				resultList.addAll(stringList);
				currentResult.add(resultList);

				resultList = new ArrayList<>(stringList);
				resultList.set(0, chars[i] + resultList.get(0));
				currentResult.add(resultList);
			}
			lastResult = currentResult;
		}

		return currentResult;
	}

	/**
	 * 对数器
	 *
	 * @param lists1 no desc
	 * @param lists2 no desc
	 * @return boolean
	 * @author steel
	 * datetime 2021/5/19 17:06
	 */
	public boolean listEquals(List<List<String>> lists1, List<List<String>> lists2) {
		AtomicBoolean flag = new AtomicBoolean(true);
		lists1.forEach(list -> {
			if (!lists2.contains(list)) {
				flag.set(false);
			}
		});

		return flag.get();
	}

	public static void main(String[] args) {
		SubString subString = new SubString();
		subString.dfs("bccdbacdbdacdda", 0);
		System.out.println(subString.lists);

		List<List<String>> resultList = subString.dynamic("bccdbacdbdacdda");
		System.out.println(resultList);

		System.out.println(subString.listEquals(subString.lists, resultList));
		/*subString.dfs("bccdbacdbdacddab", 0);
		System.out.println(subString.lists);*/
	}

}
