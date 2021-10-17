package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 어줍잖게 가지치기 하려다가 개털린 문제
// 수가 작으면 그냥 확실하게 완전탐색 하자.

public class 색종이붙이기_17136 {
	private static boolean[][] one = new boolean[10][10]; // 1*1 색종이
	private static boolean[][] two = new boolean[9][9];
	private static boolean[][] three = new boolean[8][8];
	private static boolean[][] four = new boolean[7][7];
	private static boolean[][] five = new boolean[6][6];
	private static int[] dx = { 0, 0, 1, 1 };
	private static int[] dy = { 0, 1, 0, 1 };
	private static int[] papers = { 0, 0, 0, 0, 0 }; // 1*1 ~ 5*5 색종이의 개수들
	private static boolean[][] visited = new boolean[10][10];
	private static int answer = 26;

	// (i,j) 좌표에서 cnt*cnt 만큼 flag으로 바꿈
	private static void checkVisited(int i, int j, int cnt, boolean flag) {
		for (int x = i; x < i + cnt; x++) {
			for (int y = j; y < j + cnt; y++) {
				visited[x][y] = flag;
			}
		}
	}

	// visited 넣을 수 있는지 확인하는 함수
	private static boolean check2(int i, int j, int cnt) {
		for (int x = i; x < i + cnt; x++) {
			for (int y = j; y < j + cnt; y++) {
				if(visited[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
	
	// visited[][] 체크하고 다음으로 넘어가는 함수
	private static boolean setArray2(boolean[][] arr, int i, int j, int cnt) {
		if (i < arr.length && j < arr.length && arr[i][j] && papers[cnt - 1] < 5 && check2(i, j, cnt)) {
			checkVisited(i, j, cnt, true);
			papers[cnt - 1]++;
			BF(i);
			checkVisited(i, j, cnt, false);
			papers[cnt - 1]--;
			return true;
		}
		return false;
	}

	// 모두 다 탐색했는지 확인하는 함수
	private static boolean isFound() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (visited[i][j] != one[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// 완전탐색
	private static void BF(int x) {
//		for (int i : papers) {
//			//sum += i;
//			System.out.print(i+" ");
//		}
//		System.out.println();
		if (isFound()) {
			int sum = 0;
			for (int i : papers) {
				sum += i;
				// System.out.print(i+" ");
			}
			// System.out.println();
//			if (sum <= 6) {
//				for (int i : papers) {
//					System.out.print(i + " ");
//				}
//				System.out.println();
//				print(visited);
//			}
			if (sum < answer) {
				answer = sum;
			}
		}

		boolean flag = false;
		for (int i = x; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!visited[i][j] && one[i][j]) {
					flag = true; // 찾음
					setArray2(five, i, j, 5);
					setArray2(four, i, j, 4);
					setArray2(three, i, j, 3);
					setArray2(two, i, j, 2);
					setArray2(one, i, j, 1);
				}
				if (flag) {
					break;
				}
			}
			if (flag) {
				break;
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

		BF(0);

		if (answer == 26) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
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
