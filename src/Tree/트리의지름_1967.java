package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리의지름_1967 {
	static int n; // 노드개수
	static ArrayList<node>[] list;

	static class node {
		int vertex; // 자식 노드
		int weight; // 가중치

		public node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	static int answer = Integer.MIN_VALUE;
	static int tmp_node = -1;
	private static void DFS(int mom, int child, int sum) {
		if(list[child].size() == 1) {
			if(answer < sum) {
				answer = sum;
				tmp_node = child;	// DFS를 한번 더 돌릴 노드 갱신
			}
			return;
		}
		
		for(node n : list[child]) {
			if(n.vertex == mom) {
				continue;
			}
			DFS(child, n.vertex, sum+n.weight);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		//노드 개수 1인 경우 0 반환
		if(n==1) {
			System.out.println(0);
			return;
		}
		
		list = new ArrayList[n + 1]; // 1 ~ n 노드
		
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<node>();
		}

		int mom;
		int child;
		int weight;
		for (int i = 1; i <= n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			mom = Integer.parseInt(st.nextToken());
			child = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			list[mom].add(new node(child, weight));
			list[child].add(new node(mom, weight));	//그냥 양방향으로
		}
		
		// 한 vertex만 이어져 있는 노드 찾기
		int tmp = 0;
		for(int i=1; i<=n; i++) {
			if(list[i].size() == 1) {
				tmp = i;
				break;
			}
		}
		
		//DFS
		DFS(tmp, list[tmp].get(0).vertex, list[tmp].get(0).weight);
		DFS(tmp_node, list[tmp_node].get(0).vertex, list[tmp_node].get(0).weight);
		System.out.println(answer);
	}
}
