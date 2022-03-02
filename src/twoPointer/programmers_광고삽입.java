package twoPointer;

class Solution {

	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";

		int arr_size = stringTimeToInt(play_time);
		int[] adv_arr = new int[arr_size + 1];

		for (String log : logs) {
			String[] times = log.split("-");
			int start = stringTimeToInt(times[0]);
			int end = stringTimeToInt(times[1]);

			for (int tmp = start; tmp < end; tmp++) {
				adv_arr[tmp]++;
			}
		}

		long max_sum = 0;
		long tmp_sum = 0;
		int start_idx = 0;
		int end_idx = stringTimeToInt(adv_time);
		int answer_int = 0;
		// init
		for (int i = start_idx; i < end_idx; i++) {
			tmp_sum += adv_arr[i];
		}
		max_sum = tmp_sum;

		while (end_idx < arr_size) {
			tmp_sum -= adv_arr[start_idx];
			tmp_sum += adv_arr[end_idx];
			start_idx++;
			end_idx++;

			if (tmp_sum > max_sum) {
				max_sum = tmp_sum;
				answer_int = start_idx;
			}
		}

		return intTimeToString(answer_int);
	}

	private int stringTimeToInt(String time) {
		String[] tmp = time.split(":");
		int hour, min, second;
		hour = Integer.parseInt(tmp[0]);
		min = Integer.parseInt(tmp[1]);
		second = Integer.parseInt(tmp[2]);
		return hour * 3600 + min * 60 + second;
	}

	private String intTimeToString(int time) {
		int hour, min, second;
		hour = time / 3600;
		time = time % 3600;
		min = time / 60;
		second = time % 60;

		StringBuffer sb = new StringBuffer();
		func(sb, hour);
		sb.append(":");
		func(sb, min);
		sb.append(":");
		func(sb, second);

		return sb.toString();
	}

	private void func(StringBuffer sb, int time) {
		if (time > 9) {
			sb.append(time);
		} else {
			sb.append(0).append(time);
		}
	}
}

public class programmers_광고삽입 {

	public static void main(String[] args) {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
				"01:37:44-02:02:30" };
		Solution sol = new Solution();

		String answer = sol.solution(play_time, adv_time, logs);
		System.out.println(answer);
	}
}