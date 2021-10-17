package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// greedy?  no. BF, DFS

public class 야구_17281 {
	private static int N;
	private static int[] inning = new int[10]; // 1~9번 타석 순서
	private static boolean[] visited = new boolean[10];
	private static int[][] info;
	private static int cnt; // 남은 이닝 선수 count
	private static int score = -1; // 점수

	// 타자 이동. 이닝 정보와 타 결과 정보를 가져옴
	private static int getScore(boolean[] baseball, int result) {
		int ans = 0;
		for (int i = 3; i >= 0; i--) {
			if (baseball[i]) {
				// 점수 획득
				if (i + result > 3) { 
					baseball[i] = false;
					ans++;
					continue;
				}
				//진루
				baseball[i + result] = true;
				baseball[i] = false;
			}
		}
		return ans;
	}
	
	//경기하기
	private static void simulation() {
		int tmp_score = 0;
		int prev_out = 1; // 시작 지점. 1~9까지 가능
		//N번 경기
		for(int i=0; i<N; i++) {
			int prev = prev_out;
			int out = 0;
			boolean[] baseball = new boolean[4]; // 1루, 2루, 3루
			while (out < 3) {
				baseball[0] = true;	//치는 사람은 항상 true
				if (info[i][inning[prev]] == 0) {
					out++;
				} else {
					tmp_score += getScore(baseball, info[i][inning[prev]]);
				}
				prev++;
				if (prev > 9) {
					prev = 1;
				}
			}
			prev_out = prev;
		}
		if(tmp_score > score) {
			score = tmp_score;
		}
	}
	
	private static void DFS(int x) {
		if(x==4) {
			DFS(x+1);
			return;
		}
		if(x==10) {
			//simulation
			simulation();
			return;
		}
		
		// 2번 ~ 9번 적절히 배치
		for(int i=2; i<10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				inning[x] = i;
				DFS(x+1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		info = new int[N][10];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			//이닝 정보 받기
			for(int j=1; j<=9; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		inning[4] = 1;	// 4번타자는 1번 선수
		visited[1] = true;	// 1번은 체크
		DFS(1);
		System.out.println(score);
	}
}

/*
 * 
 * 4 4 4 0 4 4 4 4 4 16 + 3 4 4 4 0 4 4 4 4 4 24
 * 
 * 알고리즘 : 시작 지점부터 오름차순으로 선수 배치. 4번 제외. 1 1 2 4 2 3 3 4 0 4 0 1 1 2 2 3 3 4
 * 
 */