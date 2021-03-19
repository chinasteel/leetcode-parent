package com.steel.leetcode.interview.start;

import java.util.HashMap;
import java.util.Map;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它扔下去。如果某个蛋扔下后没有摔碎，则可以将蛋捡起重复使用。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * <p>
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * <p>
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmup75/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/3/8 14:42
 */
public class EggDown {

	/**
	 * 暴力递归写法
	 * 状态转移方程dp(k,n) = 1 + min(max(dp(k, n - x), dp(k -1, x -1)))
	 *
	 * @author steel
	 * datetime 2021/3/11 11:22
	 */
	private static class LoopWay {
		public static int superEggDrop(int k, int n) {
			if (k < 0 || k > 100) {
				throw new IllegalArgumentException("1 <= k <= 100");
			}
			if (n < 0 || n > 10000) {
				throw new IllegalArgumentException("1 <= n <= 10000");
			}

			Map<String, Integer> map = new HashMap<>();
			return downLoop(k, n, map);
		}

		private static int downLoop(int k, int n, Map<String, Integer> map) {
			if (k == 1 || n <= 2) {
				return n;
			}

			int result = -1;
			String key = String.valueOf(k).concat(String.valueOf(n));
			if (map.containsKey(key)) {
				result = map.get(key);
			} else {
				// 求出从任意一层出发，得到F结果，然后取最小的
				for (int i = 1; i <= n; i++) {
					// result 首次赋值
					if (result == -1) {
						result = Math.max(downLoop(k, n - i, map), downLoop(k - 1, i - 1, map));
						continue;
					}
					// 和历史层数最小值进行比较
					result = Math.min(result, Math.max(downLoop(k, n - i, map), downLoop(k - 1, i - 1, map)));
				}
				map.put(key, result);
			}

			return result + 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(LoopWay.superEggDrop(1, 2));
		System.out.println(LoopWay.superEggDrop(2, 6));
		System.out.println(LoopWay.superEggDrop(3, 14));
		System.out.println(LoopWay.superEggDrop(3, 26));
		System.out.println(LoopWay.superEggDrop(4, 1000));
		System.out.println(LoopWay.superEggDrop(4, 2000));
		System.out.println(LoopWay.superEggDrop(4, 3000));
	}
}
