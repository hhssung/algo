package __STUDY.D_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1107_리모컨 {
	static int answer = Integer.MAX_VALUE;

	private static void DFS(int[] able, int cnt, int num, int orig, int N) {
		if (cnt == 0) {
			int tmp_answer = Math.abs(num - N)+orig;
			if(tmp_answer < answer) {
				answer = tmp_answer;
			}
			return;
		}

		for (int i = 0; i < able.length; i++) {
			DFS(able, cnt-1, num * 10 + able[i], orig, N);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int cnt = Integer.parseInt(br.readLine());
		boolean[] breakDown = new boolean[10];
		
		if(cnt != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cnt; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				breakDown[tmp] = true;
			}
		}
		int[] able = new int[10 - cnt];
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			if (!breakDown[i]) {
				able[idx] = i;
				idx++;
			}
		}

		if(N == 100) {
			System.out.println(0);
			return;
		}else {
			answer = Math.abs(N - 100);
		}
		
		for (int i = 6; i >= 1; i--) {
			DFS(able, i, 0, i, N);
		}
		
		System.out.println(answer);
	}
}
