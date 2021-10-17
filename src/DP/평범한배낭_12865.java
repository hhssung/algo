package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, K;
		N = Integer.parseInt(st.nextToken()); // 물품수
		K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		
		int[] W = new int[N+1];	//각 물건의 무게
		int[] V = new int[N+1];	// 물건 가치
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] DP = new int[N+1][K+1];
		
		//init
		for(int j=1; j<=K; j++) {
			if(W[1] > j) {
				DP[1][j] = 0;
			}else{
				DP[1][j] = V[1];
			}
		}
		
		// x-1개의 물품에서의 가치 부분합 ,  x 물품의 무게를 제외한 x-1개의 물품에서의 가치 부분합 + x 물품의 가치  
		// 중 더 큰 것을 선택
		for(int i=2; i<=N; i++) {	// 물품 N개 차례대로 넣어보기
			for(int j=1; j<=K; j++) {	// 가방 무게 한도
				if(j>=W[i]) {
					DP[i][j] = Math.max(DP[i-1][j-W[i]]+V[i], DP[i-1][j]);
				}else {
					DP[i][j] = DP[i-1][j];
				}
			}
		}
		
		//print(DP);
		
		System.out.println(DP[N][K]);
	}
	
	private static void print(int arr[][]) {
		for(int[] a : arr) {
			for(int b : a) {
				System.out.print(b+" ");
			}
			System.out.println();
		}
	}
}
