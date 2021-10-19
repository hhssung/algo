package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_5430_AC {
	// R : 배열 순서 뒤집기, D : 첫 번째 숫자 버림. 비어있을 경우 error
	static class AC {
		int[] array;
		int size;
		int first;
		int last;
		boolean isReverse;

		public AC(int n, String nums) {
			this.size = n;
			array = new int[n];
			first = 0;
			last = size - 1;
			isReverse = false;
			String[] tmp = nums.split(",");

			if (size == 0) {
				return;
			}

			if (size == 1) {
				array[0] = Integer.parseInt(tmp[0].substring(1, tmp[0].length() - 1));
				return;
			}

			array[0] = Integer.parseInt(tmp[0].substring(1, tmp[0].length()));
			array[size - 1] = Integer.parseInt(tmp[size - 1].substring(0, tmp[size - 1].length() - 1));
			for (int i = 1; i < size - 1; i++) {
				array[i] = Integer.parseInt(tmp[i]);
			}
		}

		public boolean executeQuery(char c) {
			if (c == 'D') {
				if (size == 0) {
					return false;
				}
				size--;
				if (!isReverse) {
					first++;
				} else {
					last--;
				}
			}

			if (c == 'R') {
				isReverse = isReverse ? false : true;
			}

			return true;
		}

		public String getArray() {
			if(size==0) {
				return "[]";
			}
			StringBuffer sb = new StringBuffer();
			
			sb.append("[");

			if (isReverse) {
				for (int i = last; i >= first; i--) {
					sb.append(array[i]).append(",");
				}
			}

			if (!isReverse) {
				for (int i = first; i <= last; i++) {
					sb.append(array[i]).append(",");
				}
			}

			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		String p;
		int n;
		AC ac;
		boolean isError;

		for (int test_case = 1; test_case <= T; test_case++) {
			p = br.readLine();
			n = Integer.parseInt(br.readLine());
			ac = new AC(n, br.readLine());
			isError = false;

			for (int i = 0; i < p.length(); i++) {
				if (!ac.executeQuery(p.charAt(i))) {
					System.out.println("error");
					isError = true;
					break;
				}
			}
			if (isError) {
				continue;
			}

			System.out.println(ac.getArray());
		}

	}
}
