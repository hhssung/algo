package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


// Brute-force + KMP 문제
public class bj_1097_마법의문자열 {
	static int answer = 0;
	static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		K = Integer.parseInt(br.readLine());

		DFS(words, 0, 0, new int[N]);
		System.out.println(answer);

	}

	// KMP 적용
	private static void DFS(String[] words, int visited, int cnt, int[] order) {
		if (cnt == words.length) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < words.length; i++) {
				sb.append(words[order[i]]);
			}
			String concated_str = sb.toString();
			String double_concated_str = sb.append(concated_str).toString();
			int[] pi = new int[concated_str.length() + 1];
			makePi(pi, concated_str);
			int strcnt = KMP(pi, double_concated_str, concated_str);

			// answer++;
			// System.out.println(strcnt);
			if (strcnt == K) {
				answer++;
			}
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if ((visited & (1 << i)) == 0) {
				order[cnt] = i;
				DFS(words, visited | (1 << i), cnt + 1, order);
			}
		}
	}

	private static void makePi(int[] pi, String P) {
		int j = 0; // 접두사
		// i : i 위치까지의 접미사. pi[0] = 0
		for (int i = 1; i < P.length(); i++) {
			while (P.charAt(i) != P.charAt(j)) {
				if (j == 0) {
					break;
				}
				j = pi[j - 1];
			}
			if (P.charAt(i) == P.charAt(j)) {
				j++;
			}

			pi[i] = j;
		}
	}

	// 겹치는 개수 반환하기
	private static int KMP(int[] pi, String T, String P) {
		int result = 0;
		int j = 0;
		for (int i = 0; i < T.length(); i++) {
			while (T.charAt(i) != P.charAt(j)) {
				if (j == 0) {
					break;
				}
				j = pi[j - 1];
			}
			if (T.charAt(i) == P.charAt(j)) {
				j++;
			}
			// 일치한 문자열 찾았을 때
			if (j == P.length()) {
				result++;
				j = pi[j - 1];
			}
		}

		return result - 1;
	}
}
