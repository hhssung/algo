package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리의지름_1167 {
	static ArrayList<pair>[] list;

	static class pair {
		int vertex;
		int length;

		public pair(int vertex, int length) {
			super();
			this.vertex = vertex;
			this.length = length;
		}
	}

	static int answer = 0;
	static int max_root = -1;	//dfs를 한번 더 돌릴 root
	private static void DFS(int mom, int node, int size) {
		//자식이 없음
		if(list[node].size() == 1) {
			if(answer < size) {
				answer = size;
				max_root = node;
			}
			//System.out.println(size);
			return;
		}

		for(pair p : list[node]) {
			// 부모노드일 경우
			if(p.vertex == mom) {
				continue;
			}
			DFS(node, p.vertex, size+p.length);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<pair>();
		}

		int tmp_v;
		int tmp_l;
		int root = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			while (true) {
				tmp_v = Integer.parseInt(st.nextToken());
				if (tmp_v == -1) {
					break;
				}
				tmp_l = Integer.parseInt(st.nextToken());
				list[v].add(new pair(tmp_v, tmp_l));
			}

			// root 노드
			if (root == 0 && list[v].size() == 1) {
				root = v;
			}
		}

		DFS(root, list[root].get(0).vertex, list[root].get(0).length);
		DFS(max_root, list[max_root].get(0).vertex, list[max_root].get(0).length);	
		
		System.out.println(answer);
	}
}
