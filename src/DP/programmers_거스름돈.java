package DP;

public class programmers_거스름돈 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int n = 5;
		int[] money = { 1, 2, 5 };
		System.out.println(sol.solution(n, money));
	}
}

class Solution {
	public int solution(int n, int[] money) {
		final int DIV = 1000000007;
		int mlen = money.length;

		int[][] DP = new int[mlen][n + 1];

		// init
		for (int i = 0; i < mlen; i++) {
			DP[i][0] = 1;
		}
		for (int j = 0; j <= n; j += money[0]) {
			DP[0][j] = 1;
		}

		// DP
		for (int i = 1; i < mlen; i++) {
			for (int j = 1; j <= n; j++) {
				DP[i][j] = DP[i - 1][j];
				if (j >= money[i]) {
					DP[i][j] += DP[i][j - money[i]];
				}
				DP[i][j] %= DIV;
			}
		}

		return DP[mlen - 1][n];
	}
}