package com.algorithm.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_18443_우체국2_DP {
	static long answer = Long.MAX_VALUE;	//합
	static long[] loc;	// 정답인 마을 저장 배열

	private static void Solution(ArrayList<Long> pos, int v, int p, long l) {
		long[] distance = new long[v];	// 맨 처음 마을 ~ 각 마을까지의 거리
		long[] psum = new long[v]; // 구간 합 만들기
		long[][] DP = new long[v][v + 1]; // DP[i][j] = i에서 시작, 앞으로 j개의 구간을 더 만들어야 될 때 비용
		int[][] post_loc = new int[v][v + 1]; // 추적용 배열
		for (int i = 0; i < v; i++) {
			for (int j = 0; j <= v; j++) {
				DP[i][j] = -1l;
			}
		}
		long tmp_sum = 0;
		
		// 원형이 아닌 직선으로 마을 있다 가정 -> distance는 무조건 증가해야 됨
		// 구간 합 구하기
		for (int i = 1; i < v; i++) {
			if (pos.get(i) < pos.get(i - 1)) {
				tmp_sum += (l - (pos.get(i - 1) - pos.get(i)));
			} else {
				tmp_sum += (pos.get(i) - pos.get(i - 1));
			}
			distance[i] += tmp_sum;
			psum[i] = psum[i - 1] + distance[i];
		}

		// DP
		long tmp_ans = DFS(p, 0, DP, post_loc, v, distance, psum);

		// 갱신시키기
		if (answer > tmp_ans) {
			answer = tmp_ans;
			int x = 0;
			for (int cnt = p; cnt > 0; cnt--) {
				// 중간값의 좌표 구해서 정답 배열에 넣기
				loc[cnt - 1] = pos.get((x + post_loc[x][cnt] - 1) / 2);
				x = post_loc[x][cnt];
			}
		}
	}

	private static long DFS(int cnt, int pos, long[][] DP, int[][] post_loc, final int v, long[] distance,
			long[] psum) {
		if (pos == v) { // 모든 마을 사용
			if (cnt == 0) {
				return 0;
			} else { // 집합 p개로 묶기 실패
				return Long.MAX_VALUE;
			}
		}
		if (cnt == 0) { // 집합 p개로 묶었는데 모든 마을 못 사용했음
			return Long.MAX_VALUE;
		}
		if (DP[pos][cnt] != -1) { // 이미 방문
			return DP[pos][cnt];
		}

		DP[pos][cnt] = Long.MAX_VALUE;
		for (int i = pos; i < v; i++) { // (pos ~ i) 집합으로 묶기
			long tmp = DFS(cnt - 1, i + 1, DP, post_loc, v, distance, psum);
			if (tmp == Long.MAX_VALUE) {
				continue;
			}
			long cost = getCost(pos, i, psum, distance);
			if (DP[pos][cnt] > tmp + cost) {
				DP[pos][cnt] = tmp + cost;
				post_loc[pos][cnt] = i + 1;
			}
		}
		return DP[pos][cnt];
	}

	// [start ~ end] 까지의 구간 합 구하기. 우체국 위치는 항상 (start+end)/2
	private static long getCost(int start, int end, long[] psum, long[] distance) {
		int middle = (start + end) / 2;

		long left = ((middle - start) * distance[middle]);
		long a = 0;
		long b = 0;
		if (middle > 0) {
			a = psum[middle - 1];
		}
		if (start > 0) {
			b = psum[start - 1];
		}
		left -= (a - b);

		long right = (psum[end] - psum[middle]);
		right -= ((end - middle) * distance[middle]);
		return Math.abs(left) + Math.abs(right);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		long L = Long.parseLong(st.nextToken());
		ArrayList<Long> pos = new ArrayList<>(); // 초기 마을 위치 저장
		loc = new long[P];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < V; i++) {
			pos.add(Long.parseLong(st.nextToken()));
		}

		long tmp;
		for (int i = 0; i < V; i++) {
			Solution(pos, V, P, L);
			// 원형 도시 -> 한 칸씩 밀기
			tmp = pos.get(0);
			pos.remove(0);
			pos.add(tmp);
		}

		// 출력
		System.out.println(answer);
		Arrays.sort(loc);
		StringBuffer sb = new StringBuffer();
		for (long x : loc) {
			sb.append(x).append(" ");
		}
		System.out.println(sb.toString());
	}
}
