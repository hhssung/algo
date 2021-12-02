package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 틀린 이유: 승객이 같은 거리일 경우를 전부 모아서 같이 처리해 줘야 됨. pq사용

public class bj_19238_스타트택시 {
	static int taxi_y;
	static int taxi_x;

	private static int Solution(int n, int m, int oil, int[][] map, myPair[][] guest_map) {
		final int[] dy = { -1, 0, 0, 1 };
		final int[] dx = { 0, -1, 1, 0 };
		final int FAIL = -1;

		// printAll(map);

		int used_oil;
		while (m > 0) {
			// 가장 가까운 승객으로 이동하기. 내 칸에 이미 있는지 확인
			used_oil = moveToGuest(n, oil, dy, dx, map);
			if (used_oil == FAIL) {
				return FAIL;
			}
			oil -= used_oil;

			// 승객 목적지로 데려다주기
			used_oil = takeTaxi(n, oil, dy, dx, map, guest_map[taxi_y][taxi_x]);
			if (used_oil == FAIL) {
				return FAIL;
			}
			oil += used_oil; // 소모한 연료 양 두 배로 채우기

			m--;
		}

		return oil;
	}

	// 소모한 기름량 반환
	private static int takeTaxi(int n, int oil, int[] dy, int[] dx, int[][] map, myPair dest) {
		Queue<myPair> q = new LinkedList<>();
		boolean[][] visited = new boolean[n + 1][n + 1];
		q.add(new myPair(taxi_y, taxi_x, 1));
		visited[taxi_y][taxi_x] = true;
		int tmpY, tmpX;

		while (!q.isEmpty()) {
			myPair p = q.poll();
			// 연료 부족
			if (p.dist > oil) {
				return -1;
			}

			for (int i = 0; i < 4; i++) {
				tmpY = p.y + dy[i];
				tmpX = p.x + dx[i];
				// 범위 초과
				if (tmpY < 1 || tmpX < 1 || tmpY > n || tmpX > n) {
					continue;
				}
				// 이미 방문
				if (visited[tmpY][tmpX]) {
					continue;
				}
				// 벽 충돌
				if (map[tmpY][tmpX] == 1) {
					continue;
				}
				// 목적지 찾기
				if (tmpY == dest.y && tmpX == dest.x) {
					taxi_y = tmpY;
					taxi_x = tmpX;
					return p.dist;
				} else {
					visited[tmpY][tmpX] = true;
					q.add(new myPair(tmpY, tmpX, p.dist + 1));
				}
			}
		}

		return -1;
	}

	// 소모한 기름량 반환
	private static int moveToGuest(int n, int oil, int[] dy, int[] dx, int[][] map) {
		if (map[taxi_y][taxi_x] == 2) {
			map[taxi_y][taxi_x] = 0;
			return 0;
		}

		Queue<myPair> q = new LinkedList<>();
		boolean[][] visited = new boolean[n + 1][n + 1];
		q.add(new myPair(taxi_y, taxi_x, 1));
		visited[taxi_y][taxi_x] = true;
		int tmpY, tmpX;

		int used_oil = Integer.MAX_VALUE;	// 사용한 기름
		PriorityQueue<myPair> pq = new PriorityQueue<>();	// 같은 거리의 손님이 여러 명일때
		
		while (!q.isEmpty()) {
			myPair p = q.poll();
			// 이미 더 가까운 손님이 있을 경우 break
			if(used_oil < p.dist) {
				break;
			}
			// 연료 부족
			if (p.dist > oil) {
				return -1;
			}
			for (int i = 0; i < 4; i++) {
				tmpY = p.y + dy[i];
				tmpX = p.x + dx[i];
				// 범위 초과
				if (tmpY < 1 || tmpX < 1 || tmpY > n || tmpX > n) {
					continue;
				}
				// 이미 방문
				if (visited[tmpY][tmpX]) {
					continue;
				}
				// 벽 충돌
				if (map[tmpY][tmpX] == 1) {
					continue;
				}
				// 빈 칸
				if (map[tmpY][tmpX] == 0) {
					visited[tmpY][tmpX] = true;
					q.add(new myPair(tmpY, tmpX, p.dist + 1));
				}
				// 승객 발견
				if (map[tmpY][tmpX] == 2) {
					used_oil = p.dist;
					pq.add(new myPair(tmpY, tmpX, p.dist));
				}
			}
		}

		if(pq.isEmpty()) {
			return -1;
		}
		
		myPair dest = pq.poll();
		taxi_y = dest.y;
		taxi_x = dest.x;
		map[taxi_y][taxi_x] = 0;
		return used_oil;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N, M, oil;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());

		// 벽 : 1, 승객 : 2
		int[][] map = new int[N + 1][N + 1];
		myPair[][] guest_map = new myPair[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		taxi_y = Integer.parseInt(st.nextToken());
		taxi_x = Integer.parseInt(st.nextToken());

		int sy, sx, ey, ex;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			guest_map[sy][sx] = new myPair(ey, ex);
			map[sy][sx] = 2;
		}

		System.out.println(Solution(N, M, oil, map, guest_map));
	}

	static class myPair implements Comparable<myPair>{
		int y;
		int x;
		int dist;

		public myPair(int y, int x, int dist) {
			this(y, x);
			this.dist = dist;
		}

		public myPair(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(myPair o) {
			if(this.y != o.y) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}

	private static void printAll(int[][] arr) {
		for (int[] y : arr) {
			for (int x : y) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
