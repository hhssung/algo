package com.algorithm.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1865_동철이의일분배 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N;
		double[][] arr = new double[17][17];

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Double.parseDouble(st.nextToken()) / 100f;
				}

			}

			
			
			System.out.printf("#%d %d\n", test_case, 23);
		}
	}
	
	private static void printall(double[][] arr) {
		for(double[] d : arr) {
			for(double x : d) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}
}
