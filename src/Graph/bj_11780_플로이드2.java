package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11780_플로이드2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 10000001;
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n+1][n+1];
		int[][] tracking = new int[n+1][n+1];
		//tracking[3][4] -> 도시 3에서 도시 4로 갈때 최소 거리가 되는 4의 이전 목적지
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				map[i][j] = INF;
				tracking[i][j] = INF;
			}
		}
		for(int i=1; i<=n; i++) {
			map[i][i] = 0;
		}
		
		int m = Integer.parseInt(br.readLine());
		
		int a, b, c;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(map[a][b] > c) {
				map[a][b] = c;
				tracking[a][b] = a;
			}
			
		}
		
		//printAll(map, n);
		
		//floyd 사용
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
						tracking[i][j] = tracking[k][j];
					}
				}
			}
		}
		
		printAll(map, n);
		
		//경로 출력
		StringBuffer answer = new StringBuffer();
		int[] list = new int[101];
		int size, tmpJ;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(tracking[i][j] == INF) {
					answer.append(0).append("\n");
					continue;
				}
				size = 1;
				list[size] = j;
				tmpJ = j;
				while(true) {
					size++;
					tmpJ = tracking[i][tmpJ];
					list[size] = tmpJ;
					if(tmpJ == i) {
						break;
					}
				}
				answer.append(size).append(" ");
				for(int k=size; k>=1; k--) {
					answer.append(list[k]).append(" ");
				}
				//sb.reverse(); // 오류부분.  2자리 수 이상 숫자일 경우 이상하게 바뀜  123 -> 321
				answer.append("\n");
			}
		}
		
		System.out.print(answer.toString());
	}
	
	private static void printAll(int[][] map, int n) {
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map[i][j] >= 10000001) {
					sb.append(0);
				}else {
					sb.append(map[i][j]);
				}
				if(j != n) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
