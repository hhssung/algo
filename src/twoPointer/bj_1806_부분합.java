package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1806_부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		if (S == 0) {
			System.out.println(0);
			return;
		}

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = Integer.MAX_VALUE;
		int start = 0;
		int sum = 0;

		for (int end = 0; end < N; end++) {
			sum += arr[end];

			// S 초과
			if (sum >= S) {
				// 앞부분 최대한 제거 시도
				while (sum - arr[start] >= S) {
					sum -= arr[start];
					start++;
				}
				if (answer > end - start + 1) {
					answer = end - start + 1;
				}
			}
		}

		if(answer == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
