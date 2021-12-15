package BipartiteMatching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_1017_소수쌍 {
	static int TARGET;
	static ArrayList<Integer> answer = new ArrayList<>();

	private static int[] Solution(int N, int[] myList, HashSet<Integer> set) {
		int half = N >> 1;
		TARGET = myList[0];
		// 중복되지 않는 수. 홀수 / 짝수는 무조건 N/2개.
		int[] odd = new int[half];
		int[] even = new int[half];
		int odd_idx = 0;
		int even_idx = 0;
		int target_idx = 0;

		for (int x : myList) {
			if (x % 2 == 0 && even_idx < half) {
				even[even_idx] = x;
				if (x == TARGET) {
					target_idx = even_idx;
				}
				even_idx++;
			} else if (x % 2 == 1 && odd_idx < half) {
				odd[odd_idx] = x;
				if (x == TARGET) {
					target_idx = odd_idx;
				}
				odd_idx++;
			} else {
				return new int[0];
			}
		}

		// target 맨 앞으로 swap하기
		if (TARGET % 2 == 0) {
			even[target_idx] = even[0];
			even[0] = TARGET;
			swapAndSearch(even, odd, set);
		}

		if (TARGET % 2 == 1) {
			odd[target_idx] = odd[0];
			odd[0] = TARGET;
			swapAndSearch(odd, even, set);
		}

		int[] myAnswer = new int[answer.size()];
		for (int i = 0; i < myAnswer.length; i++) {
			myAnswer[i] = answer.get(i);
		}
		Arrays.sort(myAnswer);
		return myAnswer;
	}

	private static void swapAndSearch(int[] src, int[] des, HashSet<Integer> set) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < src.length; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < des.length; j++) {
				if (set.contains(src[i] + des[j])) {
					graph.get(i).add(j); // index 추가
				}
			}
		}

		// TARGET만 고정
		for (int target_idx : graph.get(0)) {
			int[] desMatchIdx = new int[des.length];
			for (int i = 0; i < src.length; i++) {
				desMatchIdx[i] = -1;
			}
			desMatchIdx[target_idx] = 0;
			int cnt = 0;
			// 틀린부분: 항상 새로운 boolean 배열을 넣어야 됨
			for (int i = 1; i < src.length; i++) {
				if (DFS(new boolean[src.length], graph, desMatchIdx, i, target_idx)) {
					cnt++;
				}
			}
			if (cnt == src.length - 1) {
				answer.add(des[target_idx]);
			}
		}
	}

	// 이분매칭
	private static boolean DFS(boolean[] visited, ArrayList<ArrayList<Integer>> graph, int[] desMatchIdx, int now,
			int target) {
		for (int des : graph.get(now)) {
			if (des == target) {
				continue;
			}
			
			if (visited[des]) {
				continue;
			}
			visited[des] = true;

			if (desMatchIdx[des] == -1 || DFS(visited, graph, desMatchIdx, desMatchIdx[des], target)) {
				desMatchIdx[des] = now;
				return true;
			}
		}

		return false;
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

		if (answer.length == 0) {
			System.out.println(-1);
		} else {
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
