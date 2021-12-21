package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_16563_어려운소인수분해 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> primes = getPrime(5000000);

		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		StringBuffer answer = new StringBuffer();
		int idx;
		for (int i = 0; i < N; i++) {
			idx = 0;
			while (nums[i] >= primes.get(idx)) {
				// 그 소수로 나눠질 경우
				if (nums[i] % primes.get(idx) == 0) {
					answer.append(primes.get(idx)).append(" ");
					nums[i] /= primes.get(idx);
					continue;
				}
				// 안나눠질 경우
				idx++;
				if (idx >= primes.size()) {
					break;
				}
			}
			if (nums[i] > 1) { // 남은 값이 소수
				answer.append(nums[i]).append(" ");
			}
			answer.append("\n");
		}
		System.out.println(answer);
	}

	private static ArrayList<Integer> getPrime(final int MAX) {
		ArrayList<Integer> result = new ArrayList<>();

		boolean isPrime;
		for (int i = 2; i * i <= MAX; i++) {
			isPrime = true;
			for (int j : result) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				result.add(i);
			}
		}

		return result;
	}
}
