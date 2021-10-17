package backtracking;

import java.util.Scanner;

public class 하노이탑_1914 {
	static StringBuilder sb = new StringBuilder();
	
	// n: 몇 개 원판 옮길지
	// src : 시작, temp : 임시기둥, dest : 도착
	private static void backtracking(int n, int src, int temp, int dest) {
		if (n == 1) {
			sb.append(src).append(" ").append(dest).append("\n"); // 원판 옮기기
		}else {
			backtracking(n - 1, src, dest, temp); // 자신의 위쪽 n-1 원판 임시기둥으로 옮기기
			sb.append(src).append(" ").append(dest).append("\n"); // 원판 옮기기
			backtracking(n - 1, temp, src, dest); // 임시기둥 n-1 원판 dest로 옮기기
		}
	}

	// 2^64가 넘는 큰 수 출력
	private static void printBigNumber(int N) {
		
//		long a = 0, b = 0;
//		for (int i = 0; i < N; i++) {
//			b *= 2;
//			a = a * 2 + 1;
//			b += a / 1000000000000000000l;
//			a %= 1000000000000000000l;
//		}
//		if(b==0) {
//			System.out.print(a);
//			return;
//		}
//		System.out.printf("%d%d",b,a);
		
		Long a = (long) 1;
		for (int i = 0; i < N / 2; i++) {
			a *= 2;
		}
		Long b = a;
		String b_str = b.toString();
		if (N % 2 == 1) {
			a *= 2;
		}
		
		// a * ( 1 ~ 9 ) 모아놓은 스트링
		String[] nums = new String[10];
		for (int i = 1; i < 10; i++) {
			Long tmp = a * i;
			nums[i] = tmp.toString();
		}

		int[] result = new int[41];
		int idx = 40; // 시작
		for (int i = b_str.length() - 1; i >= 0; i--) {
			if (b_str.charAt(i) == '0') {
				idx--;
				continue;
			}
			int tmp = b_str.charAt(i) - '0';
			for (int j = idx; j > idx - nums[tmp].length(); j--) {
				result[j] += (nums[tmp].charAt(j-idx+nums[tmp].length()-1)-'0');
				while(result[j]>=10) {
					result[j] -= 10;
					result[j-1]++;
				}
			}
			idx--;
		}
		result[40]--;
		boolean flag = false;
		for(int i : result) {
			if(!flag && i==0) {
				continue;
			}
			flag= true;
			System.out.print(i);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long cnt = 1;
		if (N < 64) {
			for (int i = 0; i < N; i++) {
				cnt *= 2;
			}
			System.out.println(cnt - 1);
		} else {
			printBigNumber(N);
		}

		if (N <= 20) {
			// 과정 출력
			backtracking(N, 1, 2, 3);
			System.out.println(sb.toString());
		}
		
	}
}
