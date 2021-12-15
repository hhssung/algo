package WRONG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_1017_소수쌍_메모리초과 {
	static ArrayList<Integer> answer;
	static int couple;

	private static int[] Solution(int N, int[] myList, HashSet<Integer> set) {
		answer = new ArrayList<>();

		for (int i = 1; i < N; i++) {
			if (!set.contains(myList[0] + myList[i])) {
				continue;
			}
			couple = myList[i];
			boolean[] visited = new boolean[N];
			visited[0] = true;
			visited[i] = true;
			DFS(visited, myList, set, (N >> 1) - 1, 1);
		}

		int[] myAnswer = new int[answer.size()];
		for (int i = 0; i < myAnswer.length; i++) {
			myAnswer[i] = answer.get(i);
		}
		Arrays.sort(myAnswer);
		return myAnswer;
	}

	// 쌍 찾기
	private static void DFS(boolean[] visited, int[] myList, HashSet<Integer> set, int cnt, int start) {
		// 소수쌍 찾음
		if (cnt == 0) {
			answer.add(couple);
			couple = -1;
			return;
		}
		if (couple == -1) { // 더이상 탐색할 필요가 없을 경우
			return;
		}

		// 수 짝짓기
		for (int i = start; i < visited.length; i++) { // index가 가장 작은 소수쌍 못 이룬 수 찾기
			if (visited[i]) {
				continue;
			} else {
				for (int j = i + 1; j < visited.length; j++) {
					if (visited[j]) {
						continue;
					}
					if (set.contains(myList[i] + myList[j])) {
						visited[i] = true;
						visited[j] = true;
						DFS(visited, myList, set, cnt - 1, i + 1);
						visited[i] = false;
						visited[j] = false;
					}
				}
				break;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] myList = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			myList[i] = Integer.parseInt(st.nextToken());
		}

		// 2000보다 작은 소수들 hashset에 추가하기
		HashSet<Integer> set = new HashSet<Integer>();
		addPrimaryToSet(set, 2000);

		int[] answer = Solution(N, myList, set);

		if(answer.length == 0) {
			System.out.println(-1);
		}else {
			StringBuffer sb = new StringBuffer();
			for (int x : answer) {
				sb.append(x).append(" ");
			}
			System.out.println(sb.toString());
		}
	}

	private static void addPrimaryToSet(HashSet<Integer> set, final int MAX) {
		boolean isPrime;
		for (int i = 2; i <= MAX; i++) {
			isPrime = true;
			for (int j : set) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				set.add(i);
			}
		}

	}

}
