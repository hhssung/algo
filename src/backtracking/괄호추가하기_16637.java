package backtracking;

import java.util.Scanner;

public class 괄호추가하기_16637 {
	private static int[] numbers;
	private static boolean[] parenth;
	private static char[] op;
	private static long answer = Long.MIN_VALUE;

	private static long calc(long a, long b, char op) {
		if (op == '+') {
			return a + b;
		}
		if (op == '-') {
			return a - b;
		}
		return a * b;
	}

	private static void DFS(int x) {
		if (x >= parenth.length) {
			// 계산하기

			int idx = 1;
			long val = numbers[0];

			while (idx < numbers.length) {
				// 괄호 계산
				if (idx != numbers.length-1 && parenth[idx]) {
					long tmp = calc(numbers[idx], numbers[idx+1], op[idx]);
					val = calc(val, tmp, op[idx-1]);
					idx = idx+2;
					continue;
				}

				val = calc(val, numbers[idx], op[idx-1]);
				idx++;
			}

			//System.out.println(val);
			if (val > answer) {
				answer = val;
			}
			return;
		}

		// 괄호 추가
		for (int i = x; i < parenth.length; i++) {
			parenth[i] = true;
			DFS(i + 2);
			parenth[i] = false;
		}
	}

//	저장하는 방식
//	T F T F F-> T는 연달아서 놓을 수 없음, 맨 마지막 칸은 무시.
//	3 6 7 9 9 
//	+ * - *

	private static void solution(int N, String formula) {
		numbers = new int[N / 2 + 1];
		parenth = new boolean[N / 2 + 1];
		op = new char[N / 2];

		for (int i = 0; i < formula.length(); i++) {
			if (i % 2 == 0) {
				numbers[i / 2] = formula.charAt(i) - '0';
			} else {
				op[i / 2] = formula.charAt(i);
			}
		}

		// parenth 추가하기
		DFS(0);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String formula = sc.next();

		if (N != 1) {
			solution(N, formula);
			System.out.println(answer);
		} else {
			System.out.println(formula);
		}
	}
}
