package DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// disjoint set
// 대표자가 같아지는 경우 cycle 생성됨

public class 사이클게임_20040 {
	static class DisjointSet{
		int[] rep;
		
		public DisjointSet(int n) {
			rep = new int[n];
			for(int i=0; i<n; i++) {
				rep[i] = i;
			}
		}
		
		// true : cycle X.  false : cycle O
		public boolean union(int a, int b) {
			int a_rep = getRep(a);
			int b_rep = getRep(b);
			
			if(a_rep == b_rep) {
				return false;
			}
			
			// 큰 대표자로 전부 갱신
			if(a_rep < b_rep) {
				a_rep = b_rep;
			}
			
			update(a, a_rep);
			update(b, a_rep);
			
			return true;
		}
		
		private int getRep(int node) {
			while(node != rep[node]) {
				node = rep[node];
			}
			return node;
		}
		
		//가지치기
		private void update(int node, int nRep) {
			int before = node;
			while(node != rep[node]) {
				node = rep[node];
				rep[before] = nRep;
				before = node;
			}
			rep[node] = nRep;
			
		}
		
		public void printAll() {
			for(int i : rep) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int a, b; // 두 점
		DisjointSet dset = new DisjointSet(n);
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			//dset.printAll();
			if(!dset.union(a, b)) {
				System.out.println(i);
				return;
			}
		}
		//dset.printAll();
		
		System.out.println(0);
	}
}
