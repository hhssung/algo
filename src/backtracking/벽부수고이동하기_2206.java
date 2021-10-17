package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기_2206 {
	static class Point {
		int x;
		int y;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N;
	static int M;
	static int answer = Integer.MAX_VALUE;

	// map 최단거리 수정하기
	private static void BFS(Point p, int[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.add(p);
		map[p.y][p.x] = 1; // init
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };

		int tmpX, tmpY;
		while (!q.isEmpty()) {
			Point tmp = q.poll();

			for (int i = 0; i < 4; i++) {
				tmpY = tmp.y + dy[i];
				tmpX = tmp.x + dx[i];
				if (isValid(tmpY, tmpX)) {
					if (map[tmpY][tmpX] != -1 && map[tmpY][tmpX] > map[tmp.y][tmp.x] + 1) {
						map[tmpY][tmpX] = map[tmp.y][tmp.x] + 1; // 갱신
						q.add(new Point(tmpY, tmpX));
					}
				}
			}
		}
	}

	private static void calcAnswer(int[][] map, int[][] map2) {
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };

		int tmpX, tmpY;
		int min1;
		int min2;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == -1) { // 벽 찾음.
					min1 = Integer.MAX_VALUE;
					min2 = Integer.MAX_VALUE;
					for (int k = 0; k < 4; k++) {
						tmpY = i + dy[k];
						tmpX = j + dx[k];
						if (isValid(tmpY, tmpX) && map[tmpY][tmpX] != -1) {
							if(min1 > map[tmpY][tmpX]) {
								min1 = map[tmpY][tmpX];
							}
							if(min2 > map2[tmpY][tmpX]) {
								min2 = map2[tmpY][tmpX];
							}
						}
					}
					if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE) {
						continue;
					}
					min1 = min1 + min2 + 1;
					if (answer > min1) {
						answer = min1;
					}
				}
			}
		}
	}

	private static boolean isValid(int y, int x) {
		if (y < 1 || x < 1 || y > N || x > M) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][M + 1];
		int[][] map2 = new int[N + 1][M + 1];

		String s;

		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
				if (map[i][j] == 0) {
					map[i][j] = Integer.MAX_VALUE; // 빈 공간은 max
				} else {
					map[i][j] = -1; // 벽은 -1
				}
				map2[i][j] = map[i][j];
			}
		}

		BFS(new Point(1, 1), map);
		BFS(new Point(N, M), map2);

		if (map[N][M] != Integer.MAX_VALUE) {
			answer = map[N][M];
		}

		calcAnswer(map, map2);

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void printMap(int[][] map) {
		System.out.println();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == Integer.MAX_VALUE) {
					System.out.print(0 + " ");
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
