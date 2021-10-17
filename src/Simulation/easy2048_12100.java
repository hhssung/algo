package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class easy2048_12100 {
	static int answer = 1;

	private static void DFS(int[][] before, int cnt, int max_block) {
		// 더 탐색할 필요 x
		if (max_block << (5 - cnt) <= answer) {
			return;
		}

		// 탐색 완
		if (cnt == 5) {
			if (max_block > answer) {
				answer = max_block;
			}
			return;
		}

		int[][] after;
		int tmp_max;
		
		//회전해서 복사
		for(int i=1; i<=4; i++) {
			after = copyBoard(before, before.length, i);
			tmp_max = Move(after, after.length, max_block);
			DFS(after, cnt + 1, tmp_max);
		}
	}

	// 위로만 올린다 생각, 한번만 구현. 나머진 회전해서 계산
	private static int Move(int[][] board, int N, int max_block) {
		// 이전 블록의 크기, pos 저장하는 변수들
		int before_size = -1;
		int before_pos = -1;
		int put_pos = 0;

		for (int j = 0; j < N; j++) { // x축기준
			// 값 초기화
			put_pos = 0;
			before_size = -1;
			before_pos = -1;

			for (int i = 0; i < N; i++) {
				if (board[i][j] != 0) {
					// 처음으로 or 합친 다음 블록 발견
					if (before_size == -1) {
						before_size = board[i][j];
						before_pos = i;
						continue;
					}

					// 기존 블록과 비교, 같음. 갱신 후 size-1로 바꾸기
					if (before_size == board[i][j]) {
						board[put_pos][j] = board[i][j] << 1; // *2
						// 기존 max보다 큰 블록 발생
						if (board[put_pos][j] > max_block) {
							max_block = board[put_pos][j];
						}
						if (put_pos != before_pos) {
							board[before_pos][j] = 0;
						}
						board[i][j] = 0;
						put_pos++;
						before_size = -1;
					}
					// 기존 블록과 비교, 다름. before_block 갱신
					else {
						board[put_pos][j] = before_size;
						if (put_pos != before_pos) {
							board[before_pos][j] = 0;
						}
						before_size = board[i][j];
						before_pos = i;
						put_pos++;
					}
				}
			}
			// 남은 블록 있는지 확인
			if (before_size != -1) {
				board[put_pos][j] = before_size;
				if (put_pos != before_pos) {
					board[before_pos][j] = 0;
				}
			}
		}

		return max_block;
	}

	// flag (1,2,3,4) -> 그대로, 우, 상하, 좌
	private static int[][] copyBoard(int[][] board, int N, int flag) {
		int[][] new_board = new int[N][N];
		
		if(flag == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					new_board[i][j] = board[i][j];
				}
			}
			return new_board;
		}
		
		if(flag == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					new_board[j][N-1-i] = board[i][j];
				}
			}
			return new_board;
		}
		
		if(flag == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					new_board[N-1-i][N-1-j] = board[i][j];
				}
			}
			return new_board;
		}
		
		if(flag == 4) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					new_board[N-1-j][i] = board[i][j];
				}
			}
		}
		return new_board;
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];

		int tmpMax = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (tmpMax < board[i][j]) {
					tmpMax = board[i][j];
				}
			}
		}

		if (N == 1) {
			System.out.println(board[0][0]);
			return;
		}

		DFS(board, 0, tmpMax);
		
		System.out.println(answer);
	}

	private static void printAll(int[][] board) {
		System.out.println();
		for (int[] x : board) {
			for (int y : x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
	}
}
