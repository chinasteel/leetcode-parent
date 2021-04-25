package com.steel.leetcode.question.bank;

import java.util.*;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/3/21 16:15
 */
public class NQueensSolve {
	public List<List<String>> solveNQueens(int n) {
		if (n == 1) {
			return Collections.singletonList(Collections.singletonList("Q"));
		}
		int[] init = new int[n];
		Arrays.fill(init, -1);

		List<List<String>> nQueens = new ArrayList<>();
		Set<Integer> columnSet = new HashSet<>();
		Set<Integer> leftObliqueSet = new HashSet<>();
		Set<Integer> rightObliqueSet = new HashSet<>();
		loopJudgeQueens(nQueens, init, 0, n, columnSet, leftObliqueSet, rightObliqueSet);
		return nQueens;
	}

	public void loopJudgeQueens(List<List<String>> nQueens, int[] init, int currentRow, int n, Set<Integer> columnSet,
								Set<Integer> leftObliqueSet, Set<Integer> rightObliqueSet) {
		if (currentRow == n) {
			List<String> lineList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				char[] line = new char[n];
				Arrays.fill(line, '.');
				// init[i] 代表第i行的第几列是Queen
				line[init[i]] = 'Q';
				lineList.add(new String(line));
			}
			nQueens.add(lineList);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (columnSet.contains(i)) {
				continue;
			}
			int leftOblique = currentRow + i;
			if (leftObliqueSet.contains(leftOblique)) {
				continue;
			}
			int rightOblique = currentRow - i;
			if (rightObliqueSet.contains(rightOblique)) {
				continue;
			}
			init[currentRow] = i;
			columnSet.add(i);
			leftObliqueSet.add(leftOblique);
			rightObliqueSet.add(rightOblique);
			System.out.println("current row has make a queen:" + currentRow);
			loopJudgeQueens(nQueens, init, currentRow + 1, n, columnSet, leftObliqueSet, rightObliqueSet);

			// 这里代表currentRow的n列全部循环完，没有符合条件的，需要将上一层递归初始化，重新开始
			// 或者第一层递归的第i列开头的全部找完，继续从下一个位置开始继续
			System.out.println("current row start reset queen:" + currentRow);
			init[currentRow] = -1;
			columnSet.remove(i);
			leftObliqueSet.remove(leftOblique);
			rightObliqueSet.remove(rightOblique);
		}

	}

	public static void main(String[] args) {
		NQueensSolve nQueensSolve = new NQueensSolve();
		List<List<String>> nQueens = nQueensSolve.solveNQueens(4);
		System.out.println(nQueens);
	}
}
