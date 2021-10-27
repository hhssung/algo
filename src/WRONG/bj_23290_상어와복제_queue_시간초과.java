package WRONG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// QUEUE -> 시간초과 남. 불가능

public class bj_23290_상어와복제_queue_시간초과 {
	static class fish {
		static int[] movY = { 999, 0, -1, -1, -1, 0, 1, 1, 1 };
		static int[] movX = { 999, -1, -1, 0, 1, 1, 1, 0, -1 };
		int y;
		int x;
		int dir;

		public fish(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		public void move(int[][] map) {
			int tmp_dir = dir;
			int tmpY, tmpX;
			while (true) {
				tmpY = y + movY[tmp_dir];
				tmpX = x + movX[tmp_dir];
				if (tmpY > 0 && tmpY <= 4 && tmpX > 0 && tmpX <= 4 && map[tmpY][tmpX] == 0) { // 움직일수 있음
					y = tmpY;
					x = tmpX;
					dir = tmp_dir;
					break;
				}
				tmp_dir--; // 45도 회전
				if (tmp_dir == 0) {
					tmp_dir = 8;
				}
				if (tmp_dir == dir) { // 움직일수 없음
					break;
				}
			}
		}

		@Override
		public String toString() {
			return "fish [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
	}

	static int s_y, s_x;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 물고기 수
		int S = Integer.parseInt(st.nextToken()); // 상어 마법 횟수
		int[][] map = new int[5][5]; // 냄새, 상어 위치를 저장하는 배열
		int[][] fish_cnt = new int[5][5]; // 물고기 개수를 저장하는 배열
		int[] movY = { -1, 0, 1, 0 };
		int[] movX = { 0, -1, 0, 1 }; // 상좌하우
		// shark = 9, 냄새1 = 1, 냄새2 = 2

		Queue<fish> fishes = new LinkedList<>(); // 물고기 저장하는 q
		// 물고기 정보 받기
		int fx, fy, d;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fy = Integer.parseInt(st.nextToken());
			fx = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			fishes.add(new fish(fy, fx, d));
		}

		// 상어 정보 받기
		st = new StringTokenizer(br.readLine());
		s_y = Integer.parseInt(st.nextToken());
		s_x = Integer.parseInt(st.nextToken());
		map[s_y][s_x] = 9;

		Queue<fish> copied_fish = new LinkedList<>(); // 복제 마법 시전한 물고기 저장하는 큐

		// simulation
		for (int i = 1; i <= S; i++) {
			// 복제 마법 시전.
			func1(fishes, copied_fish);
			// 물고기 이동
			func2(map, fish_cnt, fishes);
			// 상어 이동
			func3(map, fish_cnt, fishes, movY, movX);
			// printAll(fish_cnt);
			// 마지막일경우 더이상 계산할 필요 없음
			if (i == S) {
				break;
			}
			// 냄새 없애기
			func4(map);
			// 복제 마법 시전 완료
			func5(fishes, copied_fish);
		}

		// 정답 출력
		System.out.println(fishes.size() + copied_fish.size());
	}

	// 복제 마법 시전.
	private static void func1(Queue<fish> fishes, Queue<fish> copied_fish) {
		int size = fishes.size();
		fish before;
		for (int i = 0; i < size; i++) {
			before = fishes.poll();
			copied_fish.add(new fish(before.y, before.x, before.dir));
			fishes.add(before);
		}
	}

	// 물고기 이동
	private static void func2(int[][] map, int[][] fish_cnt, Queue<fish> fishes) {
		// 물고기 카운트 초기화
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				fish_cnt[i][j] = 0;
			}
		}
		int size = fishes.size();
		fish tmp;
		for (int i = 0; i < size; i++) {
			tmp = fishes.poll();
			// System.out.println(tmp);
			tmp.move(map);
			// System.out.println(tmp);
			fish_cnt[tmp.y][tmp.x]++;
			fishes.add(tmp);
		}
	}

	// 상어 이동
	private static void func3(int[][] map, int[][] fish_cnt, Queue<fish> fishes, int[] movY, int[] movX) {
		// 상 : 0, 좌 : 1, 하 : 2, 우 : 3(11)
		// 64 경우 가능. (110000 - 48, 001100 - 12, 000011 - 3)
		int result = -1; // INT_MIN
		int[][] resultPos = new int[3][2]; // 상어가 방문하는 pos 저장하는 배열
		int tmp_result;
		int[] m = new int[3];
		int[] mx = new int[3];
		int[] my = new int[3]; // 임시로 상어 위치 저장하는 배열
		int tmpY, tmpX;
		boolean canGo;
		for (int i = 0; i < 64; i++) {
			tmpY = s_y;
			tmpX = s_x;
			canGo = true;
			m[0] = (i & 48) >> 4;
			m[1] = (i & 12) >> 2;
			m[2] = (i & 3);

			// 이동
			for (int j = 0; j < 3; j++) {
				tmpY += movY[m[j]];
				tmpX += movX[m[j]];
				if (tmpY < 1 || tmpX < 1 || tmpY > 4 || tmpX > 4) {
					canGo = false;
					break;
				}
				my[j] = tmpY;
				mx[j] = tmpX;
			}

			// 갱신
			boolean[][] visited = new boolean[5][5];
			if (canGo) {
				tmp_result = 0;
				// 처음 위치도 고려해야 됨
				visited[s_y][s_x] = true;
				tmp_result += fish_cnt[s_y][s_x];
				for (int j = 0; j < 3; j++) {
					if (visited[my[j]][mx[j]]) {
						continue;
					}
					tmp_result += fish_cnt[my[j]][mx[j]];
					visited[my[j]][mx[j]] = true;
				}
				if (tmp_result > result) {
					result = tmp_result;
					for (int j = 0; j < 3; j++) {
						resultPos[j][0] = my[j];
						resultPos[j][1] = mx[j];
					}
				}
			}
		}

		// 냄새 남기기(map 갱신)
		map[s_y][s_x] -= 9;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (map[i][j] != 0) {
					map[i][j]++;
				}
			}
		}
		if (fish_cnt[resultPos[0][0]][resultPos[0][1]] != 0) {
			map[resultPos[0][0]][resultPos[0][1]]++;
		}
		if (fish_cnt[resultPos[1][0]][resultPos[1][1]] != 0) {
			map[resultPos[1][0]][resultPos[1][1]]++;
		}
		map[resultPos[2][0]][resultPos[2][1]] += 9;
		s_y = resultPos[2][0];
		s_x = resultPos[2][1];

		// 물고기 없애기
		fish tmp;
		int size = fishes.size();
		for (int i = 0; i < size; i++) {
			tmp = fishes.poll();
			// 안 먹혔을 경우 다시 추가
			if (map[tmp.y][tmp.x] == 0) {
				fishes.add(tmp);
			}
		}
		printAll(fish_cnt);
	}

	// 냄새 없애기
	private static void func4(int[][] map) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (map[i][j] == 3 || map[i][j] == 12) {
					map[i][j] -= 3;
				}
			}
		}
	}

	// 복제 마법 시전 완료
	private static void func5(Queue<fish> fishes, Queue<fish> copied_fish) {
		while (!copied_fish.isEmpty()) {
			fishes.add(copied_fish.poll());
		}
	}

	private static void printAll(int[][] arr) {
		System.out.println();
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
