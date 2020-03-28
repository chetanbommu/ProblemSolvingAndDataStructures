package dynamicProgramming;

// Resource :: https://www.youtube.com/watch?v=eREiwuvzaUM (Gayle McDowell Video)
/* Count no of ways to reach 100th step by taking either 1 or 2 or 3 steps at a time */
public class RecursiveStairCase {
	
	public static void main(String[] args) {
		int steps = 100; 
		//System.out.println(countPaths_RecursiveApproach(steps));
		System.out.println(countPathsDP_MemoizationApproach(steps));
		System.out.println(countPathsDP_TabulationApproach(steps));
		System.out.println(countPathsDP_TabulationApproach_Simplified(steps));
	}
	
	/* Approaches ::
	 * 1. Recursive Approach
	 * 2. DP with Memoization
	 * 3. DP with Tabulation
	 * */
	
	/* Approach - 1 :: Recursive Approach
	 * Steps: The step that we need to reach taking 1/2/3 steps at a time.
	 * 
	 * Note:: Recursion approach takes more time than DP Appraoch because we calculate 
	 * for same steps again and again.
	 * To experience, change steps value to 100.
	 * */
	public static int countPaths_RecursiveApproach(int steps) {
		if(steps < 0) {
			return 0;
		} else if(steps <= 1) {
			return 1;
		}
		return countPaths_RecursiveApproach(steps - 1) +
				countPaths_RecursiveApproach(steps - 2) +
				countPaths_RecursiveApproach(steps - 3);
	}
	
	/* Approach - 2 :: DP with Memoization */
	public static long countPathsDP_MemoizationApproach(int steps) {
		return countPathsDP_MemoizationApproach(steps, new long[steps + 1]);
	}
	
	private static long countPathsDP_MemoizationApproach(int steps, long[] memo) {
		if(steps < 0) {
			return 0;
		} else if(steps <= 1) {
			return 1;
		}
		if(memo[steps] == 0) {
			memo[steps] = countPathsDP_MemoizationApproach(steps-1, memo) +
					countPathsDP_MemoizationApproach(steps-2, memo) +
					countPathsDP_MemoizationApproach(steps-3, memo);
		}
		return memo[steps];
	}
	
	/* Approach - 3 :: DP with Tabulation/Iterative */
	public static long countPathsDP_TabulationApproach(int steps) {
		if(steps < 0) {
			return 0;
		} else if(steps <= 1) {
			return 1;
		}
		long[] paths = new long[steps + 1];
		paths[0] = 1;
		paths[1] = 1;
		paths[2] = 2;
		for(int i = 3; i <= steps; i++) {
			paths[i] = paths[i - 1] + paths[i - 2] + paths[i - 3];
		}
		return paths[steps];
	}
	
	/* At any given time, we just need three path values to calculate total ways.
	 * So, Instead of using an array of size steps + 1, we can just use 3 variables or 
	 * array of size 3.
	 * */
	public static long countPathsDP_TabulationApproach_Simplified(long steps) {
		if(steps < 0) {
			return 0;
		} else if(steps <= 1) {
			return 1;
		}
		long[] paths = {1,1,2};
		for(int i = 3; i <= steps; i++) {
			long countForCurrentI = paths[0] + paths[1] + paths[2];
			paths[0] = paths[1];
			paths[1] = paths[2];
			paths[2] = countForCurrentI;
		}
		return paths[2];
	}
}
