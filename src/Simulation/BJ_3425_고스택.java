package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_3425_고스택 {

	private static class GoStack {
		/*
		 * NUM = 0 POP = 1 INV = 2 DUP = 3 SWP = 4 ADD = 5 SUB = 6 MUL = 7 DIV = 8 MOD =
		 * 9
		 */
		final int INF = 1000000000;
		int[] order = new int[100000]; // 스택연산 저장하는 배열
		int order_cnt; // 연산의 개수
		int order_idx = -1;

		ArrayList<Integer> num = new ArrayList<>();
		int num_idx = 0; //

		int[] stack = new int[1001];
		final int STACK_MAX = 1000;
		int top_idx = 0; // TOP 위치

		public GoStack() {

		}

		public void reset() {
			num.clear();
			num_idx = 0;
			order_idx = -1;
		}

		// NUM을 제외한 모든 order
		public void addOrder(String str) {
			order_idx++;
			switch (str) {
			case "POP":
				order[order_idx] = 1;
				break;
			case "INV":
				order[order_idx] = 2;
				break;
			case "DUP":
				order[order_idx] = 3;
				break;
			case "SWP":
				order[order_idx] = 4;
				break;
			case "ADD":
				order[order_idx] = 5;
				break;
			case "SUB":
				order[order_idx] = 6;
				break;
			case "MUL":
				order[order_idx] = 7;
				break;
			case "DIV":
				order[order_idx] = 8;
				break;
			case "MOD":
				order[order_idx] = 9;
				break;
			}
		}

		// NUM
		public void addOrder(int x) {
			order_idx++;
			order[order_idx] = 0;
			num.add(x);
		}

		public void setOrderCnt() {
			order_cnt = order_idx + 1;
		}

		// 계산값 string 반환
		public String calc(int x) {
			String result = null;

			// init
			stack[0] = x;
			top_idx = 0;
			num_idx = 0;

			for (int i = 0; i < order_cnt; i++) {
				switch (order[i]) {
				case 0:
					if (!NUM(num.get(num_idx))) {
						return "ERROR";
					}
					num_idx++;
					break;
				case 1:
					if (!POP()) {
						return "ERROR";
					}
					break;
				case 2:
					if (!INV()) {
						return "ERROR";
					}
					break;
				case 3:
					if (!DUP()) {
						return "ERROR";
					}
					break;
				case 4:
					if (!SWP()) {
						return "ERROR";
					}
					break;
				case 5:
					if (!ADD()) {
						return "ERROR";
					}
					break;
				case 6:
					if (!SUB()) {
						return "ERROR";
					}
					break;
				case 7:
					if (!MUL()) {
						return "ERROR";
					}
					break;
				case 8:
					if (!DIV()) {
						return "ERROR";
					}
					break;
				case 9:
					if (!MOD()) {
						return "ERROR";
					}
					break;
				}
			}
			// 유효한지 확인
			if (top_idx != 0) {
				return "ERROR";
			}

			return Integer.toString(stack[0]);
		}

		private boolean NUM(int X) {
			stack[top_idx + 1] = X;
			top_idx++;
			return true;
		}

		private boolean POP() {
			if (top_idx == -1) { // 비어 있음
				return false;
			}
			top_idx--;
			return true;
		}

		private boolean INV() {
			if (top_idx == -1) { // 비어 있음
				return false;
			}
			stack[top_idx] = -1 * stack[top_idx];
			return true;
		}

		private boolean DUP() {
			if (top_idx == -1) { // 비어있음
				return false;
			}
			stack[top_idx + 1] = stack[top_idx];
			top_idx++;
			return true;
		}

		private boolean SWP() {
			if (top_idx <= 0) { // 한개 이하일 때
				return false;
			}
			int tmp = stack[top_idx];
			stack[top_idx] = stack[top_idx - 1];
			stack[top_idx - 1] = tmp;
			return true;
		}

		private boolean ADD() {
			if (top_idx <= 0) { // 한개 이하일 때
				return false;
			}
			stack[top_idx - 1] = stack[top_idx - 1] + stack[top_idx];
			top_idx--;
			if (Math.abs(stack[top_idx]) > INF) { // 10^9 초과
				return false;
			}
			return true;
		}

		private boolean SUB() {
			if (top_idx <= 0) { // 한개 이하일 때
				return false;
			}
			stack[top_idx - 1] = stack[top_idx - 1] - stack[top_idx];
			top_idx--;
			if (Math.abs(stack[top_idx]) > INF) { // 10^9 초과
				return false;
			}
			return true;
		}

		private boolean MUL() {
			if (top_idx <= 0) { // 한개 이하일 때
				return false;
			}
			long tmp = (long) stack[top_idx - 1] * (long) stack[top_idx];
			if (Math.abs(tmp) > INF) {  // 10^9 초과
				return false;
			}
			stack[top_idx - 1] = (int) tmp;
			top_idx--;
			return true;
		}

		private boolean DIV() {
			if (top_idx <= 0 || stack[top_idx] == 0) { // 한개 이하일 때, 0으로 나눌 때
				return false;
			}
			stack[top_idx - 1] = stack[top_idx - 1] / stack[top_idx];
			top_idx--;
			return true;
		}

		private boolean MOD() {
			if (top_idx <= 0 || stack[top_idx] == 0) { // 한개 이하일 때, 0으로 나눌 때
				return false;
			}
			stack[top_idx - 1] = stack[top_idx - 1] % stack[top_idx];
			top_idx--;
			return true;
		}

		// debug
		public void printAll() {
			for (int i = 0; i < order_cnt; i++) {
				System.out.println(order[i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str;
		int N;
		GoStack gs = new GoStack();
		StringBuffer answer = new StringBuffer();
		while (true) {
			str = br.readLine();
			if (str.equals("QUIT")) {
				System.out.println(answer.toString());
				return;
			}

			while (true) {
				if (str.length() > 3) { // NUM
					st = new StringTokenizer(str);
					st.nextToken();
					gs.addOrder(Integer.parseInt(st.nextToken()));
				} else if (str.equals("END")) {
					gs.setOrderCnt();
					break;
				} else {
					gs.addOrder(str);
				}

				str = br.readLine();
			}

			N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				// TODO: 실행하고 출력
				answer.append(gs.calc(Integer.parseInt(br.readLine()))).append("\n");
			}

			answer.append("\n");
			// gs.printAll();
			gs.reset();
			br.readLine(); // 공백 읽기
		}

	}
}
