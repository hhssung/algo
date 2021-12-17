package com.algorithm.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// 1 4 9 16 25 36 49 64 81 100. 끝 수가 1, 4, 5, 6, 9, 0만 가능 

public class bj_1025_제곱수찾기 {
	private static int len = 1; // 정답의 길이
	private static int answer = -1; // 정답

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] nums = new char[N][M];
		HashSet<Integer> squareNumbers = getSquareNumbers(Math.max(N, M));

		for (int i = 0; i < N; i++) {
			nums[i] = br.readLine().toCharArray();
		}

//		if (N == 1 && M == 1) {
//			int number = nums[0][0] - '0';
//			if (squareNumbers.contains(number)) {
//				System.out.println(number);
//				return;
//			}
//		}

		int tmpY, tmpX;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int number = nums[i][j] - '0';
				if (number == 0 || number == 1 || number == 4 || number == 5 || number == 6 || number == 9) {
					// dy, dx 만들기
					for (int dy = -N; dy <= N; dy++) {
						for (int dx = -M; dx <= M; dx++) {
							if (dy == 0 && dx == 0) {
								continue;
							}
							tmpY = i;
							tmpX = j;
							ArrayList<Character> list = new ArrayList<>();
							while (tmpY >= 0 && tmpX >= 0 && tmpY < N && tmpX < M) {
								list.add(nums[tmpY][tmpX]);
								tmpY += dy;
								tmpX += dx;
							}
							updateAnswer(list, squareNumbers);
						}
					}
				}
			}
		}

		System.out.println(answer);
	}

	private static void updateAnswer(ArrayList<Character> list, HashSet<Integer> squareNumbers) {
//		for (char c : list) {
//			System.out.print(c);
//		}
//		System.out.println();

		if (list.size() < len) {
			return;
		}
		StringBuffer result;
		int idx;
		int tmp_answer;
		// 등차수열
		for (int i = 1; i <= list.size(); i++) {
			result = new StringBuffer();
			idx = 0;
			// 틀린이유 -> 987654321 식으로 list가 주어지면 "98765"처럼 자를 생각을 안함
			while (idx < list.size()) {
				result.append(list.get(idx));
				if (result.length() < len) {
					idx += i;
					continue;
				}
				result.reverse();
				tmp_answer = Integer.parseInt(result.toString());
				if (squareNumbers.contains(tmp_answer)) {
					if (tmp_answer > answer) {
						answer = tmp_answer;
						// System.out.println(answer);
						len = result.length();
					}
				}
				result.reverse();
				idx += i;
			}
		}
	}

	private static HashSet<Integer> getSquareNumbers(int n) {
		HashSet<Integer> set = new HashSet<>();
		int max = 1;
		for (int i = 0; i < n; i++) {
			max *= 10;
		}

		int tmp = 0;
		int square = 0;
		while (true) {
			square = tmp * tmp;
			if (square >= max) {
				break;
			}
			set.add(square);
			tmp++;
		}
		return set;
	}
}
