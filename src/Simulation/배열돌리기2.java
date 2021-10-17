package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기2 {
	private static int[][] getArray(int[][] arr, int R, int N, int M) {
		int middle = Math.min(N, M) / 2;
		int[][] answer = new int[N][M];
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		int x, y;
		int new_x, new_y;
		int new_R;
		for (int i = 0; i < middle; i++) {
			y = i;
			x = i; // 시작점
			new_y = i;
			new_x = i; // 새로운 시작점
			new_R = R % (2 * (N + M - 4 * i) - 4);

			//System.out.println(new_R);
			
			// 새로운 시작점 좌표 구하기
			int tmp = 0;
			int k = 0;
			while (tmp < new_R) {
				if (new_y + dy[k] < i || new_x + dx[k] < i || new_y + dy[k] >= N - i || new_x + dx[k] >= M - i) {
					k++;
					if (k == 4) {
						k = 0;
					}
				}
				new_y = new_y + dy[k];
				new_x = new_x + dx[k];
				tmp++;
			}

			// System.out.println(new_y + " " + new_x);

			int k2 = 0;
			while (answer[new_y][new_x] == 0) {
				answer[new_y][new_x] = arr[y][x];
				if (new_y + dy[k] < i || new_x + dx[k] < i || new_y + dy[k] >= N - i || new_x + dx[k] >= M - i) {
					k++;
					if (k == 4) {
						k = 0;
					}
				}
				if (y + dy[k2] < i || x + dx[k2] < i || y + dy[k2] >= N - i || x + dx[k2] >= M - i) {
					k2++;
					if (k2 == 4) {
						k = 0;
					}
				}
				new_y = new_y + dy[k];
				new_x = new_x + dx[k];
				y = y + dy[k2];
				x = x + dx[k2];
			}

			// printAll(answer, N, M);
		}

		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] answer = getArray(arr, R, N, M);

		printAll(answer, N, M);
	}

	private static void printAll(int[][] arr, int N, int M) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
