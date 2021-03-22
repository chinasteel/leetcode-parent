package com.steel.leetcode.question.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		int[][] init = new int[n][n];
		List<List<String>> nQueens = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				loopJudgeQueens(nQueens, init, i, j, 0, n);
				init = new int[n][n];
			}
		}
		return nQueens;
	}

	public void loopJudgeQueens(List<List<String>> nQueens, int[][] init, int currentX, int currentY, int count, int n) {
		if (count >= n) {
			List<String> lineList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				StringBuilder stringBuilder = new StringBuilder();
				for (int j = 0; j < n; j++) {
					stringBuilder.append(init[i][j] >= 1 ? "Q" : ".");
				}
				lineList.add(stringBuilder.toString());
			}
			nQueens.add(lineList);
			return;
		}
		if (goLevel(init, currentX, currentY, n) && goVertical(init, currentX, currentY, n)
			&& goRightObliqueLine(init, currentX, currentY, n)
			&& goLeftObliqueLine(init, currentX, currentY, n)) {
			count++;
			init[currentX][currentY] = 1;
//			System.out.println("temp hit term:" + count + "init:" + JSON.toJSONString(init));
		}
		if (currentY < n - 1) {
			currentY++;
		} else {
			currentY = 0;
			currentX++;
		}
		if (currentX >= n) {
			return;
		}
		loopJudgeQueens(nQueens, init, currentX, currentY, count, n);
	}

	public boolean goLevel(int[][] init, int currentX, int currentY, int n) {
		for (int i = 0; i < n; i++) {
			if (i != currentY && init[currentX][i] >= 1) {
				return false;
			}
		}
		return true;
	}

	public boolean goVertical(int[][] init, int currentX, int currentY, int n) {
		for (int i = 0; i < n; i++) {
			if (i != currentX && init[i][currentY] >= 1) {
				return false;
			}
		}
		return true;
	}

	public boolean goRightObliqueLine(int[][] init, int currentX, int currentY, int n) {
		int currentXTemp = currentX;
		int currentYTemp = currentY;
		if (currentX == 0 || currentY == 0) {
			while (currentXTemp < n - 1 && currentYTemp < n - 1) {
				currentXTemp++;
				currentYTemp++;
				if (init[currentXTemp][currentYTemp] >= 1) {
					return false;
				}
			}
			return true;
		}
		while (currentXTemp > 0 && currentYTemp > 0) {
			currentXTemp--;
			currentYTemp--;

			if (init[currentXTemp][currentYTemp] >= 1) {
				return false;
			}
		}
		while (currentXTemp < n - 1 && currentYTemp < n - 1) {
			currentXTemp++;
			currentYTemp++;
			if (init[currentXTemp][currentYTemp] >= 1) {
				return false;
			}
		}
		return true;
	}

	public boolean goLeftObliqueLine(int[][] init, int currentX, int currentY, int n) {
		int currentXTemp = currentX;
		int currentYTemp = currentY;
		if (currentX == 0 || currentY == n) {
			while (currentXTemp < n - 1 && currentYTemp > 0) {
				currentXTemp++;
				currentYTemp--;
				if (init[currentXTemp][currentYTemp] >= 1) {
					return false;
				}
			}
			return true;
		}
		while (currentXTemp < n - 1 && currentYTemp > 0) {
			currentXTemp++;
			currentYTemp--;

			if (init[currentXTemp][currentYTemp] >= 1) {
				return false;
			}
		}
		while (currentXTemp > 0 && currentYTemp < n - 1) {
			currentXTemp--;
			currentYTemp++;

			if (init[currentXTemp][currentYTemp] >= 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		NQueensSolve nQueensSolve = new NQueensSolve();
		List<List<String>> nQueens = nQueensSolve.solveNQueens(5);
		System.out.println(nQueens);
	}
}
