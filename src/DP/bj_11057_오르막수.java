package DP;

import java.util.Scanner;

public class bj_11057_오르막수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		final int MOD = 10007;

		int[] DP = new int[10]; // DP[n] = n, n+1, ... 9 번째 자리까지의 합
		for (int i = 0; i < 10; i++) { // 맨 처음엔 1로 초기화
			DP[i] = 1;
		}

		// N-1번 반복
		for (int i = 2; i <= N; i++) {
			for (int j = 8; j >= 0; j--) {
				DP[j] = (DP[j + 1] + DP[j]) % MOD;
			}
		}

		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += DP[i];
		}

		System.out.println(answer % MOD);
	}
}
