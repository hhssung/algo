package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR_9019 {
	static class pair {
		int cal;
		char order;

		public pair() {
			this.cal = -1; // not visited
		}

		public pair(int cal, char order) {
			this.cal = cal;
			this.order = order;
		}
	}

	private static String getAnswer(int A, int B) {
		pair[] visited = new pair[10000];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = new pair();
		}

		Queue<Integer> q = new LinkedList<>();
		q.add(A);
		visited[A].cal = 0;

		int tmp;
		int q_tmp;
		while (!q.isEmpty()) {
			tmp = q.poll();

			if (tmp == B) {
				break;
			}

			q_tmp = D(tmp);
			if (visited[q_tmp].cal == -1) {
				q.add(q_tmp);
				visited[q_tmp].cal = visited[tmp].cal + 1;
				visited[q_tmp].order = 'D';
			}

			q_tmp = S(tmp);
			if (visited[q_tmp].cal == -1) {
				q.add(q_tmp);
				visited[q_tmp].cal = visited[tmp].cal + 1;
				visited[q_tmp].order = 'S';
			}

			q_tmp = L(tmp);
			if (visited[q_tmp].cal == -1) {
				q.add(q_tmp);
				visited[q_tmp].cal = visited[tmp].cal + 1;
				visited[q_tmp].order = 'L';
			}

			q_tmp = R(tmp);
			if (visited[q_tmp].cal == -1) {
				q.add(q_tmp);
				visited[q_tmp].cal = visited[tmp].cal + 1;
				visited[q_tmp].order = 'R';
			}

		}

		StringBuffer sb = new StringBuffer();
		// 거꾸로 탐색
		// 이거 말고 parent 배열을 하나 더 만들어서 저장하는 게 빠를듯...
		tmp = B;
		int cal;
		char order;
		while (tmp != A) {
			cal = visited[tmp].cal;
			order = visited[tmp].order;
			sb.append(order);
			switch (order) {
			case 'D':
				if (visited[tmp / 2].cal == (cal - 1)) {
					tmp = tmp / 2;
				} else {
					tmp = (tmp + 10000) / 2;
				}
				break;
			case 'S':
				if (tmp + 1 > 9999) {
					tmp = 0;
				} else {
					tmp++;
				}
				break;
			case 'L':
				tmp = R(tmp);
				break;
			case 'R':
				tmp = L(tmp);
				break;
			}
		}

		return sb.reverse().toString();
	}

	private static int D(int num) {
		num = num * 2;
		num = num % 10000;
		return num;
	}

	private static int S(int num) {
		num--;
		if (num == -1) {
			num = 9999;
		}
		return num;
	}

	private static int L(int num) {
		int tmp = num / 1000;
		num = num * 10;
		num = num % 10000;
		num += tmp;
		return num;
	}

	private static int R(int num) {
		int tmp = num % 10;
		num = num / 10;
		num += (tmp * 1000);
		return num;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		int A, B;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			System.out.println(getAnswer(A, B));
		}
	}
}
