package Simulation;

import java.util.Random;

public class bj_23290_tc생성기 {

	public static void main(String[] args) {
		Random ran = new Random();

		StringBuffer sb = new StringBuffer();
		int M = getNum(10, ran);
		sb.append(M).append(" ").append(getNum(30, ran)).append("\n");

		for (int i = 0; i < M; i++) {
			sb.append(getNum(4, ran)).append(" ");
			sb.append(getNum(4, ran)).append(" ");
			sb.append(getNum(8, ran)).append("\n");
		}

		sb.append(getNum(4, ran)).append(" ").append(getNum(4, ran));
		System.out.println(sb.toString());
	}

	private static int getNum(int end, Random ran) {
		return ran.nextInt(end) + 1;
	}
}
