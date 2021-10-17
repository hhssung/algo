package com.algorithm.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_6549_가장큰직사각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n;
		long[] height = new long[100000];
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0) {
				return;
			}
			for(int i=0; i<n; i++) {
				height[i] = Long.parseLong(st.nextToken());
			}
			
		}

	}
}
