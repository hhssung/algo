package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1033_칵테일_수정 {
	static class node {
		int first;
		int second;

		public node(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}

	static class myTree {
		int[] parent;
		node[][] ratio;

		public myTree(int N) {
			parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = -1;
			}
			ratio = new node[N][N];
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
			parent[b] = a;
			ratio[a][b] = new node(p, q);
			ratio[b][a] = new node(q, p);
		}

		public int[] getAnswer(int N) {
			int[] result = new int[N];

			int root = 0;
			for (int i = 0; i < N; i++) {
				if (parent[i] == -1) {
					root = i;
					break;
				}
			}

			// BFS
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[N];
			q.add(root);
			visited[root] = true;

			boolean isFirst = true;
			while (!q.isEmpty()) {
				int prnt = q.poll();

				for (int i = 0; i < N; i++) {
					if (!visited[i] && ratio[prnt][i] != null) {
						visited[i] = true;
						if (isFirst) {
							isFirst = false;
							result[prnt] = ratio[prnt][i].first;
							result[i] = ratio[prnt][i].second;
						} else {
							result[i] = result[prnt] * ratio[prnt][i].second;
							for (int j = 0; j < N; j++) {
								if (j == i || result[j] == 0) {
									continue;
								}
								result[j] *= ratio[prnt][i].first;
							}
						}
						q.add(i);
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
