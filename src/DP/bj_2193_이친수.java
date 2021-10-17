package DP;

import java.util.Scanner;

public class bj_2193_이친수 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[] DP_one = new long[N+1];	//맨 뒷자리가 1인 수
		long[] DP_zero = new long[N+1];	//맨 뒷자리가 0인 수
		
		DP_one[1] = 1l;
		DP_zero[1] = 0l;
		
		for(int i=2; i<=N; i++) {
			DP_zero[i] = DP_one[i-1]+DP_zero[i-1];
			DP_one[i] = DP_zero[i-1];
		}
		
		System.out.println(DP_zero[N]+DP_one[N]);
	}
	
}
