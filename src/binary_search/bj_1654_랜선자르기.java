package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1654_랜선자르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] lanLine = new long[K];
		long start = 1l;
		long end = 0l;
		for (int i = 0; i < K; i++) {
			lanLine[i] = Long.parseLong(br.readLine());
			if (end < lanLine[i]) {
				end = lanLine[i];
			}
		}

		int size = 0; // 필요한 랜선의 개수
		for (int i = 0; i < K; i++) {
			size += (int) (lanLine[i] / end);
		}
		long mid;
		while (end >= start) {
			mid = (start + end) >> 1;
			int tmp_size = 0;
			for (int i = 0; i < K; i++) {
				tmp_size += (int) (lanLine[i] / mid);
			}
			if (tmp_size >= N) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		System.out.println(end);
	}
}
