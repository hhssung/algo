package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_2383_점심식사시간 {
	static int answer;

	static class pair {
		int y;
		int x;
		int[] dist = new int[2]; // 계단 1, 2까지의 거리

		public pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class pair_s {
		int y;
		int x;
		int depth;

		public pair_s(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}

	static class waiting_p {
		int idx;
		int time;

		public waiting_p(int idx, int time) {
			super();
			this.idx = idx;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N; // 한 변의 길이
		int tmp;

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			ArrayList<pair> persons = new ArrayList<>();
			ArrayList<pair_s> stairs = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					tmp = Integer.parseInt(st.nextToken());
					if (tmp == 1) {
						persons.add(new pair(i, j));
					} else if (tmp > 1) {
						stairs.add(new pair_s(i, j, tmp));
					}
				}
			}

			Solution(persons, stairs);

			System.out.printf("#%d %d\n", test_case, answer);
		}
	}

	private static void Solution(ArrayList<pair> persons, ArrayList<pair_s> stairs) {
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < persons.size(); i++) {
			for (int j = 0; j < stairs.size(); j++) {
				persons.get(i).dist[j] = Math.abs(persons.get(i).y - stairs.get(j).y)
						+ Math.abs(persons.get(i).x - stairs.get(j).x);
			}
		}

		// 1024가지 완탐
		dfs(persons, stairs, 0, new boolean[persons.size()]);

	}

	// false : 계단1, true : 계단2
	private static void dfs(ArrayList<pair> persons, ArrayList<pair_s> stairs, int idx, boolean[] state) {
		if (idx == persons.size()) {
			// simulation
			int[] time = new int[persons.size()];
			for (int i = 0; i < persons.size(); i++) {
				time[i] = persons.get(i).dist[state[i] ? 1 : 0];
			}
			int arrived = 0; // 도착한 사람들
			int tmp_time = 1;
			ArrayList<waiting_p> s1 = new ArrayList<>();
			ArrayList<waiting_p> s2 = new ArrayList<>();

			while (arrived < persons.size()) {
				tmp_time++;

				// 계단 내려가게 하기
				for (int i = 0; i < 3; i++) {
					if (i + 1 > s1.size()) {
						break;
					}
					s1.get(i).time--;
				}
				for (int i = 0; i < 3; i++) {
					if (i + 1 > s2.size()) {
						break;
					}
					s2.get(i).time--;
				}

				// 다 내려갔을 경우 제거하기
				while (!s1.isEmpty() && s1.get(0).time == 0) {
					s1.remove(0);
					arrived++;
				}
				while (!s2.isEmpty() && s2.get(0).time == 0) {
					s2.remove(0);
					arrived++;
				}

				// 사람 이동시키기
				for (int i = 0; i < persons.size(); i++) {
					time[i]--;
					if (time[i] == 0) {
						if (!state[i]) {
							s1.add(new waiting_p(i, stairs.get(0).depth));
						} else {
							s2.add(new waiting_p(i, stairs.get(1).depth));
						}
					}
				}
			}

			if (tmp_time < answer) {
				answer = tmp_time;
			}
			return;
		}

		state[idx] = true;
		dfs(persons, stairs, idx + 1, state);

		state[idx] = false;
		dfs(persons, stairs, idx + 1, state);
	}

}
