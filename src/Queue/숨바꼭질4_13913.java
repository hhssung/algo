package Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질4_13913 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N, K;
		N = sc.nextInt();
		K = sc.nextInt();

		if (N >= K) {
			StringBuffer sb = new StringBuffer();
			sb.append(N - K).append("\n");
			for (int i = N; i >= K; i--) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}

		int[] visited = new int[100001];
		// 방문 x : 0
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		visited[N] = 1;

		while (true) {
			int p = q.poll();

			// 찾음. backtrack 하면서 최적 경로 찾기
			if (p == K) {
				break;
			}

			if (p < K && p * 2 <= 100000 && visited[p * 2] == 0) {
				q.add(p * 2);
				visited[p * 2] = visited[p] + 1;
			}

			if (p + 1 <= 100000 && visited[p + 1] == 0) {
				q.add(p + 1);
				visited[p + 1] = visited[p] + 1;
			}

			if (p - 1 >= 0 && visited[p - 1] == 0) {
				q.add(p - 1);
				visited[p - 1] = visited[p] + 1;
			}
		}

		StringBuffer sb = new StringBuffer();
		ArrayList<Integer> list = new ArrayList<>();
		sb.append(visited[K] - 1).append("\n");
		while (K != N) {
			if (K-1>=0 && visited[K - 1] + 1 == visited[K]) {
				list.add(K);
				K--;
				continue;
			}

			if (K+1 <= 100000 && visited[K + 1] + 1 == visited[K]) {
				list.add(K);
				K++;
				continue;
			}

			if (K % 2 == 0 && visited[K / 2] + 1 == visited[K]) {
				list.add(K);
				K = K / 2;
				continue;
			}
		}
		list.add(N);
		for(int i=list.size()-1; i>=0; i--) {
			sb.append(list.get(i)).append(" ");
		}
		System.out.println(sb.toString());

	}
}
