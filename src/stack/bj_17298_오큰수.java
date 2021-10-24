package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// stack 사용하지 않음
public class bj_17298_오큰수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] idx = new int[N]; // 오큰수의 idx를 저장하는 배열. 없으면 -1 반환
		int[] answer = new int[N]; // 오큰수를 저장하는 배열.
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		answer[N - 1] = -1;
		idx[N - 1] = -1; // end
		int tmp_idx;
		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] < arr[i + 1]) {
				answer[i] = arr[i + 1];
				idx[i] = i + 1;
				continue;
			}
			tmp_idx = i + 1;
			while (arr[tmp_idx] <= arr[i]) {
				tmp_idx = idx[tmp_idx];
				if (tmp_idx == -1) {
					break;
				}
			}
			idx[i] = tmp_idx;
			if (tmp_idx != -1) {
				answer[i] = arr[tmp_idx];
			} else {
				answer[i] = -1;
			}
		}

		// 정답 출력
		StringBuffer sb = new StringBuffer();
		for (int i : answer) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}
}