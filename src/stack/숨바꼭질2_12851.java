package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질2_12851 {
	static class pair {
		int pos;
		int time;

		public pair(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		if (N == K) {
			System.out.printf("0\n1");
			return;
		} else if (N > K) {
			System.out.printf("%d\n1", N - K);
			return;
		}

		int fastTime = Integer.MAX_VALUE;
		int cases = 0;

		boolean[] visited = new boolean[100001];
		Queue<pair> q = new LinkedList<>();
		q.add(new pair(N, 1));

		int[] positions = { 0, 1, -1 };
		int tmp;
		
		// 틀린이유) 모든 경우의 수를 탐색해야 되므로 방문 처리를 q에서 꺼낸 다음 해야 됨
		while (!q.isEmpty()) {
			pair p = q.poll();
			visited[p.pos] = true;
			// 가장 빠른 시간 초과
			if (p.time == fastTime) {
				break;
			}
			positions[0] = p.pos;
			for (int i = 0; i < 3; i++) {
				tmp = p.pos + positions[i];
				if (tmp <= 100000 && tmp >= 0 && !visited[tmp]) {
					if (tmp == K) {
						fastTime = p.time + 1;
						cases++;
						continue;
					} else {
						q.add(new pair(tmp, p.time + 1));
					}
				}
			}
		}

		System.out.printf("%d\n%d", fastTime - 1, cases);
	}
}
