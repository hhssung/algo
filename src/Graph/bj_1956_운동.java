package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1956_운동 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int a, b, c;
		final int INF = 99999999;
		int[][] graph = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				graph[i][j] = INF;
			}
		}
		for (int i = 1; i <= V; i++) {
			graph[i][i] = 0;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
		}

		// 모든 정점 - 모든 정점까지의 거리 구하기
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// Cycle - (i -> j) + (j -> i)가 최소인 값을 구하면 됨.

		int answer = INF << 1;
		for (int i = 1; i <= V; i++) {
			for (int j = i + 1; j <= V; j++) {
				answer = Math.min(answer, graph[i][j]+graph[j][i]);
			}
		}

		if(answer > INF) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}

	private static void printAll(int[][] graph) {
		for (int[] y : graph) {
			for (int x : y) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
