package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_23291_어항정리 {
	static int firstStepY;
	static int firstStepX;
	static Pos[] firstStepArrayPos;

	static class Pos {
		int y;
		int x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] fishes = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			fishes[i] = Integer.parseInt(st.nextToken());
		}

		makeFirstStepArray(N);

		int answer = 0;
		while (true) {
			int max_fish = getMaxFish(fishes, N);
			int min_fish = getMinFish(fishes, N);
			if (max_fish - min_fish <= K) {
				break;
			}
			// 01
			putFish(fishes, N, min_fish);

			// 02
			firstOrganizeFish(fishes, N);

			// 03
			secondOrganizeFish(fishes, N);

			answer++;
		}
		System.out.println(answer);
	}

	private static void secondOrganizeFish(int[] fishes, int n) {
		int div_4 = n / 4;
		int[][] before_arr = new int[4][div_4];
		int[][] after_arr = new int[4][div_4];

		for (int i = 1; i <= div_4; i++) {
			before_arr[2][div_4 - i] = fishes[i];
		}
		for (int i = div_4 + 1; i <= div_4 * 2; i++) {
			before_arr[1][i - div_4 - 1] = fishes[i];
		}
		for (int i = div_4 * 2 + 1; i <= div_4 * 3; i++) {
			before_arr[0][div_4 * 3 - i] = fishes[i];
		}
		for (int i = div_4 * 3 + 1; i <= n; i++) {
			before_arr[3][i - div_4 * 3 - 1] = fishes[i];
		}

		regulateFish(before_arr, after_arr, 4, div_4);
		putFishToFishes(after_arr, fishes, 4, div_4);
	}

	private static void firstOrganizeFish(int[] fishes, int n) {
		int y = firstStepY;
		int x = firstStepX;
		int mul = x * y;
		int[][] before_arr = new int[y][x];
		int[][] after_arr = new int[y][x];
		before_arr[y - 1] = new int[n - mul + x];
		after_arr[y - 1] = new int[n - mul + x];

		for (int i = 1; i <= mul; i++) {
			before_arr[firstStepArrayPos[i].y][firstStepArrayPos[i].x] = fishes[i];
		}
		for (int i = mul + 1; i <= n; i++) {
			before_arr[y - 1][x + i - mul - 1] = fishes[i];
		}

		regulateFish(before_arr, after_arr, y, x);
		putFishToFishes(after_arr, fishes, y, x);

//			printarr(after_arr);
//			for(int i : fishes) {
//				System.out.print(i+" ");
//			}
	}

	// 어항을 바닥에 일렬로 놓기
	private static void putFishToFishes(int[][] after_arr, int[] fishes, int y, int x) {
		int idx = 1;
		for (int j = 0; j < x; j++) {
			for (int i = y - 1; i >= 0; i--) {
				fishes[idx] = after_arr[i][j];
				idx++;
			}
		}
		if (after_arr[y - 1].length > x) {
			for (int j = x; j < after_arr[y - 1].length; j++) {
				fishes[idx] = after_arr[y - 1][j];
				idx++;
			}
		}
	}

	// 물고기 수 조절
	private static void regulateFish(int[][] before_arr, int[][] after_arr, int y, int x) {
		// 물고기 수 조절 - 가로 방향
		int quot;
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < before_arr[i].length - 1; j++) {
				quot = Math.abs(before_arr[i][j] - before_arr[i][j + 1]) / 5;
				if (quot > 0) {
					if (before_arr[i][j] > before_arr[i][j + 1]) {
						after_arr[i][j] -= quot;
						after_arr[i][j + 1] += quot;
					} else {
						after_arr[i][j] += quot;
						after_arr[i][j + 1] -= quot;
					}
				}
			}
		}
		// 물고기 수 조절 - 세로 방향
		for (int j = 0; j < x; j++) {
			for (int i = 0; i < y - 1; i++) {
				quot = Math.abs(before_arr[i][j] - before_arr[i + 1][j]) / 5;
				if (quot > 0) {
					if (before_arr[i][j] > before_arr[i + 1][j]) {
						after_arr[i][j] -= quot;
						after_arr[i + 1][j] += quot;
					} else {
						after_arr[i][j] += quot;
						after_arr[i + 1][j] -= quot;
					}
				}
			}
		}
		// after_arr 반영하기
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < before_arr[i].length; j++) {
				after_arr[i][j] += before_arr[i][j];
			}
		}
	}

	private static void putFish(int[] fishes, int n, int min_fish) {
		for (int i = 1; i <= n; i++) {
			if (fishes[i] == min_fish) {
				fishes[i]++;
			}
		}
	}

	private static int getMaxFish(int[] fishes, int N) {
		int result = -1;
		for (int i = 1; i <= N; i++) {
			if (result < fishes[i]) {
				result = fishes[i];
			}
		}
		return result;
	}

	private static int getMinFish(int[] fishes, int N) {
		int result = 10001;
		for (int i = 1; i <= N; i++) {
			if (result > fishes[i]) {
				result = fishes[i];
			}
		}
		return result;
	}

	private static void makeFirstStepArray(int n) {
		int[] dy = { 0, -1, 0, 1 };
		int[] dx = { -1, 0, 1, 0 };

		int x = 2;
		int y = 2;

		while (true) {
			if (x * y > n) {
				x--;
				break;
			}
			y++;
			if (x * y > n) {
				y--;
				break;
			}
			x++;
		}

		int p_y = y - 1;
		int p_x = x;
		int tmp_y;
		int tmp_x;
		int dir = 0;
		int[][] testArr = new int[y][x];
		firstStepArrayPos = new Pos[x * y + 1];
		for (int i = x * y; i >= 1; i--) {
			tmp_y = p_y + dy[dir];
			tmp_x = p_x + dx[dir];
			if (tmp_y < 0 || tmp_x < 0 || tmp_y >= y || tmp_x >= x || testArr[tmp_y][tmp_x] != 0) {
				dir++;
				dir = dir % 4;
			}
			p_y = p_y + dy[dir];
			p_x = p_x + dx[dir];
			firstStepArrayPos[i] = new Pos(p_y, p_x);
			testArr[p_y][p_x] = i;
		}
		// printarr(testArr);
		firstStepY = y;
		firstStepX = x;
	}

	private static void printarr(int[][] arr) {
		for (int[] y : arr) {
			for (int x : y) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
