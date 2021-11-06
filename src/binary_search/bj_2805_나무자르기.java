package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		long start = 0l;
		long end = 0l;
		long[] trees = new long[N];
		for (int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
			if (end < trees[i]) {
				end = trees[i];
			}
		}

		long sum;
		while (start <= end) {
			long mid = (start + end) >> 1;
			sum = 0l;
			for(int i=0; i<N; i++) {
				if(trees[i] > mid) {
					sum += (trees[i]-mid);
				}
			}
			if(sum >= M) {
				start = mid+1;
			}else {
				end = mid-1;
			}			
		}
		System.out.println(end);

	}
}
