package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다각형을 이루는 순서대로 x,y좌표가 주어짐. 교차 안한다고 가정
public class bj_2166_다각형의면적 {
	static class Point {
		long x;
		long y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int x, y;
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}

		long answer = 0l;
		for (int i = 0; i < N - 1; i++) {
			answer += (points[i + 1].x * points[i].y - points[i].x * points[i + 1].y);
		}
		answer += (points[0].x * points[N - 1].y - points[N - 1].x * points[0].y);

		System.out.printf("%.1f", Math.abs((double) answer / 2.0));
	}
}
