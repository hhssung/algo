package WRONG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1033_칵테일 {
	static class pair {
		int big;
		int small;

		public pair(int big, int small) {
			super();
			this.big = big;
			this.small = small;
		}
	}

	static class myTree {
		int[] parent;
		pair[] ratio;

		public myTree(int N) {
			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = -1;
			}
			ratio = new pair[N];
		}

		public void insert(int a, int b, int p, int q) {
			if (p > q) {
				insertTree(a, b, p, q);
			} else if (p < q) {
				insertTree(b, a, q, p);
			} else { // 비율이 1:1인 경우는 큰 숫자를 무조건 부모로 함
				if (a > b) {
					insertTree(a, b, p, q);
				} else {
					insertTree(b, a, q, p);
				}
			}
		}

		// a>b, p>q인 조건
		private void insertTree(int a, int b, int p, int q) {
			pair tmp = new pair(p, q);
			parent[b] = a;
			ratio[b] = tmp;
		}

		/*
		 * ? c d, ?:c = a:b일 경우 최종적으로 ac, bc, bd가 됨
		 */
		public int[] getAnswer(int N) {
			int[] result = new int[N];

			// 여러개의 root 찾기
			ArrayList<Integer> roots = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (parent[i] == -1) {
					roots.add(i);
				}
			}

			// root부터 차례대로 훑기.
			for (int root : roots) {
				Queue<Integer> q = new LinkedList<>();
				for (int i = 0; i < N; i++) {
					if (parent[i] == root) {
						q.add(i);
					}
				}

				while (!q.isEmpty()) {
					int son = q.poll();
					for (int i = 0; i < N; i++) {
						if (parent[i] == son) {
							q.add(i);
						}
					}
					int prnt = parent[son];
					if (result[prnt] == 0 && result[son] == 0) { // 처음일 경우
						result[prnt] = ratio[son].big;
						result[son] = ratio[son].small;
						continue;
					}
					if (result[son] == 0) {	//자식 초기화 x
						result[son] = result[prnt] * ratio[son].small;
						for (int i = 0; i < N; i++) {
							if (i == son || result[i] == 0) {
								continue;
							}
							result[i] *= ratio[son].big;
						}
						continue;
					}
				}
			}

			// 유클리드 호제법으로 모든 수의 최대공약수 구하기
			int r = result[0];
			for (int i = 1; i < N; i++) {
				r = gcd(r, result[i]);
			}

			if (r > 1) {
				for (int i = 0; i < N; i++) {
					result[i] /= r;
				}
			}

			return result;
		}

		private int gcd(int a, int b) {
			if (b == 0) {
				return a;
			}
			return gcd(b, a % b);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		myTree tree = new myTree(N);

		int a, b, p, q;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			tree.insert(a, b, p, q);
		}

		int[] answer = tree.getAnswer(N);
		StringBuffer sb = new StringBuffer();
		for (int x : answer) {
			sb.append(x).append(" ");
		}
		System.out.println(sb.toString());
	}
}
