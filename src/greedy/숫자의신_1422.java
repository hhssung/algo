package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 모든 수는 적어도 한번씩 사용 -> 앞에 9 많은 순서로 정렬.
// 이후 맨 앞에

public class 숫자의신_1422 {

	// 9998 999 98 -> 999, 9998, 98 순
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		Integer[] arr = new Integer[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		//바보짓...
		/*Arrays.sort(arr, new Comparator<Integer>() {
			//같은 길이 비교
			private int compStr(String x, String y) {
				if(!x.equals(y)) {
					return y.compareTo(x);
				}
				return 0;
			}
			
			//짧은 String 길게 만들기
			private String makeStr(int len, String short_s) {
				StringBuffer sb = new StringBuffer();
				sb.append(short_s);
				char c = short_s.charAt(short_s.length()-1);
				while(len != sb.length()) {
					sb.append(c);
				}
				return sb.toString();
			}
			
			// a1 : long, a2 : short
			// ex)  a1 : 3782992,  a2 : 3782
			private int comp(String a1, String a2) {
				int returnVal = 0;
				String a_first = a1.substring(0, a2.length());	// 3782
				String a_last = a1.substring(a2.length(), a1.length());	// 992
				
				// 앞 부분 길이 비교. 3782
				returnVal = compStr(a_first, a2);
				if(returnVal != 0) {
					return returnVal;
				}
				
				//뒷 부분 길이 비교. 992, 3782
				if(a_last.length() > a2.length()) {
					a2 = makeStr(a_last.length(), a2);
				}
				if(a_last.length() < a2.length()) {
					a_last = makeStr(a2.length(), a_last);
				}
				
				//System.out.println("만든string: "+a_last + " "+ a2);
				returnVal = compStr(a_last, a2);
				return returnVal;
			}
			
			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				
				if(s1.length() > s2.length()) {
					return comp(s1, s2);
				}
				if(s1.length() < s2.length()) {
					return -comp(s2, s1);
				}
				return o2 - o1;
			}
		});*/
		
		Arrays.sort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String a = o1.toString() + o2.toString();
				String b = o2.toString() + o1.toString();
				return b.compareTo(a);
			} 
		});

		int max_val = -1;
		for (int i = 0; i < K; i++) {
			if (max_val < arr[i]) {
				max_val = arr[i];
			}
		}

		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for (int i = 0; i < K; i++) {
			if (!flag || max_val != arr[i]) {
				sb.append(arr[i]);
				continue;
			}
			//중복 안되게 한 번만
			if(flag) {
				for (int j = 0; j < N - K + 1; j++) {
					sb.append(arr[i]);
				}
				flag = false;
			}
		}

		System.out.println(sb.toString());
	}
}
