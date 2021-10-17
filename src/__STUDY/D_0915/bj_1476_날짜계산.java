package __STUDY.D_0915;

import java.util.Scanner;

public class bj_1476_날짜계산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		int tmpE = 1;
		int tmpS = 1;
		int tmpM = 1;
		int answer = 1;
		while(true) {
			if(tmpE == E && tmpS == S && tmpM == M) {
				System.out.println(answer);
				return;
			}
			tmpE++;
			tmpS++;
			tmpM++;
			if(tmpE > 15) {
				tmpE = 1;
			}
			if(tmpS > 28) {
				tmpS = 1;
			}
			if(tmpM > 19) {
				tmpM = 1;
			}
			answer++;
		}
	}
}
