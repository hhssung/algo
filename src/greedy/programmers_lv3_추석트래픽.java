package greedy;

import java.util.ArrayList;

//greedy

public class programmers_lv3_추석트래픽 {

	static class Solution {
		ArrayList<Integer> start_time = new ArrayList<>();
		ArrayList<Integer> end_time = new ArrayList<>();

		public int solution(String[] lines) {
			int answer = 0;

			for (String time : lines) {
				parseString(time);
			}

			for (int t : start_time) {
				int tmp = getThroughPut(t - 999, t);
				if (tmp > answer) {
					answer = tmp;
				}
			}
			for (int t : end_time) {
				int tmp = getThroughPut(t, t + 999);
				if (tmp > answer) {
					answer = tmp;
				}
			}

			return answer;
		}

		private int getThroughPut(int start, int end) {
			int result = 0;

			for (int idx = 0; idx < start_time.size(); idx++) {
				if (isValid(start, end, start_time.get(idx), end_time.get(idx))) {
					result++;
				}
			}

			return result;
		}

		private boolean isValid(int start, int end, int check_start, int check_end) {
			if (check_start >= start && check_start <= end) {
				return true;
			}
			if (check_end >= start && check_end <= end) {
				return true;
			}
			if (check_start <= start && check_end >= end) {
				return true;
			}
			return false;
		}

		private void parseString(String time) {
			String[] log = time.split(" ");
			String[] middle_log = log[1].split("\\.");
			short sec = hhmmssToSecond(middle_log[0]);
			short mili = Short.parseShort(middle_log[1]);
			short T = stringToMili(log[2]);
			int tmp_end_time = sec * 1000 + mili;
			int tmp_start_time = tmp_end_time - T + 1;
			start_time.add(tmp_start_time);
			end_time.add(tmp_end_time);
		}

		private short hhmmssToSecond(String time) {
			String[] hhmmss = time.split(":");
			short result = 0;
			result += Short.parseShort(hhmmss[0]) * 3600;
			result += Short.parseShort(hhmmss[1]) * 60;
			result += Short.parseShort(hhmmss[2]);
			return result;
		}

		private short stringToMili(String rare_time) {
			String time = rare_time.replaceAll("[.s]", "");
			StringBuffer sb = new StringBuffer();
			sb.append(time);
			while (sb.length() < 4) {
				sb.append(0);
			}
			// System.out.println(sb.toString());
			return Short.parseShort(sb.toString());
		}
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] lines = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" };
		int answer = sol.solution(lines);
		System.out.println(answer);
	}
}
