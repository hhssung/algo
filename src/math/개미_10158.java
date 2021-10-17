package math;

import java.util.Scanner;

public class 개미_10158 {
	static int w;
	static int h;

	static int p;
	static int q;

	static int first_p;
	static int first_q;	//맨 처음 좌표
	
	static int t;

	static int[] dy = { 1, 1, -1, -1 }; // 오른쪽 위 0, 왼쪽 위 1, 오른쪽 아래 2, 왼쪽 아래 3
	static int[] dx = { 1, -1, 1, -1 };
	static int direction = 0; // 맨 처음

	private static void changeDirection() {
		if (p == 0) { // 왼쪽 벽과 부딪힘
			if (q == 0) { // 왼쪽 아래 꼭지점
				direction = 0; // 오른쪽 위로
				return;
			}
			if (q == h) { // 왼쪽 위 꼭지점
				direction = 2; // 오른족 아래로
				return;
			}
			if (direction == 1) {
				direction = 0;
				return;
			}
			if (direction == 3) {
				direction = 2;
				return;
			}
		}
		if (p == w) { // 오른쪽 벽과 부딪힘
			if (q == 0) { // 오른쪽 아래 꼭지점
				direction = 1; // 왼쪽 위로
				return;
			}
			if (q == h) { // 오른쪽 위 꼭지점
				direction = 3; // 왼쪽 아래로
				return;
			}
			if (direction == 0) {
				direction = 1;
				return;
			}
			if (direction == 2) {
				direction = 3;
				return;
			}
		}
		if (q == 0) { // 아래쪽 벽과 부딪힘
			if (direction == 2) {
				direction = 0;
				return;
			}
			if (direction == 3) {
				direction = 1;
				return;
			}
		}
		if (q == h) { // 위쪽 벽과 부딪힘
			if (direction == 1) {
				direction = 3;
				return;
			}
			if (direction == 0) {
				direction = 2;
				return;
			}
		}
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		w = sc.nextInt();
		h = sc.nextInt();
		p = sc.nextInt();
		q = sc.nextInt();
		t = sc.nextInt();

		first_p = p;
		first_q = q;
		
		long beforeTime = System.currentTimeMillis();
		
		for (int time = 1; time <= t; time++) {
			//System.out.println(p+" "+q);
			p += dx[direction];
			q += dy[direction];
			
			
			
//			if(p==first_p && q == first_q && direction == 0) {	// 맨 처음 지점에 같은 방향으로 들어왔을 경우. 작은 격자에서 효과적
//				time = (t/time)*time;
//				System.out.println("find");
//			}
			
			if(p==0 || q==0 || p==w || q==h) {
				changeDirection();
			}
		}
		System.out.println(p+" "+q);
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = afterTime - beforeTime; //두 시간에 차 계산
		System.out.println("시간차이(m) : "+secDiffTime);
	}
}
