package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 벨만-포드

public class bj_11657_타임머신 {
	static class Node {
		int node;
		int distance;

		public Node(int node, int distance) {
			super();
			this.node = node;
			this.distance = distance;
		}
	}

	static class graph {
		int N;
		ArrayList<ArrayList<Node>> myGraph;
		boolean[][] exists;

		public graph(int N) {
			this.N = N;
			myGraph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				myGraph.add(new ArrayList<Node>());
			}
			exists = new boolean[N + 1][N + 1];
		}

		public void insert(int A, int B, int C) {
			if (exists[A][B]) {
				for (int i = 0; i < myGraph.get(A).size(); i++) {
					if (myGraph.get(A).get(i).node == B) {
						if (myGraph.get(A).get(i).distance > C) {
							myGraph.get(A).get(i).distance = C;
						}
						break;
					}
				}
			} else {
				exists[A][B] = true;
				myGraph.get(A).add(new Node(B, C));
			}
		}

		public long[] Solution() {
			final long INF = Long.MAX_VALUE;
			// answer는 Long 배열로. 극단적인 음수/양수값들만 들어올 경우 맨 나중 cycle 판별에서 under/overflow가 발생할 수 있음
			long[] answer = new long[N + 1];
			for (int i = 0; i <= N; i++) {
				answer[i] = INF;
			}
			answer[1] = 0l;

			// 모든 정점 개수만큼 반복. O(VE)
			for (int cnt = 1; cnt < N; cnt++) {
				// E번 반복 start
				for (int i = 1; i <= N; i++) { // 시작은 무조건 1번 정점부터
					for (Node n : myGraph.get(i)) {
						if (answer[i] != INF) {
							answer[n.node] = Math.min(answer[n.node], answer[i] + (long) n.distance);
						}
					}
				}
				// E번 반복 end
			}

			// 음수 사이클 체크
			for (int i = 1; i <= N; i++) {
				for (Node n : myGraph.get(i)) {
					if (answer[i] != INF && answer[n.node] > answer[i] + (long) n.distance) {
						return null;
					}
				}
			}

			return answer;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph graph = new graph(N);

		int A, B, C;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			graph.insert(A, B, C);
		}

		long[] answer = graph.Solution();

		if (answer == null) {
			System.out.println(-1);
			return;
		}

		final long INF = Long.MAX_VALUE;
		StringBuffer sb = new StringBuffer();
		for (int i = 2; i <= N; i++) {
			if (answer[i] >= INF) {
				sb.append(-1);
			} else {
				sb.append(answer[i]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

		// printAll(graph);
	}

	private static void printAll(int[][] arr) {
		System.out.println();
		for (int[] y : arr) {
			for (int x : y) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
