package com.steel.leetcode.interview.array;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmk3rv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author steel
 * datetime 2021/8/15 21:59
 */
public class MaxSubResult {

	public static int maxProduct(int[] nums) {
		int n = nums.length - 1;
		int maxResult = nums[n];
		return maxSub(nums, n, maxResult, maxResult);
	}

	// 状态方程：f(n - 1) = max(fn * f(n-1), f(n-1))
	public static int maxSub(int[] array, int n, int maxResult, int globalMaxResult) {
		if (n <= 0) {
			return globalMaxResult;
		}

		maxResult = Math.max(array[n - 1] * maxResult, array[n - 1]);
		maxResult = Math.max(array[n - 1] * array[n], maxResult);

		globalMaxResult = Math.max(globalMaxResult, maxResult);
		return maxSub(array, --n, maxResult, globalMaxResult);
	}

	public static void main(String[] args) {
		int[] targetArray = new int[]{2, 3, -2, 4};
//		targetArray = new int[]{-3, -1, -1};
		targetArray = new int[]{-2, 3, -4};
		int result = maxProduct(targetArray);
		System.out.println(result);
	}
}
