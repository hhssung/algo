package com.algorithm.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1020_디지털카운터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int x = 3;
		final int LEN = 7 * x + 1;
		final int MIN_SIZE = 2;
		final int MAX_SIZE = 7;
		int[] cal = new int[LEN];

		// init
		cal[2] = 1;
		cal[3] = 1;
		cal[4] = 1;
		cal[5] = 4;
		cal[6] = 2;
		cal[7] = 1;

		for (int i = 1; i < x; i++) {
			int[] temp = new int[LEN];
			for (int j = 0; j < cal.length; j++) {
				if (cal[j] == 0) {
					continue;
				}
				temp[j + 2] += cal[j];
				temp[j + 3] += cal[j];
				temp[j + 4] += cal[j];
				temp[j + 5] += cal[j] * 4;
				temp[j + 6] += cal[j] * 2;
				temp[j + 7] += cal[j];
			}
			cal = temp;
		}

		for (int i : cal) {
			System.out.print(i + " ");
		}
		System.out.println();

		// 구현
		String number = br.readLine();
		int N = number.length();
		int[] preSum = new int[N]; // 구간합
		int n_size = 0;

		for (int i = 0; i < number.length(); i++) {
			n_size += getSegmentSize(number.charAt(i));
		}
		preSum[0] = getSegmentSize(number.charAt(0));
		for (int i = 1; i < number.length(); i++) {
			preSum[i] = preSum[i - 1] + getSegmentSize(number.charAt(i));
		}
		
		for(int i : preSum) {
			System.out.println(i);
		}

		
		
	}

	private static int getSegmentSize(char c) {
		if (c == '2' || c == '3' || c == '5' || c == '9') {
			return 5;
		}
		if (c == '6' || c == '0') {
			return 6;
		}
		if (c == '1') {
			return 2;
		}
		if (c == '4') {
			return 4;
		}
		if (c == '7') {
			return 3;
		}
		return 8;
	}
}
