package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class programmers_lv3_순위 {

	static class Solution {
		public int solution(int n, int[][] results) {
			int answer = 0;

			int[][] map = new int[n + 1][n + 1];
			// 이겼을 경우 1, 졌을 경우 -1

			for (int[] result : results) {
				map[result[0]][result[1]] = 1;
				map[result[1]][result[0]] = -1;
			}

			// 모든 선수 순회
			int win;
			int lose;
			for (int i = 1; i <= n; i++) {
				boolean[] visited = new boolean[n + 1];
				visited[i] = true;
				win = 0;
				lose = 0;

				// 이긴 경우 세기
				win = getPlayerCount(visited, map, i, 1, n);

				// 진 경우 세기
				lose = getPlayerCount(visited, map, i, -1, n);

				if (win + lose == n - 1) {
					answer++;
				}
			}

			return answer;
		}

		private int getPlayerCount(boolean[] visited, int[][] map, int start, final int TARGET, int n) {
			int result = 0;
			int tmp;
			Queue<Integer> q = new LinkedList<>();

			q.add(start);
			while (!q.isEmpty()) {
				tmp = q.poll();
				for (int j = 1; j <= n; j++) {
					if (!visited[j] && map[tmp][j] == TARGET) {
						visited[j] = true;
						result++;
						q.add(j);
					}
				}
			}

			return result;
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int n = 5;
		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		int answer = sol.solution(n, results);
		System.out.println(answer);
	}
}
