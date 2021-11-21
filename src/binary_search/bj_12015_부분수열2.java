package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_12015_부분수열2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// init
		int[] LIS = new int[N]; // 각 배열 위치 : LIS 길이, 그에 따른 가장 작은 값 저장
		int end = 0;
		LIS[0] = arr[0];

		for (int i = 1; i < N; i++) {
			if (LIS[end] < arr[i]) {
				end++;
				LIS[end] = arr[i];
			} else {
				binary_search(LIS, end, arr[i]);
			}
		}

		
//		for(int i : LIS) {
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		System.out.println(end+1);
	}

	// start ~ end 위치에 binary search로 num 넣기
	public static void binary_search(int[] LIS, int end, int num) {
		int start = 0;
		while (start <= end) {
			int mid = (start + end) >> 1;

			if (LIS[mid] < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		LIS[start] = num;
	}
}
