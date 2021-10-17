package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1504_특정한최단경로 {
	static class Pair implements Comparable<Pair> {
		int vertex;
		int distance;

		public Pair(int vertex, int distance) {
			super();
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(Pair o) {
			return this.distance - o.distance;
		}
	}

	static class myGraph {
		int N;
		int[][] graph;

		public myGraph(int N) {
			this.N = N;
			graph = new int[N + 1][N + 1];
		}

		// 양방향
		public void addEdge(int a, int b, int c) {
			graph[a][b] = c;
			graph[b][a] = c;
		}

		// 최단경로 길이 출력
		// 1 -> v1 -> v2 -> N or 1 -> v2 -> v1 -> N
		public int getAnswer(int v1, int v2) {
			final int INF = 9999999;

			// 1에서 모든 정점으로의 최단 거리 저장하는 배열
			int[] vertex_1 = new int[N + 1];
			// v1에서 모든 정점으로의 최단 거리 저장하는 배열
			int[] vertex_v1 = new int[N + 1];
			// v2에서 모든 정점으로의 최단 거리 저장하는 배열
			int[] vertex_v2 = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				vertex_1[i] = INF;
				vertex_v1[i] = INF;
				vertex_v2[i] = INF;
			}

			// vertex 1
			dijkstra(vertex_1, 1);
			// vertex v1
			dijkstra(vertex_v1, v1);
			// vertex v2
			dijkstra(vertex_v2, v2);

			// 1 -> v1 -> v2 -> N
			int answer1;
			answer1 = vertex_1[v1] + vertex_v1[v2] + vertex_v2[N];

			// 1 -> v2 -> v1 -> N
			int answer2;
			answer2 = vertex_1[v2] + vertex_v2[v1] + vertex_v1[N];

			if (answer1 >= INF && answer2 >= INF) {
				return -1;
			} else {
				return Math.min(answer1, answer2);
			}
		}

		private void dijkstra(int[] distance, int vertex) {
			// vertex 1
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			pq.add(new Pair(vertex, 0));
			distance[vertex] = 0;
			while (!pq.isEmpty()) {
				Pair tmp = pq.poll();
				for (int i = 1; i <= N; i++) {
					if (graph[tmp.vertex][i] == 0) { // 방문 불가능 노드
						continue;
					}
					if (distance[i] > distance[tmp.vertex] + graph[tmp.vertex][i]) {
						distance[i] = distance[tmp.vertex] + graph[tmp.vertex][i];
						pq.add(new Pair(i , distance[i]));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		myGraph graph = new myGraph(N);
		int a, b, c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.addEdge(a, b, c);
		}

		int v1, v2;
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		int answer = graph.getAnswer(v1, v2);
		System.out.println(answer);
	}
}
