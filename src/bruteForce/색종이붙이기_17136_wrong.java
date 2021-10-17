package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136_wrong {
	private static boolean[][] one = new boolean[10][10]; // 1*1 색종이
	private static boolean[][] two = new boolean[9][9];
	private static boolean[][] three = new boolean[8][8];
	private static boolean[][] four = new boolean[7][7];
	private static boolean[][] five = new boolean[6][6];
	private static int[] dx = { 0, 0, 1, 1 };
	private static int[] dy = { 0, 1, 0, 1 };
	private static int[] papers = { 0, 0, 0, 0, 0 }; // 1*1 ~ 5*5 색종이의 개수들
	private static boolean[][] visited = new boolean[10][10];

	private static void checkVisited(int i, int j, int cnt) {
		for (int x = i; x < i + cnt; x++) {
			for (int y = j; y < j + cnt; y++) {
				visited[x][y] = true;
			}
		}
		// print(visited);
	}

	// 색종이 붙이기. 해당 배열, 확인해야 될 길이 인자로 받음
	private static void putPaper(boolean[][] arr, int cnt) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (!visited[i][j] && !visited[i + cnt - 1][j] && !visited[i][j + cnt - 1]
						&& !visited[i + cnt - 1][j + cnt - 1] && arr[i][j]) {
					checkVisited(i, j, cnt);
					papers[cnt - 1]++;
				}
				if (cnt != 1 && papers[cnt - 1] == 5) {
					break;
				}
			}
		}
	}

	// 탐색 전 3*3, 4*4 4개 연속으로 놓을 수 있는지 확인
	private static void preprocess() {
		// 5*5 4개 놓을 수 있을 경우 return
		if (five[0][0] && five[5][5] && five[0][5] && five[5][5]) {
			return;
		}

		// 4*4 4개 확인
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (four[i][j] && four[i + 4][j] && four[i][j + 4] && four[i + 4][j + 4]) {
					papers[3] += 4;
					checkVisited(i, j, 8);
					return;
				}
			}
		}

		// 3*3 4개 확인
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (three[i][j] && three[i + 3][j] && three[i][j + 3] && three[i + 3][j + 3]) {
					papers[2] += 4;
					checkVisited(i, j, 6);
					return;
				}
			}
		}
	}

	// 색종이 붙일 수 있는지 확인
	private static boolean check(int i, int j, boolean[][] arr) {
		for (int k = 0; k < 4; k++) {
			if (!arr[i + dx[k]][j + dy[k]]) {
				return false;
			}
		}
		return true;
	}

	// two ~ five 배열 색종이 확인
	private static void setArray(boolean[][] arr, boolean[][] arr2) {
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				arr2[i][j] = check(i, j, arr);
			}
		}
	}

	// MAIN
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				if (st.nextToken().charAt(0) == '1') {
					one[i][j] = true;
				}
			}
		}

		// 2*2 ~ 5*5 붙일 수 있는지 확인
		setArray(one, two);
		setArray(two, three);
		setArray(three, four);
		setArray(four, five);

		
		/*  틀림
		// 3*3, 4*4 연속으로 놓을 수 있는지 확인
		preprocess();
		
		// 색종이 붙여보기
		putPaper(five, 5);
		putPaper(four, 4);
		putPaper(three, 3);
		putPaper(two, 2);
		putPaper(one, 1);
		*/
	
		if (papers[0] > 5) {
			System.out.println(-1);
		} else {
			int sum = 0;
			for (int i : papers) {
				sum += i;
			}
			System.out.println(sum);
		}
	}

	// 제대로 나오는지 확인용
	private static void print(boolean[][] arr) {
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j]) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
			System.out.println();
		}
	}
}
