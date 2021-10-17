package DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_1976 {
	static class DisjointSet{
		int[] rep;
		
		public DisjointSet(int N) {
			rep = new int[N+1];
			//대표자 자기 자신으로 초기화
			for(int i=1; i<=N; i++) {
				rep[i] = i;
			}
		}
		
		public void union(int a, int b) {
			if(rep[b]>rep[a]) {
				rep[a] = rep[b];
			}else {
				rep[b] = rep[a];
			}
		}
		
		public int getRep(int node) {
			while(rep[node] != node) {
				node = rep[node];
			}
			return node;
		}
		
		public void printAll() {
			for(int i : rep) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 도시 수
		int M = Integer.parseInt(br.readLine()); // 여행계획한 도시 수
		DisjointSet dset = new DisjointSet(N);
		
		// 양방향 그래프
		//boolean[][] graph = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				//union 연산 하기
				if(st.nextToken().equals("1")) {
//					if(i>j) {
//						continue;
//					}
					dset.union(i,j);	// 무조건 높은 노드 우선
					//dset.printAll();
				}
				//graph[i][j] = st.nextToken().equals("1") ? true : false;
			}
		}

		int src, des;
		st = new StringTokenizer(br.readLine());
		// 시작점 init
		src = Integer.parseInt(st.nextToken());
		for (int i = 1; i < M; i++) {
			des = Integer.parseInt(st.nextToken());
			if(dset.getRep(src) != dset.getRep(des)) {
				System.out.println("NO");
				return;
			}
			src = des;
		}
		System.out.println("YES");
	}
}
