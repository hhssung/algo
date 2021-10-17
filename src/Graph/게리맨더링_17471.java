package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {
	private static int N;
	private static int[] population;	// 1 ~ N 구역까지의 인구 수
	private static boolean[][] concat;	//인접한 도시
	private static int answer = Integer.MAX_VALUE;
	private static boolean[] visited;
	
	// 붙어있는 도시 탐색
	private static void search(boolean[] check, boolean b, int idx) {
		for(int i=1; i<=N; i++) {
			// 도시 인접, set 확인, 아직 방문 안한 노드
			if(concat[idx][i] && visited[i] == b && check[i] == !b) {
				check[i] = b;
				search(check, b, i);
			}
		}
	}
	
	// 나눈 구역 검증하는 함수
	private static boolean isEquity(boolean b) {
		int idx=0;
		for(int i=1; i<=N; i++) {
			if(visited[i] == b) {
				idx = i;
				break;
			}
		}
		
		boolean[] check = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			
			check[i] = !b;
		}
		
		Queue <Integer>q = new LinkedList<>();
		q.add(idx);
		while(!q.isEmpty()) {
			int tmp = q.poll();
			check[tmp] = b;
			for(int i=1; i<=N; i++) {
				if(i == tmp) {
					continue;
				}
				// 도시 인접, set 확인, 아직 방문 안한 노드
				if(check[i] == !b && visited[i] == b && concat[tmp][i]) {
					q.add(i);
				}
			}
		}
		
		//search(check, b, idx);
		for(int i=1; i<=N; i++) {
			if(check[i] != visited[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void DFS(int x, int limit) {
		if(x == limit) {
			if(!isEquity(true) || !isEquity(false))
			{
				return;
			}
			int sumA = 0;
			int sumB = 0;
//			
//			for(int i=1; i<=N; i++) {
//				System.out.print(visited[i] ? 1 : 0);
//			}
//			System.out.println();
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) {
					sumA += population[i];
				}else {
					sumB += population[i];
				}
			}
			int diff = Math.abs(sumA-sumB);
			if(diff<answer) {
				answer = diff;
			}
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(x+1, limit);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		population = new int[N+1];
		concat = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for(int j=0; j<tmp; j++) {
				int k = Integer.parseInt(st.nextToken());
				//양방향그래프
				concat[i][k] = true;
				concat[k][i] = true;
			}
		}
		
		//두 구역으로 나누기
		for(int i=1; i<=N/2; i++) {
			DFS(0, i);
		}
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
		
		//print(concat);
	}
	
	//검증
	private static void print(boolean[][] arr) {
		for(int i=1; i<arr.length; i++) {
			for(int j=1; j<arr.length; j++) {
				System.out.print(arr[i][j] ? 1 : 0);
			}
			System.out.println();
		}
	}
}
