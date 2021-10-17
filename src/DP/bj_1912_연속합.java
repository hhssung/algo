package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1912_연속합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// 1개 이상 선택
		int before = Integer.parseInt(st.nextToken());
		int tmp;
		int answer = before;

		for (int i = 1; i < n; i++) {
			tmp = Integer.parseInt(st.nextToken());
			before = Math.max(before + tmp, tmp);
			if(answer < before) {
				answer = before;
			}
		}
		
		System.out.println(answer);
	}
}
