package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class softeer_마이크로서버 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N;
		int tmp;

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tmp = Integer.parseInt(st.nextToken());
				arr[i] = tmp;
			}
			System.out.println(Solution(arr));
		}
	}

	private static int Solution(int[] arr) {
		int result = 0;
		Arrays.sort(arr);

		int start_idx = 0;
		int end_idx = arr.length - 1;

		// 601~900 더하기
		while (end_idx >= 0 && arr[end_idx] > 600) {
			end_idx--;
			result++;
		}

		// 300 개수 세기
		int cnt_300 = 0;
		while (start_idx < arr.length && arr[start_idx] == 300) {
			start_idx++;
			cnt_300++;
		}

		// 600 매칭하기
		while (end_idx >= 0 && arr[end_idx] == 600) {
			end_idx--;
			cnt_300--;
			result++;
		}

		// 301 ~ 599 매칭하기
		int cnt_no_couple = 0;
		while (start_idx < end_idx) {
			if (arr[start_idx] + arr[end_idx] <= 900) {
				start_idx++;
				end_idx--;
				result++;
			} else {
				end_idx--;
				cnt_no_couple++;
			}
		}
		if (start_idx == end_idx) {
			cnt_no_couple++;
		}

		// 남은 숫자들과 300 매칭
		result += cnt_no_couple;
		cnt_300 -= cnt_no_couple;

		// 300끼리 매칭
		if (cnt_300 > 0) {
			result += cnt_300 / 3;
			result += (cnt_300 % 3 == 0 ? 0 : 1);
		}

		return result;
	}
}
