package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상근이의여행_9372 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		int N, M; // 국가 수, 비행기 종류
		int a, b;

		// 완전그래프
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
//				st = new StringTokenizer(br.readLine());
//				a = Integer.parseInt(st.nextToken());
//				b = Integer.parseInt(st.nextToken());
				br.readLine();
			}

			System.out.println(N - 1); // MST 최소 크기는 결국 N-1?
		}
	}
}
