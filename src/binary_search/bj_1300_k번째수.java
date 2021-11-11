package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1300_k번째수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final long N = Long.parseLong(br.readLine());
		final long K = Long.parseLong(br.readLine());

		long start = 1l;
		long end = (long) N * (long) N;
		long mid;
		long cnt;

		while (start <= end) {
			mid = (start + end) >> 1;

			cnt = getMidCount(mid, N);

			if (cnt >= K) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(start);
	}

	private static long getMidCount(final long mid, final long N) {
		long cnt = 0l;

		for (long i = 1; i <= N; i++) {
			cnt += Math.min(N, mid / i);
		}

		return cnt;
	}

}
