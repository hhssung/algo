package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백조의호수_3197 {
	static int R;
	static int C;
	static char[][] lake;
	static boolean[][] visited;
	static int day = 0;
	static boolean isMet = false;
	static myPair bird; // 백조 위치

	static boolean[][] visited2;
	
	private final static char BIRD = 'L';
	private final static char WATER = '.';
	private final static char ICE = 'X';
	private final static char WILL_MELT = 'O'; // 다음번에 녹을 빙하

	static Queue<myPair> q = new LinkedList<>(); // 녹을 빙하 좌표 저장하고 있는 queue

	static class myPair {
		int y;
		int x;
		int day; //

		public myPair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
			this.day = -1;
		}

		// 날짜 포함된 페어로 사용할때
		public myPair(int y, int x, int day) {
			this(y, x);
			this.day = day;
		}
	}

	// 범위 유효한지 확인
	private static boolean isInRange(int y, int x) {
		if (x < 0 || y < 0 || x >= C || y >= R) {
			return false;
		}
		return true;
	}

	// 백조 만날 수 있는지 확인
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int tmp_x;
	static int tmp_y;
	static Queue<myPair> nextBirdQ = new LinkedList<>();	//다음에 탐색할 곳 queue에 넣기 -> 가지치기 가능
	
	private static boolean isBirdMet() {
		// init. 한 번만 실행되는 코드
		if(nextBirdQ.isEmpty()) {
			Queue<myPair> birdQ = new LinkedList<>();
			visited = new boolean[R][C];
			visited[bird.y][bird.x] = true;
			birdQ.add(bird);
			BFS(birdQ);
			return isMet;
		}
		
		//첫번째 탐색 이후
		Queue<myPair> birdQ = new LinkedList<>();
		while(nextBirdQ.peek().day == day) {
			birdQ.clear();
			birdQ.add(nextBirdQ.poll());
			BFS(birdQ);
		}
		return isMet;
	}
	
	// 너비우선탐색 - init
	private static void BFS(Queue<myPair> birdQ) {
		while (!birdQ.isEmpty()) {
			int q_x = birdQ.peek().x;
			int q_y = birdQ.peek().y;
			birdQ.poll();
			for (int i = 0; i < 4; i++) {
				tmp_x = q_x + dx[i];
				tmp_y = q_y + dy[i];
				// 범위 검사
				if (!isInRange(tmp_y, tmp_x)) {
					continue;
				}
				if (visited[tmp_y][tmp_x]) {
					continue;
				}
				if (lake[tmp_y][tmp_x] == BIRD) {
					isMet = true;
					break;
				}
				if (lake[tmp_y][tmp_x] == WATER) {
					visited[tmp_y][tmp_x] = true;
					nextBirdQ.add(new myPair(tmp_y, tmp_x, day+1));
					birdQ.add(new myPair(tmp_y, tmp_x));
				}
			}
		}
	}

	// 얼음 녹이기
	static boolean willMelt;

	private static void meltIce() {
		// 맨 처음 한번만 init. 빙하가 녹을 위치를 저장
		if (q.isEmpty()) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 얼음 발견, 상하좌우로 물 있는지 검사
					if (lake[i][j] == ICE) {
						willMelt = false;
						for (int k = 0; k < 4; k++) {
							tmp_y = i + dy[k];
							tmp_x = j + dx[k];
							// 범위 검사
							if (!isInRange(tmp_y, tmp_x)) {
								continue;
							}
							// 하나라도 얼음이 아닐 경우 다음에 녹을 대상
							if (lake[tmp_y][tmp_x] != ICE) {
								q.add(new myPair(i, j, day));
								break;
							}
						}
					}
				}
			}
		}

		// 빙하 녹이기
		for(int i=0; i<q.size(); i++) {
			lake[q.peek().y][q.peek().x] = WATER;
			//System.out.println(q.peek().y+" "+q.peek().x+" "+q.peek().day);
			q.add(q.poll());
		}
		//printarr(lake);
		//System.out.println(q.size());
		
//		System.out.println();
//		System.out.println(day);
//		printarr(lake);
		
		// 녹을 대상 전부 
		while (!q.isEmpty() && q.peek().day == day) {
			myPair tmp = q.poll();
			
			//다음에 녹을 대상 전부 넣기
			for (int k = 0; k < 4; k++) {
				tmp_y = tmp.y + dy[k];
				tmp_x = tmp.x + dx[k];
				// 범위 검사
				if (!isInRange(tmp_y, tmp_x)) {
					continue;
				}
				// 이미 방문했을 경우 skip
				if(visited2[tmp_y][tmp_x]) {
					continue;
				}
				// 얼음일 경우
				if (lake[tmp_y][tmp_x] == ICE) {
					//System.out.println(tmp_y + " "+ tmp_x);
					q.add(new myPair(tmp_y, tmp_x, day+1));
					visited2[tmp_y][tmp_x] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		lake = new char[R][C];
		visited2 = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			lake[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (lake[i][j] == BIRD) {
					bird = new myPair(i, j);
				}
			}
		}

		// 만나지 못할 경우 계속 빙하 녹이기
		// System.out.println(isBirdMet());
		while (!isBirdMet()) {
			meltIce();
			day++;
		}

		// printarr(lake);
		System.out.println(day);
	}

	private static void printarr(char[][] arr) {
		System.out.println();
		StringBuffer sb = new StringBuffer();
		for (char[] x : arr) {
			for (char y : x) {
				sb.append(y);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
