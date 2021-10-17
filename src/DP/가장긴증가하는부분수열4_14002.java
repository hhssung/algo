package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열4_14002 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] DP = new int[N]; // 자신을 포함한 가장 큰 증가 부분수열의 개수 저장하는 배열
		int[] parent = new int[N]; // 자기의 부모 저장하는 배열. 없을 경우 -1
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer_idx = 0;
		for (int i = 0; i < N; i++) {
			DP[i] = 1;
			parent[i] = -1;

			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && DP[j] + 1 > DP[i]) {
					DP[i] = DP[j]+1;
					parent[i] = j;
				}
			}

			if (DP[answer_idx] < DP[i]) {
				answer_idx = i;
			}
		}

		System.out.println(DP[answer_idx]);
		ArrayList<Integer> ans = new ArrayList<>();
		while (answer_idx != -1) {
			ans.add(answer_idx);
			answer_idx = parent[answer_idx];
		}
		StringBuffer sb = new StringBuffer();
		for (int i = ans.size() - 1; i >= 0; i--) {
			sb.append(arr[ans.get(i)]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
