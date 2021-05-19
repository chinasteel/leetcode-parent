package com.steel.leetcode.interview.string;

import java.util.*;

/**
 * 单词拆分
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa503c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/5/19 14:07
 */
public class WordSplit {
	private final List<List<String>> listList = new ArrayList<>();
	private final List<String> ans = new ArrayList<>();

	public static void main(String[] args) {
		WordSplit wordSplit = new WordSplit();
		boolean result = wordSplit.wordBreak("leetcode", Arrays.asList("leet", "code"));
		System.out.println(result);

		wordSplit = new WordSplit();
		result = wordSplit.wordBreak("applepenapple", Arrays.asList("apple", "pen"));
		System.out.println(result);

		wordSplit = new WordSplit();
		result = wordSplit.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
		System.out.println(result);

		wordSplit = new WordSplit();
		result = wordSplit.wordBreak("bccdbacdbdacddabbaaaadababadad", Arrays.asList("cbc","bcda","adb","ddca","bad",
			"bbb","dad","dac","ba","aa","bd","abab","bb","dbda","cb","caccc","d","dd","aadb","cc","b","bcc","bcd","cd",
			"cbca","bbd","ddd","dabb","ab","acd","a","bbcc","cdcbd","cada","dbca","ac","abacd","cba","cdb","dbac",
			"aada","cdcda","cdc","dbc","dbcb","bdb","ddbdd","cadaa","ddbc","babb"));
		System.out.println(result);
	}

	/**
	 * 当s过长（bccdbacdbdacddabbaaaadababadad）的时候，占用内存过高，无法提交通过
	 *
	 * @param s no desc
	 * @param index no desc
	 * @author steel
	 * datetime 2021/5/19 14:58
	 */
	private void dfs(String s, int index) {
		if (index == s.length()) {
			listList.add(new ArrayList<>(ans));
			return;
		}
		for (int i = index; i < s.length(); i++) {
			String subStr = s.substring(index, i + 1);
			ans.add(subStr);
			dfs(s, i + 1);
			ans.remove(ans.size() - 1);
		}
	}

	/**
	 * 当s过长（bccdbacdbdacddabbaaaadababadad）的时候，时间不通过
	 * 灵感方法：
	 * [[b]]
	 * [[a, b], [ab]]
	 * [[a, a, b], [a, ab], [aa, b], [aab]]
	 * [[a, a, a, b], [a, a, ab], [a, aa, b], [a, aab], [aa, a, b], [aa, ab], [aaa, b], [aaab]]
	 *
	 * @param str no desc
	 * @return java.util.List<java.util.List<java.lang.String>>
	 * @author steel
	 * datetime 2021/5/19 17:29
	 */
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

		return lastResult;
	}

	public boolean wordBreak(String s, List<String> wordDict) {
		List<List<String>> listList = dynamic(s);
		Set<String> wordDictSet = new HashSet<>(wordDict);

		boolean flag = false;
		for (List<String> stringList : listList) {
			boolean subFlag = true;
			for (String string : stringList) {
				if (!wordDictSet.contains(string)) {
					subFlag = false;
					break;
				}
			}
			flag = subFlag;
			if (flag) {
				break;
			}
		}

		return flag;
	}

}
