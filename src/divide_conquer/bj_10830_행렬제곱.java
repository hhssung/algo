package divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_10830_행렬제곱 {

	private static int[][] Solution(int[][] matrix, int n, long b) {
		int[][] answer = new int[n][n];
		// 단위 행렬로 만들기
		for (int i = 0; i < n; i++) {
			answer[i][i] = 1;
		}

		while (b != 0) {
			if ((b & 1l) == 1) {
				answer = mul(matrix, answer, n);
			}
			// matrix 제곱연산
			matrix = mul(matrix, matrix, n);
			b = b >> 1;
		}

		return answer;
	}

	private static int[][] mul(int[][] m1, int[][] m2, int n) {
		int[][] result = new int[n][n];

		int tmp;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				tmp = 0;		
				for(int k=0; k<n; k++) {
					tmp += m1[y][k] * m2[k][x];
				}	
				tmp = tmp%1000;
				result[y][x] = tmp;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		int[][] matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] answer = Solution(matrix, N, B);

		printAll(answer);
	}

	private static void printAll(int[][] arr) {
		StringBuffer sb = new StringBuffer();

		for (int[] y : arr) {
			for (int x : y) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
