package DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현_1717 {
	static class DisjointSet {
		int[] dSet; // 부모 노드를 담고 있는 배열

		public DisjointSet(int n) {
			dSet = new int[n + 1];
			// 모든 노드 초기화
			for (int i = 0; i <= n; i++) {
				dSet[i] = i;
			}
		}

		// 특정 노드의 맨 꼭대기 대표자 노드 번호 반환 - 연산 1에 사용
		public int getRepNum(int num) {
			while (dSet[num] != num) {
				num = dSet[num];
			}

			return num;
		}

		// 압축 연산
		private void compression(int src, int rep) {
			int before;
			do {
				before = src;
				src = dSet[src];
				dSet[before] = rep;
			} while (dSet[src] != src);
			dSet[src] = rep;		// 이부분에서 틀림. 마지막까지 부모 노드 업데이트하기!
		}

		// a,b 노드 합치기 - 연산 0에 사용
		public void union(int a, int b) {
			int a_rep = getRepNum(a);

			// 높이 압축하기
			compression(a, a_rep);
			compression(b, a_rep);
		}

		public void printAll() {
			for (int i : dSet) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		DisjointSet dset = new DisjointSet(n);
		int calc;
		int a;
		int b;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			calc = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 포함되어 있는지 확인하는 연산
			if (calc == 1) {
				if (dset.getRepNum(a) == dset.getRepNum(b)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
			// 합집합 연산
			else {
				dset.union(a, b);
				//dset.printAll();
			}
		}
		System.out.println(sb.toString());
	}
}
