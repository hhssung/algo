package backtracking;

import java.util.Scanner;
/*
x = 0 ->  x
x = 1 -> 1,0
x = 2 ->  2, 1
x = 3 ->  3,2  /  3 2  ,  1,0
*/

public class 좋은수열_2661 {
	private static int[] arr;
	static boolean isFind = false;
	
	private static boolean isGoodSeq(int x) {
		//System.out.println("x: " +x);
		for (int i = 1; i < (x+1) / 2 + 1; i++) {
			boolean isSame = true;
			//System.out.println("xd: " +x);
			for (int j = x; j > x - i; j--) {
				//System.out.println(arr[j]+" "+arr[j-i]);
				if (arr[j] != arr[j - i]) {
					isSame = false;
				}
			}
			if (isSame) {
				return false;
			}
		}

		// x-5 x-4 x-3 x-2 x-1 x
		return true;
	}

	private static void DFS(int x, int N) {
		// 좋은수열 맞는지 체크
		if (!isGoodSeq(x - 1)) {
			return;
		}
		// DFS
		if (x == N) {
			//System.out.print("          ");
			if(!isFind)
			{
				for (int i : arr) {
					System.out.print(i);
				}
				isFind = true;
			}
		} else {
			if(isFind)
			{
				return;
			}
			for (int i = 1; i <= 3; i++) {
				arr[x] = i;
				DFS(x + 1, N);
				arr[x] = i;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		arr = new int[N];
		arr[0] = 1;

		DFS(1, N);
	}
}
