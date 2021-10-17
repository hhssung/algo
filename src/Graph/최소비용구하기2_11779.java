package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기2_11779 {
	static class Pair implements Comparable<Pair> {
		int dest;
		int cost;

		public Pair(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	static class myGraph {
		ArrayList<ArrayList<Pair>> graph;
		int n;

		public myGraph(int n) {
			this.n = n;
			graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
		}

		// 단방향
		public void add(int src, int des, int cost) {
			graph.get(src).add(new Pair(des, cost));
		}

		// 다익스트라 구현. start -> end 최소 거리, 경로 구하기
		public void dijkstra(int start, int end) {
			final int INF = Integer.MAX_VALUE;
			PriorityQueue<Pair> pq = new PriorityQueue<>();

			int[] distance = new int[n + 1];	//최단 거리 저장해놓는 배열
			int[] before_node = new int[n + 1]; // 직전 노드 저장해놓는 배열
			for (int i = 0; i <= n; i++) {
				distance[i] = INF;
			}
			distance[start] = 0;
			pq.add(new Pair(start, 0));

			ArrayList<Pair> tmp;
			int nextNode;
			int nextDistance;
			while (!pq.isEmpty()) {
				Pair p = pq.poll();
				
				if(p.cost > distance[p.dest]) {
					continue;
				}
				// 이전 노드의 도착점이 새 노드의 시작점이 됨
				tmp = graph.get(p.dest);
				for (Pair p2 : tmp) {
					nextNode = p2.dest;
					nextDistance = p.cost + p2.cost;

					// 갱신
					if (nextDistance < distance[nextNode]) {
						distance[nextNode] = nextDistance;
						before_node[nextNode] = p.dest;
						pq.add(new Pair(nextNode, nextDistance));
					}
				}
			}

			//출력
			System.out.println(distance[end]);
			ArrayList<Integer> route = new ArrayList<Integer>();
			route.add(end);
			int idx = end;
			while(before_node[idx] != start) {
				idx = before_node[idx];
				route.add(idx);
			}
			route.add(start);
			System.out.println(route.size());
			StringBuffer sb = new StringBuffer();
			for(int i=route.size()-1; i>=0; i--) {
				sb.append(route.get(i)).append(" ");
			}
			System.out.println(sb.toString());
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 도시 개수
		int m = Integer.parseInt(br.readLine()); // 버스 개수
		myGraph graph = new myGraph(n);

		int a, b, cost;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			graph.add(a, b, cost);
		}

		int first, last;
		st = new StringTokenizer(br.readLine());
		first = Integer.parseInt(st.nextToken());
		last = Integer.parseInt(st.nextToken());
		graph.dijkstra(first, last);

	}
}
