package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개미_10158_new {
	static int w;
	static int h;
	static int p;
	static int q;
	static int t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(br.readLine());

		w *= 2;
		h *= 2;

		int p_t = t - (t / w) * w;
		int q_t = t - (t / h) * h;

		p += p_t;
		q += q_t;

		if (p >= w) {
			p -= w;
		}
		if (q >= h) {
			q -= h;
		}
		if (p >= w / 2) {
			p = w - p;
		}
		if (q >= h / 2) {
			q = h - q;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(p).append(" ").append(q);
		System.out.println(sb.toString());
	}
}
