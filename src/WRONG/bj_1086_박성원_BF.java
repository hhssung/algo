package WRONG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간초과
public class bj_1086_박성원_BF {
	static class number {
		int remainder;
		int digit;

		public number(int remainder, int digit) {
			super();
			this.remainder = remainder;
			this.digit = digit;
		}
	}

	static int K; // 나누는 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		String[] nums_str = new String[N];
		for (int i = 0; i < N; i++) {
			nums_str[i] = br.readLine();
		}
		K = Integer.parseInt(br.readLine());
		number[] nums = new number[N];

		int answer_len = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = makeNumber(nums_str[i]);
			answer_len += nums_str[i].length();
		}

		// 수에 10^n을 곱한 나머지
		int[][] remainders = new int[N][answer_len];
		for (int i = 0; i < N; i++) {
			remainders[i][0] = nums[i].remainder;
			for (int j = 1; j < answer_len; j++) {
				remainders[i][j] = (remainders[i][j - 1] * 10) % K;
			}
		}

		// 일단 brute-force로 구하기 - 정답 맞음
		Solution_brute_force(new boolean[N], 0, 0, 0, remainders, nums);
		System.out.println(bf_result);

	}

	// 나머지, 자릿수 구하기
	private static number makeNumber(String s) {
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

	// ------------- BF start --------------
	static int bf_result = 0;

	private static void Solution_brute_force(boolean[] visited, int cnt, int digit, int sum, int[][] remainders,
			number[] nums) {
		if (cnt == visited.length) {
			if (sum % K == 0) {
				bf_result++;
			}
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				Solution_brute_force(visited, cnt + 1, digit + nums[i].digit, sum + remainders[i][digit], remainders,
						nums);
				visited[i] = false;
			}
		}
	}
	// ------------- BF end --------------
}
