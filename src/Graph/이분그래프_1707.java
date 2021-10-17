package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이분그래프_1707 {
	static class Graph {
		class Node {
			int node; // 노드 번호
			int set; // 어떤 집합에 속해있는지

			public Node(int node, int set) {
				this.node = node;
				this.set = set;
			}
		}

		ArrayList<ArrayList<Integer>> graph;
		int V;
		int E;

		public Graph(int V, int E) {
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Integer>());
			}
			this.V = V;
			this.E = E;
		}

		public void add(int v1, int v2) {
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}

		public boolean BFS(int start, int[] visited) {
			Queue<Node> q = new LinkedList<>();
			q.add(new Node(start, 1));
			visited[start] = 1;
			
			int set;
			while (!q.isEmpty()) {
				Node tmp = q.poll();

				for (int nodeNum : graph.get(tmp.node)) {
					if (visited[nodeNum] == 0) {
						set = tmp.set == 1 ? 2 : 1;
						visited[nodeNum] = set;
						q.add(new Node(nodeNum, set));
					}
					// 이분그래프 조건 불일치
					else if (visited[nodeNum] == tmp.set) {
						return false;
					}
				}
			}
			return true;
		}

		public String checkBiparite() {
			// 0 : 방문 못함, 1: 집합 1, 2: 집합 2
			int[] visited = new int[V + 1];

			for (int node = 1; node <= V; node++) {
				if (visited[node] == 0) {
					// 너비 우선 탐색. 조건 만족 안할시 NO 반환
					if (!BFS(node, visited)) {
						return "NO";
					}
				}
			}

			// 모든 조건 만족
			return "YES";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			Graph graph = new Graph(V, E);

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				graph.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			System.out.println(graph.checkBiparite());
		}

	}
}
