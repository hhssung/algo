package WRONG;

import java.util.ArrayList;

//greedy
// 시작, 끝 지점을 잘못 생각함

public class programmers_lv3_추석트래픽 {

	static class Solution {
		class MyTime {
			short second; // 0~86400
			short mili; // 0~999
			short T; // 처리시간

			public MyTime(short second, short mili, short t) {
				super();
				this.second = second;
				this.mili = mili;
				T = t;
			}
		}

		ArrayList<MyTime> time_list = new ArrayList<>();

		public int solution(String[] lines) {
			int answer = 0;

			for (String time : lines) {
				parseString(time);
			}

			for (int i = 0; i < time_list.size(); i++) {
				int tmp_answer = getEndThroughPut(i);
				if (answer < tmp_answer) {
					answer = tmp_answer;
				}
				tmp_answer = getStartThroughPut(i);
				if (answer < tmp_answer) {
					answer = tmp_answer;
				}
			}

			return answer;
		}

		// 시작점 기준으로 탐색
		private int getStartThroughPut(int i) {
			int result = 1;
			
			
			
			return 0;
		}

		// 끝점 기준으로 탐색
		private int getEndThroughPut(int idx) {
			int result = 1;
			MyTime time = time_list.get(idx);
			int limit_time = (time.second + 1) * 1000 + time.mili - 1;
			int tmp_start_time;
			for (int i = idx + 1; i < time_list.size(); i++) {
				MyTime tmp_time = time_list.get(i);
				// 범위 완전 초과
				if (((tmp_time.second - 3) * 1000 + tmp_time.mili + 1) > limit_time) {
					break;
				}
				tmp_start_time = tmp_time.second * 1000 + tmp_time.mili - tmp_time.T + 1;
				if(tmp_start_time <= limit_time) {
					result++;
				}
			}

			return result;
		}

		private void parseString(String time) {
			String[] log = time.split(" ");
			String[] middle_log = log[1].split("\\.");
			short sec = hhmmssToSecond(middle_log[0]);
			short mili = Short.parseShort(middle_log[1]);
			short T = stringToMili(log[2]);
			time_list.add(new MyTime(sec, mili, T));
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
