package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2110_공유기설치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] pos = new int[N];
		for (int i = 0; i < N; i++) {
			pos[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(pos);

		if (C == 2) {
			System.out.println(pos[N - 1] - pos[0]);
			return;
		} else {
			C--; // 공유기 사이의 거리를 미리 계산, 이를 C개의 집합으로 만든다고 생각
		}

		int start = 1;
		int end = (pos[N - 1] - pos[0]) / C;

		int[] sub = new int[N - 1];
		for (int i = 0; i < N - 1; i++) {
			sub[i] = pos[i + 1] - pos[i];
		}

		while (start <= end) {
			int mid = (start + end) >> 1;
			int union = 0;
			int tmp = 0;
			for (int i = 0; i < N - 1; i++) {
				tmp += sub[i];
				if (tmp >= mid) {
					tmp = 0;
					union++;
				}
				if (union >= C) {	//조건 만족
					break;
				}
			}
			if (union >= C) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}

		}

//		System.out.println();
//		for (int i : sub) {
//			System.out.println(i);
//		}
		System.out.println(end);
	}
}
