package math;

import java.util.Scanner;

//돌탑 개수 홀수 - 선공, 짝수 - 후공
public class 결함게임_17080 {
	private static int N;
	private static int[] whoWin;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		if(N % 4 == 3) {
			System.out.println(2);
		}else {
			System.out.println(1);
		}
	
		sc.close();
	}
}
/*
2개 남았을때 

1 - 선공 승리
2 - (2,1) 선공 승리
3

3 - (2,1  - 2) -> 1 후공
2 - (1) -> 3  후공
1 - (3,2   - 3) -> 2 후공 승리

4
2 - 1 - 3 - 4 로 선공 승리 (홀)
5
2 - 1 - 4 - 3 - 5 로 선공 승리 (홀)
6
2 - 1 - 4 - 3 - 6 - 5로 선공 승리 (홀)
7
2-1 4-3 6-5 7 후공 승리
2-1 4-3 (5,6,7) -> 3개 남았을 때 돌탑 짝수개. 위에 3 case에서 보듯이 무조건 후공 승리 ??
7 - (6,5,4,3,2,1) -> 위 6 case처럼 할 경우 짝수개로 만들 수 있음
6 5 4 3 2 - (5,4,3,2,1) -> 1선택.  /  1 - 2선택.
후공 1 선택 -> (7 5 4 3 2). 새로운 바닥에서 시작. 5개.
i) 선공이 제일 작은 돌을 뽑을 경우 -> 후공은 제일 큰 돌을 놓으면 됨.
ii) 선공이 제일 작은 돌을 안 뽑을 경우 -> 후공은 제일 작은 돌을 놓으면 됨.  무한반복

4로나눴을때 나머지 3인 경우만 후공 이김???
*/



