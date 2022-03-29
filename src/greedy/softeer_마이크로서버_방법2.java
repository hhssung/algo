package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방식 1

public class softeer_마이크로서버_방법2 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N;
		int[] arr;
		int tmp;

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[901];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tmp = Integer.parseInt(st.nextToken());
				arr[tmp]++;
			}
			System.out.println(Solution(arr));
		}
	}

	private static int Solution(int[] arr) {
		int result = 0;
		int tmp;

		// 601 ~ 900 전부 더하기
		for (int idx = 601; idx <= 900; idx++) {
			result += arr[idx];
		}

		// 600 - 300 매칭
		tmp = Math.min(arr[600], arr[300]);
		result += arr[600];
		arr[600] -= tmp;
		arr[300] -= tmp;

		// 599 ~ 301 매칭
		int not_matched = 0;
		int big_idx = 599;
		int small_idx = 301;
		int limit_idx;
		while (big_idx >= small_idx) {
			limit_idx = 900 - big_idx;
			// 짝 못 이룬 숫자 찾기
			if (small_idx > limit_idx) {
				not_matched += arr[big_idx];
				big_idx--;
				continue;
			}
			while (small_idx <= limit_idx) {
				// System.out.println(small_idx + " " + big_idx);
				if (small_idx == big_idx) {
					if (big_idx <= 450) {
						result += (arr[big_idx] / 2);
						not_matched += (arr[big_idx] % 2);
					} else {
						not_matched += arr[big_idx];
					}
					small_idx++;
					break;
				}
				tmp = Math.min(arr[big_idx], arr[small_idx]);
				arr[big_idx] -= tmp;
				arr[small_idx] -= tmp;
				result += tmp;
				if (arr[small_idx] == 0) {
					small_idx++;
				}
				if (arr[big_idx] == 0) {
					big_idx--;
					break;
				}
			}
		}

		// 짝 못 이룬 수와 300 매칭
		tmp = Math.min(not_matched, arr[300]);
		result += not_matched;
		arr[300] -= tmp;

		// 남은 300 3개씩 매칭
		result += (arr[300] / 3);
		result += (arr[300] % 3);

		return result;
	}
}
