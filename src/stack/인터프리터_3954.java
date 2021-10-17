package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

/*
루프 x -> 5000만번 이내 연산 가능.
루프 o -> 루프 1개 5000만번 이내 연산. 즉, 5000만번 이후 연산 시 가장 오른쪽에 있는 괄호 '[' 를 찾으면 됨
*/

public class 인터프리터_3954 {
	static int s_m; // 배열 크기
	static int s_c; // 코드 크기
	static int s_i; // 프로그램 입력 크기
	static char[] arr = new char[100000]; // 데이터 배열. s_m 크기
	static int arr_idx;
	static String programCode; // 프로그램 코드. s_c 크기
	static int code_idx;
	static String charInput; // 프로그램 입력. s_i 크기
	static int char_idx; // 문자의 인덱스
	//static Map<Integer, Integer> map = new WeakHashMap<>(); // 괄호 인덱스를  양방향으로 저장하는 맵
	static int[] p_idx = new int[100000];	// 괄호 인덱스 저장
	static Stack<Integer> stack = new Stack<>(); // 왼쪽 괄호 인덱스를 저장하는 임시 스택
	static int left;
	static int right; // [ ] 루프 저장하는 곳
	static int cnt;
	
	private static void action(char action) {
		if (action == '+') {
			if ((int)arr[arr_idx]+1 > 255) {
				arr[arr_idx] = 0;
			}else {
				arr[arr_idx]++;
			}
			code_idx++;
			return;
		}
		if (action == '-') {
			if ((int)arr[arr_idx]-1 < 0) {
				arr[arr_idx] = 255;
			}else {
				arr[arr_idx]--;
			}
			code_idx++;
			return;
		}
		if (action == '<') {
			arr_idx--;
			if (arr_idx < 0) {
				arr_idx = s_m - 1;
			}
			code_idx++;
			return;
		}
		if (action == '>') {
			arr_idx++;
			if (arr_idx >= s_m) {
				arr_idx = 0;
			}
			code_idx++;
			return;
		}
		if (action == '[') {
			// 점프 x
			if (arr[arr_idx] != 0) {
				code_idx++;
				return;
			}
			// 점프 o. 짝 찾기
			//code_idx = map.get(code_idx) + 1;
			code_idx = p_idx[code_idx]+1;
			return;
		}
		if (action == ']') {
			// 루프 x
			if (arr[arr_idx] == 0) {
				//parenth.pop();
				code_idx++;
				return;
			}
			// 루프 o
			if(cnt > 50000000 && left>p_idx[code_idx]) {
				right = code_idx;
				left = p_idx[code_idx];
			}
			code_idx = p_idx[code_idx] + 1;
			return;
		}
		if (action == ',') {
			if (char_idx >= s_i) {
				arr[arr_idx] = 255;
			} else {
				arr[arr_idx] = charInput.charAt(char_idx);
				char_idx++;
			}
			code_idx++;
			return;
		}
		if (action == '.') {
			code_idx++;
			return;
		}
	}

	// 괄호 위치를 저장하고 있는 맵
	private static void makeMap() {
		stack.clear();
		//map.clear();
		
		for (int i = 0; i < s_c; i++) {
			if (programCode.charAt(i) == '[') {
				stack.push(i);
			} else if (programCode.charAt(i) == ']') {
				int tmp = stack.pop();
				p_idx[tmp] = i;
				p_idx[i] = tmp;
				//map.put(tmp, i);
				//map.put(i, tmp);
			}
		}
	}
	
	//배열 초기화
	private static void resetArr() {
		for(int i=0; i<s_m; i++) {
			arr[i] = 0;
			p_idx[i] = 0;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());

		for (int T = 1; T <= test_case; T++) {
			// 초기화
			st = new StringTokenizer(br.readLine());
			s_m = Integer.parseInt(st.nextToken());
			resetArr();
			arr_idx = 0; // 데이터 배열의 인덱스
			s_c = Integer.parseInt(st.nextToken());
			s_i = Integer.parseInt(st.nextToken());
			programCode = br.readLine();
			code_idx = 0;
			charInput = br.readLine();
			char_idx = 0;
			left = Integer.MAX_VALUE;
			right = 0;

			// solution
			makeMap();
			cnt = 0;
			while (true) {
				cnt++;
				action(programCode.charAt(code_idx));

				// 코드를 다 읽음
				if (code_idx >= s_c) {
					System.out.println("Terminates");
					break;
				}
				if (cnt >= 100000000) {
					System.out.printf("Loops %d %d\n", left, right);
					break;
				}
			}
		}
	}
}
