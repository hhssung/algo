package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1086_박성원_DP {
	static class number {
		int remainder;
		int digit;

		public number(int remainder, int digit) {
			super();
			this.remainder = remainder;
			this.digit = digit;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		String[] nums_str = new String[N];
		for (int i = 0; i < N; i++) {
			nums_str[i] = br.readLine();
		}
		int K = Integer.parseInt(br.readLine());
		number[] nums = new number[N];

		int answer_len = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = makeNumber(nums_str[i], K);
			answer_len += nums_str[i].length();
		}

		// 10^n을 K로 나눈 나머지
		int[] power_of_10 = new int[51];
		int tmp = 1;
		for (int i = 0; i < power_of_10.length; i++) {
			tmp = tmp % K;
			power_of_10[i] = tmp;
			tmp *= 10;
		}

		// 10^n을 소수 K로 나눴을 경우 최악의 경우 K만큼의 규칙 발생. ex) 1/17
		// 0~최대 65535 모든 경우의 수, K로 나눈 나머지의 개수
		long[][] DP = new long[1 << N][K];
		DP[0][0] = 1; // init. 아무것도 없을 때 나머지는 0
		int next_remainder;
		for (int flag = 0; flag < (1 << N); flag++) {
			for (int i = 0; i < N; i++) { // 다음 방문하지 않은 flag 찾기
				if ((flag & (1 << i)) != 0) {
					continue;
				}
				int next_bit = flag | (1 << i);

				for (int j = 0; j < K; j++) {
					next_remainder = (((j * power_of_10[nums[i].digit]) % K) + nums[i].remainder) % K;
					DP[next_bit][next_remainder] += DP[flag][j];
				}
			}
		}

//		for(long[] y : DP) {
//			for(long x : y) {
//				System.out.print(x+" ");
//			}
//			System.out.println();
//		}

		long left = DP[(1 << N) - 1][0];
		if (left == 0l) {
			System.out.println("0/1");
			return;
		}
		long right = fac(N);

		long gcd = getGcd(left, right);
		System.out.println(left / gcd + "/" + right / gcd);
	}

	private static long fac(int n) {
		long result = 1;
		for (long i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	private static long getGcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return getGcd(b, a % b);
	}

	// 나머지, 자릿수 구하기
	private static number makeNumber(String s, int K) {
		number result = new number(0, 0);
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			int tmp = s.charAt(i) - '0';
			num = num * 10 + tmp;
			num = num % K;
		}
		result.remainder = num;
		result.digit = s.length();
		return result;
	}

}
