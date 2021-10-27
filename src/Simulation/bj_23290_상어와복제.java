package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Queue로 풀면 시간초과

public class bj_23290_상어와복제 {
	static int s_y, s_x; // 상어 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 물고기 수
		int S = Integer.parseInt(st.nextToken()); // 상어 마법 횟수
		int[][] map = new int[5][5]; // 냄새를 저장하는 배열
		int[] s_movY = { -1, 0, 1, 0 };
		int[] s_movX = { 0, -1, 0, 1 }; // 상좌하우
		// shark = 9, 냄새1 = 1, 냄새2 = 2

		int[][][] fishes = new int[5][5][9]; // 특정 방향의 물고기들을 저장하는 배열
		// 물고기 정보 받기
		int fx, fy, d;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fy = Integer.parseInt(st.nextToken());
			fx = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			fishes[fy][fx][d]++;
		}

		// 상어 정보 받기
		st = new StringTokenizer(br.readLine());
		s_y = Integer.parseInt(st.nextToken());
		s_x = Integer.parseInt(st.nextToken());

		int[][][] copied_fish = new int[5][5][9]; // 복제 마법 시전한 물고기 저장

		// simulation
		for (int i = 1; i <= S; i++) {
			// 복제 마법 시전.
			func1(fishes, copied_fish);
			// 물고기 이동
			func2(map, fishes);
			// 상어 이동
			func3(map, fishes, s_movY, s_movX);
			// 냄새 없애기
			func4(map);
			// 복제 마법 시전 완료
			func5(fishes, copied_fish);
			//printAll(map);
			//System.out.println("----");
		}

		int answer = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					answer += fishes[i][j][k];
				}
			}
		}
		System.out.println(answer);
	}

	// 복제 마법 시전 완료
	private static void func5(int[][][] fishes, int[][][] copied_fish) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					fishes[i][j][k] += copied_fish[i][j][k];
				}
			}
		}
	}

	// 냄새 없애기
	private static void func4(int[][] map) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (map[i][j] == 3) {
					map[i][j] = 0;
				}
			}
		}
	}

	// 상어 이동
	private static void func3(int[][] map, int[][][] fishes, int[] s_movY, int[] s_movX) {
		int result = -1; // 최댓값 찾기

		int[][] fish_cnt = new int[5][5]; // 물고기 개수를 저장하는 배열
		int[][] resultPos = new int[3][2]; // 상어가 방문하는 pos 저장하는 배열

		int[] m = new int[3]; // (상, 상, 상) 저장하는 배열
		int[] mx = new int[3];
		int[] my = new int[3]; // 임시로 상어 위치 저장하는 배열

		int tmp_result;
		int tmpY, tmpX;
		boolean canGo;

		// fishcnt 배열 만들기
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					fish_cnt[i][j] += fishes[i][j][k];
				}
			}
		}
		//System.out.println(s_y + " " + s_x);
		//printAll(fish_cnt);
		// 사전순으로 훑기. 최적화를 위해 bitmasking 사용. DFS 안썼음
		// 상 : 0, 좌 : 1, 하 : 2, 우 : 3(11)
		// 64 경우 가능. (110000 - 48, 001100 - 12, 000011 - 3)
		for (int i = 0; i < 64; i++) {
			tmpY = s_y;
			tmpX = s_x;
			canGo = true;
			m[0] = (i & 48) >> 4;
			m[1] = (i & 12) >> 2;
			m[2] = (i & 3);

			// 이동
			for (int j = 0; j < 3; j++) {
				tmpY += s_movY[m[j]];
				tmpX += s_movX[m[j]];
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
//				틀린 이유 : 처음 상어가 있는 위치는 먹지 않음 3시간날림
//				visited[s_y][s_x] = true;
//				tmp_result += fish_cnt[s_y][s_x];
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

		// 이미 있는 냄새 갱신
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (map[i][j] != 0) {
					map[i][j]++;
				}
			}
		}

		// 물고기 냄새 이미 있는곳에 상어가 갈 때 다시 "한 번 전 연습"으로 처리해야 됨
		for (int i = 0; i < 3; i++) {
			if (fish_cnt[resultPos[i][0]][resultPos[i][1]] > 0) {
				map[resultPos[i][0]][resultPos[i][1]] = 1;
			}
		}

		// 물고기 없애기
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= 8; j++) {
				fishes[resultPos[i][0]][resultPos[i][1]][j] = 0;
			}
		}
		
		//상어 위치 갱신
		s_y = resultPos[2][0];
		s_x = resultPos[2][1];
		//System.out.println(s_y + " " + s_x);
	}

	// 물고기 이동
	private static void func2(int[][] map, int[][][] fishes) {
		int[][][] tmp = new int[5][5][9];
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					if (fishes[i][j][k] != 0) {
						movefish(map, tmp, i, j, k, fishes[i][j][k]);
					}
				}
			}
		}
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					fishes[i][j][k] = tmp[i][j][k];
				}
			}
		}
	}

	// 복제 마법 시전.
	private static void func1(int[][][] fishes, int[][][] copied_fish) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 8; k++) {
					copied_fish[i][j][k] = fishes[i][j][k];
				}
			}
		}
	}

	static int[] movY = { 999, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] movX = { 999, -1, -1, 0, 1, 1, 1, 0, -1 };

	// 물고기 이동 함수
	private static void movefish(int[][] map, int[][][] tmp_fish, int y, int x, int dir, int cnt) {
		int tmp_dir = dir;
		int tmpY, tmpX;
		while (true) {
			tmpY = y + movY[tmp_dir];
			tmpX = x + movX[tmp_dir];
			if (tmpY > 0 && tmpY <= 4 && tmpX > 0 && tmpX <= 4 && map[tmpY][tmpX] == 0) {
				if (tmpY != s_y || tmpX != s_x)// 움직일수 있음
				{
					tmp_fish[tmpY][tmpX][tmp_dir] += cnt;
					return;
				}
			}
			tmp_dir--; // 45도 회전
			if (tmp_dir == 0) {
				tmp_dir = 8;
			}
			if (tmp_dir == dir) { // 움직일수 없음
				break;
			}
		}
		tmp_fish[y][x][dir] += cnt;
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

/*
 * 
 * 예외케이스 65%
 * 
 * 2 4 4 3 8 2 4 1 1 4
 * 
 * 답: 8 오답: 9
 * 
 * 143번째 줄 오답
 * 
 */
